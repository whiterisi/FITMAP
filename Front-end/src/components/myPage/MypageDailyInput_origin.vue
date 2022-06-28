<template>
  <div >
    <h3>데일리 기록 작성</h3>
    <table>
        <tr>
          <th>날짜</th>
          <td> <input v-model="showDate" type="text" readonly>
          </td>
        </tr>
        <tr> 
          <th>운동부위</th>
          <td>   
            <input type='checkbox' value=4  v-model="partAreas"/>전신
            <input type='checkbox' value=1 v-model="partAreas"/>상체
            <input type='checkbox' value=2 v-model="partAreas"/>하체
            <input type='checkbox' value=3 v-model="partAreas"/>복근
          </td> 
        </tr>
        <tr> 
          <th>운동시간</th>
          <td> <input v-model="minutes" type="text" placeholder="분 단위로 입력해주세요" >
          </td>
        </tr>
        <tr> 
          <th>내용</th>
          <td> <textarea v-model="comment" type="text" placeholder="식단, 운동 루틴 등"></textarea>
          </td>
        </tr>
      </table>
      <button @click="createRecord">저장</button>
  </div>
</template>

<script>
export default {
  data(){
    return{
        showDate:new Date(+new Date() + 3240 * 10000).toISOString().split("T")[0],
        partAreas: [],
        minutes:"",
        comment:"",
    }
  },
  methods:{
    createRecord(){
      var sum=0;
      for(let i=0; i<this.partAreas.length; i++){
        sum+=Math.pow(2, this.partAreas[i]);
      }
      //userId 가져와서 넣어줘야함.
      //부위체크박스 선택한거 골라서 합 구한다음에 part에 넣는거 구현필요
      //저장하고 데일리기록 페이지로 redirect하는 거 필요
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

<style>

</style>