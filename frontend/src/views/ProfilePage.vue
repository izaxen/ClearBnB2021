<template>
  <div class="profile-page-container">
    <div class="search-user-container">
      <h3>Go to user (user id)</h3>
      <input v-model="userToShow" placeholder="Search user (user ID)" />
      <button @click="goTo">Show</button>
    </div>
    <h1>Profile page</h1>
    <ShowRating />
    <ListingsOfAUser />
  </div>
</template>

<script>
import ListingsOfAUser from "../components/listing-components/ListingsOfAUser.vue";
import ShowRating from "../components/user-components/ShowRating.vue";
import router from "../router.js";

export default {
  data() {
    return {
      userToShow: this.$route.query.user,
    };
  },

  beforeMount() {
    this.$forceUpdate();
    this.userToShow = this.$route.query.user;
  },

  components: {
    ShowRating,
    ListingsOfAUser,
  },

  methods: {
    goTo() {
      router.push(`/profile_page?user=${this.userToShow}`);
    },
  },
};
</script>

<style scoped>
.profile-page-container {
  display: grid;
  grid-template-columns: 50vw 50vw;
  grid-template-rows: repeat(auto-fit, minmax(50px, 1fr));
}

.search-user-container {
  display: flex;
  justify-content: right;
}
</style>