import { createRouter, createWebHistory } from 'vue-router'
//Import views

import Home from './views/Home.vue'
import Test from './views/Test.vue'


export default createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      component: Home,
      name: Home,
    },

    {
      path: '/test',
      component: Test,
      name: Test,
    }
    
  ]
})