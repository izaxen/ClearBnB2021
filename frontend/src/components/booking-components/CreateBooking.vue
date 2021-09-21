<template>
  <div class="add-booking-container">
    <h1>Add Booking container</h1>
    <form @submit.prevent="createBooking">
      <input v-model="ownerID" required type="number" placeholder="Owner id" />
      <input
        v-model="listingID"
        required
        type="number"
        placeholder="listing id"
      />
      <button>Create new booking</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {};
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