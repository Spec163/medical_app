import { createVuetify } from 'vuetify'
import * as components from 'vuetify/lib/components'
import * as directives from 'vuetify/lib/directives'
import { aliases } from 'vuetify/lib/iconsets/fa'
import { mdi } from 'vuetify/lib/iconsets/mdi'

const vuetify = createVuetify({
    components,
    directives,
    icons: {
        defaultSet: 'mdi',
        aliases,
        sets: {
            mdi
        }
    },
})

export default vuetify;