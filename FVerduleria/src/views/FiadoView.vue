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
                b: false,
                nom: "",
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
            async bus(){
                try {
                    const response = await axios.get('http://localhost:8080/api/cliente/list/nom/'+this.nom);
                    this.clientes=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.clientes='';
                }
            },
        },
    }
    

</script>

<template>
    <table class="view">
        <tr>
            <th class="tit" colspan="1">Listado de clientes</th>
            <td class="esp" colspan="1">Busquedas:</td>
            <td class="esp"><input type="checkbox" v-model="b" @click="lClientes()"></td>
            <td @click="create()" class="bot" colspan="2">Crear cuenta de nuevo cliente</td>
        </tr>
        <tr><td colspan="5" class="sepa"></td></tr>
        <tr v-if="this.b">
            <td><label for="nom">Nombre:</label></td>
            <td class="esp" colspan="2">
                <input type="text" id="nom" v-model="this.nom" autocomplete="off">
            </td>

            <td class="esp" colspan="2">
                <button type="button" class="bot" id="nor" @click="bus()">Buscar</button>
            </td>
        </tr>
        <tr v-if="this.b"><td colspan="5" class="sepa"></td></tr>
        <tr>
            <td>Nombre</td>
            <td>Telefono</td>
            <td>Direccion</td>
            <td colspan="2">Cuentas</td>
        </tr>
        <tr v-if="this.clientes==''">
            <td colspan="6">No hay clientes registrados</td>
        </tr>
        <tr v-else v-for="cliente in clientes" :key="cliente.id">
            <Cliente :cliente="cliente" @e="lClientes()" />
        </tr>
    </table>
    <CreateCCnoC v-if="this.c" @cloc="create()" />

</template>