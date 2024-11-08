<template>
    <div class="midDiv" id="img">
        <div class="warnings">
            <table>
              <tr>
                  <td colspan="2">La contraseña debe contener al menos:</td>
              </tr>
              <tr>
                  <td>Al menos una letra</td>
                  <td v-if="this.checks.letra" class="signo" id="tilde">&#x2714;</td>
                  <td v-else class="signo" id="cruz">&#x2718;</td>
              </tr>
              <tr>
                  <td>Al menos un numero</td>
                  <td v-if="this.checks.num" class="signo" id="tilde">&#x2714;</td>
                  <td v-else class="signo" id="cruz">&#x2718;</td>
              </tr>
              <tr>
                  <td>Minimo de 8 caracteres</td>
                  <td v-if="this.checks.min" class="signo" id="tilde">&#x2714;</td>
                  <td v-else class="signo" id="cruz">&#x2718;</td>
              </tr>
              <tr>
                  <td>Maximo de 16 caracteres</td>
                  <td v-if="this.checks.max" class="signo" id="tilde">&#x2714;</td>
                  <td v-else class="signo" id="cruz">&#x2718;</td>
              </tr>
              <tr>
                <td colspan="3"id="warn">&#x26a0;</td>
              </tr>
              <tr><td colspan="3">No puede contener</td></tr>
              <tr><td colspan="3">caracteres especiales</td></tr>
          </table>
        </div>
    </div>
    <div class="midDiv" id="info">
        <h1 class="titulo" id="lodenico">Lo De Nico</h1>
        <div>
            <form @submit.prevent="change">
                <h1 class="ini"> {{ info }} </h1>
                <h1 class="nom"> {{ nombre }} {{ apellido }} </h1>
                <p>
                    <input type="checkbox" v-model="ver">
                    <input type="text" v-if="ver" required v-model="contra" class="dat" >
                    <input type="password" v-else required v-model="contra" class="dat" >
                </p>
                <p>
                    <input type="checkbox" v-model="ver2">
                    <input type="text" v-if="ver2" required v-model="contra2" class="dat">
                    <input type="password" v-else required v-model="contra2" class="dat">
                </p>
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
                contra: '',
                contra2: '',
                info: 'Nueva contraseña:',
                id: 0,
                nombre: '',
                apellido: '',
                checks:{
                    letra:false,
                    num:false,
                    min:false,
                    max:true,
                },
                ver: '',
                ver2: '',
            };
        },
        mounted(){
            this.id=this.$route.query.id;
            this.nombre=this.$route.query.nombre;
            this.apellido=this.$route.query.apellido;
        },
        methods: {
            change(){
                const regex = /^[a-zA-Z0-9]+$/;
                if (regex.test(this.contra)) {
                    if(this.checks.letra &&
                        this.checks.num &&
                        this.checks.min &&
                        this.checks.max){

                        if(this.contra==this.contra2){
                            this.datos.datos_cuenta=this.contra;
                            axios.post('http://localhost:8080/api/empleado/contra/'+this.id,this.datos).then(response => {
                                this.$router.push('/signUp');
                            }).catch(error => {
                                window.alert(error.response.data);
                            });
                        }else window.alert("Error, las contraseñas no coinciden");
                    }else window.alert("Contraseña invalida, no cumple con las especificaiones");
                }else window.alert("Contraseña invalida, lleva caracteres especiales");
                
            },
        },
        watch:{
            contra(){
                if(this.contra.length>=8) this.checks.min=true;
                else this.checks.min=false;
                if(this.contra.length<=16) this.checks.max=true;
                else this.checks.max=false;
                var regex = /[a-zA-Z]/;
                if (regex.test(this.contra)) this.checks.letra=true;
                else this.checks.letra=false;
                regex = /[0-9]/;
                if (regex.test(this.contra)) this.checks.num=true;
                else this.checks.num=false;
            }
        },
    }
</script>