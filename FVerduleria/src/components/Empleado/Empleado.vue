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
    <td><button type="button" @click="$emit('mod')">Modificar</button></td>
    <td v-if="this.horarios<1"> <button type="button" @click="borrar()"> Borrar </button> </td>
    <td v-else>-</td>
</template>