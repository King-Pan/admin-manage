import axios from 'axios';
import globalCfg from '../config/global-cfg'


/*设置全局请求地址*/
const url = '/api'
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// axios.defaults.headers['Content-Type'] = "application/json"
axios.interceptors.request.use(
    config => {
        let token = localStorage.getItem(globalCfg.global.TOKEN_KEY);
        if (token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
            config.headers.token = `${token}`;
        }
        if (config.url.indexOf(url) === -1) {
            config.url = url + config.url;
            /*拼接完整请求路径*/
        }
        config.timeout = 10000
        return config;
    },
    err => {
        return Promise.reject(err);
    }
)
export default axios
