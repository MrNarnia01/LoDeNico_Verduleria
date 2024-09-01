<template>
    <form @submit.prevent="crear">
        <table>
            <thead>
                <tr>
                    <td colspan="2">Proveedor</td>
                    <td colspan="3" v-if="pedido==null">
                        <select v-model="pedidoRequest.idP" required>
                            <option v-for="proveedor in proveedores" :key="proveedor.id" :value="proveedor.id">
                                {{ proveedor.negocio }}
                            </option>
                        </select>
                    </td>
                    <td colspan="3">
                        {{ findProveedor }}
                    </td>
                </tr>
                <tr>
                    <th>Producto</th>
                    <th>Cajas</th>
                    <th>Cantidad por caja</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
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
                    <td>
                        <input type="number" v-model.number="detalleRequest.cantidad" :min="1" />
                    </td>
                    <td @click="eliminarProducto(index)">
                        X
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <button type="button" @click="agregarFila(-1,1,1)">Agregar producto</button>
                    </td>
                </tr>
            </tbody>
        </table>
    
        <button type="submit" v-if="pedido==null">Crear</button>
        <button type="submit" v-else>Modificar</button>
    </form>
  </template>
  
  <script>
    import axios from 'axios'
    export default {
    props: ['pedido'],
    data() {
      return {
        productos: '',
        proveedores: '',
        pedidoRequest:  {
            idP:0,
            detallePedidoRequestList:[],
        },
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
        
        this.lProductos();
        this.lProveedores();
    },
    methods: {
        eliminarProducto(index) {
            this.pedidoRequest.detallePedidoRequestList.splice(index, 1);
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
        async lProductos(){
            try {
                const response = await axios.get('http://localhost:8080/api/producto/list/'+true);
                this.productos=response.data;
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
    },
    computed:{
        findProveedor(){
            for(let i=0;i<this.proveedores.length;i++){
                if(this.proveedores[i].id==this.pedido.idP){
                    return this.proveedores[i].negocio
                }
            }
            return "nada"
        },
    }

  }
  </script>