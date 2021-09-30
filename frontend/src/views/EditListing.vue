<template>
  <div class="add-listing-container">
    <h1>Edit listing</h1>

    <div class="currentListing"></div>
    <form v-if="initialData" @submit.prevent="addListing">
      <div class="inputFields">
        <input
          :value="initialData.description"
          type="text"
          @change="(ev) => (changedList.description = ev.target.value)"
        />

        <input
          :value="initialData.price"
          type="number"
          @change="(ev) => (changedList.price = ev.target.value)"
        />

        <input
          :value="initialData.city"
          type="text"
          @change="(ev) => (changedAddress.city = ev.target.value)"
        />

        <input
          :value="initialData.addressListing"
          type="text"
          @change="(ev) => (changedAddress.addressListing = ev.target.value)"
        />
      </div>
      <br />
      <div class="amen">
        <p>Choose amenities</p>

        <div v-if="initialData.bathTb">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.bathTub = ev.target.checked)"
          />
          <label>Bathtub</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.bathTub = ev.target.checked)"
          />
          <label>Bathtub</label>
        </div>

        <div v-if="initialData.parkingLot">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.parkingLot = ev.target.checked)"
          />
          <label>Parking lot</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.parkingLot = ev.target.checked)"
          />
          <label>Parking lot</label>
        </div>

        <div v-if="initialData.stove">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.stove = ev.target.checked)"
          />
          <label>Stove</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.stove = ev.target.checked)"
          />
          <label>Stove</label>
        </div>

        <div v-if="initialData.doubleBed">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.doubleBed = ev.target.checked)"
          />
          <label>Double bed</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.doubleBed = ev.target.checked)"
          />
          <label>Double bed</label>
        </div>

        <div v-if="initialData.bubblePool">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.bubblePool = ev.target.checked)"
          />
          <label>Bubble pool</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.bubblePool = ev.target.checked)"
          />
          <label for="isBathTub">BathTub</label>
        </div>

        <div v-if="initialData.bicycle">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.bicycle = ev.target.checked)"
          />
          <label>Bicycle</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.bicycle = ev.target.checked)"
          />
          <label>Bicycle</label>
        </div>

        <div v-if="initialData.sauna">
          <input
            type="checkbox"
            checked
            @change="(ev) => (changedAmenity.sauna = ev.target.checked)"
          />
          <label>Sauna</label>
        </div>
        <div v-else>
          <input
            type="checkbox"
            @change="(ev) => (changedAmenity.sauna = ev.target.checked)"
          />
          <label>Sauna</label>
        </div>
      </div>

      <div class="date">
        <div class="start">
          <label for="startDate">Select Start Date</label>
          <input
            :value="
              new Date(initialData.availableStartDate)
                .toISOString()
                .split('T')[0]
            "
            type="date"
            @change="(ev) => (changedList.availableStartDate = ev.target.value)"
          />
        </div>
        <div class="end">
          <label for="endDate">Select End Date</label>
          <input
            :value="
              new Date(initialData.availableEndDate).toISOString().split('T')[0]
            "
            type="date"
            :min="new Date().toISOString().split('T')[0]"
            @change="(ev) => (changedList.availableEndDate = ev.target.value)"
          />
        </div>
      </div>
      <br />
      <div class="images">
        <label>Choose images</label>
        <AddImages @formData="LoadFormData" />
        <br />
      </div>
      <br />
      <button>Save Listing</button>
    </form>
    <button type="button" v-on:click="resetPage">Abort changes</button>
  </div>
</template>

<script>
import AddImages from "../components/listing-components/AddImages.vue";

export default {
  components: {
    AddImages,
  },
  data() {
    return {
      changedList: {},
      changedAmenity: {},
      changedAddress: {},
      formData: [],
    };
  },

  computed: {
    initialData() {
      return this.$store.state.currentListing;
    },
  },

  methods: {
    async addListing() {
      await this.$store.dispatch("updateListing", {
        ...this.changedList,
        id: this.initialData.id,
      });
      this.addAddress();
    },

    async addAddress() {
      console.log("this adress", this.changedAddress);
      await this.$store.dispatch("updateAddress", this.changedAddress);
      this.addAmenity();
    },

    async addAmenity() {
      console.log("this amenty", this.changedAmenity);
      await this.$store.dispatch("updateAmenity", this.changedAmenity);
      this.addImages();
    },
    addImages() {
      let fd = this.formData.getAll("files");
      if (fd != null) {
        this.$store.dispatch("uploadFiles", this.formData);
      }
    },

    LoadFormData(formData) {
      this.formData = formData;
    },
    resetPage() {
      console.log("Reset page");
      let a = true;
      this.$emit("closeEdit", a);
    },
  },
};
</script>

<style scoped>
.amen {
  display: flex;
  flex-direction: column;
  align-content: center;
}
.images {
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

input {
  margin: 5px;
}
.add-listing-container {
  align-self: center;
  max-width: 60vw;
  grid-column-start: 1;
  grid-row-start: 1;
  background-color: rgb(216, 202, 230);
}

label {
  margin-right: 15px;
}
</style>