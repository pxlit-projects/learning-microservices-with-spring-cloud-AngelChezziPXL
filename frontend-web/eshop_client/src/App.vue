<template>
  <div class="grid-container">
    <!-- Header -->
    <header class="header">
      <h1>eShop Client (Java Full Stack)</h1>
      <div v-if="isAuthenticated">
        <p style="display: inline-block">Welcome <strong style="color: yellow">{{username}}</strong></p>
        <LogoutButton v-if="isAuthenticated" />
        <NewProductButton v-if="isAdmin" />
      </div>
    </header>

    <!-- Main Content -->
    <main class="content">
      <LoginForm v-if="!isAuthenticated" />
      <ProductMain v-if="isAuthenticated" />
    </main>

    <!-- Footer -->
    <footer class="footer">
      <p>&copy; 2024 eShop Client. All rights reserved.</p>
    </footer>
  </div>
</template>

<script>
import { ref, computed } from 'vue';
import LoginForm from '@/components/LoginForm.vue';
import LogoutButton from "@/components/LogoutButton.vue";
import {useAuthStore} from "@/stores/AuthStore.js";
import NewProductButton from "@/components/NewProductButton.vue";
import ProductMain from "@/components/ProductMain.vue";

export default {
  name: 'App',
  components: {
    NewProductButton,
    LogoutButton,
    LoginForm,
    ProductMain
  },
  setup() {
    const authStore = useAuthStore();
    const loginData = ref({ username: '', password: '' });
    const errorMessage = ref('');

    return {
      loginData,
      errorMessage,
      isAuthenticated: computed(() => authStore.isAuthenticated),
      username: computed(() => authStore.username),
      isAdmin: computed(() => authStore.isAdmin)
    };
  },
};
</script>

