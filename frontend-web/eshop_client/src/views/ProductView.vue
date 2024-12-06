<template>
  <div>
    <h1>Product List</h1>

    <!-- Category Filter -->
    <CategoryFilter />

    <!-- Tag Filter -->
    <TagFilter />

    <!-- Product List -->
    <div v-if="filteredProducts.length">
      <div v-for="product in filteredProducts" :key="product.id" class="product-item">
        <h3>{{ product.name }}</h3>
        <p>{{ product.description }}</p>
        <p><strong>Category:</strong> {{ product.category }}</p>
        <p><strong>Tags:</strong> {{ product.tags.join(", ") }}</p>
      </div>
    </div>
    <p v-else>No products match the filters.</p>
  </div>
</template>

<script>
import { computed } from "vue";
import { useProductStore } from "@/stores/ProductStore";
import CategoryFilter from "@/components/CategoryFilter.vue";
import TagFilter from "@/components/TagFilter.vue";

export default {
  components: { CategoryFilter, TagFilter },
  setup() {
    const productStore = useProductStore();
    const filteredProducts = computed(() => productStore.filteredProducts);

    return {
      filteredProducts,
    };
  },
};
</script>
