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
                proveedores: '',
                c: false,
                change: null,
                b: false,
                busRequest: {
                    f1: null,
                    f2: null,
                    b: false,
                    i:0,
                    s1: '',
                    m1: 0.0,
                    m2: 0.0,
                },
                cBus:{
                    cN:false,
                    cU:false,
                    cP:false,
                },
            };
        },
        mounted(){
            this.lPedidos();
            this.lProveedores();
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
            },
            async lProveedores(){
                try {
                    const response = await axios.get('http://localhost:8080/api/proveedor/list');
                    this.proveedores=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.proveedores=''
                }
            },
            bus(){
                console.log("busqueda")
                if(this.cBus.cN || this.cBus.cU || this.cBus.cP){
                    if(!this.cBus.cN) this.busRequest.m1=-1;
                    else this.busRequest.m1=0;
                    if(!this.cBus.cU) this.busRequest.i=-1;
                    else this.busRequest.i=0;
                    if(!this.cBus.cP) this.busRequest.m2=-1;
                    else this.busRequest.m2=0;
                    console.log(this.busRequest)
                    axios.post( 'http://localhost:8080/api/pedido/bus',this.busRequest ).then(response => {
                        this.pedidos=response.data;
                    }).catch(error => {
                        console.log(error)
                        this.pedidos=''
                    });
                }
            },
        },
    }
    

</script>

<template>
    <div  class="pag"></div>
        <table class="view">
            <tr>
                <th class="tit" colspan="2">Listado de pedidos</th>
                <td class="esp" >Busquedas:</td>
                <td class="esp"><input type="checkbox" v-model="b" @click="lPedidos()"></td>
                <td @click="create()" colspan="2" class="bot">Registrar pedido</td>
            </tr>

        <tr><td class="sepa" colspan="6"></td></tr>
            <!--Por boleta-->
        <tr v-if="this.b">
            <td class="esp"><input type="checkbox" v-model="this.cBus.cU"></td>
            <td><label for="un">Recibida:</label></td>
            <td class="esp">
                <input type="checkbox" v-if="this.cBus.cU" id="un" v-model="this.busRequest.b">
                <div class="closel" v-else>&times;</div>
            </td>

            <!--Por proveedor-->
            <td class="esp"><input type="checkbox" v-model="this.cBus.cP"></td>
            <td><label for="un">Proveedor:</label></td>
            <td class="esp" v-if="this.cBus.cP">
                <select v-model="busRequest.s1" required>
                    <option v-for="proveedor in proveedores" :key="proveedor.id" :value="proveedor.id">
                        {{ proveedor.negocio }}
                    </option>
                </select>
            </td>
            <td v-else class="esp">
                <select class="bot"></select>
            </td>
        </tr>
        <!--Por fecha-->
        <tr v-if="this.b">
                <td class="esp"><input type="checkbox" v-model="this.cBus.cN"></td>
                <td><label for="un">Fechas:</label></td>
                <td colspan="3" class="esp" v-if="this.cBus.cN">
                    <input type="date" v-model="this.busRequest.f1" :min="fHoy">
                    &emsp;
                    <input type="date" v-model="this.busRequest.f2" :min="this.busRequest.f1">
                </td>
                <td colspan="3" class="esp" v-else>
                    <input type="date" readonly>
                    &emsp;
                    <input type="date" readonly>
                </td>
                <td class="esp">
                    <button type="button" class="bot" @click="bus()">Buscar</button>
                </td>
            </tr>
        <tr><td class="sepa" colspan="6" v-if="this.b"></td></tr>

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