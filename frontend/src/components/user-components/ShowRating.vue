<template>
  <div class="rating-container" v-if="ratings">
    <h1>Rating: {{ avgRating ? avgRating : "" }}</h1>
    <h3>{{ rating }}</h3>
    <div
      class="ratingItem"
      v-for="rating in ratings.slice().reverse()"
      :key="rating.dateWritten"
    >
      <h1 class="stars">{{ rating.rating }}</h1>
      <p>Stars</p>
      <h4>{{ rating.review }}</h4>
      <p>By: {{ rating.reviewer }}, {{ rating.dateWritten }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userID: this.$route.query.user,
      ratings: [],
      avgRating: null,
    };
  },

  mounted() {
    this.getAvgRating().then(() => {
      this.getAllRatings();
    });

    this.userID = this.$route.query.user;
  },

  methods: {
    async getAllRatings() {
      let res = await fetch(`/rest/rating/${this.userID}`);
      this.ratings = await res.json();
    },
    async getAvgRating() {
      let res = await fetch(`/rest/avg-rating/${this.userID}`);
      let x = (await res.json()) + "";
      this.avgRating = x.substring(0, 3);
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
.stars h1 {
  display: inline;
}
h4 {
  margin: 20px;
}
</style>