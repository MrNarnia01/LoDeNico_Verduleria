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
            this.busqueda();
        },
        methods: {
            async busqueda(){
                try {
                    const response = await axios.get('http://localhost:8080/api/empleado/get/'+this.id);
                    this.empleado=response.data;
                    console.log(this.empleado)
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
    <div  class="pag"></div>
    <table class="view">
        <tr>
            <th class="tit" colspan="5">Listado de horarios</th>
        </tr>
        <tr>
            <th class="tit" colspan="3">Nombre:</th>
            <td colspan="2">{{ empleado.nombre }}  {{ empleado.apellido }}</td>
        </tr>
        <tr>
            <th class="tit" colspan="3">Horarios</th>
            <td @click="create()" colspan="2" class="bot">Registrar horario</td>
        </tr>
        <tr>
            <th>Dia</th>
            <th>Hora inicio</th>
            <th>Hora salida</th>
            <th colspan="2">Modificaciones</th>
        </tr>
        <tr v-for="horario in empleado.horarioResponseList" :key="horario.id">
            <Horario :horario="horario" @e="busqueda()" @m="update(horario)" />
        </tr>
    </table>
    <CreateHorario v-if="!this.c" :id="this.empleado.id" :nombre="this.empleado.nombre+' '+this.empleado.apellido"  @cloc="create()" />
    <UpdateHorario v-if="!this.u" :id="this.horario" @clou="update(null)" />
</template>