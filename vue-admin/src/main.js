import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import store from './store/'
import iview from 'iview'
import 'iview/dist/styles/iview.css'
import VueWechatTitle from 'vue-wechat-title'
import {getRequest, postRequest, deleteRequest, putRequest, uploadFileRequest} from './api/index'
import qs from 'qs'

//Vue.prototype.$axios = axios

Vue.prototype.$qs = qs

Vue.prototype.getRequest = getRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.uploadFileRequest = uploadFileRequest;


Vue.use(iview)
Vue.use(VueWechatTitle)
Vue.config.productionTip = false

let app = new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')

