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
    <form @submit.prevent="crear">
        <span class="close" @click="$emit('cloc')">&times;</span>
        <table>
            <tr>
                <td><label for="per">Seleccionar persona ya existente:</label></td>
                <td><input type="checkbox" id="per" v-model="creada"></td>
            </tr>

            <select v-if="this.creada" v-model="pS" @change="setearPersona()">
                <option v-for="persona in personas" :key="persona.id" :value="persona">
                    {{ persona.nombre }} {{ persona.apellido }} Num: {{ persona.codArea }}-{{ persona.tel }}
                </option>
            </select>

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
                <td colspan="2"><button type="submit">Crear</button></td>
            </tr>
        </table>
        
    </form>
</template>