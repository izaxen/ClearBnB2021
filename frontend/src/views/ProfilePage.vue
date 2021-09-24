<template>
  <div class="profile-page-container">
    <div class="search-user-container">
      <input v-model="userToShow" placeholder="Search user (user ID)" />
      <button @click="print">Show</button>
    </div>
    <h1>Welcome!</h1>
    <ShowRating />
  </div>
</template>

<script>
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
  },
  methods: {
    print() {
      router.push(`/profile_page?user=${this.userToShow}`);
    },
  },
};
</script>

<style scoped>
</style>