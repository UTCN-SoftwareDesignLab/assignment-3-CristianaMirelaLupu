import authHeader, { BASE_URL, HTTP } from "../http";

export default {
  allPatients() {
    return HTTP.get(BASE_URL + "/patient", { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  create(patient) {
    return HTTP.post(BASE_URL + "/patient", patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
  edit(patient) {
    return HTTP.put(BASE_URL + "/patient", patient, { headers: authHeader() }).then(
      (response) => {
        return response.data;
      }
    );
  },
    deleteById(id) {
        console.log("id: " + id)
        return HTTP.delete(BASE_URL + "/patient/" + id , {headers: authHeader()}).then(
            () => {
                return true;
            }
        );
    },
};
