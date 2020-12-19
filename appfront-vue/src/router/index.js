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
        path: '/shop',
        component: () => import('../views/Shop.vue')
    },
    {
        path: '/developer',
        component: () => import('../views/Developer.vue')
    },
    // {
    //     path: '/gamepage',
    //     component: () => import('../views/GamePage.vue')
    // },
    {
        path: '/witcher3',
        component: () => import('../views/gamepages/witcher3.vue')
    },
    {
        path: '/admin',
        component: () => import('../views/Admin.vue')
    },
    {
        path: '/allgames',
        component: () => import('../views/AllGames.vue')
    }
]

const router = new VueRouter({
    routes,
})

export default router
