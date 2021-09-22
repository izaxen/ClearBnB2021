<template>
 <DatePicker
 :attributes='attrs'
 color="purple"
  is-range
  :min-date='new Date()'
  />

</template>

<script>
import {DatePicker} from 'v-calendar'
export default {
  components: { DatePicker},
  data(){
    return{
      attrs: [
        {
          key: 'today',
          highlight: 'red',
          dates: new Date(),
        },
      ],
    }
  },

}
</script>

<style>

</style>