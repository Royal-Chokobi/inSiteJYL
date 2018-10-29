import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import test from '@/components/test'
import sitest from '@/html/si_vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    {
      path: '/test',
      name: 'aaaa',
      component: test
    },
    {
      path: '/si_vue',
      name: 'aaaaaaaaaaaaa',
      component: sitest
    }
  ]
})
