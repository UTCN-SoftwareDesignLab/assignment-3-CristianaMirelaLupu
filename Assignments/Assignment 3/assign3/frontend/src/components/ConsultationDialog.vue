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
          <v-text-field v-model="consultation.name" label="Name" />
          <v-text-field v-model="consultation.user" label="Doctor" />
          <v-text-field v-model="consultation.patient" label="Patient" />

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

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
          .create({
           name: this.consultation.name,
            user: this.consultation.user,
            patient: this.consultation.patient,
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.consultations
          .edit({
            id: this.consultation.id,
            name: this.consultation.name,
            user: this.consultation.user,
            patient: this.consultation.patient,
          })
          .then(() => this.$emit("refresh"));
      }
    },

    deletion() {
      //console.log("heiiiii");
      //console.log(this.user)
      api.consultations.deleteById(this.consultation.id)
          .then((response) => {
                if (response == true)
                  this.$emit("refresh")
              }
          );

    }
  },

  computed: {
    isNew: function () {
      return !this.consultation.id
    },
  },
};
</script>

<style scoped></style>
