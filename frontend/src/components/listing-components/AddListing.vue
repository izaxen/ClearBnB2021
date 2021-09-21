<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
    <form @submit.prevent="addListing">
      <input
        v-model="description"
        type="text"
        placeholder="Enter description here"
      />

      <button>Save Listing</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userID: "",
      description: "",
    };
  },

  methods: {
    addListing() {
      let newListing = {
        userID: 1,
        description: this.description,
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
