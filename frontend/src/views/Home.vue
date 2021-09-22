<template>
  <div class="home-container">
    <AvailableLinstings />
    <AddListing />
    <br>
      <div class = "user">
        <div class = "create">
        <CreateUser/></div>
        <div class = "login">
        <LoginUser/>
        </div>
      </div>
    <br />
   
    <Create-booking />
  </div>
  
</template>

<script>
import AvailableLinstings from "../components/listing-components/AvailableListings.vue";
import AddListing from "../components/listing-components/AddListing.vue";
import CreateUser from '../components/user-components/CreateUser.vue';
import LoginUser from '../components/user-components/LogIn.vue'
import CreateBooking from "../components/booking-components/CreateBooking.vue";

export default {
  components: {
    AvailableLinstings,
    AddListing,
    CreateUser,
    LoginUser,
    CreateBooking,
  },
};
</script>

<style scoped>
.home-container {
  display: grid;
  grid-template-columns: 1fr 50vw 1fr;
}

.user{
  display: flex;
  
  
}
.login{
  width: 300px;
  border: 2px;
  
}

.create{
  
  width: 300px;
}

</style>