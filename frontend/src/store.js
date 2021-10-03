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
    setCurrentListing(state, listing) {
      state.currentListing = listing
    },

    setUserLoggedIn(state, status) {
      state.loggedInUser = status
    },

    // setAllListingsDTO(state, allListingsDTO) {
    //   state.allListingsDTO = allListingsDTO;
    // }
  },

  //async methods that will trigger a mutation
  // this.$store.dispatch('nameOfAction')
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
      let res = await fetch('/api/logOff')
      store.commit('setUser', null)
      let userResponse = await res.json()
    },

    async addListing(store, listing) {
      let res = await fetch('/api/listing', {
        method: 'POST',
        body: JSON.stringify(listing)
      });
      let currentListingId = await res.json();
      store.commit('setCurrentListing', currentListingId); // an id
    },

    async updateListing(store, listing) {
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

    async getFileUrl(store, id) {
      let loadPath = '/api/uploads/' + id
      let res = await fetch(loadPath)
      let fileList = await res.json()
      store.commit('getImageList', fileList)
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
  }
})


