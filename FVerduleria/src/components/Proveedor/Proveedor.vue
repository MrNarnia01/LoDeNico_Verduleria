<script>
    import axios from 'axios'
    export default {
        props: {
            proveedor:Object,
        },
        computed:{
            trabajadores(){
                return this.proveedor.trabajadorResponseList.length;
            }
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/proveedor/delete/'+this.proveedor.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            ver(){
                this.$router.push({
                    name: 'trabajador',
                    query: { id:this.proveedor.id }
                });
                
            }
        }
    }
</script>

<template>
    <td>{{ proveedor.negocio }}</td>
    <td>{{ proveedor.calle }}</td>
    <td>{{ proveedor.altura }}</td>
    <td>{{ trabajadores }}</td>
    <td><button type="button" @click="ver()">Ver Trabajadores</button></td>
    <td><button type="button" @click="$emit('mod',proveedor)"> Modificar </button></td>
    <td><button type="button" @click="borrar()"> Eliminar </button></td>
</template>