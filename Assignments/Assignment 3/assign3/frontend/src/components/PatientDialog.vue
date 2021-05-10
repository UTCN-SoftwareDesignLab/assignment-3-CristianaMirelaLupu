<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>

        <v-toolbar color="primary" dark>
          {{ isNew ? "Create patient" : "Edit patient" }}
        </v-toolbar>

        <v-form >
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.card" label="Credit number" />
          <v-text-field v-model="patient.ssn" label="SSN" />
          <v-text-field type="date" v-model="patient.birthDate" label="Birthdate" />
          <v-text-field v-model="patient.address" label="Address" />

        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      // console.log("On click");
      if (this.isNew) {
        //console.log("heiiiii");
        api.patients
            .create({
              name: this.patient.name,
              card: this.patient.card,
              ssn: this.patient.ssn,
              birthDate: this.patient.birthDate,
              address: this.patient.address
            })
            .then(() => this.$emit("refresh"));

      } else {
        //console.log("here");
        api.patients
            .edit({
              id: this.user.id,
              name: this.patient.name,
              card: this.patient.card,
              ssn: this.patient.ssn,
              birthDate: this.patient.birthDate,
              address: this.patient.address
            })
      }
    },
  },

  computed: {
    isNew: function () {
      console.log(this.patient)
      return !this.patient.id
    },
  },
};
</script>

<style scoped></style>
