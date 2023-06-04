export default {
    GET_USER_ROLE(state) {
        return state.initialState.user.role
    },
    IS_ADMIN_ROLE(state) {
        return state.initialState.user.role === 'ROLE_ADMIN'
    },
    IS_MANAGER_ROLE(state) {
        return state.initialState.user.role === 'ROLE_MANAGER'
    },
    GET_USER_IS_ACTIVE(state) {
        return state.initialState.status.loggedIn
    },
    GET_EMPLOYEE_INFO(state) {
        return state.employee;
    },
    GET_ACTIVE_MEETING(state) {
        return state.meeting;
    },
};