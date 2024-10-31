<script>
    import axios from 'axios'
    import Trabajador from '@/components/Trabajador/Trabajador.vue';
    import CreateTrabajador from '@/components/Trabajador/CreateTrabajador.vue';
    import UpdateTrabajador from '@/components/Trabajador/UpdateTrabajador.vue';
    export default{
        components: {
            Trabajador,
            CreateTrabajador,
            UpdateTrabajador
        },
        data(){
            return{
                id: 0,
                trabajador:[],
                proveedor:[],
                c:true,
                u:true,
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
                    const response = await axios.get('http://localhost:8080/api/proveedor/get/'+this.id);
                    this.proveedor=response.data;
                } catch (error) {
                    console.log(error.response.data)
                }
            },
            create(){
                this.c=!this.c;
                this.busqueda();
            },
            update(t){
                this.trabajador=t;
                this.u=!this.u;
                this.busqueda();
            },
        },
    }
    
</script>


<template>
    

    <table class="view">
        <tr>
            <th class="tit" colspan="5">Proveedor</th>
        </tr>
        <tr>
            <th class="tit">Negocio :</th>
            <td> {{ proveedor.negocio }} </td>
            <th class="tit">Direccion :</th>
            <td> {{ proveedor.calle }}  {{ proveedor.altura }} </td>
            <td @click="create()" class="bot">Registrar trabajador</td>
        </tr>
        <tr>
            <th class="tit" colspan="5">Trabajadores</th>
        </tr>
        <tr>
            <td>Puesto</td>
            <td>Nombre</td>
            <td>Telefono</td>
            <td colspan="2">Modificaciones</td>
        </tr>
        <tr v-if="this.proveedor.trabajadorResponseList==''">
            <td colspan="6">No hay Trabajadores registrados</td>
        </tr>
        <tr v-else v-for="trabajador in proveedor.trabajadorResponseList" :key="trabajador.id">
            <Trabajador :trabajador="trabajador" @e="busqueda()" @m="update(trabajador)" />
        </tr>
    </table>
    
    <CreateTrabajador v-if="!this.c" :id="this.id" :negocio="this.proveedor.negocio" @cloc="create()" />
    <UpdateTrabajador v-if="!this.u" :trabajador="this.trabajador" :negocio="this.proveedor.negocio" @clou="update(null)" />
</template>