<template>
    <div class="modal-content">
      <span class="close" @click="$emit('cloc')">&times;</span>
      
      <form @submit.prevent="crearHorario">
          <table>
              <tr>
                  <th colspan="2">
                      Crear Nuevo Horario
                  </th>
              </tr>
              <tr>
                <td>Nombre del empleado:</td>
                <td>{{ nombre }}</td>
              </tr>
              <tr>
                  <td><label for="hEn">Horario de entrada:</label></td>
                  <td><input type="time" id="hEn" v-model="nuevoHorario.entrada" required></td>
              </tr>
              <tr>
                <label for="hSa">Horario de salida:</label>
                <input v-if="this.nuevoHorario.entrada==''" type="time" readonly>
                <input v-else type="time" id="hSa" v-model="nuevoHorario.salida" :min="nuevoHorario.entrada" required>
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
        props: {
            id:Object,
            nombre: Object,
        },
    data() {
        return {
            nuevoHorario: {
                idE:0,
                entrada:'',
                salida:'',
            },
        }
    },
    mounted(){
        this.nuevoHorario.idE=this.id;
    },
    methods: {
        crearHorario() {
            console.log(this.nuevoHorario)
        axios.post( 'http://localhost:8080/api/horario/create',this.nuevoHorario ).then(response => {
                this.$emit('cloc');
            }).catch(error => {
                console.log('Error: ', error.response.data);
            });
        }, 
    }
    }
</script>

