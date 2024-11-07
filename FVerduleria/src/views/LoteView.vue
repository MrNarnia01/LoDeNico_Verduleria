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
                    console.log(response.data)
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
    <div  class="pag"></div>
    <table class="view">
        <tr>
            <th class="tit" colspan="4">Producto</th>
        </tr>
        <tr>
            <th class="tit">Nombre :</th>
            <td> {{ producto.nombre }} </td>
            <th class="tit">Precio :</th>
            <td> {{ producto.precio }}</td>
        </tr>
        <tr>
            <th class="tit">Stock :</th>
            <td> {{ producto.stock }}</td>
            <th class="tit">Unitario :</th>
            <td> {{ unitario }} </td>
        </tr>
        <tr>
            <th colspan="3" class="tit">Lotes</th>
            <td @click="create()" colspan="3" class="bot">Registrar lote</td>
        </tr>
        <tr v-for="lote in producto.loteResponseList" :key="lote.id">
            <Lote :lote="lote" @e="busqueda()" />
        </tr>
    </table> 
    <CreateLote v-if="this.c" :id="this.id" @cloc="create()" />
</template>