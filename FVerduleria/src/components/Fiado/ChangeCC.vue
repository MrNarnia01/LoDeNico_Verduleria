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
    <form @submit.prevent="change">
        <span class="close" @click="$emit('cloc')">&times;</span>
        <table>

            <tr>
                <td><label for="precio">Monto:</label></td>
                <td><input type="number" id="precio" v-model="ccRequest.monto" :min="0" step="0.01" required></td>
                <td colspan="2"><button v-if="this.u" type="submit">Crear</button><button v-else type="submit">Modificar</button></td>
            </tr>
        </table>
        
    </form>
</template>