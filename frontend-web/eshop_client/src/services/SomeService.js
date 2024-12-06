import axiosInstance from './axiosInstance';

export default class SomeService {
    static async fetchProtectedResource() {
        try {
            const response = await axiosInstance.get('/protected-resource');
            return response.data;
        } catch (error) {
            console.error('Error fetching protected resource:', error.message);
            throw error;
        }
    }

    static async createResource(data) {
        try {
            const response = await axiosInstance.post('/resources', data);
            return response.data;
        } catch (error) {
            console.error('Error creating resource:', error.message);
            throw error;
        }
    }
}
