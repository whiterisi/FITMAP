import Vue from 'vue'
import VueRouter from 'vue-router'
import MainView from '../views/MainView.vue'
import HomeView from '../views/HomeView.vue'
import RecommendedView from '@/views/RecommendedView.vue'

import PRView from '@/components/main/PRView.vue'
import UserLoginView from '@/components/main/UserLogin.vue'
import UserEnrollView from '@/components/main/UserEnroll.vue'

import VideoView from '../views/VideoView.vue'

import MyPageView from '../views/MyPageView.vue'
import MypageDailyBoard from '@/components/myPage/MypageDailyBoard.vue'
import MypageDailyInput from '@/components/myPage/MypageDailyInput.vue'
import MypageDailyEdit from '@/components/myPage/MypageDailyEdit.vue'
import MypageFollowList from '@/components/myPage/MypageFollowList.vue'
import MypageLikeList from '@/components/myPage/MypageLikeList.vue'

//여기
import videoDetail from '@/components/video/VideoDetail.vue';
import CmtTable from '@/components/video/CommentTable.vue';
import editCmt from '@/components/video/CommentEdit.vue';
import addCmt from '@/components/video/addComment.vue';

Vue.use(VueRouter)

const routes = [


  


  {
    path: '/',
    name: 'main',
    component: MainView,
    children:[
      {
        path: '',
        name: 'pr',
        component: PRView
      },
      {
        path: '/login',
        name: 'login',
        component: UserLoginView
      },
      {
        path: '/enroll',
        name: 'enroll',
        component: UserEnrollView
      },
    ]
  },
  {
    path: '/home',
    name: 'home',
    component: HomeView,
    children:[
      {
        path: '',
        name: 'recommend',
        component: RecommendedView
      },
      {
        path: 'video',
        name: 'video',
        component: VideoView
      },
      {
        path: '/mypage',
        name: 'mypage',
        component: MyPageView,
        children:[
          {
            path:'',
            name: 'dailyboard',
            component: MypageDailyBoard
          },
          {
            path:'dailyinput',
            name: 'dailyinput',
            component: MypageDailyInput
          },
          {
            path:'dailyedit/:recordId',
            name: 'dailyedit',
            component: MypageDailyEdit
          },
          {
            path:'follow',
            name: 'follow',
            component: MypageFollowList
          },
          {
            path:'likelist',
            name: 'likelist',
            component: MypageLikeList
          },
        ]
      },

      {path:'/videoDetail/:videoId',
        name:'videoDetail',
        component: videoDetail,
        props:true,

        children:[
            {
              path: '',
              name: 'CmtTable',
              component: CmtTable,
              
            },
            {
              path: '/edit/:commentId',
              name: 'editCmt',
              component: editCmt,
              
            },
            {
              path: '/write/:videoId',
              name: 'addCmt',
              component: addCmt,
              
            },
          ]
        },
    ]
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})



export default router


