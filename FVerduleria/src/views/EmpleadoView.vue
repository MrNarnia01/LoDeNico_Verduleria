<script>
    import CreateEmpleado from '@/components/Empleado/CreateEmpleado.vue';
    import Empleado from '@/components/Empleado/Empleado.vue';
    import UpdateEmpleado from '@/components/Empleado/UpdateEmpleado.vue';
    import axios from 'axios';

    export default{
        components: {
            Empleado,
            CreateEmpleado,
            UpdateEmpleado,
        },
        data(){
            return{
                empleados:'',
                mod: null,
                c: true,
                u: true,
            };
        },
        mounted(){
            this.lEmpleado();
        },
        methods: {
            async lEmpleado(){
                try {
                    const response = await axios.get('http://localhost:8080/api/empleado/list');
                    this.empleados=response.data;
                } catch (error) {
                    console.log(error.response.data)
                    this.empleados='';
                }
            },
            create(){
                this.c=!this.c;
                this.lEmpleado();
            },
            update(id){
                this.mod=id;
                this.u=!this.u;
                this.lEmpleado();
            },
        },
    }
</script>



<template>
    <table v-if="this.c && this.u">
        <tr>
            <td>Nombre</td>
            <td>Telefono</td>
            <td>Mail</td>
            <td>Horarios</td>
            <td colspan="2">Modificaciones</td>
        </tr>
        <tr v-if="this.empleados==''">
            <td colspan="6">No hay empleados registrados</td>
        </tr>
        <tr v-else v-for="empleado in empleados" :key="empleado.id">
            <Empleado :empleado="empleado" @e="lEmpleado()"  @mod="update(empleado)"/>
        </tr>
        <tr>
            <td><button type="button" @click="create(null)">Crear</button></td>
        </tr>
    </table>
    <CreateEmpleado v-if="!this.c" @cloc="create(null)" />
    <UpdateEmpleado v-if="!this.u" :empleado="this.mod" @clou="update(null)" />
</template>
