<template>
  <div class="profile-page-container" v-if="user">
    <h1>{{ user.firstName.toUpperCase() }}</h1>
    <ShowRating />
  </div>
</template>

<script>
import ShowRating from "../components/user-components/ShowRating.vue";

export default {
  data() {
    return {};
  },
  computed: {
    user() {
      return this.$store.state.user;
    },
  },
  components: {
    ShowRating,
  },
  methods: {},
};
</script>

<style scoped>
</style>