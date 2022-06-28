<template>
  <div class="container">
    <h3 data-aos="fade-up" data-aos-duration="1500">드디어 운동을 해야겠다는 다짐을 하셨나요?</h3>
    <br>
    <h2 data-aos="fade-up" data-aos-duration="1500">앞으로 체계적으로 운동을 해나가고 싶으신가요?</h2>
    <br>
    <br>
    <h1 data-aos="fade-up" data-aos-duration="500">그렇다면 우리 FITMAP과 함께 해보세요</h1>
    <br>
    <div class="block" >
      <div data-aos="zoom-out-up" data-aos-duration="1000"><img src="@/assets/dailyrecord.png" width="600vh"></div>
      <div class="comment" data-aos="fade-right" data-aos-duration="1000">
        <div  >나의 데일리 운동 기록을 남겨주세요</div>
        <div  >매일매일의 기록을 한 눈에 보여드립니다.</div>
        <div  >운동시간에 따라 달라지는 색깔</div>
        <div  >오늘도 운동으로 나의 하루를 색칠해보세요</div>
      </div>
    </div>
    <br>
    <div class="block" >
      <div class="comment" data-aos="fade-right" data-aos-duration="1000">
        <div >오늘은 어떤 부위 운동을 해야할까 고민이신가요?</div>
        <div >최근 3일의 기록을 바탕으로</div>
        <div >부족한 운동 부위를 알려드립니다.</div>
        <div >함께 추천해드리는 운동 영상을 따라해보세요</div>
      </div>
      <div data-aos="zoom-in-up" data-aos-duration="1000"><img src="@/assets/hometraining.jpg" width="400vh"></div>
    </div>
    <br>
    <div class="block" >
      <div data-aos="zoom-out-up" data-aos-duration="1000"><img src="@/assets/run.png" width="350vh"></div>
      <div class="comment" data-aos="fade-right" data-aos-duration="1000">
        <div >내 친구는 운동을 잘 하고있나?</div>
        <div >다른 사람들은 얼마나 열심히 운동하고있을까?</div>
        <div >다른 사람들의 기록을 통해 운동 자극을 받아보세요</div>
        <div >그리고 월별 랭킹에 도전해보세요</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
.container{
  display: flex;
  justify-content: center;
  flex-direction: column;
  justify-content: space-between;
}
.block{
  margin: 10px;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-basis: auto;
  flex-flow: 
}

.comment{
  font-size: 20px;
  margin: 10px;
}

</style>
