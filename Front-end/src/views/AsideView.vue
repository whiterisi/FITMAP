<template>
  <div class="container">
      <div class="box">
        <aside-follow/>
      </div>
      <hr>
      <div  class="box">
        <aside-ranking/>
      </div>
      <br>
      <div>
        <img src="@/assets/hometraining.jpg" width="300vw">
      </div>
  </div>
</template>

<script>
import AsideFollow from '../components/aside/AsideFollow.vue'
import AsideRanking from '../components/aside/AsideRanking.vue'

export default {
  components: {
      AsideFollow,
      AsideRanking
      },

}
</script>

<style scoped>
 .container{
   position: sticky;
   display: flex;
   flex-direction: column;
   justify-content: space-between
 }

 .box{
   margin: 20px;
 }
</style>