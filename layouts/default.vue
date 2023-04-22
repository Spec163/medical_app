<template>
  <v-app>
    <v-navigation-drawer
        v-model="drawer"
        :color="navBarColor"
        clipped
        fixed
        app
    >
      <v-list>
        <v-list-item
            v-for="(item, i) in items"
            :key="i"
            :to="item.to"
            router
            exact
        >
          <v-list-item-action>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-action>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
    <v-app-bar
        :color="toolbarColor"
        clipped-left
        fixed
        app
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"/>
      <v-toolbar-title style="cursor: pointer" @click="$router.push('/profile')">{{ title }}</v-toolbar-title>
      <v-spacer/>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/login">
            <v-btn
                v-if="!isActiveUser"
                v-bind="attrs"
                v-on="on"
                icon
            >
              <v-icon>mdi-login</v-icon>
            </v-btn>
          </router-link>
        </template>
        <span>Перейти на страницу входа</span>
      </v-tooltip>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/login">
            <v-btn
                v-if="isActiveUser"
                @click="logout"
                v-bind="attrs"
                v-on="on"
                icon
            >
              <v-icon>mdi-exit-to-app</v-icon>
            </v-btn>
          </router-link>
        </template>
        <span>Выход из учётной записи</span>
      </v-tooltip>

      <v-tooltip bottom>
        <template v-slot:activator="{ on, attrs }">
          <router-link to="/profile">
            <v-btn
                v-if="isActiveUser"
                v-bind="attrs"
                v-on="on"
                icon
            >
              <v-icon>mdi-account-circle</v-icon>
            </v-btn>
          </router-link>
        </template>
        <span>Личный кабинет</span>
      </v-tooltip>
    </v-app-bar>
    <v-main>
      <v-container>
        <Nuxt/>
      </v-container>
    </v-main>
    <v-footer
        color="primary"
        absolute
        app
    >
      <span>Medical application by <strong>Artem Gorbatiuk</strong> &copy; {{ new Date().getFullYear() }}</span>
    </v-footer>
  </v-app>
</template>

<script>

export default {
  name: 'DefaultLayout',
  data() {
    return {
      toolbarColor: '#0db0ff',
      navBarColor: '#059be1',
      drawer: false,
      items: [
        {
          icon: 'mdi-account-circle',
          title: 'Личный кабинет',
          to: '/profile'
        },
        {
          icon: 'mdi-chart-bubble',
          title: 'История посещений',
          to: '/history'
        }
      ],
      title: 'Запись на медкомиссию'
    }
  },
  computed: {
    isActiveUser() {
      return this.$store.getters.GET_USER_IS_ACTIVE;
    },
    isAdminRole() {
      return this.$store.getters.IS_ADMIN_ROLE;
    },
    isMangerRole() {
      return this.$store.getters.IS_MANAGER_ROLE;
    },
    isUserRole() {
      return this.$store.getters.GET_USER_ROLE;
    },
  },
  methods: {
    logout() {
      console.log('== ', this.$store.state.initialState)
      this.$store.dispatch('LOGOUT');
      this.$router.push('/login');
    },
  }
}
</script>
