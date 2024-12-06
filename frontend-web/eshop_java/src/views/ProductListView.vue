<template>
  <div class="product-list">
    <h1>Our Products ({{ productCount }})</h1>
    <div class="products">
      <ProductCard
          v-for="p in products"
          :key="p.id"
          :product="p"
          @select="handleSelect"
      />
    </div>

    <div class="selected" v-if="selectedProduct">
      <h2>Selected Product Details</h2>
      <p><strong>Name:</strong> {{ selectedProduct.name }}</p>
      <p><strong>Description:</strong> {{ selectedProduct.description }}</p>
      <p><strong>Category:</strong> {{ selectedProduct.category }}</p>
      <p><strong>Tags:</strong> {{ selectedProduct.tags.join(', ') }}</p>
      <p><strong>Price:</strong> \${{ selectedProduct.price.toFixed(2) }}</p>
      <p><strong>Status:</strong> {{ selectedProduct.available ? 'In Stock' : 'Out of Stock' }}</p>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useProductStore } from '../stores/productStore'
import ProductCard from '../components/ProductCard.vue'

const productStore = useProductStore()

const products = computed(() => productStore.products)
const productCount = computed(() => productStore.productCount)
const selectedProduct = computed(() => productStore.selectedProduct)

function handleSelect(id) {
  productStore.selectProduct(id)
}
</script>

<style scoped>
.product-list {
  display: flex;
  flex-direction: column;
  gap: 2em;
  padding: 2em;
}

.products {
  display: flex;
  flex-wrap: wrap;
  gap: 1em;
}

.selected {
  border-top: 1px solid #ccc;
  padding-top: 1em;
}
</style>
