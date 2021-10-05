<template>
  <div class="give-rating-container" :v-if="ratingsToFill">
    <h1>Give a rating to your old bookings:</h1>
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
  props: ["ratingsToFill"],

  data() {
    return {};
  },

  components: {
    GiveRatingItem,
  },

  methods: {},
};
</script>

<style scoped>
.give-rating-container {
  margin: 10px;
}
</style>
