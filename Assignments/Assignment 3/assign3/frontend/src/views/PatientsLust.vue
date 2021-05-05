<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>

      <v-btn @click="addPatient">Add C</v-btn>
      <v-btn @click="goToConsultations">Go to Consultations</v-btn>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="patients"
        :search="search"
        @click:row="editPatient"
    ></v-data-table>
    <PatientDialog
        :opened="dialogVisible"
        :patient="selectedPatient"
        @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";
import router from "@/router";

export default {
  name: "PatientsList",
  components: {PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "Name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "Id No", value: "id_no" },
        { text: "Ssn", value: "ssn" },
        { text: "Birthdate", value: "birth_date" },
        { text: "Address", value: "address" },
      ],
      dialogVisible: false,
      selectedPatient: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedPatient = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.dialogVisible = true;
    },

    goToConsultations() {
      router.push("/consultations");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedPatient = {};
      this.patients = await api.patients.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
