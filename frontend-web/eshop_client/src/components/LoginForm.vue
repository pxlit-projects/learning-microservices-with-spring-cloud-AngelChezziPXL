<template>
  <div class="login-container">
    <!-- Login Form -->
    <form @submit.prevent="handleLogin" class="login-form">
      <label for="username">Username:</label>
      <input
          id="username"
          v-model="username"
          type="text"
          placeholder="Enter your username"
          required
      />

      <label for="password">Password:</label>
      <input
          id="password"
          v-model="password"
          type="password"
          placeholder="Enter your password"
          required
      />

      <button type="submit">Login</button>

      <p v-if="errorMessage" style="color:red">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import { ref } from 'vue';
import { useAuthStore } from '@/stores/AuthStore.js'; // Adjust the path/file name as needed

export default {
  setup() {
    const authStore = useAuthStore();
    const username = ref('');
    const password = ref('');
    const errorMessage = ref('');

    const handleLogin = async () => {
      if (username.value && password.value) {
        try {
          await authStore.login(username.value, password.value);
          // If you have a router, you could navigate after login, e.g.:
          // router.push('/dashboard');
        } catch (error) {
          errorMessage.value = 'Invalid credentials';
        }
      } else {
        alert('Please enter valid credentials.');
      }
    };

    return {
      authStore,
      username,
      password,
      errorMessage,
      handleLogin,
    };
  },
};
</script>

