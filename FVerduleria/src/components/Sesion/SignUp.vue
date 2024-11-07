<template>
    <div class="midDiv" id="img"></div>
    <div class="midDiv" id="info">
        <h1 class="titulo" id="lodenico">Lo De Nico</h1>
        <div>
            <form @submit.prevent="signUp">
                <h1 class="ini">Inicio de<br>sesion</h1>
                <p><input type="password" required v-model="datos.datos_cuenta" placeholder="Contraseña" class="dat"></p>
                <p><input type="submit" value="Iniciar" class="bot"></p>
                <p><input type="button" value="Olvide mi contraseña" class="bot" @click="recover()"></p>
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
            };
        },
        mounted(){
        },
        methods: {
            signUp(){
                axios.post('http://localhost:8080/api/empleado/signUp',this.datos).then(response => {
                    this.$emit('signUp');
                }).catch(error => {
                    window.alert(error.response.data);
                });
            },
            recover(){
                this.$router.push('/recover');
            }
        },
    }
</script>