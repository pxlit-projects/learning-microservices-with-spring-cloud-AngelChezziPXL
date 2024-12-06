<template>
  <h1>Product List</h1>
  <CategoryFilter />
  <ProductList />
</template>


<script>
import { computed } from "vue";
import { useProductStore } from "@/stores/ProductStore";
import CategoryFilter from "@/components/CategoryFilter.vue"
import ProductList from "@/components/ProductList.vue";

export default {
  components: {
    ProductList,
    CategoryFilter
  },
  setup() {
    const productStore = useProductStore();
    return {

    };
  },
};

</script>