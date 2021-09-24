<template>
  <div class="rating-container" v-if="ratings">
    <h1>Rating as renter</h1>
    <h3>{{ rating }}</h3>
    <div
      class="ratingItem"
      v-for="rating in ratings.slice().reverse()"
      :key="rating.dateWritten"
    >
      <div class="rating-c">
        <h1>{{ rating.rating }}</h1>
        <p>Stars</p>
      </div>
      <h4>{{ rating.review }}</h4>
      <p>By: {{ rating.reviewer }}, {{ rating.dateWritten }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ratings: [],
      userID: this.$route.query.user,
    };
  },

  beforeMount() {
    this.getAllRatings();
    this.userID = this.$route.query.user;
  },

  methods: {
    async getAllRatings() {
      let res = await fetch(`/rest/rating/${this.userID}`);
      this.ratings = await res.json();
    },
  },
};
</script>

<style scoped>
.rating-container {
  max-width: 50%;
}

.ratingItem {
  height: 100%;
  display: flex;
  flex-direction: column;
  background-color: cadetblue;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  margin: 5px;
  padding: 10px;
}

h4 {
  margin: 20px;
}
</style>