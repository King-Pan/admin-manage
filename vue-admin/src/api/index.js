import axios from 'axios';
import globalCfg from '../config/global-cfg'
import {getToken} from "../assets/js/token";
import {Message} from 'iview'


/*设置全局请求地址*/
const url = '/api'
axios.defaults.withCredentials = true
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'
// axios.defaults.headers['Content-Type'] = "application/json"
axios.interceptors.request.use(
    config => {
        let token = getToken();
        if (token) {  // 判断是否存在token，如果存在的话，则每个http header都加上token
            config.headers.token = `${token}`
        }
        if (config.url.indexOf(url) === -1) {
            config.url = url + config.url
            /*拼接完整请求路径*/
        }
        config.timeout = 10000
        return config;
    },
    err => {
        Message.error('请求超时')
        return Promise.reject(err)
    })

axios.interceptors.response.use(data => {
    if (data.status && data.status !== 200) {
        Message.error(data.data.msg)
        return;
    }
    return data;
}, err => {
    console.log(err)
    if (err.response.status == 504 || err.response.status == 404) {
        Message.error('服务器被吃了⊙﹏⊙∥')
    } else if (err.response.status == 403) {
        Message.error('权限不足,请联系管理员!')
    } else if (err.request.status === 500) {
        Message.error('服务异常，请联系管理员!')
    } else {
        Message.error('其他异常')
    }
    return Promise.resolve(err)
})

export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            return ret
        }]
    })
}

export const uploadFileRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })
}

export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            return ret
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        }
    })
}


export const deleteRequest = (url) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`
    });
}

export const getRequest = (url) => {
    return axios({
        method: 'get',
        url: `${base}${url}`
    });
}

export default axios
