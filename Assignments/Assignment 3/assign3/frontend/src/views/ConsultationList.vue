<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>

      <v-btn @click="addConsultation">Add Consultation</v-btn>
      <v-btn @click="goToPatients">Go to Patients</v-btn>

    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="consultations"
        :search="search"
        @click:row="editConsultation"
    ></v-data-table>
    <ConsultationDialog
        :opened="dialogVisible"
        :consultation="selectedConsultation"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ConsultationDialog from "../components/ConsultationDialog";
import router from "@/router";

export default {
  name: "ConsultationsList",
  components: { ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "Description",
          align: "start",
          sortable: false,
          value: "description",
        },
        { text: "Doctor", value: "doctor" },
        { text: "Patient", value: "patient" },
        {text: "Date", value: "date"},
        {text: "Time", value: "time"},
      ],
      dialogVisible: false,
      selectedConsultation: {},
    };
  },
  methods: {
    editConsultation(consultation) {
      this.selectedConsultation = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.dialogVisible = true;
    },

    goToPatients() {
      router.push("/patients");
    },

    async refreshList() {
      this.dialogVisible = false;
      this.selectedConsultation = {};
      this.consultation = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
