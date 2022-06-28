<template>
  <div  class="container-fluid">
    <b-navbar toggleable="lg" type="dark" variant="dark">
    <b-nav-item @click = "setOwner" to="/home" >FITMAP</b-nav-item>
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
    
    <b-collapse id="nav-collapse" is-nav>
      
      <b-nav-item to="/home/video">영상검색</b-nav-item>

      <b-nav-item-dropdown text="마이페이지">
        <b-dropdown-item  @click.native = "setOwner" to="/mypage">데일리기록</b-dropdown-item>
        <b-dropdown-item to="/mypage/likelist">찜영상</b-dropdown-item>
        <b-dropdown-item to="/mypage/follow">나의 친구</b-dropdown-item>
      </b-nav-item-dropdown>
      <b-nav-item right @click = "logout" to="/">로그아웃</b-nav-item>

    </b-collapse>
  </b-navbar>
   <div class="hello" ><strong >{{this.$store.getters.GET_USERID}}님, 좋은 {{time}}입니다.</strong></div>
  </div>
</template>

<script>
export default {
  methods:{
    logout(){
      sessionStorage.clear();
      this.$router.push('/')
    },
    async setOwner(){
        this.$store.dispatch("SET_PAGE_OWNER",this.$store.getters.GET_USERID);
        //sessionStorage.setItem("page_owner",this.$store.getters.GET_USERID);
        await this.$store.dispatch('getRecordWithEntries');  
        console.log("header nav session set completed here")
        
    }
  },
  data(){
    return{
      time:''
    }
  },
  created(){
     let now = new Date().getHours();
     if(now<11){
       this.time = '아침'
     }else if(now <14){
       this.time = '점심'
     }else if(now <17){
       this.time = '오후'
     }else{
       this.time = '저녁'
     }
    
  }
} 
</script>

<style scoped>
.hello{
  font-size: 20px;
  color: white;
  font-family: 'Gothic A1', sans-serif;
}

.container-fluid{
  font-size: 2vh;
}

</style>
