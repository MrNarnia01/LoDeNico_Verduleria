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
    <td>{{ horario.dia }}</td>
    <td>{{ horario.entrada }}</td>
    <td>{{ horario.salida }}</td>
    <td @click="$emit('m')" class="bot">Modificar</td>
    <td @click="borrar()" class="bot">Borrar </td>
</template>