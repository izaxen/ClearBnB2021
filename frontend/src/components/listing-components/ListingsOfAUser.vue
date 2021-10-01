<template>
  <div class="listing-summary-container" v-if="listingsInSummary">
    <h1>Listings owned by user</h1>
    <div
      class="listing-summary-item"
      v-for="listing in listingsInSummary"
      :key="listing.id"
    >
      <h3>{{ listing.description }}</h3>
      <p>Prices from: {{ listing.price }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: ["listingsInSummary"],

  data() {
    return {
      /* userToShow: this.$route.params.id, */
    };
  },

  beforeMount() {
    /* this.userToShow = this.$route.params.id; */
    /* this.getListingsInSum(); */
  },

  methods: {
    /*    async getListingsInSum() {
      let res = await fetch(`/rest/${this.userToShow}/listings`);
      this.listings = await res.json();
    }, */
  },
};
</script>


<style scoped>
.listing-summary-container {
  margin: 10px;
}

.listing-summary-item {
  display: flex;
  height: 35px;
  align-items: center;
  background-color: #c380ba;
  border-radius: 10px;
  margin-bottom: 5px;
  padding-left: 10px;
}

p {
  margin-left: auto;
  padding: 10px;
}
</style>
