<script setup>
</script>

<template>
  <div class="app">
    <header>
      <Navbar />
    </header>
    <main>
      <router-view :key="$route.fullPath" />
    </main>
  </div>
</template>

<script>
import { createHydrationRenderer } from "@vue/runtime-core";
import Navbar from "./views/Navbar.vue";

export default {
  components: {
    Navbar,
  },
  async created() {
    this.$store.dispatch("whoAmI");
  },
};
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
