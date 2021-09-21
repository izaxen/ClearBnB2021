<template>
  <div class="home-container">
    <AvailableLinstings />
    <AddListing />
    <br />
    <CreateUser />
    <Create-booking />
  </div>
</template>

<script>
import AvailableLinstings from "../components/listing-components/AvailableListings.vue";
import AddListing from "../components/listing-components/AddListing.vue";
import CreateUser from "../components/user-components/CreateUser.vue";
import CreateBooking from "../components/booking-components/CreateBooking.vue";

export default {
  components: {
    AvailableLinstings,
    AddListing,
    CreateUser,
    CreateBooking,
  },
};
</script>

<style scoped>
.home-container {
  display: grid;
  grid-template-columns: 1fr 50vw 1fr;
}
</style>