<template>
  <div class="product-card">
    <h2 class="product-name">{{ product.name }}</h2>
    <p class="product-description">{{ product.description }}</p>
    <p class="product-category">Category: <strong>{{ product.category }}</strong></p>
    <div class="product-tags">
      <span v-for="tag in product.tags" :key="tag" class="tag">{{ tag }}</span>
    </div>
    <p class="product-price">Price: ${{ product.price.toFixed(2) }}</p>
    <p class="product-status" :class="{ 'out-of-stock': !product.available }">
      {{ product.available ? 'In Stock' : 'Out of Stock' }}
    </p>

    <div v-if="product.available" class="cart-actions">
      <label for="quantity">Quantity:</label>
      <input
          id="quantity"
          type="number"
          v-model.number="quantity"
          min="1"
          :max="20"
          placeholder="Enter quantity"
      />
      <button @click="addToCart">Add to Cart</button>
    </div>

    <div v-if="isAdmin" class="admin-actions">
      <button class="edit-button" @click="editProduct">Edit</button>
      <button class="delete-button" @click="confirmDelete">Delete</button>
    </div>

  </div>
</template>

<script>
import { computed, ref } from 'vue';
import { useAuthStore } from '@/stores/AuthStore';
import { useProductStore } from '@/stores/ProductStore';

export default {
  name: 'ProductCard',
  props: {
    product: {
      type: Object,
      required: true,
    },
  },
  setup(props) {
    const authStore = useAuthStore();
    const productStore = useProductStore();

    const quantity = ref(1);
    const isAdmin = computed(() => authStore.roles.includes('admin'));

    const addToCart = () => {
      try {
        productStore.addToCart(props.product.id, quantity.value);
      } catch (error) {
        alert(error.message);
      }
    };

    const editProduct = () => {
        productStore.openEditModal(props.product); // Call store action to open modal
    };

    const confirmDelete = () => {
      const confirmation = confirm(
          `Are you sure you want to delete the product "${props.product.name}"?`
      );
      if (confirmation) {
        productStore.deleteProduct(props.product.id);
      }
    };

    return {
      quantity,
      addToCart,
      editProduct,
      confirmDelete,
      isAdmin,
    };
  },
};
</script>

<style scoped>
.product-card {
  border: 1px solid #ccc;
  border-radius: 5px;
  padding: 15px;
  width: 350px;
  margin: 15px auto; /* Center horizontally */
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  text-align: center;
}

/* Product Name Styling */
.product-name {
  font-size: 1.5rem;
  margin-bottom: 10px;
  color: #333;
}

/* Product Description */
.product-description {
  font-size: 1rem;
  margin-bottom: 10px;
  color: #666;
}

/* Product Category and Price */
.product-category,
.product-price,
.product-status {
  font-size: 0.9rem;
  margin-bottom: 5px;
  color: #333;
}

/* Tags Styling */
.product-tags {
  display: flex;
  justify-content: center;
  gap: 5px;
  margin-bottom: 10px;
}

.tag {
  background-color: #007bff;
  color: #fff;
  padding: 3px 8px;
  border-radius: 3px;
  font-size: 0.8rem;
}

/* Cart Actions Styling */
.cart-actions {
  margin-top: 10px;
}

.cart-actions input {
  width: 60px;
  margin: 10px ;
  padding: 5px;
  text-align: center;
  border: solid thin grey;
  border-radius: 5px;
}

.cart-actions button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 3px;
  cursor: pointer;
}

.cart-actions button:hover {
  background-color: #0056b3;
}

/* Admin Actions Styling */
.admin-actions {
  margin-top: 15px;
  display: flex;
  justify-content: center;
  gap: 10px;
}

/* Edit Button Styling */
.edit-button {
  background-color: #28a745;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 3px;
  cursor: pointer;
}

.edit-button:hover {
  background-color: #218838;
}

/* Delete Button Styling */
.delete-button {
  background-color: #dc3545;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 3px;
  cursor: pointer;
}

.delete-button:hover {
  background-color: #c82333;
}

/* Out of Stock Styling */
.out-of-stock {
  color: red;
  font-weight: bold;
}
</style>
