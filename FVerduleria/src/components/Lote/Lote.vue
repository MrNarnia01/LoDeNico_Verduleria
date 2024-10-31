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
        },
        computed:{
            fechas(){
                return moment(this.lote.fVenci).format('DD/MM/YYYY');
            },
        },
    }
</script>


<template>
    <td>{{ lote.nombre }}</td>
    <td>{{ lote.nlote }}</td>
    <td>{{ fechas }}</td>
    <td class="bot" @click="borrar()">  Borrar</td>
</template>