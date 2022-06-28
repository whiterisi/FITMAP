<template>
  <div class="container">
    <br>
      <div v-if="followList.length" class="record-box">
          <b-table-simple class="table">
            <b-thead>
              <b-tr>
                <b-th scope="col">No.</b-th>
                <b-th scope="col">친구</b-th>
                <b-th scope="col">최근운동</b-th>
                <b-th scope="col">운동시간(분)</b-th>
              </b-tr>
            </b-thead>
            <b-tbody>
              <b-tr v-for="(follow, index) in followList" :key="index">
                <b-th scope="row">{{index}}</b-th>
                <b-td><button style="border : 0; background-color : transparent" @click="setOwner(follow.userId)">{{follow.userId}}</button></b-td>
                <b-td>{{follow.date}}</b-td>
                <b-td>{{follow.minutes}}</b-td>
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
export default {
  data(){
      return{
        currentPage: 1,
        perPage: 10
      }
    },
  computed:{
    ...mapState([
      'followList'
    ]),
    rows(){
          return this.followList.length
        },
        pageBoardList(){
          return this.followList.slice(
            (this.currentPage-1)*this.perPage, this.currentPage *this.perPage
          )
        },
  },
  methods:{
    setOwner(param){
      
      this.$store.dispatch("SET_PAGE_OWNER",param);
      this.$router.go(); //여기
    }
  },
  created(){
    
    var userID = this.$store.getters.GET_PAGE_OWNER;
    console.log(userID+" 현재 페이지 오너")

    this.$store.dispatch('getFollow', userID);
    console.log("비동기 어디까지 왔니..")
    
  }
}
</script>

<style >

</style>
