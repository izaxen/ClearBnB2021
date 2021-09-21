<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
    <form @submit.prevent="addListing">
      <input
        v-model="description"
        type="text"
        placeholder="Enter description here"
      />

      <input v-model="price" type="number" placeholder="Enter price" />

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
    };
  },

  computed: {},

  methods: {
    addListing() {
      let newListing = {
        user: this.$store.state.user,
        description: this.description,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        price: this.price,
      };

      this.$store.dispatch("addListing", newListing);
    },

    async test() {
      await this.$store.dispatch("test");
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