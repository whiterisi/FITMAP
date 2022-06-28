<template>
  <div class="container"> 
      <div >
        <div class="nametag">Yearly Record of <span class="highlight">{{recordOwner}}</span></div>

        <div v-show= "recordOwner!= userId">
        <b-button pill variant="outline-danger" @click="follow">{{f}}</b-button>
        </div>

        <vuejs-heatmap :entries="entries"></vuejs-heatmap>
      </div>
      <b-nav tabs>
        <b-nav-item class="navbtn" router-link-active @click="gotoRecord()">데일리기록</b-nav-item>
        <b-nav-item class="navbtn" router-link-active @click="gotoLikeList()">영상찜리스트</b-nav-item>
        <b-nav-item class="navbtn" router-link-active @click="gotoFollow()"> 친구관리</b-nav-item>
      </b-nav> 


      <main>
        <router-view/>
      </main>
  </div>
</template>

<script>
import VuejsHeatmap  from '@/components/common/VuejsHeatmap'
//import VuejsHeatmap from '@/components/common/HeatMapTest'
import axios from 'axios';


export default {
  data(){
    return{
      entries : this.$store.getters.GET_ENTRIES,
      pageOwner: this.$store.getters.GET_PAGE_OWNER,
      active: 'dailyRecord',
      f : '-',
      userId : this.$store.getters.GET_USERID,
      followDTO :{
        userId : this.$store.getters.GET_USERID,
        follow : this.$store.getters.GET_PAGE_OWNER
      },
    }
  }, 
  components: {
    VuejsHeatmap,
  },
  computed: {
    recordOwner: function () {
      return this.$store.getters.GET_PAGE_OWNER;
      }
    },

  methods : {
      gotoRecord() {
         this.$router.push('/mypage')
        },
      gotoFollow() {
         this.$router.push('/mypage/follow')
        },
      gotoLikeList() {
         this.$router.push('/mypage/likelist')
        },
      makeActive(item){
        this.active = item;
      },
      follow(){
        
        if(this.f == "FOLLOW") this.$store.dispatch("addFollow", this.followDTO)
        else this.$store.dispatch("unFollow", this.followDTO)
      
        this.$router.go();
      },
  },
  created(){
    this.$store.dispatch('getRecordWithEntries');   //created인지 mounted인지 잘...

  },
  mounted: function(){
        console.log("myPageView mounted 시작")
        let param = this.$store.getters.GET_USERID+" "+this.$store.getters.GET_PAGE_OWNER;
        console.log(param);

        const REST_URL = `http://localhost:9999/api/follow/`+param;
          axios({
            url: REST_URL,
            method: 'GET',
            param,
            headers : { 
              "access-token" : sessionStorage.getItem("access-token")
            },
          })
          .then((res) => {
            //console.log(res.data)
            if(res.data=="TRUE"){
              this.f = "UNFOLLOW";
            }else{
              this.f = "FOLLOW";            
            }
          }).catch((err) => {
          console.log(err);
          })
  

  },
 
  
}
</script> 

<style scoped>

.nametag{
	font-size: 30px;
}
.container{
  display: flex;
  flex-direction: column;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

span.highlight {
  color: rgba(39, 52, 196, 0.865);
}

aside{
  display: flex;
  flex-direction: column;
}

main{
  width: 80%;
}
a{
  font-size: 20px;
  color: black;

  
}
a:hover, a:active{
  color: rgb(39, 136, 133);
  font-weight: bold;
}




</style>
