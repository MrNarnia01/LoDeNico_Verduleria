<script>
    import axios from 'axios'
    export default{
        props: {
            empleado:Object,
        },
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
                rContra: '',
                id: 0,
            }
        },
        mounted(){
            this.id=this.empleado.id;
            this.empleadoRequest.nombre=this.empleado.nombre;
            this.empleadoRequest.apellido=this.empleado.apellido;
            this.empleadoRequest.codArea=this.empleado.codArea;
            this.empleadoRequest.tel=this.empleado.tel;
            this.empleadoRequest.mail=this.empleado.mail;
            this.empleadoRequest.contra=this.empleado.contra;
        },
        methods:{
            crear(){
                if(this.empleadoRequest.contra==this.rContra){
                    axios.post( 'http://localhost:8080/api/empleado/update/'+this.id,this.empleadoRequest).then(response => {
                        this.$emit('clou');
                    }).catch(error => {
                        console.log('Error: ', error.response.data);
                    });
                }else window.alert("Error entre las contraseñas");
                
            },
        },
    }
</script>

<template>
    <form @submit.prevent="crear">
        <span class="close" @click="$emit('cloc')">&times;</span>
        <table>
            

            <tr>
                <td><label for="tipo">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="empleadoRequest.nombre" required autocomplete="off"></td>
                <td><label for="tipo">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="empleadoRequest.apellido" required autocomplete="off"></td>
            </tr>

            <tr>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="empleadoRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="empleadoRequest.tel" :min="1" required></td>
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
        <button type="submit">Modificar</button>
    </form>
</template>