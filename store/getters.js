export default {
    GET_USER_ROLE(state) {
        return state.auth.user.role
    },
    IS_ADMIN_ROLE(state) {
        return state.auth.user.role === 'ROLE_ADMIN'
    },
    IS_MANAGER_ROLE(state) {
        return state.auth.user.role === 'ROLE_MANAGER'
    },
    GET_USER_IS_ACTIVE(state) {
        return state.auth.status.loggedIn
    },
};