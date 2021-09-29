<template>
  <div class="give-rating-container" v-if="ratingsToFill">
    <h1>Give rating:</h1>
    <div
      class="give-rating-item"
      v-for="booking in ratingsToFill"
      :key="booking.id"
    >
      <h1>{{ booking.bookingID }}</h1>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      ratingsToFill: [],
    };
  },

  beforeMount() {
    this.getRatingsToFill();
  },

  methods: {
    async getRatingsToFill() {
      let res = await fetch("/rest/check-if-there-is-ratings-to-fill/63");
      this.ratingsToFill = await res.json();
      console.log(this.ratingsToFill);
    },
  },
};
</script>

<style scoped>
.give-rating-item {
  display: flex;
  flex-direction: column;
  background-color: cadetblue;
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  margin-bottom: 5px;
  padding: 10px;
}
</style>
