<template>
  <div class="add-listing-container">
    <h1>Add listing container</h1>
    <form @submit.prevent="addListing">
      <div class="inputFields">
        <input
          v-model="description"
          required
          type="text"
          placeholder="Enter description here"
        />

        <input
          v-model="price"
          type="number"
          required
          placeholder="Enter price here"
        />

        <input
          v-model="city"
          type="text"
          required
          placeholder="Enter city here"
        />

        <input
          v-model="addressListing"
          required
          type="text"
          placeholder="Enter address here"
        />
      </div>
      <br />
      <div class="amen">
        <p>Choose amenities</p>

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
      </div>

      <div class="date">
        <div class="start">
          <label for="startDate">Select Start Date</label>
          <input
            v-model="available_start_date"
            id="startDate"
            required
            type="date"
            placeholder="Start Date"
            min="date()"
          />
        </div>
        <div class="end">
          <label for="endDate">Select End Date</label>
          <input
            v-model="available_end_date"
            id="endDate"
            required
            type="date"
            placeholder="End Date"
            min="date()"
          />
        </div>
      </div>

      <br />

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
      available_start_date: null,
      available_end_date: null,
      isBathTub: false,
      isParkingLot: false,
      isStove: false,
      isDoubleBed: false,
      isBubblePool: false,
      isCycle: false,
      isSauna: false,
      city: null,
      addressListing: null,
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
      this.addAddress();
    },

    async addAddress() {
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
        cycle: this.isCycle,
        sauna: this.isSauna,
      };

      await this.$store.dispatch("addAmenity", newAmenity);
    },
  },
};
</script>

<style scoped>
.add-listing-container {
  display: flex;
  flex-direction: column;
}
.inputFields {
  display: flex;
  flex-direction: column;
}

.add-listing-container {
  grid-column-start: 1;
  grid-row-start: 1;
  background-color: blueviolet;
}
</style>