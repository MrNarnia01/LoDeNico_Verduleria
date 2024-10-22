<script>
    import ChangeCierre from '@/components/Cierre/ChangeCierre.vue';
    import Cierre from '@/components/Cierre/Cierre.vue';
    import axios from 'axios';

    export default{
        components: {
            Cierre,
            ChangeCierre,
        },
        data(){
            return{
                lotes:'',
                mod: null,
                tMod: 0,
            };
        },
        mounted(){
            this.lCierres();
        },
        methods: {
            async lCierres(){
                try {
                    const response = await axios.get('http://localhost:8080/api/cierre/list');
                    this.lotes=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.lotes='';
                }
            },
            update(id,tip){
                this.mod=id;
                this.tMod=tip;
                this.lCierres();
            },
        },
    }
</script>



<template>
    <!--Div de CierreLote
    <div>
        <table>
            <tr>
                <td>Tipo</td>
                <td>Monto</td>
                <td>Fecha de cierre</td>
                <td colspan="2">Modificaciones</td>
            </tr>
            <tr v-if="this.lotes==''">
                <td colspan="6">No hay cierres registrados</td>
            </tr>
            <tr v-else v-for="cierre in lotes" :key="cierre.id">
                <Cierre :cierre="cierre" @e="lCierres()"  @mod="update(cierre,1)"/>
            </tr>
        </table>
        <button type="button" @click="update(null,1)">Crear</button>
    </div>
    <ChangeCierre v-if="this.tMod==1" :id="this.mod" @cloc="update(null,0)" />
    -->
    <h1> Home </h1>
    <RouterLink to="/producto"> Productos </RouterLink>
    <RouterLink to="/proveedor"> Proveedores </RouterLink>
    <RouterLink to="/pedidoList"> Pedidos </RouterLink>
    <RouterLink to="/boletaList"> Boletas </RouterLink>
    <RouterLink to="/empleado"> Empleado </RouterLink>
    <RouterLink to="/"> Fiado </RouterLink>


</template>
