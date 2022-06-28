<template>
  <div>
      <h3>수정페이지</h3>
      <table>
        <tr>
          <th>날짜</th>
          <td> <input v-model="dailyRecord.date" type="text" readonly>
          </td>
        </tr>
        <tr>
          <th>운동부위</th>
          <td>
            <input type='checkbox' value=4  v-model="partAreas"/>전신
            <input type='checkbox' value=1 v-model="partAreas"/>상체
            <input type='checkbox' value=2 v-model="partAreas"/>하체
            <input type='checkbox' value=3 v-model="partAreas"/>복부
          </td>
        </tr>
        <tr>
          <th>운동시간</th>
          <td> <input v-model="dailyRecord.minutes" type="text">
          </td>
        </tr>
        <tr>
          <th>내용</th>
          <td> <textarea v-model="dailyRecord.comment" type="text" ></textarea>
          </td>
        </tr>
      </table>
      <button @click="editRecord">저장</button>
  </div>
</template>

<script>
import { mapState } from 'vuex';

export default {
  data(){
    return{
        partAreas: [],
    }
  },
  computed:{
    ...mapState([
      'dailyRecord'
    ])
  },
  created(){
    const pathName = new URL(document.location).pathname.split("/");
    const id = pathName[pathName.length-1];
    this.$store.dispatch('getDailyRecord', id)
  },
  methods:{
    editRecord(){
      var sum=0;
      for(let i=0; i<this.partAreas.length; i++){
        sum+=Math.pow(2, this.partAreas[i]);
      }
       let record = {
        recordId:this.dailyRecord.recordId,
        userId:this.$store.getters.GET_USERID,
        date:this.dailyRecord.date,
        part:sum,
        minutes:this.dailyRecord.minutes,
        comment:this.dailyRecord.comment,
      };
      this.$store.dispatch("editDailyRecord", record)

    }
  }
}
</script>

<style>

</style>
