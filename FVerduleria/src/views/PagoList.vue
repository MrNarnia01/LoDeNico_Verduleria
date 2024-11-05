<script>
    import Pago from '@/components/Pago/Pago.vue';
    import PagoChange from '@/components/Pago/PagoChange.vue';
    import axios from 'axios';
    import moment from 'moment';
    export default{
        components:{
            Pago,
            PagoChange,
        },
        data(){
            return{
                boleta: [],
                id: 0,
                show: false,
                c: null,
            };
        },
        mounted(){
            this.id=this.$route.query.id;
            this.getBoleta();
        },
        methods: {
            async getBoleta(){
                try {
                    const response = await axios.get('http://localhost:8080/api/boleta/get/'+this.id);
                    this.boleta=response.data;
                    console.log(this.boleta)
                } catch (error) {
                    console.log(error.response.data)
                    this.boleta='';
                }
            },
            change(idB){
                this.c=idB;
                this.show=!this.show;
                this.getBoleta();
            },
        },
        computed:{
            fechas(){
                console.log(this.boleta)
                return moment(this.boleta.fRecibo).format('DD/MM/YYYY');
            },
        },
    }
</script>

<template>
    <table class="view">
        <tr>
            <th class="tit" colspan="4">Boleta</th>
        </tr>
        <tr>
            <td>Numero de boleta:</td>
            <td>{{ boleta.nb }}</td>
            <td>Negocio:</td>
            <td  colspan="2">{{ boleta.negocio }}</td>
        </tr>
        <tr>
            <td>Fecha:</td>
            <td colspan="2">{{ fechas }}</td>
            <td>Monto:</td>
            <td>{{ boleta.monto }}</td>
        </tr>
        <tr>
            <th class="tit" colspan="3">Listado de pagos</th>
            <td v-if="!boleta.paga" class="bot" @click="change(null)" colspan="2">Pagar</td>
            <td v-else class="nBot" colspan="2">Pagar</td>
        </tr>
        <tr>
            <td>Tipo de pago</td>
            <td>Fecha</td>
            <td>Monto</td>
            <td colspan="2">Modificaciones</td>
        </tr>
        <tr v-for="pago in boleta.pagoResponseList" :key="pago.id">
            <Pago :pago="pago" @e="getBoleta()" @mod="change(pago)" />
        </tr>
    </table>
    <PagoChange v-if="show":pago="this.c" :aPagar="this.boleta.faltaPagar" :idBoleta="this.boleta.id" @cloc="change(null)"/>
</template>