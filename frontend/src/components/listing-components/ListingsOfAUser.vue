<template>
  <div class="listing-summary-container" v-if="listings">
    <h1>Listings owned by user</h1>
    <div
      class="listing-summary-item"
      v-for="(listing) in listings"
      :key="listing.id"
      @click="editList(listing)"
    >
      <h3>{{ listing.description }}</h3>
      <h2>{{listing.id}}</h2>
      <p>Prices from: {{ listing.price }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userToShow: this.$route.query.user,
      listings: [],
    };
  },

  beforeMount() {
    this.userToShow = this.$route.query.user;
    this.getListingsInSum();
  },

  methods: {
    async getListingsInSum() {
      let res = await fetch(`/rest/${this.userToShow}/listings`);
      this.listings = await res.json();
    },
    editList(id){
      console.log("Öppnar edit list");
      console.log(id)
    }
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
  background-color: #afa3ad;
  border-radius: 10px;
  margin-bottom: 5px;
  padding-left: 10px;
}

p {
  margin-left: auto;
  padding: 10px;
}
</style>
