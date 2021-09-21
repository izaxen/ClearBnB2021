<template>
<div class="hmm">
<h2>Login</h2>
<form @submit.prevent="login" ref="clearForm">
  <input v-model="userName"
    required type ="text"
    placeholder="Enter email"/>
<input v-model="pw"
required type="password"
placeholder="Enter password"/>
<button>Login</button>
</form>
</div>
</template>
<script>
export default {
data(){
  return{
    userName:"",
    pw:"",
  }
},

methods:{
  login(){
  let userDetail ={
    email: this.userName,
    pw: this.pw,
  }
  this.$store.dispatch("loginUser",userDetail);
  this.reset()
  },
  reset(){
    this.$refs.clearForm.reset()
  }
}

};
</script>

<style scoped>
.hmm{
  display: flex;
  flex-direction: column;
  align-items: center;
  border: 1px solid violet;
  margin: 30px;
}

input{
  display: flex;
  flex-direction: column;
  margin: 5px;
}
button{
  margin-bottom: 10px;
}

</style>