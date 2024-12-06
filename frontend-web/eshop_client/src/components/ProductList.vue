<template>
  <div class="product-list">
    <ProductCard
        v-for="product in productStore.products"
        :key="product.id"
        :product="product"
    />
  </div>
  <ProductModal />
</template>

<script>
import { useProductStore } from '@/stores/ProductStore';
import ProductCard from '@/components/ProductCard.vue';
import ProductModal from "@/components/ProductModal.vue";

export default {
  components: {ProductModal, ProductCard },
  setup() {
    const productStore = useProductStore();

    return {
      productStore,
    };
  },
};
</script>

<style scoped>
.product-list {
  display: grid;
  grid-template-columns: 1fr; /* Default: 1 product per row */
  gap: 20px; /* Space between products */
  justify-content: center;
}

/* 2 products per row for screen widths between 800px and 1200px */
@media (min-width: 800px) and (max-width: 1199px) {
  .product-list {
    grid-template-columns: repeat(2, 1fr); /* 2 products per row */
  }
}

/* 3 products per row for screen widths 1200px or wider */
@media (min-width: 1200px) {
  .product-list {
    grid-template-columns: repeat(3, 1fr); /* 3 products per row */
  }
}

</style>
