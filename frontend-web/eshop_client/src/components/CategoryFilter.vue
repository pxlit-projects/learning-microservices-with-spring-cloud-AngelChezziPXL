<template>
  <div>
    <h2>Filter by Category</h2>
    <select v-model="selectedCategory" @change="filterProducts">
      <option value="">All Categories</option>
      <option v-for="category in categories" :key="category" :value="category">
        {{ category.name}}
      </option>
    </select>
  </div>
</template>

<script>
import { computed } from "vue";
import { useProductStore } from "@/stores/ProductStore";

export default {
  name: "CategoryFilter",
  setup() {
    const productStore = useProductStore();

    const categories = computed(() => productStore.categories);
    const selectedCategory = computed({
      get: () => productStore.selectedCategory,
      set: (value) => (productStore.selectedCategory = value),
    });


    const filterProducts = () => {
      if (selectedCategory.value) {
        productStore.filterByCategory(selectedCategory.value);
      } else {
        productStore.resetFilters(); // Reset filters if "All Categories" is selected
      }
    };

    return {
      categories,
      selectedCategory,
      filterProducts,
    };
  },
};
</script>

<style scoped>
/* Add any necessary styles */
select {
  padding: 8px;
  font-size: 1rem;
  border: grey thin solid;
  border-radius: 5px;
}
select:hover{
  background-color: lightgray;
}
</style>
