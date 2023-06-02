import { createRouter, createWebHistory } from "vue-router";
import VueMain from '@/views/VueMain.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from "@/views/board/BoardDetail.vue";

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
    },
    {
        path: '/board/detail',
        name: 'BoardDetail',
        component: BoardDetail
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
