<template>
  <div>
    <label for="category-filter">Filter by Category:</label>
    <select id="category-filter" v-model="selectedCategoryFilter" @change="applyFilter">
      <option value="">All Categories</option>
      <option v-for="category in categories" :key="category.id" :value="category.name">
        {{ category.name }}
      </option>
    </select>
  </div>
</template>

<script>
import { useProductStore } from "@/stores/ProductStore";

export default {
  name: "CategoryFilter",
  setup() {
    const productStore = useProductStore();

    const applyFilter = () => {
      productStore.updateCategoryFilter(productStore.selectedCategoryFilter);
    };

    return {
      categories: productStore.categories,
      selectedCategoryFilter: productStore.selectedCategoryFilter, // Two-way bind to the store
      applyFilter,
    };
  },
};
</script>

<style scoped>
/* Optional styles */
select {
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 1rem;
}
</style>
