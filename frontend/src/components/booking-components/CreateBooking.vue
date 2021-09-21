<template>
  <div class="add-booking-container">
    <h1 v-if="user">Add Booking as {{ this.$store.state.user.firstName }}</h1>
    <form @submit.prevent="createBooking">
      <select v-model="allListings">
        <option v-for="listing in allListings" :key="listing.id">
          {{ listing.id }}, {{ listing.description }}
        </option>
      </select>
      <button>Create new booking</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selected: "",
      allListings: [],
      user: this.$store.state.user,
    };
  },

  beforeMount() {
    this.getAllListings();
  },

  methods: {
    createBooking() {
      console.log("Created booking called!");
      let newBooking = {
        userID: this.ownerID,
        listingID: this.listingID,
        startDate: new Date(),
        endDate: new Date("2021-12-12"),
      };
      this.postNewBooking(newBooking);
    },
    async postNewBooking(newBooking) {
      let res = await fetch("/api/createBooking", {
        method: "POST",
        body: JSON.stringify(newBooking),
      });
    },
    async getAllListings() {
      console.log("getAllListings runned!");
      let res = await fetch("/api/getListings");
      this.allListings = await res.json();
      console.log(this.allListings);
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