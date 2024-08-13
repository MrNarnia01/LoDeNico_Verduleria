<script>
    import axios from 'axios'
    export default {
        props: {
            producto:Object,
        },
        computed:{
            unitario(){
                if(this.producto.unit){
                    return 'Si';
                }else return 'No';
            },
            lotes(){
                return this.producto.loteResponseList.length;
            }
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/producto/delete/'+this.producto.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
            ver(){
                this.$router.push({
                    name: 'lote',
                    query: { id:this.producto.id }
                });
            }
        }
    }
</script>

<template>
    <td>{{ producto.nombre }}</td>
    <td>{{ producto.precio }}</td>
    <td>{{ producto.stock }}</td>
    <td>{{ unitario }}</td>
    <td>{{ lotes }}</td>
    <td><button type="button" @click="ver()">Ver Lotes</button></td>
    <td><button type="button" @click="$emit('mod',producto)"> Modificar </button></td>
    <td><button type="button" @click="borrar()"> Eliminar </button></td>
</template>