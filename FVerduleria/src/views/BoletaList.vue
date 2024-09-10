<script>
    import Boleta from '../components/Boleta/Boleta.vue';
    import axios from 'axios'
    import CreateBoleta from '@/components/Boleta/CreateBoleta.vue';
    import UpdateBoleta from '@/components/Boleta/UpdateBoleta.vue';

    export default{
        components: {
            Boleta,
            CreateBoleta,
            UpdateBoleta
        },
        data(){
            return{
                boletas: '',
                c: false,
                u: false,
                change: null
            };
        },
        mounted(){
            if(this.$route.query.id){
                this.change=this.$route.query.id;
                this.c=true;
            }
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
                this.u=!this.u;
                this.lPedidos();
            }
        },
    }
    

</script>

<template>
    <div v-if="!this.c && !this.u">
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
                <Boleta :boleta="boleta" @e="lPedidos()"  @mod="update(boleta.id)"/>
            </tr>
        </table>
    </div>
    <CreateBoleta v-if="this.c" :detalle="this.change"  @cloc="create()" />
    <UpdateBoleta v-if="this.u" :id="this.change" @cloc="update(null)"/>
</template>