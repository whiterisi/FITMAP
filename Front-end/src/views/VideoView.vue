<template>
  <div>
    <div>
      <div class="nametag">Yearly Record of <span class="highlight">{{userId}}</span></div>
        <vuejs-heatmap :entries="this.$store.getters.GET_ENTRIES" ></vuejs-heatmap>
      </div>
    <div class="container">
        <div class="button-box">
            <div class="button-7">
              <div class="eff-7"></div>
              <a @click="partSearch(4)"> 전신 </a>
            </div>
            <div class="button-7">
              <div class="eff-7"></div>
              <a @click="partSearch(1)"> 상체 </a>
            </div>
            <div class="button-7">
              <div class="eff-7"></div>
              <a @click="partSearch(2)"> 하체 </a>
            </div>
            <div class="button-7">
              <div class="eff-7"></div>
              <a @click="partSearch(3)"> 복근 </a>
            </div>
            </div>
            <div class="search-box">
              <input type="text" v-model="keyword" placeholder="운동부위를 검색해주세요">
              <b-button variant="info" @click="search">검색</b-button>
            </div>
    </div>
    <div v-if="flag">
        <video-part-list/>
    </div>
    <div v-else>
      <video-youtube-list/>
    </div>
    
  </div>
</template>

<script>
import VideoPartList from '../components/video/VideoPartList.vue'
import VideoYoutubeList from '../components/video/VideoYoutubeList.vue'
import VuejsHeatmap  from '@/components/common/VuejsHeatmap'

export default {
  components: {
    VideoPartList,
    VideoYoutubeList,
    VuejsHeatmap,

  },
  data() {
    return {
      initialKeyword:'전신',
      keyword:'',
      flag:true,
      userId : this.$store.getters.GET_USERID,
    }
  },
  methods: {
    search() {
      this.flag=false;
      this.$store.dispatch("searchYoutube", this.keyword)
    },
    partSearch(partId){
       var partID=partId
       this.flag=true;
       this.$store.dispatch("searchPartVideo", partID)
    },

  },
 created(){
    this.$store.dispatch('getRecordWithEntries');  
    
  }
  ,
}
</script>

<style scoped>

.nametag{
	font-size: 30px;
}
  .container{
    padding: 20px;
    display: flex;
    justify-content: space-evenly ;
  }

  button{
    margin: 10px;
  }
    span.highlight {
    color: rgba(220, 51, 51, 0.865);
  }


  
.button-7{
  width:90px;
  height:50px;
  border:2px solid #34495e;
  border-radius: 12px;
  float:left;
  text-align:center;
  cursor:pointer;
  position:relative;
  box-sizing:border-box;
  overflow:hidden;
  margin:0 0 10px 10px;
}
.button-7 a{
  font-family:arial;
  font-size:16px;
  color:#34495e;
  text-decoration:none;
  line-height:50px;
  transition:all .5s ease;
  z-index:2;
  position:relative;
}
.eff-7{
  width:90px;
  height:50px;
  border-radius: 12px;
  border:0px solid #34495e;
  position:absolute;
  transition:all .5s ease;
  z-index:1;
  box-sizing:border-box;
}
.button-7:hover .eff-7{
  border:70px solid #34495e;
}
.button-7:hover a{
  color:#fff;
}
</style>
