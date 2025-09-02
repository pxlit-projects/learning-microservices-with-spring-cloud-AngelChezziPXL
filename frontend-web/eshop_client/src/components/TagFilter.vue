<template>
  <div>
    <label for="tag-filter">Filter by Tags:</label>
    <div id="tag-filter" class="tag-container">
      <div v-for="tag in uniqueTags" :key="tag" class="tag-item">
        <input
            type="text"
            v-model="searchTagList"
            @change="updateTags"
        />
        <label :for="tag">{{ tag }}</label>
      </div>
    </div>
  </div>
</template>

<script>
import { computed } from "vue";
import { useProductStore } from "@/stores/ProductStore.js";

export default {
  name: "TagFilter",
  components: {
    searchTagList: "",
  },
  getters() {
    
  },
  setup() {
    const productStore = useProductStore();
    this.searchTagList = "Hello"

    // Compute unique tags from all products
    const uniqueTags = computed(() => {
      const allTags = productStore.products.flatMap((product) => product.tags);
      return [...new Set(allTags)];
    });

    return {
      uniqueTags,
      selectedTags: productStore.selectedTags, // Two-way bind to store's selectedTags
      updateTags: productStore.updateTags, // Action to update selectedTags
      searchTagList
    };
  },
};
</script>

<style>
.tag-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.tag-item {
  display: flex;
  align-items: center;
}
</style>
