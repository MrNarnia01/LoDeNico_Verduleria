<script>
    import axios from 'axios';
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
            },
            pagos(){
                if(this.boleta.pagoResponseList.length==0){
                    return true;
                }else false;
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
            boleta(){
                this.$router.push({
                    name: 'pagoList',
                    query: { id:this.boleta.id }
                });
            }
        }
    }
</script>

<template>
    <td>{{ this.boleta.nb }}</td>
    <td>{{ this.boleta.negocio }}</td>
    <td>{{ fechas }}</td>
    <td>{{ lotes }}</td>
    <td>{{ paga }}</td>
    <td>{{ this.boleta.monto }}</td>
    <td @click="$emit('mod')" class="bot">Modificar</td>
    <td v-if="pagos" @click="borrar()" class="bot">Eliminar</td>
    <td v-else class="nBot">Eliminar</td>
    <td @click="boleta()" class="bot">Pagos</td>
</template>