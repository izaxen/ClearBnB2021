<template>
  <div class="filter-listing-cont">
    <h1>Filters</h1>
    <form @submit.prevent="filterListings">
      <div class="filterFields">
        <div class="price">
          <input v-model="price" type="number" placeholder="Max price" />
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

        <div class="amenity">
          <p>Choose anemities</p>
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

          <input
            type="checkbox"
            id="isBicycle
"
            v-model="isBicycle"
          />
          <label
            for="isBicycle
"
            >BiCycle</label
          >

          <input type="checkbox" id="isSauna" v-model="isSauna" />
          <label for="isSauna">Sauna</label>
        </div>
      </div>
      <button>Find My Dream House!</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      isBathTub: null,
      isParkingLot: null,
      isStove: null,
      isDoubleBed: null,
      isBubblePool: null,
      isBicycle: null,
      isSauna: null,
      price: null,
      available_start_date: this.available_start_date,
      available_end_date: this.available_end_date,
      matchedListings: [],
    };
  },

  methods: {
    async filterListings() {

      let newFilter = {
        price: this.price,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        bathTub: this.isBathTub,
        parkingLot: this.isParkingLot,
        stove: this.isStove,
        doubleBed: this.isDoubleBed,
        bubblePool: this.isBubblePool,
        bicycle: this.isBicycle,
        sauna: this.isSauna,
      };

      this.matchedListings = await this.$store.dispatch(
        "getFilteredListing",
        newFilter
      );
      console.log("inFilter " + this.available_start_date);
      this.$emit("updatelist", this.matchedListings);
    },
  },
};
</script>

<style scoped>
</style>