<template>
    <li @click="ir('/',0)">Lo De Nico</li>

    <li v-if="this.sel!=1" @click="ir('producto',1)" class="botH">Productos</li>
    <li v-else class="sel">Productos</li>
    
    <li v-if="this.sel!=2" @click="ir('proveedor',2)" class="botH">Proveedores</li>
    <li v-else class="sel">Proveedores</li>
    
    <li v-if="this.sel!=3" @click="ir('pedidoList',3)" class="botH">Pedidos</li>
    <li v-else class="sel">Pedidos</li>
    
    <li v-if="this.sel!=4" @click="ir('boletaList',4)" class="botH">Boletas</li>
    <li v-else class="sel">Boletas</li>
    
    <li v-if="this.sel!=5" @click="ir('empleado',5)" class="botH">Empleado</li>
    <li v-else class="sel">Empleado</li>
    
    <li v-if="this.sel!=6" @click="ir('fiado',6)" class="botH">Fiado</li>
    <li v-else class="sel">Fiado</li>

</template>

<script>
  import { RouterLink, RouterView } from 'vue-router'
  export default {
    data(){
        return{
            sel:0,
        };
    },
    methods:{
        ir(lugar, s){
            this.$router.push(lugar);
            this.sel=s;
        },
    }
  }
</script>