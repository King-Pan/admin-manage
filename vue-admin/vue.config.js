module.exports = {
    // 这里写你调用接口的基础路径，来解决跨域，如果设置了代理，那你本地开发环境的axios的baseUrl要写为 '' ，即空字符串
    devServer: {
        host: '0.0.0.0',
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:8888/api',
                ws: true,
                changeOrigin: true,
                pathRewrite:{
                    '^/api':''
                }
            }
        }
    }
}
