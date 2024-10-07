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
    

    <table v-if="this.c && this.u">
        <h1>Proveedor :</h1>
        <tr>
            <th>Negocio :</th>
            <td> <input type="text" v-model="proveedor.negocio" readonly> </td>
            <th>Direccion :</th>
            <td> {{ proveedor.calle }}  {{ proveedor.altura }} </td>
        </tr>
        <tr>
            <th>Trabajadores</th>
        </tr>

        <tr v-for="trabajador in proveedor.trabajadorResponseList" :key="trabajador.id">
            <Trabajador :trabajador="trabajador" @e="busqueda()" @m="update(trabajador)" />
        </tr>
        <button type="button" @click="create()">Crear Trabajador</button>
    </table>
    
    <CreateTrabajador v-if="!this.c" :id="this.id" :boleta="this.proveedor.negocio" @cloc="create()" />
    <UpdateTrabajador v-if="!this.u" :trabajador="this.trabajador" :boleta="this.proveedor.negocio" @clou="update(null)" />
</template>