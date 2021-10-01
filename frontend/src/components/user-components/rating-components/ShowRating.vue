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
      <p
        v-if="rating.reviewer === loggedInFullName"
        @click="deleteRating(rating.id)"
      >
        Delete this rating
      </p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userID: this.$route.params.id,
      ratings: [],
      rating: null,
      avgRating: null,
      loggedInFullName:
        this.$store.state.user.firstName + " " + this.$store.state.user.surName,
    };
  },

  mounted() {
    this.getAvgRating().then(() => {
      this.getAllRatings();
    });

    console.log(this.loggedInFullName);

    this.userID = this.$route.params.id;
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

    async deleteRating(ratingID) {
      let res = await fetch(`/api/delete-rating`, {
        method: "DELETE",
        body: JSON.stringify({ id: ratingID }),
      });
    },
  },
};
</script>

<style scoped>
.rating-container {
  margin: 10px;
}

.ratingItem {
  display: flex;
  flex-direction: column;
  background-color: cadetblue;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  margin-bottom: 5px;
  padding: 10px;
}
.stars h1 {
  display: inline;
}
h4 {
  margin: 20px;
}
</style>