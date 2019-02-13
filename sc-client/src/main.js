import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import VueRouter from 'vue-router'
import axios from 'axios'
import VueAxios from 'vue-axios'


import Register from './components/Register'
import Home from './components/Home'
import Uslugi from './components/Uslugi'
import Popusti from './components/Popusti'
import Novosti from './components/Novosti'
import Login from './components/Login'
import NewPost from './components/NewPost'


Vue.use(VueAxios, axios)
Vue.use(VueRouter);

const routes = [
  { path: '/register', component: Register },
  { path: '/', component: Home },
  { path: '/uslugi', component: Uslugi },
  { path: '/popusti', component: Popusti },
  { path: '/novosti', component: Novosti},
  { path: '/login', component: Login},
  { path: '/newpost', component: NewPost}
];
const router = new VueRouter({
  routes
})

library.add(faCoffee)
Vue.component('font-awesome-icon', FontAwesomeIcon)


Vue.config.productionTip = false

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})



