import './assets/common.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "./utils/axios";
import store from "@/vuex/store";

const app = createApp(App)
app.config.globalProperties.$axios = axios;
app.config.globalProperties.$serverUrl = '//localhost:8080'
app.config.globalProperties.$store = store
app
    .use(router)
    .use(store)
    .mount('#app')
