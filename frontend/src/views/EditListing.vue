<template>
  <div class="add-listing-container">
    <h1>Edit listing</h1>
    
    <div class = "currentListing">


    </div>
    <form v-if="initialData" @submit.prevent="addListing">
      <div  class="inputFields">
        <input
          :value="initialData.description"
          type="text"
          @change="ev  => changedList.description = ev.target.value"
        />

        <input
          :value="initialData.price"
          type="number"
          @change="ev => changedList.price = ev.target.value"
        />

        <input
         :value="initialData.city"
          type="text"
          @change="ev => changedAddress.city = ev.target.value"
        />

        <input
        :value="initialData.addressListing"
          type="text"
          @change="ev => changedAddress.addressListing = ev.target.value"
        />
      </div>
      <br />
      <div class="amen">
        <p>Choose amenities</p>
        <div v-if="initialData.bathTub">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.bathTub = ev.target.value" />
           <label >BathTub</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.bathTub = ev.target.value" />
           <label>BathTub</label>
        </div>
        
         <div v-if="initialData.parkingLot">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.parkingLot= ev.target.value" />
           <label>Parking lot</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.parkingLot = ev.target.value" />
           <label>Parking lot</label>
        </div>
        
         <div v-if="initialData.stove">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.stove = ev.target.value" />
           <label>Stove</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.stove = ev.target.value" />
          <label>Stove</label>
        </div>

        
         <div v-if="initialData.doubleBed">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.doubleBed = ev.target.value" />
          <label>Double bed</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.doubleBed = ev.target.value" />
            <label>Double bed</label>
        </div>

         <div v-if="initialData.bubblePool">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.bubblePool = ev.target.value" />
          <label>Bubble pool</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.bubblePool = ev.target.value" />
           <label for="isBathTub">BathTub</label>
        </div>

         <div v-if="initialData.bicycle">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.bicycle = ev.target.value" />
           <label>Bicycle</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.bicycle = ev.target.value" />
           <label>Bicycle</label>
        </div>

         <div v-if="initialData.sauna">
          <input type="checkbox"
          checked
          @change ="ev=> changedAmenity.sauna = ev.target.value" />
          <label>Sauna</label>
        </div>
        <div v-else>
          <input type="checkbox"
          @change ="ev=> changedAmenity.sauna = ev.target.value" />
            <label>Sauna</label>
        </div>
      </div>

      <div class="date">
        <div class="start">
          <label for="startDate">Select Start Date</label>
          <input
            :value="new Date(initialData.availableStartDate).toISOString().split('T')[0]"
            type="date"
            :min="new Date().toISOString().split('T')[0]"
            @change="ev=>changedData.availableStartDate"
          />
        </div>
        <div class="end">
          <label for="endDate">Select End Date</label>
          <input
            :value="new Date(initialData.availableEndDate).toISOString().split('T')[0]"
            type="date"
            :min="new Date().toISOString().split('T')[0]"
          />
        </div>
      </div>
      <br>
      <div class="images">
      <label>Choose images</label>
      <br />
</div>
      <button>Save Listing</button>
    </form>
  </div>
</template>

<script>

export default {
  components:{
  },

  data() {
    return {
      changedList:  { },
      changedAmenity:{},
      changedAddress:{},
    };
  },

  computed: {
    initialData() {
      return this.$store.state.currentListing;
    }
  },

  methods: {
    async addListing() {
      let newListing = {
        description: this.description,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        price: this.price,
      };
     
     await this.$store.dispatch("updateListing", {...newListing, id: this.initialData.id});
      this.addAddress();
    },

    async addAddress() {
      console.log("Inne i address");
      let newAddress = {
        city: this.city,
        address: this.addressListing,
      };
      await this.$store.dispatch("addAddress", newAddress);
      this.addAmenity();
    },

    async addAmenity() {
      let newAmenity = {
        bathTub: this.isBathTub,
        parkingLot: this.isParkingLot,
        stove: this.isStove,
        doubleBed: this.isDoubleBed,
        bubblePool: this.isBubblePool,
        bicycle: this.isBicycle,
        sauna: this.isSauna,
      };

      await this.$store.dispatch("addAmenity", newAmenity);
    },
    
  },
};
</script>

<style scoped>

.amen{
  display: flex;
  flex-direction: column;
  align-content: center;
}
.images{
  display: Flex;
  justify-content: center;
}
.add-listing-container {
  display: flex;
  flex-direction: column;
}
.inputFields {
  display: flex;
  flex-direction: column;
}

input{
  margin: 5px;
}
.add-listing-container {
  align-self: center;
  max-width: 60vw;
  grid-column-start: 1;
  grid-row-start: 1;
  background-color: rgb(216, 202, 230);
}

label{
  margin-right: 15px;
}
</style>