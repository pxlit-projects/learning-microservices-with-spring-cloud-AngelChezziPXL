<template>
    <h1>Product List</h1>




    <ProductList />
</template>


<script>
import { computed } from "vue";
import { useProductStore } from "@/stores/ProductStore";
import ProductList from "@/components/ProductList.vue";

export default {
  components: {
    ProductList,
  },
  setup() {
    const productStore = useProductStore();
    return {

    };
  },
};

</script>