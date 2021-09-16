import { createStore } from 'vuex'

export const store = createStore({

  // state is where we store reactive variables
  // this.$store.state.nameOfVariable
  state() {
    return {

    }
  },
  // we cannot update state directly, so we use mutation methods to do that
  // this.$store.commit('nameOfMutation', data)
  mutations: {

  },

  //async methods that will trigger a mutation
  // this.$store.dispatch('nameOfAction')
  actions: {

    async test() {
      let res = await fetch('/api/')
      console.log(await res.json());
    }

  }
})
