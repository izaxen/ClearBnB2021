<template>
  <div class="home-container">
    <AvailableLinstings />
    <AddListing />
  </div>
</template>

<script>
import AvailableLinstings from "../components/listing-components/AvailableListings.vue";
import AddListing from "../components/listing-components/AddListing.vue";

export default {
  components: {
    AvailableLinstings,
    AddListing,
  },
};
</script>

<style scoped>
.home-container {
  display: grid;
  grid-template-columns: 1fr 50vw 1fr;
}
</style>