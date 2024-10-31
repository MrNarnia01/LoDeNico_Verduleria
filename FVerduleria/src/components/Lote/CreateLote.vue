<template>
    <div class="popUp">
        <div class="popUp-content">
      <span class="close" @click="$emit('cloc')">&times;</span>
      
      <form @submit.prevent="crearLote">
          <table>
              <tr>
                  <th colspan="4">
                      Crear Nuevo Lote
                  </th>
              </tr>
              <tr>
                <td><label for="id">Producto Id:</label></td>
                <td><input type="number" id="id" v-model="nuevoLote.idP" readonly></td>
                <!--
                <td><label for="fVenci">Fecha de Vencimiento:</label></td>
                <td><input type="date" id="fVenci" v-model="nuevoLote.date" :min="fHoy"></td>
                -->
              </tr>
              <tr>
                  <td><label for="nLote">Numero de Lote:</label></td>
                  <td><input type="number" id="nLote" v-model="nuevoLote.num" :min="1" required></td>
                    <!--
                  <td colspan="2"> <button type="submit" class="bot">Registrar</button> </td>
                  -->
              </tr>
              <tr>
                <td><label for="fVenci">Fecha de Vencimiento:</label></td>
                <td><input type="date" id="fVenci" v-model="nuevoLote.date" :min="fHoy"></td>
              </tr>
              <tr>
                <td colspan="2"> <button type="submit" id="nor" class="bot">Registrar</button> </td>
              </tr>

          </table>
      </form>
    </div>
</div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: {
            id:Object,
        },
    data() {
        return {
            nuevoLote: {
                idP:0,
                num:1,
                date: new Date(),
            },
            fHoy: '',
        }
    },
    mounted(){
        const today = new Date();
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day = day < 10 ? '0' + day : day;
                this.fHoy = `${year}-${month}-${day}`;
        
        console.log('producto: '+this.id)
        this.nuevoLote.idP=this.id;
    },
    methods: {
        crearLote() {
            console.log(this.nuevoLote)
        axios.post( 'http://localhost:8080/api/lote/create',this.nuevoLote ).then(response => {
                this.$emit('cloc');
            }).catch(error => {
                console.log('Error: ', error.response.data);
            });
        }, 
    }
    }
</script>

