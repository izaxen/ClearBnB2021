<template>
  <div class= "images">
    <input @change="addPictures" id="file-input" type="file" name="files" multiple>
  </div>
</template>

<script>
export default {
  data(){
    return{
      files: [],
      url:[]
    }
  },
  computed:{
    
  },
  methods:{
    addPictures(){
  this.files = document.querySelector('input[type=file]').files;
  let formData = new FormData();

  for(let file of this.files) {
  formData.append('files', file, file.name);
  //this.url.push(URL.createObjectURL(file))
  }
  this.$emit('formData', formData)
  
},
  }

}
</script>

<style>

</style>