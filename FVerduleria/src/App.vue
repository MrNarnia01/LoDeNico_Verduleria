<script>
  import { RouterLink, RouterView } from 'vue-router'
  import axios from 'axios';
  import Navbar from './components/Navbar.vue';
  export default {
    components:{
      Navbar,
    },
    mounted(){
      this.$router.push('/');
    },
  }
  
</script>


<template>
  <header>
    <nav>
      <ul>
        <Navbar/>
      </ul>
    </nav>
  </header>
  <div>
    <RouterView />
  </div>
  
</template>


<style scoped>

</style>
