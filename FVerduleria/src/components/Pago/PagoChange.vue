<script>
    import axios from 'axios'
    export default{
        props: {
            pago:Object,
            aPagar:Object,
            idBoleta:Object,
        },
        data() {
            return {
                pagoRequest:  {
                    idB: 0,
                    tipo: '',
                    monto: 0.0,
                },
                faltaPagar: 0,
            }
        },
        mounted(){
            console.log(this.pago)
            if(this.pago!=null){
                console.log(this.pago)
                this.pagoRequest.idB=this.pago.id;
                this.pagoRequest.tipo=this.pago.tipo;
                this.pagoRequest.monto=this.pago.monto;
                this.faltaPagar+=this.pago.monto;
            }
            this.faltaPagar+=this.aPagar
            this.pagoRequest.idB=this.idBoleta;
        },
        methods:{
            crear(){
                let a = "";
                if(this.pago==null)   a+="create";
                else a+="update/"+this.pago.id;
                console.log(this.pagoRequest)
                axios.post( 'http://localhost:8080/api/pago/'+a,this.pagoRequest,{
                    headers: { 'Content-Type': 'application/json' }
                } ).then(response => {
                    this.$emit('cloc');
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            },
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
    <div class="close" @click="$emit('cloc')">&times;</div>
    <form @submit.prevent="crear">
        <table>
            <tr>
                <td>Falta pagar:</td>
                <td>{{ faltaPagar }}</td>
            </tr>
            <tr>
                <td><label for="tipo">Tipo:</label></td>
                <td><input type="text" id="tipo" v-model="pagoRequest.tipo" required autocomplete="off"></td>
            </tr>
            <tr>
                <td><label for="monto">Monto del pago:</label></td>
                <td><input type="number" id="monto" v-model="pagoRequest.monto" :min="0" step="0.01" :max="faltaPagar" required></td>
            </tr>
            <td colspan="2">
                <button type="submit" class="bot" id="nor" v-if="pago==null">Crear</button>
                <button type="submit" class="bot" id="nor" v-else>Modificar</button>
            </td>
        </table>
        
    </form>
    </div>
    </div>
</template>