<template>
  <div class="available-listings-container">
    <h1 @click="test">All listings component</h1>
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
.available-listings-container {
  grid-column-start: 2;
  background-color: aqua;
}
</style>