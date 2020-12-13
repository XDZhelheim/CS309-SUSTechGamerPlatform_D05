import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/about',
        name: 'About',
        component: () => import('../views/About.vue')
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
    }
]

const router = new VueRouter({
    routes,
})

export default router
