<template>
  <div class="home-container">
    <AvailableLinstings />
  </div>
</template>

<script>
import AvailableLinstings from "../components/available-listings/AvailableLinstings.vue";

export default {
  components: {
    AvailableLinstings,
  },
};
</script>

<style scoped>
.home-container {
  display: grid;
  grid-template-columns: 1fr 60vw 1fr;
}
</style>