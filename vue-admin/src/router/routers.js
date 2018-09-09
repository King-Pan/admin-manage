import Main from '../views/main/Main'

export default [
    {
        path: '/',
        name: 'main',
        component: Main,
        meta: {
            title: '后台管理系统-首页'
        }
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('../views/About.vue'),
        meta: {
            title: '后台管理系统-关于'
        }
    },
    {
        path: '/user',
        name: 'user',
        component: () => import('../views/login/Login.vue'),
        meta: {
            title: '后台管理系统-登陆'
        }
    }
]
