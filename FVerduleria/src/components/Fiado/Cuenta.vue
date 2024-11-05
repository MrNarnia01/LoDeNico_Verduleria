<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            cuenta:Object,
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/cuenta/delete/'+this.cuenta.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            pagar(){
                axios.post( 'http://localhost:8080/api/cuenta/update/f/'+this.cuenta.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            pago(){
                if(moment(this.cuenta.fregistro).format('DD/MM/YYYY HH:mm:ss')==moment(this.cuenta.fpago).format('DD/MM/YYYY HH:mm:ss')){
                    return true;
                }else return false;
            },
        },
        computed:{
            fechaR(){
                return moment(this.cuenta.fregistro).format('DD/MM/YYYY HH:mm');
            },
            fechaP(){
                if(this.pago()){
                    return "No paga";
                }
                return moment(this.cuenta.fpago).format('DD/MM/YYYY HH:mm');
            },
        },
    }
</script>


<template>
    <td>{{ fechaR }}</td>
    <td>{{ fechaP }}</td>
    <td >{{ cuenta.monto }}</td>
    <td v-if="this.pago()" @click="pagar()" class="bot">Pagar</td>
    <td v-else class="nBot">Pagar</td>
    <td @click="$emit('m')" class="bot">Modificar</td>
    <td @click="borrar()" class="bot">Borrar</td>
</template>