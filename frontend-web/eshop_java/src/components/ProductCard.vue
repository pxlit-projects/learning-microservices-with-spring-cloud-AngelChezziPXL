<template>
  <div class="product-card">
    <h2 class="product-name">{{ product.name }}</h2>
    <p class="product-category">{{ product.category }}</p>
    <p class="product-description">{{ product.description }}</p>

    <ul class="product-tags">
      <li v-for="tag in product.tags" :key="tag">{{ tag }}</li>
    </ul>

    <p class="product-price">\${{ product.price.toFixed(2) }}</p>
    <p class="product-availability">
      <span :class="{ 'available': product.available, 'unavailable': !product.available }">
        {{ product.available ? 'In Stock' : 'Out of Stock' }}
      </span>
    </p>

    <button @click="$emit('select', product.id)" :disabled="!product.available">
      {{ product.available ? 'Select Product' : 'Unavailable' }}
    </button>
  </div>
</template>

<script setup>
defineProps({
  product: {
    type: Object,
    required: true
  }
})
</script>

<style scoped>
.product-card {
  border: 1px solid #ddd;
  border-radius: 8px;
  padding: 1em;
  max-width: 300px;
}

.product-name {
  margin: 0;
  font-size: 1.4em;
  font-weight: bold;
}

.product-category {
  font-style: italic;
  color: #666;
}

.product-description {
  margin: 0.5em 0;
}

.product-tags {
  padding: 0;
  list-style: none;
  display: flex;
  gap: 0.5em;
  margin: 0.5em 0;
}

.product-tags li {
  background: #eee;
  border-radius: 4px;
  padding: 0.25em 0.5em;
  font-size: 0.9em;
}

.product-price {
  font-weight: bold;
  font-size: 1.2em;
}

.product-availability {
  margin: 0.5em 0;
}

.available {
  color: green;
}

.unavailable {
  color: red;
}

button {
  background: #2196f3;
  color: #fff;
  border: none;
  border-radius: 4px;
  padding: 0.5em;
  cursor: pointer;
}

button[disabled] {
  background: #ccc;
  cursor: not-allowed;
}
</style>
