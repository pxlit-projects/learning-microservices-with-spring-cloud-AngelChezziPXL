<template>
  <div v-if="isVisible" class="modal-overlay" @click.self="closeModal">
    <div class="modal-content">
      <header class="modal-header">
        <h2>Edit Product</h2>
        <button class="close-button" @click="closeModal">×</button>
      </header>
      <main class="modal-body">
        <form @submit.prevent="saveProduct">
          <!-- Name -->
          <label>
            Name:
            <input v-model="selectedProduct.name" type="text" required />
          </label>

          <!-- Description -->
          <label>
            Description:
            <textarea class="input-field" v-model="selectedProduct.description" required></textarea>
          </label>

          <!-- Category Dropdown -->
          <label>
            Category:
            <select class="input-field" v-model="selectedProduct.category" required>
              <option v-for="category in categories" :key="category.id" :value="category.name">
                {{ category.name }}
              </option>
            </select>
          </label>

          <!-- Tags -->
          <label>
            Tags:
            <div class="tags-container">
              <div v-for="(tag, index) in 4" :key="index" class="tag-item">
                <input
                    v-model="selectedProduct.tags[index]"
                    type="text"
                    placeholder="Enter tag"
                    maxlength="20"
                    class="input-field tag-input"
                    @input="handleTagInput(index)"
                />
              </div>
            </div>
          </label>


          <!-- Price -->
          <label>
            Price:
            <input
                class="input-field"
                v-model.number="selectedProduct.price"
                type="number"
                step="0.01"
                min="0"
                placeholder="0.00"
                required
            />
          </label>

          <!-- Available Checkbox -->
          <label>
            Available:
            <input v-model="selectedProduct.available" type="checkbox" />
          </label>



          <!-- Save Button -->
          <button type="submit">Save</button>
        </form>
      </main>
    </div>
  </div>
</template>

<script>
import { computed, reactive } from "vue";
import { useProductStore } from "@/stores/ProductStore";

export default {
  name: "ProductModal",
  setup() {
    const productStore = useProductStore();

    // Bind directly to the selectedProduct in the store
    const isVisible = computed(() => productStore.isModalVisible);
    const selectedProduct = computed(() => ({
      ...productStore.selectedProduct,
          tags: productStore.selectedProduct.tags || [""], // Initialize with one empty tag
    }));

    const saveProduct = () => {
      productStore.saveProduct(selectedProduct.value);
    };

    const closeModal = () => {
      productStore.closeEditModal();
    };


    return {
      isVisible,
      selectedProduct,
      categories: productStore.categories,
      closeModal,
      saveProduct,
    };
  },
};
</script>

<style scoped>
.modal-overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  border-radius: 8px;
  padding: 20px;
  width: 400px;
  max-width: 90%;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.close-button {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
}

.modal-body {
  margin-top: 10px;
}

form {
  display: flex;
  flex-direction: column;
  gap: 10px;
}

form label {
  display: flex;
  flex-direction: column;
  font-weight: bold;
}

.input-field{
  border: grey thin solid;
  border-radius: 5px;
}


form button {
  align-self: flex-start;
  padding: 5px 10px;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>
