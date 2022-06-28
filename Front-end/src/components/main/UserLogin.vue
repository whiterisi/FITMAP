<template>
<div id="app">
   <main class="form-signin">
        <div class="card">
               <div class="card-body">
                    <form>
                        <h2 class="h3 mb-3 fw-normal text-center">Let's Start FITMAP</h2>
                        <br>
                        <div class="form-group">
                            <label>ID</label>
                            <input ref="cursor" v-model="userId" type="text" maxlength="20" class="form-control form-control-lg" placeholder="ID"/>
                        </div>

                        <div class="form-group">
                            <label>PASSWORD</label>
                            <input v-model="pw" type="password" maxlength="16" class="form-control form-control-lg" placeholder="PASSWORD" @keyup.13="login()"/>
                        </div>
                        <br>
                        <b-button @click="login()">Login</b-button>
                    </form>
                </div>
        </div>  

    </main>  
</div>
</template>

<script>
export default {
  data(){
    return{
      userId:'',
      pw:'',
    }
  },
   mounted() {
    this.startCursor();
    //window.location.href = '#move'
    document.body.scrollTop = document.body.scrollHeight;
  },
  methods:{
    login(){
      let userInfo= {
          userId: this.userId,
          pw: this.pw,
      }
      this.$store.dispatch("loginUser", userInfo)
    },
    startCursor(){
        this.$refs.cursor.focus();
      }
  },

}
</script>

<style scoped>
body {
        display: flex;
        padding-top: 60px;
        padding-bottom: 60px;        
        align-items: center;
        background-color: #f6f6f6;
    }
    .form-signin {
        width: 100%;
        max-width: 450px;
        margin: auto;
    }
    label {
        font-weight: 600;
    }

</style>