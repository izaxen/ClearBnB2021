<template>
  <div class="filter-listing-cont">
    <h1>Filters</h1>
    <form @submit.prevent="filterListings">
      <div class="filterFields">
        <input v-model="price" type="number" placeholder="Max price" />

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
      <button>Filter Listing</button>
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
      isCycle: null,
      isSauna: null,
      price: null,
      available_start_date: 1632393710000,
      available_end_date: 1632566510000,
    };
  },

  methods: {
    async filterListings() {
      console.log("Frontend Filter" + this.price);
      // if (this.price === null) {
      //   this.price = 99999;
      // }
      console.log(this.price);

      let newFilter = {
        price: this.price,
        availableStartDate: this.available_start_date,
        availableEndDate: this.available_end_date,
        bathTub: this.isBathTub,
        parkingLot: this.isParkingLot,
        stove: this.isStove,
        doubleBed: this.isDoubleBed,
        bubblePool: this.isBubblePool,
        cycle: this.isCycle,
        sauna: this.isSauna,
      };

      // if ((this.price = 99999)) {
      //   this.price = null;
      // }

      await this.$store.dispatch("getA", newFilter);
      console.log(this.isBathTub);
    },
  },
};
</script>

<style scoped>
</style>