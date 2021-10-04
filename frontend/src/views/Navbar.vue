 <template>
  <div class="navi">
    <router-link @click.native ="reset" to="/">Home</router-link>

    <div v-if="!user" class="reg-login">
      <div class="createU">
        <router-link to="/reg-user">Registera user</router-link>
      </div>
      <Login />
    </div>

    <div v-else class="loggedIn">
      <div class="links">
        <router-link to="/reg-list">Lägg till ny bostad</router-link>
        <router-link
          :to="{
            path: `/profile_page/${this.$store.state.user.id}`,
          }"
          >My pages</router-link
        >
      </div>
      <div class="logoff">
        <button v-on:click="logoff">Logoff</button>

        <h3>Logged In as</h3>
        <h3>Firstname:{{ user.firstName }}</h3>
      </div>
    </div>
  </div>
</template>

<script>
import Login from "../components/user-components/LogIn.vue";
import router from "../router";

export default {
  components: {
    Login,
  },

  computed: {
    user() {
      return this.$store.state.user;
    },
    //loggedIn(){
    // return this.$store.state.loggedInUser
    //},
  },
  methods: {
    logoff() {
      this.$store.dispatch("logOff");
      router.push("/");
    },
    reset(){
      this.$store.commit('setListing', null);
    }
  },
};
</script>

<style scoped>
.createU {
  margin-right: 30px;
}

.reg-login {
  display: flex;
  align-items: center;
}
.navi {
  border: 2px darkblue dotted;
  height: 150px;
  padding: 15px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}

.loggedIn {
  display: flex;
  flex-direction: row;
}

.links {
  display: flex;
  flex-direction: column;
}
</style>