import AuthService from "~/services/auth-service";

export default {
    LOGIN({commit}, user) {
        console.log('User from LOGIN action == ', user)
        return AuthService.login(user).then(
            user => {
                commit('LOGIN_SUCCESS', user);
                return Promise.resolve(user);
            },
            error => {
                commit('LOGIN_FAILURE');
                return Promise.reject(error);
            }
        );
    },

    LOGOUT({commit}) {
        AuthService.logout();
        commit('LOGOUT');
    },
}