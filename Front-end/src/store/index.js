import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'


Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    resultVideos: [],
    recommendedVideos: [],
    likeVideos: [],
    dailyRecordList: [],
    dailyRecord: {},
    followList: [],
    ENTRIES : [],

    rankingList: {},
    
    userId : '', //userId,token 저장하자
    PAGE_OWNER : '',
    recommend_part : '--',

    Comment : [],
  },
  getters: {
    GET_ENTRIES(state){
      return state.ENTRIES;
    },

    GET_USERID(state){
      //일단 session에 안담겨있을 땐 여기 접근하지 않도록 한다 
      state.userId = sessionStorage.getItem("current-userId");
      
      return state.userId
    },

    GET_PAGE_OWNER(state){
      state.PAGE_OWNER = sessionStorage.getItem("page_owner");
      return state.PAGE_OWNER;
    },
    GET_RECPART(state){
      return state.recommend_part;
    },

    GET_COMMENT(state){
      return state.Comment;
    }
    
  },
  mutations: {
    SEARCH_VIDEO(state, videos) {
      state.resultVideos = videos
    },
    SEARCH_PART_VIDEO(state, videos) {
      state.resultVideos = videos
    },
    RECOMMEND_PART_VIDEO(state, videos){
      state.recommendedVideos =videos
    },
    LIKELIST_VIDEO(state, videos){
      state.likeVideos =videos
      console.log(videos)
    },
    DAILY_RECORD_LIST(state, records){
      state.dailyRecordList = records
    },
    DAILY_RECORD(state, record){
      state.dailyRecord = record
    },
    CREATE_RECORD(state, record){
      state.dailyRecordList = record
    },
    // UPDATE_RECORD(state, record){
    //   state.dailyRecordList.push(record)
    // }
    FOLLOW_LIST(state, follows){
      state.followList = follows
    },
    SET_ENTRIES(state){
      
      for(let i=state.ENTRIES.length; i>0; i--){
        state.ENTRIES.pop();
      }
      let n = state.dailyRecordList.length;
      //console.log(n+" store 42번 라인 뮤테이션 호출")
      for(let i=0; i<n; i++){
        let record = state.dailyRecordList[i];
        let cnt = record.minutes;
        let crea = record.date;
       // console.log(record+" "+cnt+" "+crea)
        state.ENTRIES.push({'counting' : cnt, 'created_at' : crea});
      }
    },
    RANKING_LIST(state, rankingInfo){
      state.rankingList = rankingInfo
    },

    USER_LOGIN(state,userId){
      state.userId=userId;
      state.PAGE_OWNER = userId; //여기!!
    }

    ,
    SET_PAGE_OWNER(state,userId){
      state.PAGE_OWNER = userId;
    },
    
    SET_RECOMMEND(state,part){
      state.recommend_part = part.substr(0,part.length-2);
    },

    SET_COMMENT(state,comments){
      state.Comment = comments;
    }
  },
  actions: {
    searchYoutube({ commit }, keyword) {
      const YOUTUBE_KEY = 'AIzaSyCyT3NSsRlN4J5EzFIMml2XYTMP5oMaZKM'
      const API_URL = `https://www.googleapis.com/youtube/v3/search`

      const params = {
        key: YOUTUBE_KEY,
        part: 'snippet',
        type: 'video',
        q: keyword+'운동',
        maxResults: 12
      }

      axios({
        url: API_URL,
        method: 'GET',
        params,
      })
        .then((res) => {
          console.log(res.data)
          commit("SEARCH_VIDEO", res.data.items)
        
        }).catch((err) => {
          console.log(err);
        })
    },

    addVideo({commit},videoInfo){
    console.log("저장할 비디오 정보")
     console.log(videoInfo)
      
      commit
      const REST_URL = `http://localhost:9999/api/video`

      axios({
        url: REST_URL,
        method: 'POST',
        params : videoInfo,
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
       
        }).catch((err) => {
          console.log(err);
        })

    },

    searchPartVideo({commit}, partId){
      const partID = Math.pow(2, partId);
      const REST_URL = `http://localhost:9999/api/video/part/${partID}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("SEARCH_PART_VIDEO", res.data)
        }).catch((err) => {
          console.log(err);
        })
    },

    recommendVideo({commit}){
      var userID = this.getters.GET_USERID;  
      const REST_URL = `http://localhost:9999/api/video/recommend/${userID}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("RECOMMEND_PART_VIDEO", res.data["videos"]);
          commit("SET_RECOMMEND",res.data["recPart"])
          
        }).catch((err) => {
          console.log(err);
        })
    },

    getLikeList({commit}){
      var userID = this.getters.GET_USERID;  
      const REST_URL = `http://localhost:9999/api/video/getLike/${userID}`
      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("LIKELIST_VIDEO", res.data)
        }).catch((err) => {
          console.log(err);
        })
    },

    
    getDailyRecordList({commit}){
      var userID = this.getters.GET_USERID; 
      const REST_URL = `http://localhost:9999/api/record/${userID}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        }
      })
        .then((res) => {
          commit("DAILY_RECORD_LIST", res.data)
        }).catch((err) => {
          console.log(err);
        })
    },
    getDailyRecord({commit}, recordId){
      const REST_URL = `http://localhost:9999/api/record/edit/${recordId}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { 
          "access-token" : sessionStorage.getItem("access-token")
        } 
      })
        .then((res) => {
          commit("DAILY_RECORD", res.data)
          router.push('/mypage/dailyedit/${recordId}')
        }).catch((err) => {
          console.log(err);
        })
    },
    editDailyRecord({commit}, record){
      console.log('기록 수정정보 보냈으니까 바꿔줘')
      console.log(record)
      commit
      const REST_URL = `http://localhost:9999/api/record/edit`
      axios({
        url: REST_URL,
        method: 'POST',
        params: record,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        }
      }) .then(() => {
      
        router.push('/mypage')
      }).catch((err) => {
        console.log(err);
      })
    },

    deleteDailyRecord({commit}, recordId){
      const REST_URL = `http://localhost:9999/api/record/${recordId}`
      commit
      axios({
        url: REST_URL,
        method: 'DELETE',
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
      .then(() => {
        router.go()
      }).catch((err) => {
        console.log(err);
      })

    },
    
    inputRecord({commit,dispatch}, record){
      const REST_URL = `http://localhost:9999/api/record/write`
      axios({
        url: REST_URL,
        method: 'POST',
        params: record,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        }
      }).then(() => {
          commit('CREATE_RECORD', record)
          dispatch("getRecordWithEntries");
          
          router.push('/mypage')
        }).catch((err) => {
          console.log(err);
        })
    },





    enrollUser({commit}, userInfo){
      const REST_URL = `http://localhost:9999/api/join`
      //console.log(userInfo)
      commit
      axios({
        url: REST_URL,
        method: 'POST',
        params: userInfo,
      }).then(() => {
          router.push('/login')
          // throw new Error('error');
        }).catch((err) => {
          console.log(err);
        })
    },



    loginUser({commit}, userInfo){
      const REST_URL = `http://localhost:9999/api/login`
      commit
      axios({
        url: REST_URL,
        method: 'POST',
        params: userInfo,
      }).then((res) => {
        console.log(res) //access token도 같이 온다
        sessionStorage.setItem("access-token",res.data["access-token"]);
        sessionStorage.setItem("current-userId",userInfo.userId);
        sessionStorage.setItem("page_owner",userInfo.userId);
        commit('USER_LOGIN',userInfo.userId);
        commit('SET_PAGE_OWNER',userInfo.userId);
        console.log(this.state.userId);
        
        
        router.push('/home')
      }).catch((err) => {
        //여기서 비번 틀리거나 했을 때 처리
        console.log(err.response.data) //"사용자를 찾을 수 없습니다" or "패스워드 어쩌고"
        console.log(err);
      })
    },

    getRecordWithEntries({commit}){
     console.log('Hellooooo')

     var userID = this.getters.GET_PAGE_OWNER;
   
      const REST_URL = `http://localhost:9999/api/record/${userID}`

      axios({
        url: REST_URL,
        method: 'GET',
        headers : { //여기
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          // console.log("9999 port 통해 record 가져온다")
          // console.log(res.data[0])
          // console.log(res.data.length)
          commit("DAILY_RECORD_LIST", res.data)
          commit("SET_ENTRIES") //여기
        }).catch((err) => {
          console.log(err);
        })
    },

    deleteLike({commit}, likeList){
      const REST_URL = `http://localhost:9999/api/video/like/delete`
      commit
      console.log(likeList)
      axios({
        url: REST_URL,
        method: 'POST',
        params: likeList,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
      .then(() => {
        alert('찜목록에서 삭제되었습니다.')
        router.go()
      }).catch((err) => {
        console.log(err);
      })
    },

    getRanking({commit}){
      const REST_URL = `http://localhost:9999/api/record/rank`
      axios({
        url: REST_URL,
        method: 'GET',
        headers : { //여기
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
        
         commit("SET_RANKERS",res.data["names"]);
         commit("SET_RECORDS",res.data["names"]);
      
        }).catch((err) => {
          console.log(err);
        })
    },
    
    getFollow({commit}){
      var userId = this.getters.GET_USERID;
      const REST_URL = `http://localhost:9999/api/record/follow/${userId}`
      axios({
        url: REST_URL,
        method: 'GET',
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("FOLLOW_LIST", res.data)
        
        }).catch((err) => {
          console.log(err);
        })
    },


    addLikeVideo({commit}, params){
      const REST_URL = `http://localhost:9999/api/video/like`
      commit
      console.log(params)
      axios({
        url: REST_URL,
        method: 'Post', //post, Post, POST
        params,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then(() => {
          console.log(0)
        }).catch((err) => {
          console.log(err);
        })
    },



    SET_PAGE_OWNER({commit}, userId){
      sessionStorage.setItem("page_owner",userId);
      commit("SET_PAGE_OWNER",userId);
    },

    addFollow({commit},follow){
      const REST_URL = `http://localhost:9999/api/follow`
      commit
      
      axios({
        url: REST_URL,
        method: 'POST', 
        params: follow,
        headers : {//여기
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
        }).catch((err) => {
          console.log(err);
        })
    },
    unFollow({commit},follow){
      const REST_URL = `http://localhost:9999/api/follow/delete`
      commit
      
      axios({
        url: REST_URL,
        method: 'POST', 
        params: follow,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
          
        }).catch((err) => {
          console.log(err);
        })
    },
    addComment({commit,dispatch},params){
      const REST_URL = `http://localhost:9999/api/comment/write`
      commit
      axios({
        url: REST_URL,
        method: 'POST', 
        params,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
          dispatch("loadComment",params.videoId)
          
        }).catch((err) => {
          console.log(err);
        })
    },
    editComment({commit,dispatch},params){
      const REST_URL = `http://localhost:9999/api/comment/edit`
      commit
      axios({
        url: REST_URL,
        method: 'POST', 
        params,
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          console.log(res.data)
          dispatch("loadComment",params.videoId)
        }).catch((err) => {
          console.log(err);
        })
    },
    loadComment({commit,dispatch}, params){
      
      const REST_URL = `http://localhost:9999/api/comment/`+params
      commit

      axios({
        url: REST_URL,
        method: 'GET', 
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("SET_COMMENT",res.data);
          dispatch("goToVideoDetail",params)
        }).catch((err) => {
          console.log(err);
        })

    },

    goToVideoDetail({commit},param){
      commit
      router.push('/videoDetail/'+param)
    }
    
    
  },
  modules: {
  }
})
