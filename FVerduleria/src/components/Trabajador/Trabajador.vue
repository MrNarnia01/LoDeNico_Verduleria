<script>
    import axios from 'axios'
    export default {
        props: {
            trabajador:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/trabajador/delete/'+this.trabajador.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        }
    }
</script>


<template>
    <td>{{ trabajador.puesto }}</td>
    <td>{{ trabajador.nombre }}  {{ trabajador.apellido }}</td>
    <td>{{ trabajador.codArea }}-{{ trabajador.tel }}</td>
    <td @click="$emit('m')" class="bot"> Modificar </td>
    <td @click="borrar()" class="bot"> Borrar </td>
</template>