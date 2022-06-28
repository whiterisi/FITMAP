<template>
  <div id = "main-wrapper">
      <!-- addComment vue
      videoId : {{this.$route.params.videoId}} -->
      
   
    <div class="m-4">
     
      <b-form-group label="글쓴이" label-for="input-2">
        <b-form-input type="text" id="input-2" :value="commentDTO.userId" readonly></b-form-input>
      </b-form-group>
      <b-form-group label="글내용" label-for="textarea">
        <b-form-textarea id="textarea" v-model="commentDTO.content"></b-form-textarea>
      </b-form-group>
      <b-button variant="outline-success" @click="addComment">등록</b-button>
    </div>
  
  </div>
</template>

<script>


export default {
  data(){
    return{
      commentDTO:{
        commentId : 0,
        userId : '-',
        videoId : this.$route.params.videoId,
        parentId : 0,
        date : '-',
        content : ''
      }
    }
  },
  methods:{
    
    async addComment(){
      await this.$store.dispatch("addComment",this.commentDTO);
     // this.$router.push('/videoDetail/'+this.commentDTO.videoId);
    }
  },
  mounted(){
    let dt = new Date();
    this.commentDTO.date = dt.getFullYear()+'-'+(dt.getMonth()+1)+'-'+dt.getDate();
    this.commentDTO.userId = this.$store.getters.GET_USERID;

  }

}
</script>


<style scoped>
.main-wrapper ::v-deep {
    @import "~bootstrap/dist/css/bootstrap.min";
  }
</style>
