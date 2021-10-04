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
  <div v-for ="(pImg, index) in initialData.imageslist " class="image"
  :key="index">
  <img :src="pImg" width="100">   
  </div>
  
  <button type="button" @click="close">Close</button>


<div v-if="!oldVersions"></div> 
  <div v-else>
    <div v-for="version in oldVersions"
    :key="version.id"
    :value= "version">
<br><br>
  <h2>Old Version</h2>
    <p>Price: {{version.price}}</p>
  <p>Description: {{version.description}}</p>
  <p>Address:  {{version.addressListing}}</p>
  <p>City:  {{version.city}}</p>
  <p>Start date: {{version.availableStartDate}}</p>
  <p>Closing date:  {{version.availableEndDate}}</p>
  <p>Doublebed: {{version.doubleBed}}</p>
  <p>Bathtub: {{version.bathTub}}</p>
  <p>Stove: {{version.stove}}</p>
  <p>BubblePool: {{version.bubblePool}}</p>
  <p>ParkingLot: {{version.parkingLot}}</p>
  <p>Bicycle: {{version.parkingLot}}</p>
  <p>Sauna: {{version.sauna}}</p>
<br>
<br>
    </div>
  </div>
</div>  

</template>


<script>
export default {
data(){
  return{
oldVersions:"",
id:"",

  }
} ,
  computed: {
    initialData() {
      this.oldVersions = this.$store.state.currentListingOldVersion
      return this.$store.state.listing;
    },
  },
  methods:{
    close(){
      this.$store.commit('setListing', null)
    }
  } 
};
</script>

<style scoped>
</style>