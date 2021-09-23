<template>
  <div class="add-booking-container" v-if="this.$store.state.user">
    <h1>Add Booking as {{ this.$store.state.user.firstName }}</h1>
    <form @submit.prevent="createBooking">
      <select v-model="selected">
        <option
          v-for="listing in allListings"
          :key="listing.id"
          :value="listing"
        >
          {{ listing.id }}, {{ listing.description }}
        </option>
      </select>
      <h3>Selected Listing: {{ selected.id }}</h3>

      <input
        v-model="startDate"
        required
        type="date"
        placeholder="Startdatum"
      />

      <input v-model="endDate" required type="date" placeholder="Slut Datum" />

      <button @click="printSelected">Create new booking</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selected: {},
      allListings: [],
      startDate: new Date(),
      endDate: "",
    };
  },

  beforeMount() {
    this.getAllListings();
  },

  methods: {
    createBooking() {
      let newBooking = {
        user: this.$store.state.user,
        listing: this.selected,
        startDate: this.startDate,
        endDate: this.endDate,
      };
      this.postNewBooking(newBooking);
    },
    async postNewBooking() {
      console.log(this.startDate, this.endDate);
      let res = await fetch(
        `/rest/createBooking/${this.selected.id}/${this.startDate}/${this.endDate}`
      );
      console.log(await res.json());
    },
    async getAllListings() {
      let res = await fetch("/api/getAllListings");
      this.allListings = await res.json();
    },
  },
};
</script>

<style scoped>
.add-booking-container {
  grid-column-start: 1;
  grid-row-start: 2;
  background-color: rgb(14, 128, 29);
}
</style>