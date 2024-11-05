<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            cierre:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/cierre/delete/'+this.cierre.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        },
        computed:{
            fechas(){
                return moment(this.cierre.fRegis).format('DD/MM/YYYY');
            },
            tipo(){
                console.log(this.cierre);
                if(this.cierre.tipo)    return "Efectivo";
                else return "Posnet";
            }
        },
    }
</script>


<template>
    <td>{{ tipo }}</td>
    <td>{{ cierre.monto }}</td>
    <td>{{ fechas }}</td>
    <td @click="$emit('mod')" class="bot">Modificar</td>
    <td @click="borrar()" class="bot">Borrar</td>
</template>