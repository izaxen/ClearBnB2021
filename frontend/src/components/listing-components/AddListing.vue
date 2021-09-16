<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  methods: {
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
