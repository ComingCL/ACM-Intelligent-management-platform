import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import home from '../components/home/home'
import login from '../components/home/login'
import register from '../components/home/register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      redirect: '/user/login'
    },
    {
      path: '/user',
      component: home,
      children: [
        { path: 'login', component: login },
        { path: 'register', component: register },
      ]
    }
  ]
})
