<template>
  <v-app>
    <div>
    <v-app-bar
        app
        color="blue"
        dense
        dark
    >

      <v-app-bar-nav-icon
          v-if="GET_USER_IS_ACTIVE"
          @click.stop="drawer = !drawer"
      ></v-app-bar-nav-icon>

      <router-link to="/main">
        <v-btn
            v-if="GET_USER_IS_ACTIVE && $route.path !== '/main'"
            v-icon
        >
          <v-icon>home</v-icon>
        </v-btn>
      </router-link>
      <v-toolbar-title>
        тут должна быть иконка тгу
      </v-toolbar-title>

      <v-spacer></v-spacer>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/login">
            <v-btn
                v-if="!GET_USER_IS_ACTIVE"
                v-icon
                :disabled="$route.path === '/login'"
                v-bind="attrs"
                v-on="on"
                color="black"
            >
              <v-icon icon="mdi-login" />
            </v-btn>
          </router-link>
        </template>
        <span>Вход в систему</span>
      </v-tooltip>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/profile">
            <v-btn

                v-bind="attrs"
                v-on="on"
            >
              <v-icon icon="mdi-account-circle"/>
            </v-btn>
          </router-link>
        </template>
        <span>Личный кабинет</span>
      </v-tooltip>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/login">
            <v-btn
                v-if="GET_USER_IS_ACTIVE"
                v-icon
                @click="logout"
                v-bind="attrs"
                v-on="on"
            >
              <v-icon>exit_to_app</v-icon>
            </v-btn>
          </router-link>
        </template>
        <span>Выход из учётной записи</span>
      </v-tooltip>

    </v-app-bar>
    </div>

    <div class="container">
      <router-view />
    </div>

  </v-app>
</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: 'App',
  components: {
  },
  data() {
    return {
      drawer: false,
      group: null,
    }
  },
  watch: {
    group() {
      this.drawer = false
    },
  },
  computed: {
    ...mapGetters([
      'GET_USER_IS_ACTIVE',
      'GET_USER_ROLE',
      'IS_ADMIN_ROLE',
      'IS_MANAGER_ROLE'
    ]),
  },
  methods: {
    logout() {
      this.$store.dispatch('auth/LOGOUT');
      // this.$router.push('/login');
    },
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
