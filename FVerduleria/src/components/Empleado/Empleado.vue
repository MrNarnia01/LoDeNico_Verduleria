<script>
    import axios from 'axios'
    export default {
        props: {
            empleado:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/empleado/delete/'+this.empleado.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            ver(){
                this.$router.push({
                    name: 'horario',
                    query: { id:this.empleado.id }
                });
                
            }
        },
        computed:{
            horarios(){
                return this.empleado.horarioResponseList.length;
            },
        },
    }
</script>


<template>
    <td>{{ empleado.nombre }}  {{ empleado.apellido }}</td>
    <td>{{ empleado.codArea }}-{{ empleado.tel }}</td>
    <td>{{ empleado.mail }}</td>
    <td>{{ horarios }}</td>
    <td @click="ver()" class="bot">Ver Horarios</td>
    <td @click="$emit('mod')" class="bot">Modificar</td>
    <td v-if="this.horarios<1" @click="borrar()" class="bot">Borrar</td>
    <td v-else class="nBot">Borrar</td>
</template>