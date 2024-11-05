<script>
    import axios from 'axios'
    export default{
        props: {
            id:Object,
            negocio:Object,
        },
        data() {
            return {
                trabajadorRequest:  {
                    idP: 0,
                    puesto: '',
                    nombre: '',
                    apellido: '',
                    codArea: 1,
                    tel: 1,
                },
                personas:[],
                creada: false,
                pS: null,
            }
        },
        mounted(){
            this.trabajadorRequest.idP=this.id;
            this.busqueda();
        },
        methods:{
            crear(){
                axios.post( 'http://localhost:8080/api/trabajador/create',this.trabajadorRequest).then(response => {
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
                this.trabajadorRequest.nombre=this.pS.nombre;
                this.trabajadorRequest.apellido=this.pS.apellido;
                this.trabajadorRequest.codArea=this.pS.codArea;
                this.trabajadorRequest.tel=this.pS.tel;
            }
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
    <form @submit.prevent="crear">
        <div class="close" @click="$emit('cloc')">&times;</div>
        <h3>Crear trabajador</h3>
        <table>
            <tr>
                <td><label for="tipo">Negocio:</label></td>
                <td> {{ negocio }} </td>
                <td><label for="tipo">Puesto:</label></td>
                <td><input type="text" id="puesto" v-model="trabajadorRequest.puesto" required autocomplete="off"></td>
            </tr>
            <tr>
                <td><label for="per">Persona ya existente:</label></td>
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
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="trabajadorRequest.nombre" required autocomplete="off"></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="trabajadorRequest.apellido" required autocomplete="off"></td>
            </tr>
            <tr v-else>
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="trabajadorRequest.nombre" readonly></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="trabajadorRequest.apellido" readonly></td>
            </tr>

            <tr v-if="!this.creada">
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="trabajadorRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="trabajadorRequest.tel" :min="1" required></td>
            </tr>
            <tr v-else>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="trabajadorRequest.codArea" readonly></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="trabajadorRequest.tel" readonly></td>
            </tr>
            <tr>
                <td colspan="4">
                    <button type="submit" class="bot" id="nor">Crear</button>
                </td>
            </tr>
        </table>
    </form>
    </div>
    </div>
</template>