<template>
    <div class="popUp">
        <div class="popUp-content">
      <div class="close" @click="$emit('cloc')">&times;</div>
      
      <form @submit.prevent="crearHorario">
            <h3>
              Crear Nuevo Horario
          </h3>
          <table>
              <tr>
                  
              </tr>
              <tr>
                <td>Nombre del empleado:</td>
                <td>{{ nombre }}</td>
              </tr>
              <tr>
                <td>Dia:</td>
                <td>
                    <select v-model="nuevoHorario.dia" required>
                        <option value="Lunes" selected>Lunes</option>
                        <option value="Martes">Martes</option>
                        <option value="Miercoles">Miercoles</option>
                        <option value="Jueves">Jueves</option>
                        <option value="Viernes">Viernes</option>
                        <option value="Sabado">Sabado</option>
                        <option value="Domingo">Domingo</option>
                    </select>
                  </td>
              </tr>
              <tr>
                  <td><label for="hEn">Horario de entrada:</label></td>
                  <td><input type="time" id="hEn" v-model="nuevoHorario.entrada" required></td>
              </tr>
              <tr>
                <td><label for="hSa">Horario de salida:</label></td>
                <td v-if="this.nuevoHorario.entrada==''"><input type="time" readonly></td>
                <td v-else>
                <input  type="time" id="hSa" v-model="nuevoHorario.salida" :min="nuevoHorario.entrada" required>
                </td>
            </tr>
              <tr>
                  <td colspan="2"> <button type="submit" class="bot" id="nor">Registrar</button> </td>
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
            nombre: Object,
        },
    data() {
        return {
            nuevoHorario: {
                idE:0,
                dia:'',
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

