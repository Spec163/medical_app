import { createApp } from 'vue'
import App from './App.vue'
import store from '@/store'
import sharedComponents from '@/components/ui'
// Vuetify
import 'vuetify/styles'
import vuetify from "@/vuetify/vuetify";
import router from '@/router'

const app = createApp(App);


sharedComponents.forEach(component => {
    app.component(component.name, component);
})


app
    .use(vuetify)
    .use(store)
    .use(router)
    .mount('#app');




