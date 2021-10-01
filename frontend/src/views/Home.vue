<template>
  <div class="home-container">
    <AvailableLinstings />
    <br />
    <div class="user">
      <div class="create">
        <CreateUser />
      </div>
      <div class="login">
        <LoginUser />
      </div>
    </div>
    <br />

    <Create-booking />
  </div>
</template>

<script>
import AvailableLinstings from "../components/listing-components/AvailableListings.vue";
import CreateBooking from "../components/booking-components/CreateBooking.vue";

export default {
  components: {
    AvailableLinstings,
    CreateBooking,
  },
  computed:{
    user() {
      return this.$store.state.user;
    },
    }
  };
  
</script>

<style scoped>
.home-container {
  display: grid;
  grid-template-columns: 1fr 50vw 1fr;
}

.login {
  width: 300px;
  border: 2px;
}

.create {
  width: 300px;
}
</style>