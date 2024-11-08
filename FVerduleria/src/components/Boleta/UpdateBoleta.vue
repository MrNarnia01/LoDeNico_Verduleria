<template>
    <div class="popUp">
        <div class="popUp-content">
    <div class="close" @click="$emit('clou')">&times;</div>
    <form @submit.prevent="crear">
        <h3>Modificar boleta</h3>
        <table>
                <tr>
                    <td >Proveedor</td>
                    <td>
                        {{ this.proveedor }}
                    </td>
                    <td >
                        <button type="button" @click="agregarFila(-1,1,1)" class="bot">Agregar producto</button>
                    </td>
                    <td>
                        <button type="button" class="bot" @click="create()">Nuevo producto</button>
                    </td>
                    <td>
                        <button type="submit" class="bot">Modificar</button>
                    </td>
                </tr>
                <tr>
                    <td><label for="numB">Numero de boleta:</label></td>
                    <td><input type="number" id="numB" v-model="boletaRequest.numB" :min="1" required></td>

                    <td colspan="2"><label for="monto">Monto:</label></td>
                    <td v-if="aPagar"><input type="number" id="monto" v-model="boletaRequest.monto" :min="0.01" step="0.01" required></td>
                    <td v-else><input type="number" id="monto" v-model="boletaRequest.monto" readonly></td>
                </tr>
                <tr>
                    <th>Producto</th>
                    <th>Cajas</th>
                    <th colspan="2">Cantidad por caja</th>
                    <th>Eliminar</th>
                </tr>
                <tr v-for="(detalleRequest, index) in boletaRequest.detalleBoletaRequestList" >
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
        props: {
            id:Object,
        },
        data() {
            return {
                productos: '',
                boletaRequest:  {
                    numB:0,
                    idP:0,
                    monto:0,
                    detalleBoletaRequestList:[],
                },
                proveedor: '',
                aPagar: false,
                c: false,
            }
        },
        mounted(){
            console.log(this.id)
            this.busDatos();
            this.lProductos(false);
            this.aPagar=this.pagos;
        },
        methods: {
            agregarFila(i,c,ca){
                this.boletaRequest.detalleBoletaRequestList.push({
                    idP: i,
                    caja: c,
                    cantidad: ca,
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
            async busDatos(){
                try {
                    const response = await axios.get('http://localhost:8080/api/boleta/get/'+this.id);
                    if(response.data.pagoResponseList.length==0) this.aPagar=true;
                    this.setearDatos(response.data);
                } catch (error) {
                    console.log(error)
                }
            },
            setearDatos(response){
                this.boletaRequest.idP=response.idP;
                this.boletaRequest.numB=response.nb;
                this.boletaRequest.monto=response.monto;
                this.proveedor=response.negocio;
                if(response.aPagar==response.monto){
                    this.aPagar=true;
                }
                const detallesP = response.detalleBoletaResponseList;
                for(let i=0; i<detallesP.length;i++){
                    this.agregarFila(
                        detallesP.at(i).idProdu,
                        detallesP.at(i).caja,
                        detallesP.at(i).cantidad);
                }

            },
            eliminarProducto(index) {
                if(this.boletaRequest.detalleBoletaRequestList.length>1){
                    this.boletaRequest.detalleBoletaRequestList.splice(index, 1);
                }
            },
            validarProducto(index){
                let detalles = this.boletaRequest.detalleBoletaRequestList;
                let con=0;
                let id = detalles.at(index);
                for(let i=0; i<detalles.length;i++){
                    if(detalles[i].idP==id.idP){
                        con++;
                    }
                }
                
                if(con>1){
                    window.alert("Producto ya cargado en la lista");
                    this.boletaRequest.detalleBoletaRequestList.at(index).idP=-1;
                }
        
            },
            crear(){
                axios.post( 'http://localhost:8080/api/boleta/update/'+this.id,this.boletaRequest,).then(response => {
                    this.$emit('clou');
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            },
            create(){
                this.c=!this.c;
                var id = this.productos.length;
                this.lProductos(!this.c);   
            },
        },
    }
</script>

