import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [{
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import( /* webpackChunkName: "about" */ '../views/About.vue')
    },
    {
        path: '/hello',
        name: 'HelloWorld',
        component: () => import('../components/HelloWorld')
    },
    {
        path: '/my-games',
        name: 'Mygames',
        component: () => import('../views/Mygames.vue')
    },
    {
        path: '/shop',
        component: () => import('../views/Shop.vue')
    },
    {
        path: '/community',
        component: () => import('../views/Community.vue')
    },
    {
        path: '/customer-service',
        component: () => import('../views/Service.vue')
    },
    {
        path: '/developer',
        component: () => import('../views/Developer.vue')
    },
    {
        path: '/user',
        component: () => import('../views/UserInfo.vue')
    }
]

const router = new VueRouter({
    routes,
})

export default router
