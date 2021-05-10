package com.demo.user;

import com.demo.user.dto.UserListDTO;
import com.demo.user.dto.UserMinimalDTO;
import com.demo.user.mapper.UserMapper;
import com.demo.user.model.ERole;
import com.demo.user.model.Role;
import com.demo.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final RoleRepository roleRepository;

    User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
    }


    public List<UserMinimalDTO> allUsersMinimal() {
        return userRepository.findAll()
                .stream().map(userMapper::userMinimalFromUser)
                .collect(toList());
    }

    public List<UserListDTO> allUsersForList() {
        return userRepository.findAll()
                .stream().map(userMapper::userListDtoFromUser)
                .collect(toList());
    }

    public UserListDTO create(UserListDTO user) {
        User userToSave = userMapper.userFromUserListDto(user);
        userToSave.setPassword(encoder.encode(user.getPassword()));

        Set<String> rolesStr = user.getRoles();
        Set<Role> roles = new HashSet<>();

        if(rolesStr == null){
            Role defaultRole = roleRepository.findByName(ERole.SECRETARY).orElseThrow(() -> new RuntimeException("Role not found!"));
            roles.add(defaultRole);
        }
        else {
            Set<String> r = user.getRoles();
            Role role;
            for(String s: r){
                role = roleRepository.findByName(ERole.valueOf(s)).orElseThrow(()->new RuntimeException("Role not found!"));
                roles.add(role);
            }
        }
        userToSave.setRoles(roles);
        userRepository.save(userToSave);

        return user;
    }

//    private Role rolesFromString(String role){
//        return roleRepository.findByName(ERole.valueOf(role))
//                .orElseThrow(()-> new EntityNotFoundException("Role not found!"));
//    }

    public UserListDTO edit(UserListDTO user){
        User actUser = findById(user.getId());
        actUser.setUsername(user.getUsername());
        actUser.setEmail(user.getEmail());

        Set<String> roleStr = user.getRoles();
        Set<Role> roles = new HashSet<>();
        for(String s: roleStr){
            roles.add(roleRepository.findByName(ERole.valueOf(s))
                    .orElseThrow(()-> new EntityNotFoundException("Role not found!")));
        }
        actUser.setRoles(roles);

        return userMapper.userListDtoFromUser(
                userRepository.save(actUser));
        }

    public void deleteAll (){
        userRepository.deleteAll();
    }

    public void deleteById (Long id) {
        userRepository.deleteById(id);
    }

}
