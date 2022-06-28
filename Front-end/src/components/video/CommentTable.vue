<template>
<div id="main-wrapper">
  <div class="comment-area">
        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button type="button" class="btn btn-outline-success btn-sm"  @click = "addCmt">댓글 쓰기</button>
        </div>

            
            <div v-if="Comments.length">
            <b-table-simple hover responsive class="text-center">
            <b-thead>
              <b-tr>
                <b-th scope="col">No.</b-th>
                <b-th scope="col">User</b-th>
                <b-th scope="col">Comment</b-th>
                <b-th scope="col">date</b-th>
                <b-th scope="col">설정</b-th>
             </b-tr>
           </b-thead>
           <b-tbody>
                <b-tr v-for="(comment, index) in Comments" :key="index">
                    <b-th scope="row">{{index}}</b-th>
                    <b-td><span @click = "UserPage(comment.userId)">{{comment.userId}}</span></b-td> 
                    <b-td>{{comment.content}}</b-td>
                    <b-td>{{comment.date}}</b-td>
                    <b-td>
                        
                    <div v-show ="comment.userId == userId">
                        <button type="button" class="btn btn-outline-primary btn-sm" @click="EditCmt(comment.commentId)">수정</button>
                        <button type="button" class="btn btn-outline-danger btn-sm"  @click="DeleteCmt(comment.commentId)">삭제</button>
                    </div>


                    </b-td>
                </b-tr>
            </b-tbody>
            </b-table-simple>
            </div>
              <div v-else>
                등록된 게시글이 없습니다.
                 </div>
            </div>
    
            <!-- <b-pagination
            v-model="currentPage"
            :total-rows="rows"
            :per-page="perPage"
            aria-controls="my-table"
            align="center"
            ></b-pagination> -->
        <!-- end comment area-->
</div>
        
  
</template>

<script>
import axios from 'axios';

export default {
    // props:{
    //     id : String,
    // },
    data(){
        return{
            userId : this.$store.getters.GET_USERID,
            Comments : [{
                commentId : 0,
                userId : 'ㅎㅇㅇ',
                comment : "123",
                date : "2022-02-02",
            }],
            videoId : this.$route.params.videoId,
        }
    },
    methods:{
        UserPage(param){
            this.$store.dispatch("SET_PAGE_OWNER",param);
            this.$store.dispatch('getRecordWithEntries');  
            this.$router.push('/mypage');
        },

        EditCmt(param){
            console.log(param)
            this.$router.push('/edit/'+param)
        },
      
        DeleteCmt(param){
            const REST_URL = `http://localhost:9999/api/comment/${param}`
            axios({
                url: REST_URL,
                method: 'DELETE',
                headers : { 
                "access-token" : sessionStorage.getItem("access-token")
                },
            })
            .then((res) => {
                console.log(res.data)
                
            }).catch((err) => {
                  console.log(err);
            })
        
            this.$router.go();
        },
        
        addCmt(){
            this.$router.push('/write/'+this.id);
        }

    },
    mounted(){
      
      this.id = this.$route.params.videoId
      
      this.Comments = this.$store.getters.GET_COMMENT;
      const REST_URL = `http://localhost:9999/api/comment/${this.id}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
            console.log(res.data)
          this.Comments = res.data;
        }).catch((err) => {
          console.log(err);
        })
    }
}
</script>

<style scoped>

button{
  margin: 1px;
}

span{
 cursor: pointer;
}
.main-wrapper ::v-deep {
    @import "~bootstrap/dist/css/bootstrap.min";
  }
</style>
