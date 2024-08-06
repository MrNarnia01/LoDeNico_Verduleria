<script >
  import { RouterLink, RouterView } from 'vue-router'
  import axios from 'axios';
  export default {
    data(){
      return{
        b: false,
      };
    },
  }


</script>


<template>
  <header>
    <div v-if="b"> 
      <nav>
        <ul>
          <li>Sin sesion activa
          </li>
        </ul>
      </nav>
    </div>
    <div v-else>
          <nav>
            <ul>
              <li> <RouterLink to="/"> Home </RouterLink> </li>
              <li> <RouterLink to="/"> Producto </RouterLink> </li>
              <li> <RouterLink to="/"> Pedido </RouterLink> </li>
              <li> <RouterLink to="/"> Boleta </RouterLink> </li>
            </ul>
          </nav>
    </div>
  </header>

  <RouterView/>
  
</template>


<style scoped>

</style>
