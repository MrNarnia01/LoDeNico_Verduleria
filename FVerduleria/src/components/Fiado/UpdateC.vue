<script>
    import axios from 'axios'
    export default{
        props: {
            cli:Object,
        },
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
            }
        },
        mounted(){
            console.log(this.cli)
            this.clienteRequest.nombre=this.cli.nombre;
            this.clienteRequest.apellido=this.cli.apellido;
            this.clienteRequest.codArea=this.cli.codArea;
            this.clienteRequest.tel=this.cli.tel;
            this.clienteRequest.calle=this.cli.calle;
            this.clienteRequest.altura=this.cli.altura;
        },
        methods:{
            crear(){
                axios.post( 'http://localhost:8080/api/cliente/update/'+this.cli.id,this.clienteRequest).then(response => {
                    this.$emit('clocli');
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
        <div class="close" @click="$emit('clocli')">&times;</div>
    <form @submit.prevent="crear">
        <table>

            <tr>
                <td><label for="nombre">Nombre:</label></td>
                <td><input type="text" id="nombre" v-model="clienteRequest.nombre" required autocomplete="off"></td>
                <td><label for="apellido">Apellido:</label></td>
                <td><input type="text" id="apellido" v-model="clienteRequest.apellido" required autocomplete="off"></td>
            </tr>

            <tr>
                <td><label for="codArea">Codigo de area:</label></td>
                <td><input type="number" id="codArea" v-model="clienteRequest.codArea" :min="1" required></td>
                <td><label for="tel">Telefono:</label></td>
                <td><input type="number" id="tel" v-model="clienteRequest.tel" :min="1" required></td>
            </tr>

            <tr>
                <td><label for="calle">Calle:</label></td>
                <td><input type="text" id="calle" v-model="clienteRequest.calle" required autocomplete="off"></td>
                <td><label for="alt">Altura:</label></td>
                <td><input type="number" id="alt" v-model="clienteRequest.altura" :min="1" required></td>
            </tr>

            <tr>
                <td colspan="4"><button type="submit" class="bot" id="nor">Modificar</button></td>
            </tr>
            <tfoot>
                <tr>
                    <td colspan="4">
                        Advertencia: cualquier datos que sea cambiado aqui sera permanente si la persona esta registrarda como un empleado o un trabajador
                    </td>
                </tr>
            </tfoot>
        </table>
    </form>
        </div>
        </div>
</template>