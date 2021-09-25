<template>
  <div class="available-listings-container">
    <FilterListings />
    <h1 @click="test">All listings component</h1>
  </div>
</template>

<script>
import FilterListings from "../listing-components/filter-components/FilterListings.vue";
export default {
  components: {
    FilterListings,
  },

  data() {
    return {};
  },

  methods: {},
};
</script>


<style scoped>
.available-listings-container {
  grid-column-start: 2;
  background-color: aqua;
}
</style>