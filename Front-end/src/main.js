import VuejsHeatmap from '@/components/common/VuejsHeatmap'
export default VuejsHeatmap

import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)


Vue.config.productionTip = false

// 안돌아가면 아랫부분 싹 지우고 여기 주석 풀기
// new Vue({
//   store,
//   router,
//   render: h => h(App)
// }).$mount('#app')

import AOS from 'aos'
import 'aos/dist/aos.css'


new Vue({
  store,
  router,
  created () {
    AOS.init()
  },
  render: h => h(App)
}).$mount('#app')