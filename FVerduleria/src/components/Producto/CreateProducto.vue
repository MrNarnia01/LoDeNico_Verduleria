<template>
    <div class="modal-content">
      <span class="close" @click="$emit('cloc')">&times;</span>
      
      <form @submit.prevent="crearProducto">
          <table>
              <tr>
                  <th colspan="2">
                      Crear Nuevo Producto
                  </th>
              </tr>
              <tr>
                  <td><label for="nombre">Nombre:</label></td>
                  <td><input type="text" id="nombre" v-model="nuevoProducto.nombre" required autocomplete="off"></td>
              </tr>
              <tr>
                  <td><label for="precio">Precio:</label></td>
                  <td><input type="number" id="precio" v-model="nuevoProducto.precio" :min="0" step="0.01" required></td>
              </tr>
              <tr>
                <td><label for="unit">Unitario:</label></td>
                <td><input type="checkbox" id="unit" v-model="nuevoProducto.unit"></td>
              </tr>
              <tr>
                  <td colspan="2"> <button type="submit">Crear</button> </td>
              </tr>

          </table>
      </form>
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

