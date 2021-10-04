<template>
  <div class="available-listings-container">
    <FilterListings @updatelist="updatelist"> 

    </FilterListings>
    <h1 @click="test">All listings component</h1>
    
    <div v-if="matchedListings && !listing" class="filteredListings">
      <li v-for="listing in matchedListings.allListings" 
      :key="listing.id" 
      :value="listing"
      @click="openDetail(listing.id)">
        {{ listing.description }}, {{ listing.price }} / night,
        {{ listing.address }},
        {{ listing.city }}
      </li>
    </div>
  
    <div v-else>
    <DetailedListing/>
          
    </div>
  </div>
</template>

<script>
import FilterListings from "../listing-components/filter-components/FilterListings.vue";
import DetailedListing from "../../views/DetailedListing.vue";
 
export default {
  components: {
    FilterListings,
    DetailedListing
  },

  data() {
    return {
      matchedListings: [],
    };
  },
  computed:{
  listing(){
    return this.$store.state.listing
  }

  },
  created() {
    this.getAllListingsDTO();
  },

  methods: {
    updatelist(matchedListings) {
      console.log('matched', matchedListings);
      this.matchedListings = matchedListings;
    },
    async openDetail(id){
      console.log(id);
    id = id.toString()
    this.$store.dispatch('getSingleListingVersion', id)
    this.currentList = this.$store.dispatch('getSingleListing', id)
    
    },

    async getAllListingsDTO() {
      let res = await fetch("/api/allListings");
      this.matchedListings = await res.json();
      console.log('this.matchedListing', this.matchedListings.allListings);
      
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