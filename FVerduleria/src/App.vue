<script>
  import { RouterLink, RouterView } from 'vue-router'
  import axios from 'axios';
  export default {
    mounted(){
      this.$router.push('/');
    },
  }
</script>


<template>

  <RouterView/>
  
</template>


<style scoped>

</style>
