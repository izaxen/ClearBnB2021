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
      uploadedImages: this.$store.state.uploadedImages,
      url:[]
    }
  },
  computed:{
    imageFolder: function(){
      return this.$store.state.uploadedImages
    } 
  },
  methods:{
    addPictures(){
  this.files = document.querySelector('input[type=file]').files;
  let formData = new FormData();

  for(let file of this.files) {
  formData.append('files', file, file.name);
  this.url.push(URL.createObjectURL(file))
  }
  this.$emit('formData', formData)
  this.$store.commit('addUploadedImages', this.files) 
},
  }

}
</script>

<style>

</style>