import { createStore } from 'vuex'

export const store = createStore({

  // state is where we store reactive variables
  // this.$store.state.nameOfVariable
  state() {
    return {
      user: null,
      failedLogIn: false,
      currentListing: null,
      loggedInUser: false,
      currentListingOldVersion: null,
      listing: null,
      // allListingsDTO: [],

    }
  },
  // we cannot update state directly, so we use mutation methods to do that
  // this.$store.commit('nameOfMutation', data)
  mutations: {
    setUser(state, user) {
      state.user = user
      state.setUserLoggedIn = true
    },
    setFailedLogin(state, value) {
      state.failedLogIn = value
    },


    setUserLoggedIn(state, status) {
      state.loggedInUser = status
    },

    setCurrentListing(state, listing) {
      state.currentListing = listing
    },

    setCurrentListingOldVersions(state, listing) {
      state.currentListingOldVersion = listing
    },
    setListing(state, listing) {
      state.listing = listing
    },
  },
  actions: {

    async registerUser(store, user) {
      let res = await fetch('/api/registerUser', {
        method: 'POST',
        body: JSON.stringify(user),
      })
      let loggedInUser = await res.json()
      if (loggedInUser === null) {
        console.log("Failed to register account email not valid");
        this.state.failedLogIn = true
        return
      }
      store.commit('setUser', loggedInUser)
    },

    async loginUser(store, details) {
      let res = await fetch('/api/login', {
        method: 'POST',
        body: JSON.stringify(details)
      })
      let loggedInUser = await res.json()
      if ('Error' in loggedInUser) {
        console.log("Login failed");
        this.state.failedLogIn = true;
        return
      }
      console.log("Login active", loggedInUser);
      store.commit('setUser', loggedInUser)

    },
    async whoAmI(store) {
      let res = await fetch('/api/whoAmI')
      let status = await res.json()
      store.commit('setUser', status)
    },

    async logOff(store) {
      let res = await fetch('/rest/logOff')
      store.commit('setUser', null)
      let userResponse = await res.json()
    },

    async addListing(store, listing) {
      let res = await fetch('/api/listing', {
        method: 'POST',
        body: JSON.stringify(listing)
      });
      //let currentListingId = await res.json();
      //store.commit('setCurrentListing', currentListingId); // an id
    },

    async updateListing(store, listing) {
      console.log('updatelisting', listing);
      await fetch('/api/listing', {
        method: 'PUT',
        body: JSON.stringify(listing)
      });
    },

    async addAddress(store, address) {
      await fetch('/api/address', {
        method: 'POST',
        body: JSON.stringify(address)
      })
    },

    async updateAddress(store, address) {
      console.log('Update adress',address );
      await fetch('/api/address', {
        method: 'PUT',
        body: JSON.stringify(address)
      })
    },

    async addAmenity(store, amenity) {
      await fetch('/api/amenity', {
        method: 'POST',
        body: JSON.stringify(amenity)
      })
    },
    async updateAmenity(store, amenity) {
      console.log("Update Amenti", amenity)
      await fetch('/api/amenity', {
        method: 'PUT',
        body: JSON.stringify(amenity)
      })
    },

    async addBank(store, bank) {
      await fetch('/api/bank', {
        method: 'POST',
        body: JSON.stringify(bank)
      })
    },

    async updateBank(store, bank) {
      await fetch('/api/bank', {
        method: 'PUT',
        body: JSON.stringify(bank)
      })
    },

    async getFilteredListing(store, filters) {
      let res = await fetch('/api/filteredListings', {
        method: 'POST',
        body: JSON.stringify(filters)
      })
      let matchedListings = await res.json();
      console.log(matchedListings);
      
      return matchedListings;
    },

    async uploadFiles(store, formData) {
      console.log('Upload', formData.getAll('files'))
      let savePath = '/api/uploads/'
      await fetch(savePath, {
        method: 'POST',
        body: formData,
      })
    },

    async getSingleListing(store, id) {
      let res = await fetch('/rest/getSingleListing/' + id)
      let a = await res.json()
      store.commit('setListing', a);
      console.log(a);
      return a
    },

    async getAllRatings(store, userID) {
      let res = await fetch(`/rest/rating/` + userID);
      return await res.json();
    },

    async getAvgRating(store, userID) {
      let res = await fetch(`/rest/avg-rating/` + userID);
      let x = (await res.json()) + "";
      return x.substring(0, 3);
    },

    async deleteRating(store, ratingID) {
      let res = await fetch(`/api/delete-rating`, {
        method: "DELETE",
        body: JSON.stringify({ id: ratingID }),
      });
    },

    async getListingsInSummary(store, userID) {
      let res = await fetch(`/rest/${userID}/listings`);
      return await res.json();
    },



    // async getAllListingsDTO(_) {
    //   let res = await fetch('/rest/getAllListingsDTO', {
    //     method: 'GET',
    //     body: JSON.stringify()
    //   })
    //   let getAllListingsDTO = await res.json();
    //   console.log("res ");
    //   store.commit('setAllListingsDTO', getAllListingsDTO)
    // },
    async getSingleListingVersion(store, id) {
      console.log("Startar get singöle");
      let res = await fetch('/rest/getSingleListingVersion/' + id)
      let a = await res.json()
      store.commit('setCurrentListingOldVersions', a)
      return a
    },

    async getRatingsToFill() {
      let res = await fetch("/api/check-if-there-is-ratings-to-fill/");
      return await res.json();
    },

  }
})


