import { createRouter, createWebHistory } from 'vue-router'

import Home from './views/Home.vue'
import RegisterUser from './components/user-components/CreateUser.vue'
import RegisterListing from "./components/listing-components/AddListing.vue";
import ProfilePage from './views/ProfilePage.vue';


export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Home,
      name: Home,
    },

    {
      path: '/reg-User',
      component: RegisterUser,
      name: RegisterUser,
    },

    {
      path: '/reg-list',
      component: RegisterListing,
      name: RegisterListing,
    },
    {
      path: '/profile_page',
      component: ProfilePage,
      name: ProfilePage,
    },
    {
      path: '/profile_page/:id',
      component: ProfilePage,
      name: ProfilePage,
    },


  ]
})