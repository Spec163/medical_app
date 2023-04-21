import store from '@/store/index'
import {createRouter, createWebHistory} from "vue-router";
import ProfilePage from "@/components/profile/ProfilePage";
import LoginPage from "@/components/auth/LoginPage";

// проверка авторизации пользователя
function checkAuth(to, from, next) {
    if (store.getters.GET_USER_IS_ACTIVE) {
        next()
    } else {
        console.warn('Вам необходимо авторизоваться!')
        next('login')
    }
}

// проверка прав
// function checkPrivilege(to, from, next) {
//     if (store.getters.GET_USER_IS_ACTIVE) {
//         if (store.getters.IS_ADMIN_ROLE) {
//             next()
//         } else {
//             // если недостаточно прав
//             next('main')
//         }
//     } else {
//         console.warn('Вам необходимо авторизоваться!')
//         next('login')
//     }
// }

// запрещает посещать страницу, если пользователь авторизован
// function checkNoAuth(to, from, next) {
//     if (store.getters.GET_USER_IS_ACTIVE) {
//         console.warn('Вам необходимо выйти из своего аккаунта!')
//         next('main')
//     } else {
//         next()
//     }
// }

const routes = [
    // {
    //     // 404
    //     path: '*',
    //     name: 'NotFound',
    //     redirect: 'main',
    //     beforeEnter(to, from, next) {
    //         checkAuth(to, from, next);
    //     },
    // },
    {
        path: '/login',
        name: 'login',
        component: LoginPage,
        // beforeEnter(to, from, next) {
        //     checkNoAuth(to, from, next);
        // }
    },
    {
        path: '/profile',
        name: 'profile',
        component: ProfilePage,
        beforeEnter(to, from, next) {
            checkAuth(to, from, next);
        }
    },
];


const router = createRouter({
    // mode: 'history',
    routes,
    history: createWebHistory(),
});

export default router;
