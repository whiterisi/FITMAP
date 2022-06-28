<template>
<div class="body">
   <main class="form-record">
        <div class="card">
               <div class="card-body">
                    <form>
                        <h2 class="h3 mb-3 fw-normal text-center">Please sign up</h2>

                        <div class="form-group">
                            <label>날짜</label>
                            <input v-model="showDate" type="text" readonly>
                        </div>

                        <div class="form-group">
                            <label>운동 부위</label>
                            <input type='checkbox' value=4  v-model="partAreas"/>전신
                            <input type='checkbox' value=1 v-model="partAreas"/>상체
                            <input type='checkbox' value=2 v-model="partAreas"/>하체
                            <input type='checkbox' value=3 v-model="partAreas"/>복근
                        </div>

                        <div class="form-group">
                            <label>운동 시간</label>
                            <input v-model="minutes" type="text" placeholder="분 단위로 입력해주세요" >
                        </div>                        

                        <div class="form-group">
                            <label>기타 내용</label>
                           <textarea v-model="comment" type="text" placeholder="식단, 운동 루틴 등"></textarea>
                        </div>

                        <b-button @click="createRecord">저장</b-button>
                    </form>
                </div>
        </div>  
    </main>
    </div>
</template>

<script>
export default {
    data () {
    return {
         showDate:new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0],
        partAreas: [],
        minutes:"",
        comment:"",
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
   createRecord(){
      var sum=0;
      for(let i=0; i<this.partAreas.length; i++){
        sum+=Math.pow(2, this.partAreas[i]);
      }
      let record = {
        recordId:0,
        userId:this.$store.getters.GET_USERID,
        date: this.showDate,
        part:sum,
        minutes:this.minutes,
        comment:this.comment,
      };
      this.$store.dispatch("inputRecord", record)
    }
    }
}
</script>

<style scoped>
.body {
display: flex;
padding-top: 60px;
padding-bottom: 60px;
align-items: center;
}
.form-record {
width: 100%;
max-width: 450px;
margin: auto;
}
label {
font-weight: 600;
}
.card-body{
  margin: 1px;
  display: flex;
  justify-content: center;
}
.form-group{
  display: flex;
  align-items: center;
  justify-content: space-around;

  padding: 1px;
}

</style>