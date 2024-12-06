import { defineStore } from 'pinia'

export const useProductStore = defineStore('product', {
    state: () => ({
        products: [
            {
                id: 1,
                name: 'Ultra-HD Television',
                description: 'A 55-inch ultra-HD television with HDR and smart features.',
                category: 'Electronics',
                tags: ['tv', 'home entertainment', '4k'],
                price: 799.99,
                available: true
            },
            {
                id: 2,
                name: 'Espresso Machine',
                description: 'A premium espresso machine with built-in grinder.',
                category: 'Kitchen',
                tags: ['coffee', 'espresso', 'kitchen appliance'],
                price: 299.99,
                available: true
            },
            {
                id: 3,
                name: 'Wireless Headphones',
                description: 'Noise-cancelling wireless headphones with 30-hour battery life.',
                category: 'Audio',
                tags: ['headphones', 'wireless', 'noise-canceling'],
                price: 199.99,
                available: false
            }
        ],
        selectedProductId: null
    }),
    getters: {
        selectedProduct(state) {
            return state.products.find(p => p.id === state.selectedProductId) || null
        },
        productCount(state) {
            return state.products.length
        }
    },
    actions: {
        selectProduct(id) {
            this.selectedProductId = id
        },
        // You could add more actions here to fetch products from an API, update availability, etc.
    }
})
