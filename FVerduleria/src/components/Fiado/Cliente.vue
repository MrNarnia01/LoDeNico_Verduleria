<script>
    export default {
        props: {
            cliente:Object,
        },
        computed:{
            cuentas(){
                return this.cliente.cuentaCorrienteResponseList.length;
            }
        },
        methods: {
            ver(){
                this.$router.push({
                    name: 'cliente',
                    query: { id:this.cliente.id }
                });
                
            }
        }
    }
</script>

<template>
    <td>{{ cliente.nombre }} {{ cliente.apellido }}</td>
    <td>{{ cliente.codArea }}-{{ cliente.tel }}</td>
    <td>{{ cliente.calle }} {{ cliente.altura }}</td>
    <td>{{ cuentas }}</td>
    <td><button type="button" @click="ver()">Ver Cuentas</button></td>
    <td><button type="button" @click="$emit('mod')"> Modificar </button></td>
</template>