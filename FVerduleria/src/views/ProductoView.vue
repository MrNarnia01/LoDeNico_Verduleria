<script>
    import Producto from '../components/Producto/Producto.vue'
    import axios from 'axios'
    import CreateProducto from '../components/Producto/CreateProducto.vue'
    import UpdateProducto from '../components/Producto/UpdateProducto.vue'

    export default{
        components: {
            Producto,
            CreateProducto,
            UpdateProducto,
        },
        data(){
            return{
                productos: '',
                c:false,
                u:false,
                produ: null,
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
            console.log('creado');
            this.lProductos();
        },
        methods: {
            async lProductos(){
                try {
                    const response = await axios.get('http://localhost:8080/api/producto/list/'+false);
                    this.productos=response.data;
                    console.log(this.productos)
                } catch (error) {
                    console.log(error.response.data)
                    this.productos=''
                }
            },
            bus(){
                if(this.cBus.cN || this.cBus.cU || this.cBus.cP){
                    if(!this.cBus.cN) this.busRequest.s1="";
                    if(!this.cBus.cU) this.busRequest.i=-1;
                    else this.busRequest.i=0;
                    if(!this.cBus.cP) this.busRequest.m1=-1.0;
                    console.log(this.busRequest)
                    axios.post( 'http://localhost:8080/api/producto/bus',this.busRequest ).then(response => {
                        this.productos=response.data;
                    }).catch(error => {
                        console.log(error)
                        this.productos=''
                    });
                }
            },
            create(){
                this.c=!this.c;
                this.lProductos();
            },
            update(){
                this.u=!this.u;
                this.lProductos();
            },
            modi(producto){
                this.produ=producto;
                this.update();
            }
        },
    }
    

</script>

<template>
    <div  class="pag"></div>

    <table class="view">
        <tr>
            <th class="tit" colspan="2">Listado de productos</th>
            <td class="esp" colspan="2">Busquedas:</td>
            <td class="esp"><input type="checkbox" v-model="b" @click="lProductos()"></td>
            <td class="esp"></td>
            <td @click="create()" colspan="2" class="bot">Registrar producto</td>
        </tr>
        <tr><td class="sepa" colspan="8"></td></tr>
        <tr v-if="this.b">
            <td class="esp"><input type="checkbox" v-model="this.cBus.cN"></td>
            <td><label for="nom">Nombre:</label></td>
            <td class="esp">
                <input v-if="this.cBus.cN" type="text" id="nom" v-model="this.busRequest.s1" autocomplete="off">
                <input v-else type="text" id="nom" readonly class="des" placeholder="No seleccionado">
            </td>

            <td class="esp"><input type="checkbox" v-model="this.cBus.cP"></td>
            <td><label for="preb">Desde:</label></td>
            <td class="esp">
                <input type="number" id="preb" v-model="this.busRequest.m1" :min="0.01" step="0.01" v-if="this.cBus.cP">
                <input type="number" id="preb" v-else class="des" placeholder="No seleccionado">
            </td>

            <td colspan="2" rowspan="2" class="esp">
                <button type="button" class="bot" id="nor" @click="bus()">Buscar</button>
            </td>
        </tr>
        <tr v-if="this.b">
            <td class="esp"><input type="checkbox" v-model="this.cBus.cU"></td>
            <td><label for="un">Unitario:</label></td>
            <td class="esp">
                <input type="checkbox" v-if="this.cBus.cU" id="un" v-model="this.busRequest.b">
                <div class="closel" v-else>&times;</div>
            </td>

            <td class="esp"></td>
            <td><label for="pref">Hasta:</label></td>
            <td class="esp">
                <input type="number" id="pref" v-model="this.busRequest.m2" :min="this.busRequest.m1" step="0.01" v-if="this.cBus.cP">
                <input type="number" id="preb" v-else class="des" placeholder="No seleccionado">
            </td>
        </tr>
        <tr><td class="sepa" colspan="8" v-if="this.b"></td></tr>
        <tr>
            
            <th>Nombre</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Unitario</th>
            <th colspan="2">Lotes</th>
            <th colspan="2">Modificaciones</th>
            
        </tr>
        <tr v-if="this.productos==''">
            <td colspan="8">No hay productos registrados</td>
        </tr>
        <tr v-else v-for="producto in productos" :key="producto.id">
            <Producto :producto="producto" @mod="modi(producto)" @e="lProductos()" />
        </tr>
    </table>
    
    <CreateProducto v-if="this.c" @cloc="create()" @clox="this.c=!this.c" />
    <UpdateProducto v-if="this.u" @clou="update()" :producto="this.produ" />
</template>