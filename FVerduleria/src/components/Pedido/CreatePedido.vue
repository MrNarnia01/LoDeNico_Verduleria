<template>
    <div class="popUp">
    <div class="popUp-content">  
      <div class="close" @click="$emit('cloc')">&times;</div>
    <form @submit.prevent="crear">
        <h3 v-if="pedido==null">Crear pedido</h3>
        <h3 v-else>Modificar pedido</h3>
        <table>
                <tr>
                    <td >Proveedor</td>
                    <td v-if="pedido==null">
                        <select v-model="pedidoRequest.idP" required>
                            <option v-for="proveedor in proveedores" :key="proveedor.id" :value="proveedor.id">
                                {{ proveedor.negocio }}
                            </option>
                        </select>
                    </td>
                    <td v-else>
                        {{ findProveedor }}
                    </td>
                    <td>
                        <button type="button" class="bot" @click="agregarFila(-1,1,1)">Agregar producto</button>
                    </td>
                    <td>
                        <button type="button" class="bot" @click="create()">Nuevo producto</button>
                    </td>
                    <td>
                        <button type="submit" class="bot" v-if="pedido==null">Crear</button>
                        <button type="submit" class="bot" v-else>Modificar</button>
                    </td>
                </tr>
                <tr>
                    <th>Producto</th>
                    <th>Cajas</th>
                    <th colspan="2">Cantidad por caja</th>
                    <th>Eliminar</th>
                </tr>
                <tr v-for="(detalleRequest, index) in pedidoRequest.detallePedidoRequestList" >
                    <td>
            <select v-model="detalleRequest.idP" @change="validarProducto(index)" required>
                <option v-for="producto in productos" :key="producto.id" :value="producto.id">
                    {{ producto.nombre }}
                </option>
            </select>
                    </td>
                    <td>
                        <input type="number" v-model.number="detalleRequest.caja" :min="1" />
                    </td>
                    <td colspan="2">
                        <input type="number" v-model.number="detalleRequest.cantidad" :min="1" />
                    </td>
                    <td @click="eliminarProducto(index)" class="closel">
                        &times;
                    </td>
                </tr>
            </table>
    </form>

        <CreateProducto v-if="this.c" @cloc="create()" @clox="this.c=!this.c" />
    </div>
    </div>
  </template>
  
  <script>
    import axios from 'axios'
import CreateProducto from '../Producto/CreateProducto.vue';
    export default {
        components: {
            CreateProducto,
        },
    props: ['pedido'],
    data() {
      return {
        productos: '',
        proveedores: '',
        pedidoRequest:  {
            idP:0,
            detallePedidoRequestList:[],
        },
        c: false,
      }
    },
    mounted(){
        if(this.pedido!=null){
            this.pedidoRequest.idP=this.pedido.idP;
            const detallesP = this.pedido.detallePedidoResponseList;
            for(let i=0; i<detallesP.length;i++){
                this.agregarFila(detallesP.at(i).idProdu,detallesP.at(i).caja,detallesP.at(i).cantidad);
            }
        }else{
            this.agregarFila(-1,1,1);
        }
        
        this.lProductos(false);
        this.lProveedores();
    },
    methods: {
        eliminarProducto(index) {
                if(this.pedidoRequest.detallePedidoRequestList.length>1){
                    this.pedidoRequest.detallePedidoRequestList.splice(index, 1);
                }
        },
        crear(){
            let a = "";
            if(this.pedido==null)   a+="create";
            else a+="update/"+this.pedido.id;
            console.log(this.pedidoRequest);
            axios.post( 'http://localhost:8080/api/pedido/'+a,this.pedidoRequest,{
                headers: { 'Content-Type': 'application/json' }
            } ).then(response => {
            this.$emit('cloc');
            }).catch(error => {
                console.log('Error: ', error.response.data);
            });
        },
        async lProductos(b){
            try {
                const response = await axios.get('http://localhost:8080/api/producto/list/'+false);
                this.productos=response.data;
                if(b){
                    const index = (this.productos.length-1);
                    this.agregarFila(this.productos[index].id,1,1);
                }
            } catch (error) {
                console.log(error)
                this.productos=''
            }
        },
        async lProveedores(){
            try {
                const response = await axios.get('http://localhost:8080/api/proveedor/list');
                this.proveedores=response.data;
            } catch (error) {
                console.log(error.response.data)
                this.proveedores=''
            }
        },
        agregarFila(i,c,ca){
            this.pedidoRequest.detallePedidoRequestList.push({
                idP: i,
                caja: c,
                cantidad: ca,
            });
        },
        validarProducto(index){
            let detalles = this.pedidoRequest.detallePedidoRequestList;
            let con=0;
            let id = detalles.at(index);
            for(let i=0; i<detalles.length;i++){
                if(detalles[i].idP==id.idP){
                    con++;
                }
            }
            
            if(con>1){
                window.alert("Producto ya cargado en la lista");
                this.pedidoRequest.detallePedidoRequestList.at(index).idP=-1;
            }
    
        },
        create(){
                this.c=!this.c;
                var id = this.productos.length;
                this.lProductos(!this.c);   
            },
    },
    computed:{
        findProveedor(){
            for(let i=0;i<this.proveedores.length;i++){
                if(this.proveedores[i].id==this.pedido.idP){
                    return this.proveedores[i].negocio
                }
            }
        },
    }

  }
  </script>