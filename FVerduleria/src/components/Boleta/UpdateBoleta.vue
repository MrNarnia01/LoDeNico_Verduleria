<template>
    <form @submit.prevent="crear">
        <span class="close" @click="$emit('cloc')">&times;</span>
        <table>
            <thead>
                <tr>
                    <td >Proveedor</td>
                    <td>
                        {{ this.proveedor }}
                    </td>
                </tr>
                <tr>
                    <td><label for="numB">Numero de boleta:</label></td>
                    <td><input type="number" id="numB" v-model="boletaRequest.numB" :min="0" required></td>
                </tr>
                <tr>
                    <td><label for="monto">Monto:</label></td>
                    <td v-if="aPagar"><input type="number" id="monto" v-model="boletaRequest.monto" :min="0" step="0.01" required></td>
                    <td v-else><input type="number" id="monto" v-model="boletaRequest.monto" readonly></td>
                </tr>
                <tr>
                    <th>Producto</th>
                    <th>Cajas</th>
                    <th>Cantidad por caja</th>
                    <th>Eliminar</th>
                </tr>
            </thead>
            <tbody>
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
    
        <button type="submit">Modificar</button>
    </form>
  </template>

<script>
    import axios from 'axios'
    export default {
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
            }
        },
        mounted(){
            console.log(this.id)
            this.busDatos();
            this.lProductos();
        },
        methods: {
            agregarFila(i,c,ca){
                this.boletaRequest.detalleBoletaRequestList.push({
                    idP: i,
                    caja: c,
                    cantidad: ca,
                });
            },
            async lProductos(){
                try {
                    const response = await axios.get('http://localhost:8080/api/producto/list/'+false);
                    this.productos=response.data;
                } catch (error) {
                    console.log(error)
                    this.productos=''
                }
            },
            async busDatos(){
                try {
                    const response = await axios.get('http://localhost:8080/api/boleta/get/'+this.id);
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
                this.boletaRequest.detalleBoletaRequestList.splice(index, 1);
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
                    this.$emit('cloc');
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            },
        },
    }
</script>

