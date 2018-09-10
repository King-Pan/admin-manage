import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import store from './store/'
import iview from 'iview'
import 'iview/dist/styles/iview.css'
import VueWechatTitle from 'vue-wechat-title'
import axios from './api/index'
import qs from 'qs'
Vue.prototype.$axios = axios

Vue.prototype.$qs = qs

Vue.use(iview)
Vue.use(VueWechatTitle)
Vue.config.productionTip = false

let app = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')

