<script>
    import axios from 'axios'
    export default{
        data() {
            return {
                empleadoRequest:  {
                    nombre: '',
                    apellido: '',
                    codArea: 1,
                    tel: 1,
                    mail: '',
                    contra: '',
                },
                personas:[],
                creada: false,
                pS: null,
                rContra: '',
            }
        },
        mounted(){
            this.busqueda();
        },
        methods:{
            crear(){
                if(this.empleadoRequest.contra==this.rContra){
                    axios.post( 'http://localhost:8080/api/empleado/create',this.empleadoRequest).then(response => {
                        this.$emit('cloc');
                    }).catch(error => {
                        console.log('Error: ', error.response.data);
                    });
                }else window.alert("Error entre las contraseñas");
                
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
                this.empleadoRequest.nombre=this.pS.nombre;
                this.empleadoRequest.apellido=this.pS.apellido;
                this.empleadoRequest.codArea=this.pS.codArea;
                this.empleadoRequest.tel=this.pS.tel;
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
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="empleadoRequest.nombre" required autocomplete="off"></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="empleadoRequest.apellido" required autocomplete="off"></td>
            </tr>
            <tr v-else>
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="empleadoRequest.nombre" readonly></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="empleadoRequest.apellido" readonly></td>
            </tr>

            <tr v-if="!this.creada">
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="empleadoRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="empleadoRequest.tel" :min="1" required></td>
            </tr>
            <tr v-else>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="empleadoRequest.codArea" readonly></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="empleadoRequest.tel" readonly></td>
            </tr>

            <tr>
                <td><label for="mail">Mail:</label></td>
                <td colspan="3"><input type="text" id="mail" v-model="empleadoRequest.mail" required></td>
            </tr>

            <tr>
                <td><label for="con">Contraseña:</label></td>
                <td><input type="password" id="con" v-model="empleadoRequest.contra" required></td>
                <td><label for="rCon">Repetir contraseña:</label></td>
                <td><input type="password" id="rCon" v-model="rContra" required></td>
            </tr>
        </table>
        <button type="submit">Crear</button>
    </form>
</template>