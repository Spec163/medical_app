import {createStore} from "vuex";
import mutations from "@/store/mutations";
import actions from "@/store/actions";
import getters from "@/store/getters";
import auth from "@/store/auth.module";

export default createStore({
    state: {
        // accountInfo: UserInfo,
        profile: {},
        users: [],
        meetings: []
    },
    mutations,
    actions,
    getters,
    modules: {
        auth
    }
});