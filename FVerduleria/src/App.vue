<script>
  import { RouterLink, RouterView } from 'vue-router'
  import axios from 'axios';
  import Navbar from './components/Navbar.vue';
  import '@/assets/main.css';
  export default {
    components:{
      Navbar,
    },
    data(){
        return{
            navB:false,
        };
    },
    mounted(){
      this.cerrar();
    },
    methods: {
      iniciar(){
        this.navB=true;
        this.$router.push('/');
      },
      cerrar(){
        this.navB=false;
        this.$router.push('/signUp');
      }
    },
  }
  
</script>


<template>
  <header v-if="navB">
    <nav>
      <ul>
        <Navbar @cerrar="cerrar()" />
      </ul>
    </nav>
  </header>
  <div>
    <RouterView @signUp="iniciar()" />
  </div>
  
</template>

