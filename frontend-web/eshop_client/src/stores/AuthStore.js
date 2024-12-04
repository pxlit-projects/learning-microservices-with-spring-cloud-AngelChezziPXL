import { defineStore } from 'pinia';
import AuthService from '../services/AuthService';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        username: null,
        roles: [],
        token: null,
    }),
    getters: {
        isAuthenticated: (state) => !!state.token,
        isAdmin: (state) => state.roles.includes('admin'),
    },
    actions: {
        async login(username, password) {
            try {
                // Call AuthService and destructure the result
                const { username: returnedUsername, roles, token } = await AuthService.sendLoginRequest(username, password);

                // Update the store with the returned values
                this.username = returnedUsername;
                this.roles = roles;
                this.token = token;
            } catch (error) {
                console.error('Login failed:', error.message);
                throw error; // Re-throw the error to be handled by the caller
            }
        },
        async logout() {
            try {
                // Call the AuthService to handle logout
                await AuthService.sendLogoutRequest();

                // Reset the store
                this.username = null;
                this.roles = [];
                this.token = null;
            } catch (error) {
                console.error('Logout failed:', error.message);
                throw error; // Re-throw the error to be handled by the caller
            }
        },
    },
});
