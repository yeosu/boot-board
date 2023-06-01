import { createRouter, createWebHistory } from "vue-router";
import VueMain from '@/views/VueMain.vue'
import BoardList from '@/views/board/BoardList.vue'

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
    },
    {
        path: '/board/list',
        name: 'BoardList',
        component: BoardList
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
