<template>
  <div class="container">
    <div class="card">
      <iframe :src='`https://www.youtube.com/embed/${video.videoId}`'></iframe>
        <div  class="card-body">
          <p class="card-text">{{video.title}}</p>
          <button :value='`${}" @click="addlike`'>찜</button>
        </div>
        <div>
          <button>찜하기</button>
        </div>
    </div>

    <div>
       <table class="table">
        <thead>
          <tr>
            <th scope="col">No.</th>
            <th scope="col">작성자</th>
            <th scope="col">댓글내용</th>
            <th scope="col">작성날짜</th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>abc</td>
            <td>aaa</td>
            <td>2022</td>
          </tr>
        </tbody>
      </table>
      <button>댓글작성</button>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      boardList:[],
    }
  }

}
</script>

<style scoped>
  .container{
    display: flex;
  }

</style>