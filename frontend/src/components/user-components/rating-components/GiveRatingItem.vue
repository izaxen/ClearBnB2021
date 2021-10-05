<template>
  <div class="give-rating-item" v-if="this.$props.booking">
    Give a rating to
    {{
      this.$store.state.user.id === this.$props.booking.reviewer
        ? this.$props.booking.recipientName
        : this.$props.booking.reviewerName
    }}
    {{
      this.$store.state.user.id === this.$props.booking.reviewer
        ? "as your guest"
        : "as your host"
    }}
    <form action="" name="{{this.$props.booking.id}}">
      <div class="radio">
        <input
          type="radio"
          id="1"
          name="rating"
          value="1"
          v-model="this.rating"
        />
        <label for="1">1</label>
        <input
          type="radio"
          id="2"
          name="rating"
          value="2"
          v-model="this.rating"
        />
        <label for="2">2</label>
        <input
          type="radio"
          id="3"
          name="rating"
          value="3"
          v-model="this.rating"
        />
        <label for="3">3</label>
        <input
          type="radio"
          id="4"
          name="rating"
          value="4"
          v-model="this.rating"
        />
        <label for="4">4</label>
        <input
          type="radio"
          id="5"
          name="rating"
          value="5"
          v-model="this.rating"
        />
        <label for="5">5</label>
      </div>
      <input
        type="text"
        placeholder="Comment"
        v-model="this.comment"
        required
      />

      <button @click="this.createARating()">Send rating</button>
    </form>
    <p>
      {{
        this.$store.state.user.id === this.$props.booking.reviewer
          ? `You hosted ${
              this.$props.booking.recipientName
            } at ${this.$props.booking.dateVisited.substring(0, 10)}`
          : `You visited ${
              this.$props.booking.reviewerName
            } at ${this.$props.booking.dateVisited.substring(0, 10)}`
      }}
      Booking id: {{ this.$props.booking.bookingID }}
    </p>
  </div>
</template>

<script>
export default {
  props: ["booking"],

  data() {
    return {
      ratingsToFill: [],
      recipient:
        this.$store.state.user.id === this.$props.booking.guest
          ? this.$props.booking.landlordID
          : this.$props.booking.guestID,
      rating: 1,
      comment: null,
      bookingID: this.$props.booking.bookingID,
      dateVisited: this.$props.booking.dateVisited,
    };
  },

  methods: {
    async createARating() {
      let rating1 = {
        reviewerID: this.$store.state.user.id,
        recipientID:
          this.$store.state.user.id === this.$props.booking.recipient
            ? this.$props.booking.reviewer
            : this.$props.booking.recipient,
        bookingID: this.booking.bookingID,
        rating: this.rating,
        message: this.comment,
      };

      let res = await fetch("/api/createNewRating", {
        method: "POST",
        body: JSON.stringify(rating1),
      });
    },
  },
};
</script>

<style scoped>
.give-rating-item {
  display: flex;
  flex-direction: column;
  background-color: rgb(100, 160, 95);
  align-items: center;
  justify-content: center;
  border-radius: 15px;
  margin-bottom: 5px;
  padding: 10px;
}

.active {
  display: none;
}
</style>