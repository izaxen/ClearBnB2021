 <template>
 <div class = navi>

   <div v-if="!user"></div>

  <div v-else class="user_details">
    <button v-on:click ="logoff">Logoff</button>

    <h3>Logged In as</h3>
    <h3>Firstname:{{user.firstName}}</h3>

  </div>




 </div>

  
</template>

<script>


export default {
components:{

},

  computed:{
    user(){
    return this.$store.state.user}

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