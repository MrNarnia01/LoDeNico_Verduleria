<script>
    import ChangeCC from '@/components/Fiado/ChangeCC.vue';
    import Cuenta from '@/components/Fiado/Cuenta.vue';
    import axios from 'axios'
    export default{
        components: {
            Cuenta,
            ChangeCC,
        },
        data(){
            return{
                id: 0,
                cuenta: 0,
                cliente:[],
                c:false,
                mon: 0,
                u: true,
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
        },
    }
    
</script>


<template>
    <table>
        <tr>
            <th> Cliente:</th>
            <td> {{ cliente.nombre }}  {{ cliente.apellido }} </td>
            <th> Telefono:</th>
            <td> {{ cliente.codArea }}-{{ cliente.tel }} </td>
        </tr>
        <tr>
            <th>Direccion:</th>
            <td> {{ cliente.calle }}  {{ cliente.altura }} </td>
        </tr>
        <tr>
            <th>Cuentas</th>
        </tr>

        <tr v-for="cuenta in cliente.cuentaCorrienteResponseList" :key="cuenta.id">
            <Cuenta :cuenta="cuenta" @e="busqueda()" @m="update(cuenta.id, cuenta.monto)" />
        </tr>
        <button type="button" @click="update(-1,0)">Crear Cuenta</button>
    </table>
    <ChangeCC v-if="this.c" :id="this.cuenta" :mont="this.mon" :c="this.u" @cloc="update(0,0)" />

</template> 