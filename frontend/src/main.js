import { createApp } from 'vue'
import App from './App.vue'
import router from '@/router/router'
import store from '@/store'
import sharedComponents from '@/components/ui'
// Vuetify
import 'vuetify/styles'
import vuetify from "@/vuetify/vuetify";

const app = createApp(App);


sharedComponents.forEach(component => {
    app.component(component.name, component);
})


app
    .use(vuetify)
    .use(router)
    .use(store)
    .mount('#app');




