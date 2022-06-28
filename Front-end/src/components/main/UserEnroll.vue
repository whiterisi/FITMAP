<template>
   <main class="form-signin">
        <div class="card">
               <div class="card-body">
                    <form>
                        <h2 class="h3 mb-3 fw-normal text-center">Please sign up</h2>

                        <div class="form-group">
                            <label>ID</label>
                            <input ref="cursor" v-model="userId" type="text" maxlength="20" class="form-control form-control-lg" placeholder="ID"/>
                        </div>

                        <div class="form-group">
                            <label>PASSWORD</label>
                            <input v-model="pw" type="password" maxlength="16" class="form-control form-control-lg" placeholder="PASSWORD"/>
                        </div>

                        <div class="form-group">
                            <label>PASSWORD CHECK</label>
                            <input v-model="pwCheck" type="password" maxlength="16" class="form-control form-control-lg" placeholder="Password Checking" @keyup="passwordCheckValid"/>
                            <div v-if="!passwordCheckFlag"> 비밀번호가 동일하지 않습니다. </div> 
                        </div>

                        <div class="form-group">
                            <label>NAME</label>
                            <input v-model="name" type="email" class="form-control form-control-lg" placeholder="Name" />
                        </div>

                        <div class="form-group">
                            <label>NICK NAME</label>
                            <input v-model="nickName" type="text"  class="form-control form-control-lg" placeholder="Nick Name"/>
                        </div>                        

                        <div class="form-group">
                            <label>E-MAIL</label>
                            <input v-model="email"  type="email" class="form-control form-control-lg" placeholder="Email address" />
                        </div>

                        <b-button @click="enroll()">Sign up</b-button>
                    </form>
                </div>
        </div>  

    </main>      
</template>

<script>
export default {
    data () {
    return {
        userId: '',
        pw: '',
        pwCheck: '',
        name:'',
        nickName:'',
        email:'',
    }
  },
  created:{

  },
  mounted() {
    this.startCursor();
    document.body.scrollTop = document.body.scrollHeight;
  },
  methods: {
      passwordCheckValid () {
        if (this.pw === this.pwCheck) {
          this.passwordCheckFlag = true }
        else {
          this.passwordCheckFlag = false
          }
        },
    enroll() {
        let userInfo= {
                userId: this.userId,
                pw: this.pw,
                nickName:this.nickName,
                name:this.name,
                email:this.email,
              }
          this.$store.dispatch("enrollUser", userInfo)
      },
      startCursor(){
        this.$refs.cursor.focus();
      }
    }
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