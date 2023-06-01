import { createRouter, createWebHistory } from "vue-router";
import VueMain from '@/views/VueMain.vue'

const routes = [
    {
        path: '/',
        name: 'VueMain',
        component: VueMain
    },
    {
        path: '/login',
        name: 'VueLogin',
        component: () => import('../views/VueLogin.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
