<template>
<div id = "main-wrapper">
      <!--comment edit 페이지 <br>

      comment id : {{this.$route.params.commentId}}-->

      <div class="m-4">
     
      <b-form-group label="글쓴이" label-for="input-2">
        <b-form-input type="text" id="input-2" :value="commentDTO.userId" readonly></b-form-input>
      </b-form-group>
      <b-form-group label="글내용" label-for="textarea">
        <b-form-textarea id="textarea" v-model="commentDTO.content"></b-form-textarea>
      </b-form-group>
      <b-button variant="outline-success" @click="editComment">수정</b-button>
    </div>
  
  </div>

</template>

<script>
import axios from 'axios';

export default {
  data(){
    return{
      commentDTO:{
        commentId : 0,
        userId : '-',
        videoId : '-',
        parentId : 0,
        date : '-',
        content : '-',
      }
    }
  },
  methods:{
    editComment(){
      this.$store.dispatch("editComment",this.commentDTO)
      //this.$router.push('/videoDetailTest/'+this.commentDTO.videoId);
      
    }
  },
  mounted(){

    this.commentDTO.commentId = this.$route.params.commentId;

    const REST_URL = `http://localhost:9999/api/comment/id/`+this.commentDTO.commentId

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
          this.commentDTO = res.data;
          console.log(this.commentDTO)

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
