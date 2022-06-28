<template>
  <div class="container">
       <h4> Monthly Ranking </h4>
       <br>
       <table>
          <tr>
            <th>순위</th>
            <th>이름</th>
            <th>운동시간</th>
          </tr>
          <tr v-for="(ranker,index) in rankers" :key = "index">
            <td>{{records[ranker][0]}}</td>
            <td>{{rankers[index]}}</td>
            <td>{{records[ranker][1]}}</td>
          </tr>
      </table>
  </div>
</template>

<script>

import axios from 'axios';

export default {
  data(){
    return{
      rankers : [],
      records : {},
    }
  },
  mounted(){
    
     const REST_URL = `http://localhost:9999/api/record/rank`
      axios({
        url: REST_URL,
        method: 'GET',
        headers : { //여기
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data["names"])
          console.log(res.data["records"])
          this.rankers = res.data["names"];
          this.records = res.data["records"];
       
        }).catch((err) => {
          console.log(err);
        })

  }
}
</script>

<style scoped>

.container{
  margin: 10px;
}
</style>