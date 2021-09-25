<template>
  <div class="available-listings-container">
    <FilterListings @updatelist="updatelist"> </FilterListings>
    <h1 @click="test">All listings component</h1>
    <div class="filteredListings">
      <ul v-if="this.matchedListings">
        <li
          v-for="listing in matchedListings"
          :key="listing.id"
          :value="listing"
        >
          {{ listing.description }}, {{ listing.price * 1.15 }}/night,
          {{ listing.address }},
          {{ listing.city }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import FilterListings from "../listing-components/filter-components/FilterListings.vue";
export default {
  components: {
    FilterListings,
  },

  data() {
    return {
      matchedListings: [],
    };
  },

  created() {
    this.getAllListings();
  },

  methods: {
    updatelist(matchedListings) {
      this.matchedListings = matchedListings;
    },

    async getAllListings() {
      let res = await fetch("/api/getAllListings");
      this.matchedListings = await res.json();
    },
  },
};
</script>


<style scoped>
.available-listings-container {
  grid-column-start: 2;
  background-color: aqua;
}

li {
  list-style-type: none;
}
</style>