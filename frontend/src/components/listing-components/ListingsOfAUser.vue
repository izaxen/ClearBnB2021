<template>
  <div class="listing-summary-container" v-if="listingsInSummary">
    <h1>Listings owned by user</h1>
    <div v-if="!currentList">
      <div
        class="listing-summary-item"
        v-for="listing in listingsInSummary"
        :key="listing.id"
        @click="editList(listing.id)"
      >
        <h3>{{ listing.description }}</h3>
        <h2>{{ listing.id }}</h2>
        <p>Prices from: {{ listing.price }}</p>
      </div>
    </div>
    <div v-else>
      <EditListing @closeEdit="closeEdit" />
    </div>
  </div>
</template>

<script>
import EditListing from "../../views/EditListing.vue";

export default {
  props: ["listingsInSummary"],
  components: {
    EditListing,
  },

  data() {
    return {
      userToShow: this.$route.query.user,
      currentList: "",
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

    editList(id) {
      id = id.toString();
      this.currentList = this.$store.dispatch("getSingleListing", id);
    },
    closeEdit(closeEdit) {
      if (closeEdit) {
        this.currentList = null;
      }
    },
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
