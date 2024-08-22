<template>
    <form @submit.prevent="crear">
        <table>
            <thead>
                <tr>
                    <td colspan="2">Proveedor</td>
                    <td colspan="3">
                        <select v-model="pedidoRequest.idP" required>
                            <option v-for="proveedor in proveedores" :key="proveedor.id" :value="proveedor.id">
                                {{ proveedor.negocio }}
                            </option>
                        </select>
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
                        <button type="button" @click="agregarFila()">Agregar producto</button>
                    </td>
                </tr>
            </tbody>
        </table>
    
        <button type="submit">Crear</button>
    </form>
  </template>
  
  <script>
    import axios from 'axios'
    export default {
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
        this.lProductos();
        this.lProveedores();
        this.agregarFila();
    },
    methods: {
        eliminarProducto(index) {
            this.pedidoRequest.detallePedidoRequestList.splice(index, 1);
        },
        crear(){
            axios.post( 'http://localhost:8080/api/pedido/create',this.pedidoRequest ).then(response => {
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
        agregarFila(){
            this.pedidoRequest.detallePedidoRequestList.push({
                idP: 0,
                caja: 1,
                cantidad: 1,
            });
        },
        validarProducto(index){
            /*
            let detalles = this.pedidoRequest.detallePedidoRequestList;
            let id = detalles.at(index);
            const duplicado = detalles.find(detalle => detalle.idP === id.idP);
            console.log(duplicado)
            if(duplicado){
                window.alert("Producto ya cargado en la lista");
                this,pedidoRequest.detallePedidoRequestList.at(index).idP=0;
            }
                */
        },
    }
  }
  </script>