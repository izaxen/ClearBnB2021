<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
    <form @submit.prevent="addListing">
      <input
        v-model="description"
        type="text"
        placeholder="Enter description here"
      />

      <input v-model="price" type="number" placeholder="Enter price here" />

      <input v-model="city" type="text" placeholder="Enter city here" />

      <input
        v-model="addressListing"
        type="text"
        placeholder="Enter address here"
      />

      <input type="checkbox" id="isBathTub" v-model="isBathTub" />
      <label for="isBathTub">BathTub</label>

      <input type="checkbox" id="isParkingLot" v-model="isParkingLot" />
      <label for="isParkingLot">ParkingLot</label>

      <input type="checkbox" id="isStove" v-model="isStove" />
      <label for="isStove">Stove</label>

      <input type="checkbox" id="isDoubleBed" v-model="isDoubleBed" />
      <label for="isDoubleBed">DoubleBed</label>

      <input type="checkbox" id="isBubblePool" v-model="isBubblePool" />
      <label for="isBubblePool">BubblePool</label>

      <input type="checkbox" id="isCycle" v-model="isCycle" />
      <label for="isCycle">Cycle</label>

      <input type="checkbox" id="isSauna" v-model="isSauna" />
      <label for="isSauna">Sauna</label>

      -->

      <button>Save Listing</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: null,
      description: "",
      price: null,
      available_start_date: "2020-10-10 13:00",
      available_end_date: "2021-10-10 15:00",
      // city: "",
      // addressListing: "",
      // listing: null,
      // isBathTub: false,
      // isParkingLot: false,
      // isStove: false,
      // isDoubleBed: false,
      // isBubblePool: false,
      // isCycle: false,
      // isSauna: false,
    };
  },

  computed: {},

  methods: {
    async addListing() {
      let newListing = {
        description: this.description,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        price: this.price,
      };

      // two thread at same session how to fix?
      // temp workaround : i made them into different methods and create it one by one
      await this.$store.dispatch("addListing", newListing);
      console.log("after await");
      this.addAddress();
    },

    async addAddress() {
      console.log("before address");
      console.log("currentListing.id " + this.$store.state.currentListing.id); //null
      console.log("currentListing " + this.$store.state.currentListing); //id
      let newAddress = {
        city: this.city,
        address: this.addressListing,
      };
      await this.$store.dispatch("addAddress", newAddress);
      this.addAmenity();
    },

    addAmenity() {
      let newAmenity = {
        bathTub: this.isBathTub,
        parkingLot: this.isParkingLot,
        stove: this.isStove,
        doubleBed: this.isDoubleBed,
        bubblePool: this.isBubblePool,
        cycle: this.isCycle,
        sauna: this.isSauna,
      };

      this.$store.dispatch("addAmenity", newAmenity);
    },
  },
};
</script>

<style scoped>
.add-listing-container {
  grid-column-start: 1;
  grid-row-start: 1;
  background-color: blueviolet;
}
</style>