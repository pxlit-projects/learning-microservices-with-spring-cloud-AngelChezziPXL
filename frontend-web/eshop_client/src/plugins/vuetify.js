import 'vuetify/styles'; // Vuetify styles
import '@mdi/font/css/materialdesignicons.css'; // Material Design Icons
import { createVuetify } from 'vuetify';
import { aliases, mdi } from 'vuetify/iconsets/mdi';

const vuetify = createVuetify({
    icons: {
        defaultSet: 'mdi', // Use Material Design Icons
        aliases,
        sets: {
            mdi,
        },
    },
    theme: {
        defaultTheme: 'light',
        themes: {
            light: {
                colors: {
                    primary: '#6200EE',
                    secondary: '#03DAC6',
                    error: '#B00020',
                    background: '#FFFFFF',
                    surface: '#F5F5F5',
                },
            },
            dark: {
                colors: {
                    primary: '#BB86FC',
                    secondary: '#03DAC6',
                    error: '#CF6679',
                    background: '#121212',
                    surface: '#1E1E1E',
                },
            },
        },
    },
    defaults: {
        VBtn: {
            color: 'primary',
            rounded: 'lg',
        },
    },
});

export default vuetify;
