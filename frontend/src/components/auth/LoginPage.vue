<template>
  <div>
    <h3>ALOHA</h3>
    <h3>{{ message }}</h3>
    <v-form name="form" @submit.prevent="handleLogin">
      <v-container>
        <v-row justify="center">

          <v-col cols="12" sm="8" md="4">
            <v-text-field
                v-model="user.login"
                type="text"
                label="Login"
                counter
                outlined
            ></v-text-field>
          </v-col>
        </v-row>

        <v-row>
          <v-col cols="12" sm="8" md="4"></v-col>
          <v-col cols="12" sm="8" md="4">
            <v-text-field
                v-model="user.password"
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :type="show ? 'text' : 'password'"
                name="password"
                label="Password"
                counter
                outlined
                @click:append="show = !show"
            ></v-text-field>
          </v-col>
        </v-row>
        <v-btn
            width="100px"
            rounded
            color="primary"
            dark
            @click="handleLogin"
        >
          <span>Login</span>
        </v-btn>
      </v-container>
    </v-form>

  </div>
</template>

<script>
import User from '../../models/user'
export default {
  name: 'LoginPage',
  data() {
    return {
      user: new User('', ''),
      show: false,
      message: ''
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    }
  },
  // created() {
  //   if (this.loggedIn) {
  //     this.$router.push('/profile');
  //   }
  // },
  methods: {
    handleLogin() {
      if (this.user.login && this.user.password) {
        this.$store.dispatch('auth/LOGIN', this.user)
            .then(() => {
              // this.$router.push('/profile');
            })
            .catch(() => {
              this.message = 'Incorrect login or password!'
            })
      }
    }
  }
};
</script>

<style scoped>
label {
  display: block;
  margin-top: 10px;
}
h3 {
  color: red;
  font-family: "Arial Narrow";
  font-style: italic;
}
</style>