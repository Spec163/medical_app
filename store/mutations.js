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
}