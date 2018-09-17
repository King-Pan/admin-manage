export const LOGIN = 'login'
export const LOGOUT = 'logout'
import {setToken} from "../../assets/js/token";

// initial state
const state = {
    token: ''
}

// getters
const getters = {}

// actions
const actions = {
    login({commit}, token) {
        commit(LOGIN, token)
    },
    logout({commit}) {
        commit(LOGOUT)
    }
}


// mutations
const mutations = {
    [LOGIN](state, token) {
        state.token = token
        setToken(token)
    },
    [LOGOUT](state) {
        state.token = ''
        setToken('')
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
