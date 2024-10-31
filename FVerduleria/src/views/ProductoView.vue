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
                produ: null
            };
        },
        mounted(){
            console.log('creado');
            this.lProductos();
        },
        methods: {
            async lProductos(){
                console.log('llego');
                try {
                    const response = await axios.get('http://localhost:8080/api/producto/list/'+false);
                    this.productos=response.data;
                    console.log(this.productos)
                } catch (error) {
                    console.log(error.response.data)
                    this.productos=''
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

    <table class="view">
        <tr>
            <th class="tit" colspan="2">Listado de productos</th>
            <td colspan="4" class="esp"></td>
            <td @click="create()" colspan="2" class="bot">Registrar producto</td>
        </tr>
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
    
    <CreateProducto v-if="this.c" @cloc="create()" />
    <UpdateProducto v-if="this.u" @clou="update()" :producto="this.produ" />
</template>