<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            lote:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/lote/delete/'+this.lote.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            formatoFechas(fecha){
                return moment(fecha).format('DD/MM/YYYY');
            }
        }
    }
</script>


<template>
    <td>{{ lote.nombre }}</td>
    <td>{{ lote.nlote }}</td>
    <td>{{ formatoFechas(lote.fVenci) }}</td>
    <td> <button type="button" @click="borrar()"> Borrar </button> </td>
</template>