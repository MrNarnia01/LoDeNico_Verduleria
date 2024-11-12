<script>
    import axios from 'axios'
    export default{
        props: {
            id:Object,
            mont:Object,
            c:Object,
        },
        data(){
            return{
                ccRequest:  {
                    id:0,
                    monto:0
                },
                a: "",
                u: false,
            };
        },
        mounted(){
            this.ccRequest.id=this.id;
            this.ccRequest.monto=this.mont;
            this.u=this.c;

        },
        methods:{
            change(){
                if(this.u) this.a="create/wC";
                else this.a="update";
                axios.post( 'http://localhost:8080/api/cuenta/'+this.a,this.ccRequest).then(response => {
                    this.$emit('cloc');
                }).catch(error => {
                    console.log('Error: ', error);
                });
            },
        },
    }
</script>

<template>
    <div class="popUp">
        <div class="popUp-content">
    <div class="close" @click="$emit('cloc')">&times;</div>
    <form @submit.prevent="change">
        <h3>Modificar monto de cuenta</h3>
        <table>
            <tr>
                <td><label for="precio">Monto:</label></td>
                <td><input type="number" id="precio" v-model="ccRequest.monto" :min="0" step="0.01" required></td>
                <td>
                    <button v-if="this.u" type="submit" class="bot">Crear</button>
                    <button v-else type="submit" class="bot">Modificar</button>
                </td>
            </tr>
        </table>
    </form>
    </div>
    </div>
</template>