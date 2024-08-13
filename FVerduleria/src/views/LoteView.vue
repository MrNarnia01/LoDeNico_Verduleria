<script>
    import { RouterLink } from 'vue-router';
    import Lote from '@/components/Lote/Lote.vue'
    import axios from 'axios'
    import CreateLote from '@/components/Lote/CreateLote.vue'
    export default{
        components: {
            Lote,
            CreateLote,
        },
        data(){
            return{
                id: -1,
                producto: {
                    id:0,
                    nombre:'',
                    precio:0,
                    stock:0,
                    unit:false,
                    loteResponseList:[],
                },
                c:false,
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
                    const response = await axios.get('http://localhost:8080/api/producto/get/'+this.id);
                    this.producto=response.data;
                } catch (error) {
                    console.log(error.response.data)
                }
            },
            create(){
                this.c=!this.c;
                this.busqueda();
            },
        },
        computed:{
            unitario(){
                if(this.producto.unit){
                    return 'Si';
                }else return 'No';
            },
        },
    }
    
</script>


<template>
    <h1>Producto :</h1>

    <table v-if="this.id!=-1">
        <tr>
            <th>Nombre :</th>
            <td> <input type="text" v-model="producto.nombre" readonly> </td>
            <th>Precio :</th>
            <td> <input type="text" v-model="producto.precio" readonly> </td>
        </tr>
        <tr>
            <th>Stock :</th>
            <td> <input type="text" v-model="producto.stock" readonly> </td>
            <th>Unitario :</th>
            <td> <input type="text" v-model="unitario" readonly> </td>
        </tr>
        <tr>
            <th>Lotes</th>
        </tr>

        <tr v-for="lote in producto.loteResponseList" :key="lote.id">
            <Lote :lote="lote" @e="busqueda()" />
        </tr>
    </table>
    <button type="button" @click="create()">Crear Lote</button>
    <CreateLote v-if="this.c" :id="this.id" @cloc="create()" />

</template>