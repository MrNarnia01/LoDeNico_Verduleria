<script>
    import Cliente from '@/components/Fiado/Cliente.vue';
    import CreateCCnoC from '@/components/Fiado/CreateCCnoC.vue';
    import axios from 'axios'

    export default{
        components: {
            Cliente,
            CreateCCnoC,
        },
        data(){
            return{
                clientes: '',
                c:false,
                u:false,
            };
        },
        mounted(){
            this.lClientes();
        },
        methods: {
            async lClientes(){
                try {
                    const response = await axios.get('http://localhost:8080/api/cliente/list/all');
                    this.clientes=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.clientes='';
                }
            },
            create(){
                this.c=!this.c;
                this.lClientes();
            },
            update(){
                this.u=!this.u;
                this.lClientes();
            },
            modi(cliente){
                //this.prove=cliente;
                this.update();
            }
        },
    }
    

</script>

<template>
    <table>
        <tr>
            <td>Nombre</td>
            <td>Telefono</td>
            <td>Direccion</td>
            <td colspan="2">Cuentas</td>
            <td>Modificaciones</td>
        </tr>
        <tr v-if="this.clientes==''">
            <td colspan="6">No hay clientes registrados</td>
        </tr>
        <tr v-else v-for="cliente in clientes" :key="cliente.id">
            <Cliente :cliente="cliente" @mod="modi(cliente)" @e="lClientes()" />
        </tr>
        <tr>
            <td><button type="button" @click="create()">Crear cuenta de nuevo cliente</button></td>
        </tr>
    </table>
    <CreateCCnoC v-if="this.c" @cloc="create()" />

</template>