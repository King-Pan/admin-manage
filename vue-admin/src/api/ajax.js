import Vue from 'vue'
import axios from 'axios'
import globalCfg from '../config/global-cfg'



/**
 * 获取一个新的自定义的axios实例
 */
const ajaxUrl =
    process.env.NODE_ENV === 'development'
        ? globalCfg.Host.development // 开发环境中的后端地址
        : process.env.NODE_ENV === 'production'
        ? globalCfg.Host.production // 生产环境中的后端地址
        : globalCfg.Host.test // 测试环境的后端地址
