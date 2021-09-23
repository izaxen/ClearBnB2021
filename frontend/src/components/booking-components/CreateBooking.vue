<template>
  <div class="add-booking-container" v-if="this.$store.state.user">
    <h1>Add Booking as {{ this.$store.state.user.firstName }}</h1>
    <form @submit.prevent="createBooking">
      <select v-model="selected">
        <option
          v-for="listing in allListings"
          :key="listing.id"
          :value="listing"
          @click="printSelected"
        >
          {{ listing.id }}, {{ listing.description }}
        </option>
      </select>
      <h3>Selected Listing: {{ selected.id }}</h3>
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
      startDate: "2021-12-12 00:00:00",
      endDate: "2021-12-13 00:00:00",
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
        startDate: "2021-12-12 00:00:00",
        endDate: "2021-12-13 00:00:00",
      };
      this.postNewBooking(newBooking);
    },
    async postNewBooking() {
      let res = await fetch(
        `/rest/createBooking/${this.selected.id}/${this.startDate}/${this.endDate}`
      );
    },
    async getAllListings() {
      let res = await fetch("/api/getAllListings");
      this.allListings = await res.json();
    },
    printSelected() {
      console.log(this.selected);
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