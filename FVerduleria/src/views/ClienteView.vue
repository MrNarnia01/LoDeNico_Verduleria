<script>
    import ChangeCC from '@/components/Fiado/ChangeCC.vue';
    import Cuenta from '@/components/Fiado/Cuenta.vue';
    import UpdateC from '@/components/Fiado/UpdateC.vue';
    import axios from 'axios'
    export default{
        components: {
            Cuenta,
            ChangeCC,
            UpdateC
        },
        data(){
            return{
                id: 0,
                cuenta: 0,
                cliente:[],
                c:false,
                mon: 0,
                u: true,
                cli:false,
            };
        },
        mounted(){
            this.id=this.$route.query.id;
            console.log(this.id);
            this.busqueda();
        },
        methods: {
            async busqueda(){
                try {
                    const response = await axios.get('http://localhost:8080/api/cliente/get/'+this.id);
                    this.cliente=response.data;
                    console.log(this.cliente);
                } catch (error) {
                    console.log(error.response.data)
                }
            },
            update(t,m){
                if(t==-1){
                    this.cuenta=this.cliente.id;
                    this.u=true;
                } 
                else{
                    this.cuenta=t;
                    this.u=false;
                } 
                this.mon=m;
                this.c=!this.c;
                this.busqueda();
            },
            updateC(){
                this.cli=!this.cli;
                this.busqueda();
            }
        },
    }
    
</script>

<template>
    <table class="view">
        <tr>
            <th class="tit" colspan="6">Listado de cuentas</th>
        </tr>
        <tr>
            <th class="tit"> Cliente:</th>
            <td> {{ cliente.nombre }}  {{ cliente.apellido }} </td>
            <th class="tit"> Telefono:</th>
            <td> {{ cliente.codArea }}-{{ cliente.tel }} </td>
            <th class="tit">Direccion:</th>
            <td> {{ cliente.calle }}  {{ cliente.altura }} </td>
        </tr>
        <tr>
            <td class="bot" @click="updateC()">Modificar datos del cliente</td>
            <th class="tit" colspan="2">Cuentas</th>
            <td colspan="3" class="bot" @click="update(-1,0)">Crear cuenta</td>
        </tr>
        <tr>
            <td>Fecha de registro</td>
            <td>Fecha de pago</td>
            <td>Monto</td>
            <td colspan="3">Modificaciones</td>
        </tr>
        <tr v-for="cuenta in cliente.cuentaCorrienteResponseList" :key="cuenta.id">
            <Cuenta :cuenta="cuenta" @e="busqueda()" @m="update(cuenta.id, cuenta.monto)" />
        </tr>
    </table>
    <ChangeCC v-if="this.c" :id="this.cuenta" :mont="this.mon" :c="this.u" @cloc="update(0,0)" />
    <UpdateC v-if="this.cli" :cli="this.cliente" @clocli="updateC()" />

</template> 