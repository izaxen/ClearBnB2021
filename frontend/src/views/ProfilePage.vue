<template>
  <div class="profile-page-container">
    <div class="profiletxt">
      <h1>Profile page</h1>
    </div>

    <div class="search-user-container">
      <div class="go-to-rating"><h3 @click="goToRating">Give rating</h3></div>
      <div>
        <h3>Go to user (user id)</h3>
        <input v-model="userToShow" placeholder="Search user (user ID)" />
        <button @click="goToUser">Show</button>
      </div>
    </div>

    <ShowRating v-bind:ratings="ratings" v-bind:avgRating="avgRating" />
    <ListingsOfAUser v-bind:listingsInSummary="listingsInSummary" />
    <GiveRating v-bind:ratingsToFill="ratingsToFill" />
  </div>
</template>

<script>
import ListingsOfAUser from "../components/listing-components/ListingsOfAUser.vue";
import ShowRating from "../components/user-components/rating-components/ShowRating.vue";
import router from "../router.js";
import GiveRating from "../components/user-components/rating-components/GiveRating.vue";

export default {
  data() {
    return {
      userToShow: this.$route.params.id,
      ratings: [],
      rating: null,
      avgRating: null,
      listingsInSummary: [],
      ratingsToFill: [],
    };
  },

  beforeMount() {
    //All .then methods are a desperate fix to get Hibernate to work more reliable... //Mac
    this.getAvgRating()
      .then(() => {
        this.getAllRatings();
      })
      .then(() => {
        this.getListingsInSummary();
      })
      .then(() => {
        this.getRatingsToFill();
      });
  },

  components: {
    ShowRating,
    ListingsOfAUser,
    GiveRating,
  },

  methods: {
    goToUser() {
      router.push(`/profile_page/${this.userToShow}`);
    },

    async getAllRatings() {
      this.ratings = await this.$store.dispatch(
        "getAllRatings",
        this.$route.params.id
      );
    },
    async getAvgRating() {
      this.avgRating = await this.$store.dispatch(
        "getAvgRating",
        this.$route.params.id
      );
    },
    async deleteRating(ratingID) {
      await this.$store.dispatch("deleteRating", ratingID);
    },

    async getListingsInSummary() {
      this.listingsInSummary = await this.$store.dispatch(
        "getListingsInSummary",
        this.$route.params.id
      );
    },
    async getRatingsToFill() {
      this.ratingsToFill = await this.$store.dispatch("getRatingsToFill");
    },
  },
};
</script>


<style scoped>
.profile-page-container {
  display: grid;
  grid-template-columns: 50vw 50vw;
  grid-template-rows: repeat(auto-fit, minmax(50px, 1fr));
  grid-row-gap: 30px;
}

.profiletxt {
  display: flex;
  height: 100%;
  background-color: chocolate;
  align-items: center;
  place-content: center;
  border-radius: 10px 0px 0px 10px;
  margin-left: 10px;
}

.search-user-container {
  display: flex;
  justify-content: right;
  align-items: center;
  background-color: chocolate;
  border-radius: 0px 10px 10px 0px;
  margin-right: 10px;
  justify-content: space-around;
}

.rating input {
  height: 70%;
  margin: 10px;
}

button {
  margin-right: 10px;
}

h3 {
  margin-left: 10px;
}
</style>