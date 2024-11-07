<script>
    import Proveedor from '../components/Proveedor/Proveedor.vue'
    import axios from 'axios'
    import CreateProveedor from '../components/Proveedor/CreateProveedor.vue'
    import UpdateProveedor from '../components/Proveedor/UpdateProveedor.vue'


    export default{
        components: {
            Proveedor,
            CreateProveedor,
            UpdateProveedor,
        },
        data(){
            return{
                proveedores: '',
                c:false,
                u:false,
                prove: null
            };
        },
        mounted(){
            console.log('creado');
            this.lProveedores();
        },
        methods: {
            async lProveedores(){
                console.log('llego');
                try {
                    const response = await axios.get('http://localhost:8080/api/proveedor/list');
                    this.proveedores=response.data;
                    console.log(this.proveedores)
                } catch (error) {
                    console.log(error.response.data)
                    this.proveedores=''
                }
            },
            create(){
                this.c=!this.c;
                this.lProveedores();
            },
            update(){
                this.u=!this.u;
                this.lProveedores();
            },
            modi(proveedor){
                this.prove=proveedor;
                this.update();
            }
        },
    }
    

</script>

<template>
    <div  class="pag"></div>

    <table class="view">
        <tr>
            <th class="tit" colspan="2">Listado de proveedores</th>
            <td colspan="3" class="esp"></td>
            <td @click="create()" colspan="2" class="bot">Registrar proveedor</td>
        </tr>
        <tr>
            <td>Negocio</td>
            <td>Calle</td>
            <td>Altura</td>
            <td colspan="2">Trabajadores</td>
            <td colspan="2">Modificaciones</td>
        </tr>
        <tr v-if="this.proveedores==''">
            <td colspan="6">No hay proveedores registrados</td>
        </tr>
        <tr v-else v-for="proveedor in proveedores" :key="proveedor.id">
            <Proveedor :proveedor="proveedor" @mod="modi(proveedor)" @e="lProveedores()" />
        </tr>
    </table>
    
    <CreateProveedor v-if="this.c" @cloc="create()" />
    <UpdateProveedor v-if="this.u" @clou="update()" :proveedor="this.prove" />
    
</template>