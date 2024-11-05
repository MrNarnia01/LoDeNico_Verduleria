<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            pago:Object,
        },
        computed:{
            fechas(){
                console.log(this.pago)
                return moment(this.pago.fPago).format('DD/MM/YYYY');
            },
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/pago/delete/'+this.pago.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        }
    }
</script>

<template>
    <td>{{ pago.tipo }}</td>
    <td>{{ fechas }}</td>
    <td>{{ pago.monto }}</td>
    <td @click="$emit('mod')" class="bot">Modificar</td>
    <td @click="borrar()" class="bot">Eliminar</td>
</template>