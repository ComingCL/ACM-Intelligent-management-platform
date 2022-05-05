// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import global_ from './Global'
import axios from "axios"; //引用文件
import ElementUI from 'element-ui';
import "./assets/icons/icon.css";
import './styles/element-variables.scss'
import '@/styles/index.scss'
import '@/styles/meetboxs.scss'

Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.prototype.GLOBAL = global_//挂载到Vue实例上面
axios.defaults.withCredentials = true
Vue.prototype.axios = axios;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
