<template>
    <div class="midDiv" id="img"></div>
    <div class="midDiv" id="info">
        <h1 class="titulo" id="lodenico">Lo De Nico</h1>
        <div>
            <form @submit.prevent="signUp">
                <h1 class="ini"> {{ info }} </h1>
                <p><input type="text" required v-model="datos.datos_cuenta" class="dat"></p>
                <p>Email para cambiar la contraseña</p>
                <p><input type="submit" value="Solicitar cambio" class="bot"></p>
            </form>
        </div>
        
    </div>
</template>

<script>
    import axios from 'axios';

    export default{
        data(){
            return{
                datos: {
                    datos_cuenta:'',
                },
                info: 'Recuperacion de\ncontraseña',
            };
        },
        methods: {
            signUp(){
                axios.post('http://localhost:8080/api/empleado/recover',this.datos).then(response => {
                    console.log(response.data)
                    this.renovar(response.data);
                }).catch(error => {
                    window.alert(error.response.data);
                });
            },
            renovar(data){
                this.$router.push({
                        name: 'newPass',
                        query: { 
                            id:data.id,
                            nombre:data.nombre,
                            apellido:data.apellido 
                        }
                    });
            }
        },
    }
</script>