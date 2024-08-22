<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            pedido:Object,
        },
        computed:{
            productos(){
                return this.pedido.detallePedidoResponseList.length;
            },
            fechas(){
                console.log(this.pedido)
                return moment(this.pedido.fPedido).format('DD/MM/YYYY');
            },
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/pedido/delete/'+this.pedido.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        }
    }
</script>

<template>
    <td>{{ pedido.negocio }}</td>
    <td>{{ fechas }}</td>
    <td>{{ productos }}</td>
    <td v-if="pedido.nB ==null"> 
        <button type="button"> Recibir Pedido </button> 
    </td>
    <td v-else> {{ pedido.nB }} </td>
    <td><button type="button">Modificar</button></td>
    <td><button type="button" @click="borrar()">Eliminar</button></td>
</template>