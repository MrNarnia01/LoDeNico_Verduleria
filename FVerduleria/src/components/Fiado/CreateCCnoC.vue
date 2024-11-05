<script>
    import axios from 'axios'
    export default{
        data() {
            return {
                clienteRequest:  {
                    nombre: '',
                    apellido: '',
                    codArea: 1,
                    tel: 1,
                    calle: '',
                    altura: 0,
                    monto: 0.0,
                },
                personas:[],
                creada: false,
                pS: null,
            }
        },
        mounted(){
            this.busqueda();
        },
        methods:{
            crear(){
                axios.post( 'http://localhost:8080/api/cuenta/create/nC',this.clienteRequest).then(response => {
                    this.$emit('cloc');
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            },
            async busqueda(){
                try {
                    const response = await axios.get('http://localhost:8080/api/persona/list');
                    this.personas=response.data;
                    console.log(this.personas);
                } catch (error) {
                    console.log(error.response.data)
                }
            },
            setearPersona(){
                this.clienteRequest.nombre=this.pS.nombre;
                this.clienteRequest.apellido=this.pS.apellido;
                this.clienteRequest.codArea=this.pS.codArea;
                this.clienteRequest.tel=this.pS.tel;
            }
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
        <div class="close" @click="$emit('cloc')">&times;</div>
    <form @submit.prevent="crear">
        <table>
            <tr>
                <td><label for="per">Seleccionar persona ya existente:</label></td>
                <td><input type="checkbox" id="per" v-model="creada"></td>
                <td colspan="2">
                    <select v-if="this.creada" v-model="pS" @change="setearPersona()">
                <option v-for="persona in personas" :key="persona.id" :value="persona">
                    {{ persona.nombre }} {{ persona.apellido }} Num: {{ persona.codArea }}-{{ persona.tel }}
                </option>
            </select>
            <select v-else class="bot"></select>
                </td>
            </tr>

            <tr v-if="!this.creada">
                <td><label for="nombre">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="clienteRequest.nombre" required autocomplete="off"></td>
                <td><label for="apellido">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="clienteRequest.apellido" required autocomplete="off"></td>
            </tr>
            <tr v-else>
                <td><label for="nombre">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="clienteRequest.nombre" readonly></td>
                <td><label for="apellido">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="clienteRequest.apellido" readonly></td>
            </tr>

            <tr v-if="!this.creada">
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="clienteRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="clienteRequest.tel" :min="1" required></td>
            </tr>
            <tr v-else>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="clienteRequest.codArea" readonly></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="clienteRequest.tel" readonly></td>
            </tr>

            <tr>
                <td><label for="calle">Calle:</label></td>
                <td><input type="text" id="calle" v-model="clienteRequest.calle" required autocomplete="off"></td>
                <td><label for="alt">Altura:</label></td>
                <td><input type="number" id="alt" v-model="clienteRequest.altura" :min="1" required></td>
            </tr>

            <tr>
                <td><label for="precio">Monto:</label></td>
                <td><input type="number" id="precio" v-model="clienteRequest.monto" :min="0" step="0.01" required></td>
                <td colspan="2"><button type="submit" class="bot">Registrar</button></td>
            </tr>
        </table>
    </form>
        </div>
        </div>
</template>