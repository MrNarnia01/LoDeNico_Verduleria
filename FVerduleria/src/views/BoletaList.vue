<script>
    import Boleta from '../components/Boleta/Boleta.vue';
    import axios from 'axios'

    export default{
        components: {
            Boleta,
        },
        data(){
            return{
                boletas: '',
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
                    const response = await axios.get('http://localhost:8080/api/boleta/list');
                    this.boletas=response.data;
                    console.log(this.boletas)
                } catch (error) {
                    console.log(error.response.data)
                    this.boletas='';
                }
            },
            create(){
                this.change=null;
                this.c=!this.c;
                this.lPedidos();
            },
            update(boleta){
                this.change=boleta;
                this.c=!this.c;
                this.lPedidos();
            }
        },
    }
    

</script>

<template>
    <div v-if="!this.c">
        <table>
            <tr>
                <td>Numero de boleta</td>
                <td>Negocio</td>
                <td>Fecha de boleta</td>
                <td>Productos</td>
                <td>Paga</td>
                <td>Monto</td>
                <td colspan="2">Modificaciones</td>
            </tr>
            <tr v-if="this.boletas==''">
                <td colspan="6">No hay boletas registradas</td>
            </tr>
            <tr v-else v-for="boleta in boletas" :key="boleta.id">
                <Boleta :boleta="boleta" @e="lPedidos()"  @mod="update(boleta)"/>
            </tr>
        </table>
        <button type="button" @click="create()">Crear</button>
    </div>
    <!--
    <CreatePedido v-if="this.c" :boleta="this.change"  @cloc="create()" />
    -->
</template>