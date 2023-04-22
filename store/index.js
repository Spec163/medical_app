import mutations from "@/store/mutations";
import actions from "@/store/actions";
import getters from "@/store/getters";
import auth from "@/store/auth.module";

const state = () => ({
    profile: {},
    users: [],
    meetings: []
})

export default {
    state,
    mutations,
    actions,
    getters,
    modules: {
        auth
    }
}