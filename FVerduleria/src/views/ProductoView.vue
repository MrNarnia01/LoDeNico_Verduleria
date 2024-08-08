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
                    const response = await axios.get('http://localhost:8080/api/producto/list/'+true);
                    this.productos=response.data
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

    <table>
        <tr>
            <td>Nombre</td>
            <td>Precio</td>
            <td>Stock</td>
            <td>Unitario</td>
            <td colspan="2">Lotes</td>
            <td colspan="2">Modificaciones</td>
        </tr>
        <tr v-if="this.productos==''">
            <td colspan="6">No hay productos registrados</td>
        </tr>
        <tr v-else v-for="producto in productos" :key="producto.id">
            <Producto :producto="producto" @mod="modi(producto)" @e="lProductos()" />
        </tr>
    </table>
    <button type="button" @click="create()">Crear</button>
    <CreateProducto v-if="this.c" @cloc="create()" />
    <UpdateProducto v-if="this.u" @clou="update()" :producto="this.produ" />
</template>