<script>
    import axios from 'axios'
    export default {
        props: {
            horario:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/horario/delete/'+this.horario.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        }
    }
</script>

<template>
    <td>{{ horario.entrada }}</td>
    <td>{{ horario.salida }}</td>
    <td> <button type="button" @click="$emit('m')"> Modificar </button> </td>
    <td> <button type="button" @click="borrar()"> Borrar </button> </td>
</template>