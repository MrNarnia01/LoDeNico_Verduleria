<script>
    import Pago from '@/components/Pago/Pago.vue';
    import PagoChange from '@/components/Pago/PagoChange.vue';
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
        },
        methods: {
            async getBoleta(){
                try {
                    const response = await axios.get('http://localhost:8080/api/boleta/get/'+this.id);
                    this.boleta=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.boleta='';
                }
            },
        },
        computed:{
            fechas(){
                console.log(this.boleta)
                return moment(this.boleta.fRecibo).format('DD/MM/YYYY');
            },
        },
        change(idB){
            this.c=idB;
            this.show=!this.show;
            this.getBoleta();
        },
    }
</script>

<template>
    <table v-if="!show">
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
            <td v-if="!boleta.paga"><button @click="change(null)">Pagar</button></td>
            <td v-else>-</td>
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
    <PagoChange v-else :pago="this.c" @cloc="change(null)"/>
</template>