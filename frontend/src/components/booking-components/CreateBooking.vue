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
      <h3>Selected Days: {{ this.calculatedays }}</h3>
      <h3>Total cost: {{ this.totalPrice }}</h3>

      <input
        v-model="startDate"
        required
        type="date"
        :min="new Date().toISOString().split('T')[0]"
        placeholder="Startdatum"
      />

      <input
        v-model="endDate"
        required
        type="date"
        placeholder="Slut Datum"
        :min="new Date().toISOString().split('T')[0]"
      />

      <div v-if="calculatedays <= 0">
        <button type="button" disabled>Create new booking</button>
      </div>
      <div v-else>
        <button @click="printSelected">Create new booking</button>
      </div>
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
      calculatedays: "",
      totalPrice: "",
    };
  },

  beforeMount() {
    this.getAllListings();
  },

  watch: {
    startDate: function () {
      this.calculateDays();
    },
    endDate: function () {
      this.calculateDays();
    },
    selected: function () {
      this.calculateDays();
    },
  },

  methods: {
    calculateDays() {
      if (this.endDate !== "") {
        var sDate = new Date(this.startDate);
        var eDate = new Date(this.endDate);
        var diff = eDate.getTime() - sDate.getTime();
        var diffDays = diff / (1000 * 3600 * 24);

        this.calculatedays = diffDays;
        if (this.calculatedays > 0) {
          this.totalPrice = Math.ceil(this.calculatedays * this.selected.price);
        } else {
          this.totalPrice = 0;
          this.calculatedays = 0;
        }
      } else {
        this.calculatedays == 0;
      }
    },

    createBooking() {
      let newBooking = {
        listingID: this.selected.id,
        startDate: this.startDate,
        endDate: this.endDate,
        totalPrice: this.totalPrice,
      };

      this.postNewBooking(newBooking);
    },

    async postNewBooking(newBooking) {
      console.log(newBooking);
      let res = await this.$store.dispatch("postNewBooking", newBooking);
      console.log(await res);
    },

    async getAllListings() {
      let res = await fetch("/api/allListings");
      let updatedList = await res.json();
      this.allListings = updatedList.allListings;

      // this should have been rest /rest/listing   since we don't send id we are sending everything, thus no need for listingS
      // await this.$store.dispatch("getAllListingsDTO");
      // this.allListings = this.$store.state.allListingsDTO;
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