<template>
<br>
<h3>Create a new user</h3>
<form @submit.prevent="addUser" ref="clearForm">
<input
v-model ="name"
required
type="text"
placeholder="Enter firstname"
/>
<input
v-model ="surName"
required
type="text"
placeholder="Enter surname"
/>
<input
v-model ="email"
required
type="text"
placeholder="Enter email"
/>
<input
v-model ="pw"
required
type="text"
placeholder="Enter password"
/>
<button>Create new account</button>
</form>

</template>

<script>



export default{

  data(){
    return{
      name:'',
      surName:'',
      email:'',
      pw:'',
    }
  },
  methods:{
    
    addUser(){
      let newUser={
        name: this.name,
        surName: this.surName,
        email: this.email,
        pw: this.pw,
      }
    
    this.$store.dispatch('registerUser', newUser)
    this.reset();
       },
    
  reset(){
    console.log("Inne");
    this.$refs.clearForm.reset();
    }
  },
}


</script>

<style scoped>
input{
  display: flex;
  flex-direction: column;
}
</style>
