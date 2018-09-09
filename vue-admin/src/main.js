import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import store from './store/'
import iview from 'iview'
import 'iview/dist/styles/iview.css'
import VueWechatTitle from 'vue-wechat-title'

Vue.use(iview)
Vue.use(VueWechatTitle)
Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')