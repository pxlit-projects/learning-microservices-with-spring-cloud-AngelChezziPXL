import axios from 'axios';
import { useAuthStore } from '@/stores/AuthStore';

const axiosInstance = axios.create({
    baseURL: 'https://example-api.com', // Replace with your API base URL
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add a request interceptor to include the token in the headers
axiosInstance.interceptors.request.use((config) => {
    const authStore = useAuthStore(); // Access the Pinia store
    if (authStore.token) {
        config.headers['Authorization'] = authStore.token; // Include the token
    }
    return config;
}, (error) => {
    // Handle the error
    return Promise.reject(error);
});

export default axiosInstance;
