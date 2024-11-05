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
                    cU:false,
                    cN:false,
                },
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
            },
            bus(){
                if(this.cBus.cU || this.cBus.cN){
                    if(!this.cBus.cN) this.busRequest.m1=-1;
                    else this.busRequest.m1=0;
                    if(!this.cBus.cU) this.busRequest.i=-1;
                    else this.busRequest.i=0;
                    console.log(this.busRequest)
                    axios.post( 'http://localhost:8080/api/boleta/bus',this.busRequest ).then(response => {
                        this.boletas=response.data;
                    }).catch(error => {
                        console.log(error)
                        this.boletas=''
                    });
                }
            },
        },
    }
    

</script>

<template>
        <table class="view">
            <tr>
                <th colspan="5" class="tit">Listado de boletas</th>
                <td class="esp" colspan="2">Busquedas:</td>
                <td class="esp" colspan="2"><input type="checkbox" v-model="b" @click="lPedidos()"></td>
            </tr>
            <tr><td class="sepa" colspan="9"></td></tr>

            <tr v-if="this.b">
                <td class="esp"><input type="checkbox" v-model="this.cBus.cU"></td>
                <td><label for="un">Paga:</label></td>
                <td class="esp">
                    <input type="checkbox" v-if="this.cBus.cU" id="un" v-model="this.busRequest.b">
                    <div class="closel" v-else>&times;</div>
                </td>
                <!--Por fecha-->
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
            <tr><td class="sepa" colspan="9" v-if="this.b"></td></tr>


            <tr>
                <td>Numero de boleta</td>
                <td>Negocio</td>
                <td>Fecha de boleta</td>
                <td>Productos</td>
                <td>Paga</td>
                <td>Monto</td>
                <td colspan="2">Modificaciones</td>
                <td>Pagos</td>
            </tr>
            <tr v-if="this.boletas==''">
                <td colspan="9">No hay boletas registradas</td>
            </tr>
            <tr v-else v-for="boleta in boletas" :key="boleta.id">
                <Boleta :boleta="boleta" @e="lPedidos()"  @mod="update(boleta.id)"/>
            </tr>
        </table>
    <CreateBoleta v-if="this.c" :detalle="this.change"  @cloc="create()" />
    <UpdateBoleta v-if="this.u" :id="this.change" @clou="update(null)"/>
</template>