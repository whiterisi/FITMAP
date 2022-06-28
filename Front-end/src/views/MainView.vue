<template>
  <div class="main-box">
    <div class="top-box">
      <div class="video-view">
        <video class="video" autoplay muted loop >
          <source src="@/assets/FITMAP.mp4" type="video/mp4">
        </video>
        <div class="video-content">
          <span id="mm">LET'S</span><br>
          <span id="mm">GET</span><br>
          <span id="mm">MOVING</span><br>
          <br>
          <div class="text-center">
            <button class="snip1535" @click="gotoHome()">Main</button>
            <button class="snip1535" @click="gotoLoginPage()">Login</button>
            <button class="snip1535" @click="gotoEnrollPage()">Sign Up</button>
          </div>
        </div>
      </div>
    </div>
    

    <div class="bottom-box">
      <router-view/>
    </div>
  </div>
</template>

<script>
export default {
  methods:{
    gotoHome(){
      this.$router.push('/');
    },
    gotoLoginPage(){
      this.$router.push('/login');
    },
    gotoEnrollPage(){
      this.$router.push('/enroll');
    }
  }
}
</script>

<style scoped>

#mm{
  font-family: Josefin Sans;
  font-weight: bold;
  font-size: 70px;
}

@import url(https://fonts.googleapis.com/css?family=BenchNine:700);
.snip1535 {
  background-color: #2c36a2;
  border: none;
  color: #ffffff;
  cursor: pointer;
  display: inline-block;
  font-family: 'BenchNine', Arial, sans-serif;
  font-size: 1em;
  font-size: 22px;
  line-height: 1em;
  margin: 15px 40px;
  outline: none;
  padding: 12px 40px 10px;
  position: relative;
  text-transform: uppercase;
  font-weight: 700;
}
.snip1535:before,
.snip1535:after {
  border-color: transparent;
  -webkit-transition: all 0.25s;
  transition: all 0.25s;
  border-style: solid;
  border-width: 0;
  content: "";
  height: 24px;
  position: absolute;
  width: 24px;
}
.snip1535:before {
  border-color: #3e4bd2;
  border-right-width: 2px;
  border-top-width: 2px;
  right: -5px;
  top: -5px;
}
.snip1535:after {
  border-bottom-width: 2px;
  border-color:#0d1780;
  border-left-width: 2px;
  bottom: -5px;
  left: -5px;
}
.snip1535:hover,
.snip1535.hover {
  background-color: #696be5;
}
.snip1535:hover:before,
.snip1535.hover:before,
.snip1535:hover:after,
.snip1535.hover:after {
  height: 100%;
  width: 100%;
}

span{
	-webkit-text-stroke-width: 0.1px;
	-webkit-text-stroke-color: white
}


.main-box{
    width: 100%;
    height: 100%;
    overflow: hidden;

}
.video-view {
  width: 100%;
  flex: 0 1 auto;
}
.video {
  width: 100%;
  max-height: 85vh;
  object-fit: cover;
  background-color: #ccc;
}
.video-view {
  font-size: 20px;
  width: 100%;
  display: flex;
  flex-direction: column;
}
.video-content{
  position: absolute;
  left: 35vw;
  text-align: center;
  top: 20vh;
  font-size: 50px;
  height: 20vh;
  color: rgb(10, 25, 53)
}
.bottom-box{
  padding-top:5vh;
  padding-bottom: 5vh;
  flex: 0 1 60px;
}
.text-center{
  display: flex;
  justify-content: center;
}
button{
  margin: 5px;
}

</style>