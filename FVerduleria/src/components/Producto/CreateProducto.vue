<template>
  <div class="popUp">
    <div class="popUp-content">
        <div class="close" @click="$emit('cloc')">
          &times;
        </div>
        <form @submit.prevent="crearProducto">
          <h3>Crear Nuevo Producto</h3>
          <table>
              <tr>
                  <td><label for="nombre">Nombre:</label></td>
                  <td><input type="text" id="nombre" v-model="nuevoProducto.nombre" required autocomplete="off"></td>
                  <td ><label for="unit">Unitario</label></td>
                  <td><input type="checkbox" id="unit" v-model="nuevoProducto.unit"></td>
              </tr>
              <tr>
                  <td><label for="precio">Precio:</label></td>
                  <td><input type="number" id="precio" v-model="nuevoProducto.precio" :min="0" step="0.01" required></td>
                  <td colspan="2"><button class="bot" type="submit">Registrar</button> </td>
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
      nuevoProducto: {
        nombre: '',
        precio: 0.0,
        unit: false,
      }
    }
  },
  methods: {
    crearProducto() {
      axios.post( 'http://localhost:8080/api/producto/create',this.nuevoProducto ).then(response => {
            this.$emit('cloc');
        }).catch(error => {
            console.log('Error: ', error.response.data);
        });
    }, 
  }
}
</script>

