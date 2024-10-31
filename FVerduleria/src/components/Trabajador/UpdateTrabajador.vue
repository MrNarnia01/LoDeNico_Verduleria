<script>
    import axios from 'axios'
    export default{
        props: {
            trabajador:Object,
            negocio:Object,
        },
        data() {
            return {
                id:0,
                trabajadorRequest:  {
                    idP: 0,
                    puesto: '',
                    nombre: '',
                    apellido: '',
                    codArea: 1,
                    tel: 1,
                },
            }
        },
        mounted(){
            this.trabajadorRequest.puesto=this.trabajador.puesto;
            this.trabajadorRequest.nombre=this.trabajador.nombre;
            this.trabajadorRequest.apellido=this.trabajador.apellido;
            this.trabajadorRequest.codArea=this.trabajador.codArea;
            this.trabajadorRequest.tel=this.trabajador.tel;
            this.id=this.trabajador.id;
        },
        methods:{
            crear(){
                axios.post( 'http://localhost:8080/api/trabajador/update/'+this.id,this.trabajadorRequest).then(response => {
                    this.$emit('clou');
                }).catch(error => {
                    console.log('Error: ', error.response.data);
                });
            },
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
        <div class="close" @click="$emit('clou')">&times;</div>
        <form @submit.prevent="crear">
        <h3>Modificar trabajador</h3>
        <table>
            <tr>
                <td><label for="tipo">Negocio:</label></td>
                <td> {{ negocio }} </td>
                <td><label for="tipo">Puesto:</label></td>
                <td><input type="text" id="puesto" v-model="trabajadorRequest.puesto" required autocomplete="off"></td>
            </tr>
            <tr>
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="trabajadorRequest.nombre" required autocomplete="off"></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="trabajadorRequest.apellido" required autocomplete="off"></td>
            </tr>
            <tr>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="trabajadorRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="trabajadorRequest.tel" :min="1" required></td>
            </tr>
            <tr>
                <td colspan="4">
                    <button type="submit" class="bot" id="nor">Modificar</button>
                </td>
            </tr>
        </table>
    </form>
    </div>
    </div>
</template>