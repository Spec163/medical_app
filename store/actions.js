import AuthService from "~/services/auth-service";
import ProfileService from "~/services/profile-service";

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

    SET_EMPLOYEE_INFO({commit}) {
        ProfileService.getEmployeeInfo().then(
            employee => {
                commit('SET_EMPLOYEE_INFO_MUTATION', employee);
                // return Promise.resolve(employee);
                return employee;
            },
            error => {
                // return Promise.reject(error);
                return error;
            }
        );
    },

    SET_ACTIVE_MEETING({commit}) {
        ProfileService.getActiveMeeting().then(
            meeting => {
                commit('SET_ACTIVE_MEETING_MUTATION', meeting);
                // return Promise.resolve(meeting);
                return meeting;
            },
            error => {
                // return Promise.reject(error);
                return error;
            }
        );
    },
}