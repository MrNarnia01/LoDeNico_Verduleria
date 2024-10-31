<script>
    import Pedido from '../components/Pedido/Pedido.vue';
    import axios from 'axios'
    import CreatePedido from '@/components/Pedido/CreatePedido.vue';

    export default{
        components: {
            Pedido,
            CreatePedido,
        },
        data(){
            return{
                pedidos: '',
                c: false,
                change: null
            };
        },
        mounted(){
            this.lPedidos();
        },
        methods: {
            async lPedidos(){
                try {
                    const response = await axios.get('http://localhost:8080/api/pedido/list');
                    this.pedidos=response.data;
                    console.log(this.pedidos)
                } catch (error) {
                    console.log(error.response.data)
                    this.pedidos='';
                }
            },
            create(){
                this.change=null;
                this.c=!this.c;
                this.lPedidos();
            },
            update(pedido){
                this.change=pedido;
                this.c=!this.c;
                this.lPedidos();
            }
        },
    }
    

</script>

<template>
        <table class="view">
            <tr>
                <th class="tit" colspan="2">Listado de pedidos</th>
                <td colspan="2" class="esp"></td>
                <td @click="create()" colspan="2" class="bot">Registrar pedido</td>
            </tr>
            <tr>
                <td>Negocio</td>
                <td>Fecha de pedido</td>
                <td>Productos</td>
                <td>Boleta</td>
                <td colspan="2">Modificaciones</td>
            </tr>
            <tr v-if="this.pedidos==''">
                <td colspan="6">No hay pedidos registrados</td>
            </tr>
            <tr v-else v-for="pedido in pedidos" :key="pedido.id">
                <Pedido :pedido="pedido" @e="lPedidos()"  @mod="update(pedido)"/>
            </tr>
        </table>
    <CreatePedido v-if="this.c":pedido="this.change"  @cloc="create()" />

</template>