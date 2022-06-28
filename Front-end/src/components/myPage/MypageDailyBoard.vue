<template>
  <div class="container">
    <br>
      <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <div v-show="showSetting">
        <button type="button" class="btn btn-outline-success btn-sm" @click="goInput"> 기록하기</button>
        </div>
      </div>
      
      <div v-if="dailyRecordList.length" class="record-box">
        <b-table-simple hover responsive  class="text-center">
        <b-thead>
          <b-tr>
            <b-th >No.</b-th>
            <b-th >날짜</b-th>
            <b-th>운동부위</b-th>
            <b-th >운동시간(분)</b-th>
            <b-th >내용</b-th>
            <b-th >설정</b-th>
          </b-tr>
        </b-thead>
        <b-tbody>
          <b-tr v-for="(record, index) in pageBoardList" :key="index">
            <b-th >{{index}}</b-th>
            <b-td>{{record.date}}</b-td>
            <b-td>{{ppp.get(record.recordId)}}</b-td>
            <b-td>{{record.minutes}}</b-td>
            <b-td>{{record.comment}}</b-td>
            <b-td>
              <div v-show="showSetting">
              <button type="button" class="btn btn-outline-primary btn-sm" @click="goEdit(record.recordId)">수정</button>
              <button type="button" class="btn btn-outline-danger btn-sm" @click="deleteRecord(record.recordId)">삭제</button>
              </div>
            </b-td>
          </b-tr>
        </b-tbody>
      </b-table-simple>
      </div>
      <div v-else>
        등록된 게시글이 없습니다.
      </div>
        <b-pagination
          v-model="currentPage"
          :total-rows="rows"
          :per-page="perPage"
          aria-controls="my-table"
          align="center"
        ></b-pagination>

  </div>
</template>

<script>
import { mapState } from 'vuex'
import axios from 'axios';

export default {
    data(){
      return{
        ppp : {},
        currentPage: 1,
        perPage: 10
      }
    },
    computed: {
        ...mapState([
            "dailyRecordList"
        ]),
        rows(){
          return this.dailyRecordList.length
        },
        pageBoardList(){
          return this.dailyRecordList.slice(
            (this.currentPage-1)*this.perPage, this.currentPage *this.perPage
          )
        },
        showSetting: function () {
        if(this.$store.getters.GET_PAGE_OWNER != this.$store.getters.GET_USERID){
          return false;
        }
        return true;
        }
    },
    mounted() {
        //this.$store.dispatch("getDailyRecordList");
        let names = ['','상체','하체','복근','전신'];
        var userID = this.$store.getters.GET_PAGE_OWNER;

        const REST_URL = `http://localhost:9999/api/record/${userID}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          this.$store.commit("DAILY_RECORD_LIST", res.data)
          this.ppp = new Map();
          this.ppp.set('test','test' )
          
          for(let i=0; i<this.dailyRecordList.length; i++){
            let val = this.dailyRecordList[i].part;
           
            let parts = [];
            for(let i=4; i>0; i--){
            if(parseInt(val/Math.pow(2,i))!=0){
              //console.log(val+" "+i)
              parts.push(names[i]);
              val = val - Math.pow(2,i);
            }
            }
            let str = '' 
            for(let i=0; i<parts.length; i++){
              str = str+parts[i]+' ';
            }
            this.ppp.set(this.dailyRecordList[i].recordId,str);
  
          }
          console.log("최종 ppp")
          console.log(this.ppp)
        }).catch((err) => {
          console.log(err);
        })


    },
    methods: {
        goInput(){
            this.$router.push("/mypage/dailyinput");
        },
        goEdit(recordId) {
            this.$store.dispatch("getDailyRecord", recordId);
        },
        deleteRecord(recordId){
          this.$store.dispatch('deleteDailyRecord', recordId);
        }

    },
}
</script>

<style scoped>

button{
  margin: 1px;
}

.record-box{
  margin: 10px;
}
.container{
  display: flex;
  flex-direction: column;
}

</style>
