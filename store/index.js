import mutations from "@/store/mutations";
import actions from "@/store/actions";
import getters from "@/store/getters";

const user = process.client ? JSON.parse(localStorage.getItem('user')) : null;

const state = () => ({
    initialState: user
        ? {status: {loggedIn: true}, user}
        : {status: {loggedIn: false}, user: null},
    profile: {},
    users: [],
    meetings: []
})

export default {
    state,
    mutations,
    actions,
    getters,
}