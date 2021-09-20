import { createStore } from 'vuex'

export const store = createStore({

  // state is where we store reactive variables
  // this.$store.state.nameOfVariable
  state() {
    return {
      user: null
      
    }
  },
  // we cannot update state directly, so we use mutation methods to do that
  // this.$store.commit('nameOfMutation', data)
  mutations: {
   // setUser(state, user) {
     // state.user = user
     // state.failedLogIn = false
    
    },

    //async methods that will trigger a mutation
    // this.$store.dispatch('nameOfAction')
  actions: {
      
    async registerUser(store, user) {
      let res = await fetch('/api/registerUser', {
      method: 'POST',
      body: JSON.stringify(user),
      })

      },

      async registerUser1(store, user) {
        let res = await fetch('/api/registerUser', {
          method: 'POST',
          body: JSON.stringify(user),
        })

        let loggedInUser = await res.json()
        console.log(loggedinUser);
        if ('error' in loggedInUser) {
          this.state.failedLogIn = true
          return
        }
        store.commit('setUser', loggedInUser)
      },
      async test() {
      let res = await fetch('/api/')
      console.log("test was fired from store!");
      console.log(await res.json());
    },
    
    }
  })


