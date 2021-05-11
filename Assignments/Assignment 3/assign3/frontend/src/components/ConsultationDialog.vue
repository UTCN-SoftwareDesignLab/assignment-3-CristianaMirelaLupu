<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create consultation" : "Edit consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.description" label="Description" />
          <v-text-field v-model="consultation.user" label="Doctor's name" />
          <v-text-field v-model="consultation.patient" label="Patient's name" />
<!--          <v-label >Patient</v-label>-->
<!--          <v-select v-model="patient"-->
<!--                    :items ="patients"-->
<!--                    item-text='name'-->
<!--                    item-value='id'-->
<!--                    :patient = "selectedPatient"-->
<!--                    >-->
<!--          </v-select>-->
<!--          <v-label >Doctor</v-label>-->
<!--          <v-select :items="doctors"-->
<!--                    @refresh="refreshList">-->
<!--          </v-select>-->

          <datetime format="MM/DD/YYYY HH:mm" width="300px" v-model="consultation.dateAndTime" label = "DateAndTime"></datetime>

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>

          <v-btn v-if="!isNew" @click="deletion">Delete</v-btn>

        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";
import datetime from 'vuejs-datetimepicker';

export default {
  components: { datetime },
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
    patients: {},
    doctors: {},
    patient:Object,
  },
  data() {
    return {
      selectedPatient:{},
      selectedDoctor:{},
    };
  },
  methods: {

  persist() {
    if (this.isNew) {
      api.consultations
          .create({
            description: this.consultation.description,
            user: this.consultation.user,
            patient: this.consultation.patient,
            dateAndTime: this.consultation.dateAndTime,
          })
          .then(() => this.$emit("refresh"));
    } else {
      api.consultations
          .edit({
            id: this.consultation.id,
            name: this.consultation.name,
            user: this.consultation.user,
            patient: this.consultation.patient,
            dateAndTime: this.consultation.dateAndTime,

          })
          .then(() => this.$emit("refresh"));
    }
  },

  deletion() {
    api.consultations.deleteById(this.consultation.id)
        .then((response) => {
              if (response == true)
                this.$emit("refresh")
            }
        );
  },
    async refreshList() {
      this.selectedPatient = {};
      this.patients = await api.patients.allPatients();

      this.selectedDoctor = {};
      this.doctors = await api.users.allDoctors();
    },

  created() {
    this.refreshList();
  },
},

computed: {
  isNew: function () {
    return !this.consultation.id
  },
},
};
</script>

<style scoped></style>
