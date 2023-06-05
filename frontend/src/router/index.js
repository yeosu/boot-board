import { createRouter, createWebHistory } from "vue-router";
import VueMain from '@/views/VueMain.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from "@/views/board/BoardDetail.vue";
import BoardWrite from "@/views/board/BoardWrite.vue";
import UserJoin from "@/views/user/UserJoin.vue";

const routes = [
    {
        path: '/',
        name: 'VueMain',
        component: VueMain
    },
    {
        path: '/login',
        name: 'VueLogin',
        component: () => import('../views/user/VueLogin.vue')
    },
    {
        path: '/join',
        name: 'UserJoin',
        component: UserJoin
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
    },
    {
        path: '/board/write',
        name: 'BoardWrite',
        component: BoardWrite
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
