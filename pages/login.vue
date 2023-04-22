<template>
  <div>
    <v-form name="form" @submit.prevent="handleLogin">
      <v-container>
        <v-row class="justify-center">

          <v-col cols="12" sm="8" md="4">
            <v-text-field
                v-model="user.login"
                type="text"
                name="Имя пользователя"
                label="Имя пользователя"
                counter
                outlined
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row class="justify-center">
          <v-col cols="12" sm="8" md="4">
            <v-text-field
                v-model="user.password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show ? 'text' : 'password'"
                name="Пароль"
                label="Пароль"
                counter
                outlined
                @click:append="show = !show"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-row class="justify-center text-center">
          <v-col cols="12" sm="8" md="4">
        <v-btn
            width="100px"
            rounded
            color="primary"
            dark
            @click="handleLogin"
        >
          <span>Войти</span>
        </v-btn>
          </v-col>
        </v-row>
      </v-container>
    </v-form>

    <v-snackbar
        v-model="snackbar"
        :timeout="notificationTimeout"
        color="error"
    >
      {{ message }}
    </v-snackbar>
  </div>
</template>

<script>
import User from '@/models/user'


export default {
  name: 'login',
  data() {
    return {
      snackbar: false,
      notificationTimeout: 3500,
      user: new User('', ''),
      show: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.initialState.status.loggedIn;
    }
  },
  created() {
    if (this.loggedIn) {
      this.$router.push('/profile');
    }
  },
  methods: {
    handleLogin() {
      console.log('AUTH before login === ', this.$store.state);

      if (this.user.login && this.user.password) {
        this.$store.dispatch('LOGIN', this.user)
            .then(() => {
              this.$router.push('/profile');
            })
            .catch(() => {
              this.message = 'Неверное имя пользователя или пароль';
              this.snackbar = true;
            })
      }
      console.log('AUTH after login === ', this.$store.state)
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
</style>