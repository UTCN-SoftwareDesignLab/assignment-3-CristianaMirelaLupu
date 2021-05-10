import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import ConsultationList from "../views/ConsultationList.vue";
import PatientList from "@/views/PatientsList";
import { auth as store } from "../store/auth.module";
import Login from "../views/Login";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isAdmin) {
        next();
      } else {
        next({ name: "User" });
      }
    },
  },
  {
    path: "/patients",
    name: "Patients",
    component: PatientList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isDoctor) {
        next();
      } else {
        next({ name: "Patient" });
      }
    },
  },
  {
    path: "/consultations",
    name: "Consultations",
    component: ConsultationList,
    beforeEnter: (to, from, next) => {
      if (store.getters.isSecretary) {
        next();
      } else {
        next({ name: "Consultation" });
      }
   },
  },
];

const router = new VueRouter({
  routes,
});

export default router;
