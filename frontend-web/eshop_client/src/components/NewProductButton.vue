<template>
  <button v-if="isAdmin" class="new-product-button" @click="addNewProduct">
    Add New Product
  </button>
</template>

<script>
import { computed } from 'vue';
import { useAuthStore } from '@/stores/AuthStore';
import { useProductStore} from "@/stores/ProductStore.js";

export default {
  name: 'NewProductButton',
  setup() {
    const authStore = useAuthStore();
    const productStore = useProductStore();

    // Check if the user is an admin
    const isAdmin = computed(() => authStore.roles.includes('admin'));

    const addNewProduct = () => {
      productStore.openEditModal()
    };

    return {
      isAdmin,
      addNewProduct,
    };
  },
};
</script>

<style scoped>
.new-product-button {
  float: left;
  padding: 10px 20px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.new-product-button:hover {
  background-color: #218838;
}
</style>
