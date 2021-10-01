<template>
  <div class="give-rating-container" v-if="ratingsToFill">
    <h1>Give rating:</h1>
    <GiveRatingItem
      v-for="booking in ratingsToFill"
      :key="booking.id"
      :booking="booking"
    />
  </div>
</template>

<script>
import GiveRatingItem from "./GiveRatingItem.vue";

export default {
  data() {
    return {
      ratingsToFill: [],
      user: this.$store.state.user,
    };
  },

  components: {
    GiveRatingItem,
  },

  beforeMount() {
    this.getRatingsToFill();
  },

  methods: {
    async getRatingsToFill() {
      let res = await fetch("/rest/check-if-there-is-ratings-to-fill/");
      this.ratingsToFill = await res.json();
      console.log(this.ratingsToFill);
    },
  },
};
</script>

<style scoped>
.give-rating-container {
  margin: 10px;
}
</style>
