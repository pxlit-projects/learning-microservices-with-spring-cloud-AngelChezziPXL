import { defineStore } from 'pinia';

export const useProductStore = defineStore('product', {
    state: () => ({
        emptyProduct: {
            id: null,
            name: '',
            description: '',
            category: '',
            tags: [],
            price: 0,
            available: true,
        },
        selectedProduct: Object,
        isModalVisible: false,

        selectedCategory: "", // Declarative naming for the category filter
        filteredProducts: [], // List of products matching filters

        categories: [
            {
                id: 1,
                name: "laptop"
            },
            {
                id: 2,
                name: "desktop"
            },
            {
                id: 3,
                name: "tv"
            },
            {
                id: 4,
                name: "smartphone"
            },

        ],
        products: [
            {
                id: 1,
                name: 'Product A',
                description: 'This is product A.',
                category: 'laptop',
                tags: ['Tag1', 'Tag2'],
                price: 99.99,
                available: true,
            },
            {
                id: 2,
                name: 'Product B',
                description: 'This is product B.',
                category: 'desktop',
                tags: ['Tag3'],
                price: 49.99,
                available: false,
            },
            {
                id: 3,
                name: 'Product C',
                description: 'This is product B.',
                category: 'smartphone',
                tags: [],
                price: 49.99,
                available: false,
            },
            {
                id: 4,
                name: 'Product d',
                description: 'This is product D.',
                category: 'tv',
                tags: ['Tag3'],
                price: 49.99,
                available: false,
            },
        ],
    }),
    getters: {
        getProductById: (state) => (id) => state.products.find((product) => product.id === id),
        getFilteredProducts(state) {
            return state.filteredProducts.length ? state.filteredProducts : state.products;
        },
    },

    actions: {
        filterByCategory(category) {
            this.filteredProducts = this.products.filter(
                (product) => product.category === category.name
            );
        },
        resetFilters() {
            this.filteredProducts = [];
            this.selectedCategory = "";
        },


        addToCart(productId, quantity) {
            const product = this.getProductById(productId);
            if (!product || !product.available) {
                throw new Error('Product not available or does not exist.');
            }
            // Simulate adding to cart
            alert(`Added ${quantity} of ${product.name} to the cart.`);
        },

        // EDIT
        openEditModal(product=null) {
            if(product) {
                this.selectedProduct = JSON.parse(JSON.stringify(product)); // Deep clone to prevent reference issues
            } else {
                this.selectedProduct = JSON.parse(JSON.stringify(this.emptyProduct)); // Add new product
            }
            this.isModalVisible = true;
        },
        closeEditModal() {
            this.selectedProduct = null;
            this.isModalVisible = false;
        },
        saveProduct(updatedProduct) {
            if (updatedProduct.id === null) {
                // Simulate POST to the database
                console.log('Posting new product to the database:', updatedProduct);

                // Assign a temporary ID for now (if required for frontend use)
                const newId = Math.max(...this.products.map((p) => p.id), 0) + 1;
                updatedProduct.id = newId;

                this.products.push({ ...updatedProduct });
            } else {
                // Update existing product
                const index = this.products.findIndex((p) => p.id === updatedProduct.id);
                if (index !== -1) {
                    this.products[index] = { ...updatedProduct };
                }
                console.log('Updating product in the database:', updatedProduct);
            }
            this.closeEditModal();
        },
        deleteProduct(productId) {
            const index = this.products.findIndex((product) => product.id === productId);
            if (index !== -1) {
                this.products.splice(index, 1);
                this.filteredProducts = this.filteredProducts.filter(
                    (product) => product.id !== productId
                );
            }
        },
    },
});
