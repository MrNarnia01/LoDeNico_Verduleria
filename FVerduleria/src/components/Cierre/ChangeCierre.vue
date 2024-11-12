<template>
    <div class="popUp">
        <div class="popUp-content">
      <div class="close" @click="$emit('cloc')">&times;</div>
      
      <form @submit.prevent="crearCierre">
        <h3>Registro de cierre</h3>
          <table>
              <tr>
                <td><p>Método de cierre:</p></td>
                <td>
                    <input type="radio" value="Posnet" v-model="newCierre.tipo" id="pos">
                    <label for="pos">Posnet</label>
                    <input type="radio" value="Efectivo" v-model="newCierre.tipo" id=efe>
                    <label for="efe">Efectivo</label>
                </td>
              </tr>
              <tr>
                <td>Monto</td>
                <td><input type="number" id="monto" v-model="newCierre.monto" :min="0" step="0.01"  required></td>
            </tr>
              <tr>
                <td v-if="this.id==null" colspan="2"><button type="submit" class="bot" id="nor">Crear</button></td>
                <td v-else colspan="2"><button type="submit" class="bot" id="nor">Modificar</button></td>
              </tr>
          </table>
      </form>
    </div>
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

