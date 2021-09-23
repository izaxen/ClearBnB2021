 <template>
 <div class = navi>

   <div v-if="!loggedInUser"></div>

  <div v-else class="user_details">
    <button v-on:click ="logoff">Logoff</button>

    <h3>Logged In as</h3>
    <h3>Firstname:{{user}}</h3>

  </div>

 </div>
  
</template>

<script>
export default {
  computed:{
    user(){
    return this.$store.state.user},
    loggedIn(){
      return this.$store.state.loggedInUser
    },

  },
  methods:{
    logoff(){
      this.$store.dispatch('logOff')
    }
  }

}
</script>

<style scoped>
.navi{
  border: 2px darkblue dotted;
  height: 150px;
  margin-bottom: 25px;
}

</style>