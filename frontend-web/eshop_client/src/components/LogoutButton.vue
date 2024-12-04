<template>
  <button class="logout-button" @click.prevent="handleLogout">Logout</button>
</template>

<script>
import { useAuthStore } from "@/stores/AuthStore.js"
import { computed } from "vue";
export default {
  name: "LogoutButton",
  setup() {
    const authStore = useAuthStore();
    const handleLogout = computed(() => authStore.logout());
    return {
        handleLogout,
    }
  },

}
</script>
