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
                ver: false,
            }
        },
        mounted(){
            this.id=this.empleado.id;
            this.empleadoRequest.nombre=this.empleado.nombre;
            this.empleadoRequest.apellido=this.empleado.apellido;
            this.empleadoRequest.codArea=this.empleado.codArea;
            this.empleadoRequest.tel=this.empleado.tel;
            this.empleadoRequest.mail=this.empleado.mail;
        },
        methods:{
            crear(){
                var mensajeError="La contraseña debe:\n";
                mensajeError+="Tener al menos una letra\n";
                mensajeError+="Tener al menos un numero\n";
                mensajeError+="Tener minimo 8 caracteres\n";
                mensajeError+="Tener maximo 16 caracteres\n";
                mensajeError+="No usar caracteres especiales";
                const regexAll = /^[a-zA-Z0-9]+$/;
                const regexLetra = /[a-zA-Z]/;
                const regexNum = /[0-9]/;
                if(regexAll.test(this.empleadoRequest.contra) &&
                    regexLetra.test(this.empleadoRequest.contra) &&
                    regexNum.test(this.empleadoRequest.contra) &&
                    this.empleadoRequest.contra.length>=8 &&
                    this.empleadoRequest.contra.length<=16
                ){
                    if(this.empleadoRequest.contra==this.rContra){
                        axios.post( 'http://localhost:8080/api/empleado/update/'+this.id,this.empleadoRequest).then(response => {
                            this.$emit('clou');
                        }).catch(error => {
                            console.log('Error: ', error.response.data);
                        });
                    }else window.alert("Error entre las contraseñas");
                }else window.alert(mensajeError);
            },
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
        <div class="close" @click="$emit('clou')">&times;</div>
    <form @submit.prevent="crear">
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
                <td><input type="text" id="mail" v-model="empleadoRequest.mail" required></td>
                <td><label for="con">Contraseña:</label></td>
                <td>
                    <input type="text" v-if="ver" id="con" v-model="empleadoRequest.contra" required>
                    <input type="password" v-else id="con" v-model="empleadoRequest.contra" required>
                </td>
                <td>
                    <input type="checkbox" v-model="ver">
                </td>
            </tr>

            <tr>
                <td><label for="rCon">Repetir contraseña:</label></td>
                <td><input type="password" id="rCon" v-model="rContra" required></td>
                <td colspan="3"><button type="submit" class="bot" id="nor">Modificar</button></td>
            </tr>
        </table>
        
    </form>
    </div>
</div>
</template>