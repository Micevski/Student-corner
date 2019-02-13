const path = require('path')

module.exports = {
    configureWebpack: {
        resolve: {
            alias: {
                'vue$': path.resolve('./node_modules/vue/dist/vue.common.js'),
            },
        },
    },

    devServer: {
        proxy: {
            '^/api': {
                target: 'http://localhost:8090',
                ws: true,
                changeOrigin: true
            },
        }
    }

}
