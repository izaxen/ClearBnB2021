<template>
  <div class="rating-container">
    <h1>Ratings as renter</h1>
    <h3>{{ rating }}</h3>
    <div class="ratingItem" v-for="rating in ratings" :key="rating.dateWritten">
      Rating:{{ rating.rating }} {{ rating.review }}, reviewer:
      {{ rating.reviewer }}
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ratings: [],
    };
  },
  beforeMount() {
    this.getAllRatings(2);
  },

  methods: {
    async getAllRatings(id) {
      console.log("Rating called");
      let res = await fetch(`/rest/rating/${id}`);
      this.ratings = await res.json();
    },
  },
};
</script>

<style scoped>
</style>