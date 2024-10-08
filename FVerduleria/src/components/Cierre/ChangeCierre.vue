<template>
    <div class="modal-content">
      <span class="close" @click="$emit('cloc')">&times;</span>
      
      <form @submit.prevent="crearCierre">
          <table>
              <tr>
                <td><p>Seleccione el método de pago:</p></td>
                <td>Monto</td>
              </tr>
              <tr>
                <td><input type="radio" value="Efectivo" v-model="newCierre.tipo"> Efectivo </td>
                <td><input type="number" id="monto" v-model="newCierre.monto" :min="0" step="0.01"  required></td>
              </tr>
              <tr><td><input type="radio" value="Posnet" v-model="newCierre.tipo"> Posnet</td></tr>
              <tr>
                <td v-if="this.id==null"><button type="submit">Crear</button></td>
                <td v-else><button type="submit">Modificar</button></td>
              </tr>
          </table>
      </form>
    </div>
</template>

<script>
    import axios from 'axios';
    export default {
        props: {
            id:Object,
        },
    data() {
        return {
            newCierre: {
                tipo: "Efectivo",
                monto: 0.0,
            },
        }
    },
    mounted(){
        console.log(this.id);
        if(this.id!=null){
            if(!this.id.tipo){
                this.newCierre.tipo="Posnet";
            }
            this.newCierre.monto=this.id.monto;
        }
    },
    methods: {
        crearCierre(){
            console.log(this.newCierre)
            if(this.newCierre.tipo=="Efectivo"){
                this.newCierre.tipo=true;
            }else this.newCierre.tipo=false;
            let a = "";
            if(this.id==null)   a+="create";
            else a+="update/"+this.id.id;
            console.log(this.newCierre)
            axios.post( 'http://localhost:8080/api/cierre/'+a,this.newCierre,{
                headers: { 'Content-Type': 'application/json' }
            } ).then(response => {
                this.$emit('cloc');
            }).catch(error => {
                console.log('Error: ', error.response.data);
                this.$emit('cloc');
            });
        },
    }
    }
</script>

