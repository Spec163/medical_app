export default {
    LOGIN_SUCCESS(state, user) {
        state.initialState.status.loggedIn = true;
        state.initialState.user = user;
    },
    LOGIN_FAILURE(state) {
        state.initialState.status.loggedIn = false;
        state.user = null;
    },
    LOGOUT(state) {
        state.initialState.status.loggedIn = false;
        state.initialState.user = null;
    },
    SET_EMPLOYEE_INFO_MUTATION(state, employee) {
        console.log('SET_EMPLOYEE_INFO_MUTATION', employee)
        state.employee = employee;
    },
    SET_ACTIVE_MEETING_MUTATION(state, activeMeeting) {
        console.log('SET_ACTIVE_MEETING_MUTATION', activeMeeting)
        state.activeMeeting = activeMeeting;
    },
}