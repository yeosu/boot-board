import { createRouter, createWebHistory } from "vue-router";
import VueMain from '@/views/VueMain.vue'
import BoardList from '@/views/board/BoardList.vue'
import BoardDetail from "@/views/board/BoardDetail.vue";
import BoardWrite from "@/views/board/BoardWrite.vue";
import UserJoin from "@/views/user/UserJoin.vue";
import store from "@/vuex/store"

const requireAuth = () => (from, to, next) => {
    const token = localStorage.getItem('user_token')
    if(token){
        console.log(token);
        store.state.isLogin = true
        return next();
    }
    next('/login');
}


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
        component: BoardList,
        beforeEnter: requireAuth()
    },
    {
        path: '/board/detail',
        name: 'BoardDetail',
        component: BoardDetail,
        beforeEnter: requireAuth()
    },
    {
        path: '/board/write',
        name: 'BoardWrite',
        component: BoardWrite,
        beforeEnter: requireAuth()
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
