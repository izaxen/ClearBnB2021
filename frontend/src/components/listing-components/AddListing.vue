<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
    <form @submit.prevent="addListing">
      <input
        v-model="description"
        type="text"
        placeholder="Enter description here"
      />

      <input v-model="price" type="number" placeholder="Enter price here" />

      <input v-model="city" type="text" placeholder="Enter city here" />

      <input
        v-model="addressListing"
        type="text"
        placeholder="Enter address here"
      />

      

      <button>Save Listing</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
      description: "",
      price: null,
      available_start_date: "2020-10-10 13:00",
      available_end_date: "2021-10-10 15:00",
      city: "",
      addressListing: "",
      listing: null,
    };
  },

  computed: {},

  methods: {
    async addListing() {
      let newListing = {
        user: this.$store.state.user,
        description: this.description,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        price: this.price,
      };

      // two thread at same session how to fix?
      // temp workaround : i made them into different methods and create it one by one
      await this.$store.dispatch("addListing", newListing);
      this.addAddress();
    },

    addAddress() {
      let newAddress = {
        city: this.city,
        addressListing: this.addressListing,
        listing: this.$store.state.currentListing,
      };
      this.$store.dispatch("addAddress", newAddress);
    },
  },
};
</script>

<style scoped>
.add-listing-container {
  grid-column-start: 1;
  grid-row-start: 1;
  background-color: blueviolet;
}
</style>