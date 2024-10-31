<template>
    <div class="popUp">
      <div class="popUp-content">
      <span class="close" @click="$emit('cloc')">&times;</span>
      
        <form @submit.prevent="crearProveedor">
          <h3>Crear Nuevo Proveedor</h3>  
          <table>
                <tr>
                    <td><label for="negocio">Negocio:</label></td>
                    <td><input type="text" id="negocio" v-model="nuevoProveedor.negocio" required autocomplete="off"></td>
                </tr>
                <tr>
                    <td><label for="calle">Calle:</label></td>
                    <td><input type="text" id="calle" v-model="nuevoProveedor.calle" required></td>
                </tr>
                <tr>
                    <td><label for="altura">Altura:</label></td>
                    <td><input type="number" id="altura" v-model="nuevoProveedor.altura" :min="0" required></td>
                </tr>
                <tr>
                    <td colspan="2"> <button type="submit" class="bot" id="nor">Crear</button> </td>
                </tr>

            </table>
        </form>
      </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      nuevoProveedor: {
        negocio: '',
        calle: '',
        altura: 0,
      }
    }
  },
  methods: {
    crearProveedor() {
      axios.post( 'http://localhost:8080/api/proveedor/create',this.nuevoProveedor ).then(response => {
            this.$emit('cloc');
        }).catch(error => {
            console.log('Error: ', error.response.data);
        });
    }, 
  }
}
</script>

