<script>
    import ChangeCierre from '@/components/Cierre/ChangeCierre.vue';
    import Cierre from '@/components/Cierre/Cierre.vue';
    import Lote from '@/components/Lote/Lote.vue';
    import axios from 'axios';
    import moment from 'moment';

    export default{
        components: {
            Cierre,
            ChangeCierre,
            Lote,
        },
        data(){
            return{
                cierres:'',
                lotes:'',
                mod: null,
                tMod: 0,
                tipo: 'Posnet',
                busRequestL: {
                    f1: null,
                    f2: null,
                    b: false,
                    i:0,
                    s1: '',
                    m1: 0.0,
                    m2: 0.0,
                },
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
                    cP:false,
                },
                b: false,
                fHoy:'',
            };
        },
        mounted(){
            this.lCierres();
            this.busRequestL.f1=new Date();
            this.busRequestL.f2=moment(this.busRequestL.f1).add(7, 'days');
            this.lLotes();
            const today = new Date();
            const year = today.getFullYear();
            let month = today.getMonth() + 1;
            month = month < 10 ? '0' + month : month;
            let day = today.getDate();
            day = day < 10 ? '0' + day : day;
            this.fHoy = `${year}-${month}-${day}`;
        },
        methods: {
            async lCierres(){
                try {
                    const response = await axios.get('http://localhost:8080/api/cierre/list');
                    this.cierres=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.cierres='';
                }
            },
            update(id,tip){
                this.mod=id;
                this.tMod=tip;
                this.lCierres();
            },
            lLotes() {
                axios.post( 'http://localhost:8080/api/lote/dias',this.busRequestL ).then(response => {
                    this.lotes=response.data;
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            }, 
            bus(){
                if(this.cBus.cU || this.cBus.cP){
                    if(this.tipo=="Efectivo"){
                        this.busRequest.b=true;
                    }else this.busRequest.b=false;

                    if(!this.cBus.cU) this.busRequest.i=-1;
                    else this.busRequest.i=0;
                    if(!this.cBus.cP) this.busRequest.m1=-1;
                    else this.busRequest.m1=0;

                    axios.post( 'http://localhost:8080/api/cierre',this.busRequest ).then(response => {
                        this.cierres=response.data;
                    }).catch(error => {
                        console.log(error)
                        this.cierres=''
                    });
                }
            },
        },
    }
</script>



<template>
    <div  class="pag"></div>
    <h1> Info principal </h1>
    <!--Div de CierreLote-->
    <div class="midDiv" id="izq">
        <table class="view">
            <tr>
                <th class="tit" colspan="5">Listado de cierres</th>
            </tr>
            <tr>
                <td class="esp" colspan="2">Busquedas:</td>
                <td class="esp"><input type="checkbox" v-model="b" @click="lCierres()"></td>
                <td colspan="2" class="bot" @click="update(null,1)">Registrar cierre</td>
            </tr>
            <tr><td class="sepa" colspan="5"></td></tr>
            <!--Busqueda por tipo-->
            <tr v-if="this.b">
                <td class="esp"><input type="checkbox" v-model="this.cBus.cU"></td>
                <td><label for="un">Tipo:</label></td>
                <td v-if="this.cBus.cU" colspan="2" class="esp">
                    <input type="radio" value="Posnet" v-model="tipo" id="pos">
                    <label for="pos">Posnet</label>
                    <input type="radio" value="Efectivo" v-model="tipo" id=efe>
                    <label for="efe">Efectivo</label>
                </td>
                <td class="esp" v-else colspan="2">
                    <div class="closel" >&times;</div>
                </td>
                <td class="esp">
                    <button type="button" class="bot" id="nor" @click="bus()">Buscar</button>
                </td>
            </tr>
            <!--Busqueda por fecha-->
            <tr v-if="this.b">
                <td class="esp"><input type="checkbox" v-model="this.cBus.cP"></td>
                <td><label for="un">Fechas:</label></td>

                <td colspan="3" class="esp" v-if="this.cBus.cP">
                    <input type="date" v-model="this.busRequest.f1" :min="fHoy">
                    &emsp;
                    <input type="date" v-model="this.busRequest.f2" :min="this.busRequest.f1">
                </td>
                <td colspan="3" class="esp" v-else>
                    <input type="date" readonly>
                    &emsp;
                    <input type="date" readonly>
                </td>
            </tr>
            <tr v-if="this.b"><td class="sepa" colspan="5"></td></tr>
            <tr>
                <td>Tipo</td>
                <td>Monto</td>
                <td>Fecha de cierre</td>
                <td colspan="2">Modificaciones</td>
            </tr>
            <tr v-if="this.cierres==''">
                <td colspan="6">No hay cierres registrados</td>
            </tr>
            <tr v-else v-for="cierre in cierres" :key="cierre.id">
                <Cierre :cierre="cierre" @e="lCierres()"  @mod="update(cierre,1)"/>
            </tr>
        </table>
    </div>
       
    <div class="midDiv" id="der">
        <table class="view">
            <tr>
                <th class="tit" colspan="4">Listado de lotes por vencer</th>
            </tr>
            <tr>
                <td class="esp" colspan="2"><label for="fVenci">Fecha limite:</label></td>
                <td class="esp" colspan="2"><input type="date" id="fVenci" v-model="this.busRequestL.f2" :min="fHoy" @change="lLotes()"></td>
            </tr>
            <tr><td class="sepa" colspan="4"></td></tr> 
            <tr>
                <th>Nombre</th>
                <th>Lote</th>
                <th>Fecha de vencimiento</th>
                <th>Eliminacion</th>
            </tr>
            <tr v-if="this.lotes==''">
                <td colspan="6">No se encontraron lotes</td>
            </tr>
            <tr v-else v-for="lote in lotes" :key="lote.id">
                <Lote :lote="lote" @e="lLotes()"/>
            </tr>
        </table>
    </div>

    <ChangeCierre v-if="this.tMod==1" :id="this.mod" @cloc="update(null,0)" />
</template>

