<template>
    <div class="modal-content">
      <span class="close" @click="$emit('clou')">&times;</span>
      
        <form @submit.prevent="crearProveedor">
            <table>
                <tr>
                    <th colspan="2">
                        Modificar Proveedor
                    </th>
                </tr>
                <tr>
                    <td><label for="negocio">Negocio:</label></td>
                    <td><input type="text" id="negocio" v-model="nuevoProveedor.negocio" required autocomplete="off"></td>
                </tr>
                <tr>
                    <td><label for="calle">calle:</label></td>
                    <td><input type="text" id="calle" v-model="nuevoProveedor.calle" required></td>
                </tr>
                <tr>
                    <td><label for="altura">Altura:</label></td>
                    <td><input type="number" id="altura" v-model="nuevoProveedor.altura" :min="0" required></td>
                </tr>
                <tr>
                    <td colspan="2"> <button type="submit">Modificar</button> </td>
                </tr>

            </table>
        </form>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    props: ['proveedor'],
  data() {
    return {
      nuevoProveedor: {
        negocio: '',
        calle: '',
        altura: 0,
      }
    }
  },
  mounted(){
    this.nuevoProveedor.negocio = this.proveedor.negocio;
    this.nuevoProveedor.calle = this.proveedor.calle;
    this.nuevoProveedor.altura = this.proveedor.altura;
  },
  methods: {
    crearProveedor() {
        axios.post( 'http://localhost:8080/api/proveedor/update/'+this.proveedor.id,this.nuevoProveedor ).then(response => {
            this.$emit('clou');
        }).catch(error => {
            console.log('Error: ', error.response.data);
        });
    }, 
    }, 
  }

</script>

