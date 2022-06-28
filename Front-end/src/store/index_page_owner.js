import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '@/router'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
   
    PAGE_OWNER : '',
  
  },
  getters: {
  
    GET_PAGE_OWNER(state){
      state.PAGE_OWNER = sessionStorage.getItem("page_owner");
      return state.PAGE_OWNER;
    }

  },
  mutations: {
   
    
    SET_PAGE_OWNER(state,userId){
      state.PAGE_OWNER = userId;
    },
   
  },
  actions: {
  
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
        headers : {
          "access-token" : sessionStorage.getItem("access-token")
        },
      })
        .then((res) => {
          commit("DAILY_RECORD_LIST", res.data)
          commit("SET_ENTRIES") //여기
        }).catch((err) => {
          console.log(err);
        })
    },

    SET_PAGE_OWNER({commit}, userId){
      sessionStorage.setItem("page_owner",userId);
      commit("SET_PAGE_OWNER",userId);
    }

  },
  modules: {
  }
})
