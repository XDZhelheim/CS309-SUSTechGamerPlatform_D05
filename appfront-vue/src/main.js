import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'

Vue.config.productionTip = false

new Vue({
    data: function() {
        return {
            userInfo: {
                avatarURL: require("./assets/avatars/testavatar.jpg"),
                username: "Test User Name",
                usertype: "D", // A=admin, D=developer, U=user
                money: 666,
                ID:1
            },

            loginStatus: true
        }
    },
    router,
    store,
    render: h => h(App)
}).$mount('#app')
