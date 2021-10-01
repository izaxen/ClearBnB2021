<template>
<div v-if="initialData">
<h1> Listing</h1>

<p>Price: {{initialData.price}}</p>
<p>Description: {{initialData.description}}</p>
<p>Address:  {{initialData.addressListing}}</p>
<p>City:  {{initialData.city}}</p>
<p>Start date: {{initialData.availableStartDate}}</p>
<p>Closing date:  {{initialData.availableEndDate}}</p>
<p>Doublebed: {{initialData.doubleBed}}</p>
<p>Bathtub: {{initialData.bathTub}}</p>
<p>Stove: {{initialData.stove}}</p>
<p>BubblePool: {{initialData.bubblePool}}</p>
<p>ParkingLot: {{initialData.parkingLot}}</p>
<p>Bicycle: {{initialData.parkingLot}}</p>
<p>Sauna: {{initialData.sauna}}</p>

<button type="button" @click="close">Close</button>



</div>
</template>


<script>
export default {
data(){
  return{

  }
} ,
  computed: {
    initialData() {
      return this.$store.state.currentListing;
    },
  },
  methods:{
    close(){
      console.log("Död");
      //this.$store.commit('setCurrentListing', null)
    }
  } 
};
</script>

<style scoped>
</style>