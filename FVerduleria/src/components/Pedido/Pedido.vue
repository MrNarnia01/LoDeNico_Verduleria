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
                return moment(this.pedido.fpedido).format('DD/MM/YYYY');
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
            boleta(){
                this.$router.push({
                    name: 'boletaList',
                    query: { id:this.pedido.id }
                });
            }
        }
    }
</script>

<template>
    <td>{{ pedido.negocio }}</td>
    <td>{{ fechas }}</td>
    <td>{{ productos }}</td>
    <td v-if="pedido.nb ==-1" @click="boleta()" class="bot"> Recibir Pedido </td>
    <td v-else> {{ pedido.nb }} </td>
    <td v-if="pedido.nB ==null" @click="$emit('mod')" class="bot">Modificar</td>
    <td v-else> - </td>
    <td @click="borrar()" class="bot">Eliminar</td>
</template>