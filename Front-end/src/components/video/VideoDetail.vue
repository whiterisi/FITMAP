<template>
  <div class="main-wrapper">
    <div class="container">


      <div>
        <b-embed type="iframe" aspect="16by9" :src = "videoLink" allowfullscreen></b-embed>
        <!-- <iframe id="video" :src="videoLink"></iframe> -->
        <div  class="card-body">
          <p class="card-text" style="font-size: 35px;">{{videoDTO.title}}</p>
          <!-- <p class="card-text"  style="font-size: 20px;">{{videoDTO.channel}}</p> -->
        </div>
      </div>


        <router-view :id = videoId></router-view>
        <!-- CommentTable.vue / CommentEdit.vue / addComment.vue-->


        
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {

    data(){
        return{
            userId : this.$store.getters.GET_USERID,
            videoId : this.$route.params.videoId,
            videoLink : '-',
            videoDTO: {},
        }
    },
    mounted(){
     
      this.videoLink = `https://www.youtube.com/embed/`+this.$route.params.videoId

      const REST_URL = `http://localhost:9999/api/video/`+this.$route.params.videoId

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
     
          this.videoDTO = res.data
         
        }).catch((err) => {
          console.log(err);
        })

    }
 

}
</script>

<style scoped>
.main-wrapper ::v-deep {
    @import "~bootstrap/dist/css/bootstrap.min";
  }
</style>





<!--        <div class="comment-area">
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
                    <b-td><button @click = "UserPage(comment.userId)">{{comment.userId}}</button></b-td> 
                    <b-td>{{comment.comment}}</b-td>
                    <b-td>{{comment.date}}</b-td>
                    <b-td>
                        
                    <div v-show ="comment.userId == userId">
                        <button @click="EditCmt(comment.commentId)">수정</button>
                        <button @click="DeleteCmt(comment.commentId)">삭제</button>
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
    
    <b-pagination
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      aria-controls="my-table"
      align="center"
    ></b-pagination>-->
        <!-- end comment area-->
