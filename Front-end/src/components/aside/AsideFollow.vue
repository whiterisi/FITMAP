<template>
  <div class="container">
       <h4>Friend's Recent Workout</h4>
       <br>
        <table>
        <tr>
          <th>친구</th>
          <th>마지막 운동</th>
          <th>운동시간</th>
        </tr>
        <tr v-for="(follow, index) in followList" :key="index">
          <td>{{follow.userId}}</td>
          <td>{{follow.date}}</td>
          <td>{{follow.minutes}}</td>
        </tr>

      </table>
  </div>
</template>

<script>
import { mapState } from 'vuex'

export default {
   computed:{
     ...mapState([
         "followList"  //Map<User,DailyRecord>
    ])
   },
}
</script>

<style scoped>
  .container{
  margin: 10px;
  }

</style>