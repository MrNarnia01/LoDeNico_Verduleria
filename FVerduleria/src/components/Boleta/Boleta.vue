<script>
    import axios from 'axios'
    import moment from 'moment';
    export default {
        props: {
            boleta:Object,
        },
        computed:{
            fechas(){
                console.log(this.boleta)
                return moment(this.boleta.fRecibo).format('DD/MM/YYYY');
            },
            paga(){
                if(this.boleta.paga){
                    return 'Si';
                }else return 'No';
            },
            lotes(){
                return this.boleta.detalleBoletaResponseList.length;
            }
        },
        methods: {
            borrar(){
                axios.delete( 'http://localhost:8080/api/boleta/delete/'+this.boleta.id ).then(response => {
                    this.$emit('e');
                }).catch(error => {
                        console.log(error.response.data);
                });
            },
        }
    }
</script>

<template>
    <td>{{ boleta.nb }}</td>
    <td>{{ boleta.negocio }}</td>
    <td>{{ fechas }}</td>
    <td>{{ lotes }}</td>
    <td>{{ paga }}</td>
    <td>{{ boleta.monto }}</td>
    <td><button type="button" @click="$emit('mod')">Modificar</button></td>
    <td><button type="button" @click="borrar()">Eliminar</button></td>
</template>