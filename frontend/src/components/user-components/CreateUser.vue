<template>
  <div class="bla">
    <h3>Create a new user</h3>
    <form @submit.prevent="addUser" ref="clearForm">
      <input
        v-model="firstName"
        required
        type="text"
        placeholder="Enter firstname"
      />
      <input
        v-model="surName"
        required
        type="text"
        placeholder="Enter surname"
      />
      <input v-model="email" required type="text" placeholder="Enter email" />
      <input v-model="pw" required type="text" placeholder="Enter password" />
      <input
        v-model="bankAddress"
        required
        type="text"
        placeholder="Enter bank address"
      />
      <button>Create new account</button>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstName: "",
      surName: "",
      email: "",
      pw: "",
      bankAddress: "",
    };
  },
  methods: {
    async addUser() {
      let newUser = {
        firstName: this.firstName,
        surName: this.surName,
        email: this.email,
        pw: this.pw,
      };

      await this.$store.dispatch("registerUser", newUser);
      this.addBank();
    },

    async addBank() {
      let newBank = {
        bankAddress: this.bankAddress,
      };

      console.log(newBank);
      await this.$store.dispatch("addBank", newBank);
      this.reset();
    },

    reset() {
      this.$refs.clearForm.reset();
    },
  },
};
</script>

<style scoped>
.bla {
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid violet;
  margin: 30px;
}

input {
  display: flex;
  flex-direction: column;
  margin: 5px;
}

button {
  margin-bottom: 10px;
}
</style>
