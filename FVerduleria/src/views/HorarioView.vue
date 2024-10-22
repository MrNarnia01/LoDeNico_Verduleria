<script>
    import CreateHorario from '@/components/Horario/CreateHorario.vue';
    import Horario from '@/components/Horario/Horario.vue';
import UpdateHorario from '@/components/Horario/UpdateHorario.vue';
    import axios from 'axios'

    export default{
        components: {
            Horario,
            CreateHorario,
            UpdateHorario,
        },
        data(){
            return{
                id: 0,
                horario:[],
                empleado:[],
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
                    const response = await axios.get('http://localhost:8080/api/empleado/get/'+this.id);
                    this.empleado=response.data;
                } catch (error) {
                    console.log(error.response.data)
                }
            },
            create(){
                this.c=!this.c;
                this.busqueda();
            },
            update(t){
                this.horario=t;
                this.u=!this.u;
                this.busqueda();
            },
        },
    }
    
</script>


<template>
    <table v-if="this.c && this.u">
        <h1>Empleado :</h1>
        <tr>
            <th>Nombre:</th>
            <td>{{ empleado.nombre }}  {{ empleado.apellido }}</td>
        </tr>
        <tr>
            <th>Horarios</th>
        </tr>

        <tr v-for="horario in empleado.horarioResponseList" :key="horario.id">
            <Horario :horario="horario" @e="busqueda()" @m="update(horario)" />
        </tr>
        <button type="button" @click="create()">Crear horario</button>
    </table>
    <CreateHorario v-if="!this.c" :id="this.empleado.id" :nombre="this.empleado.nombre+' '+this.empleado.apellido"  @cloc="create()" />
    <UpdateHorario v-if="!this.u" :id="this.horario" @clou="update(null)" />
</template>