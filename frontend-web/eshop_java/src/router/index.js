import { createRouter, createWebHistory } from 'vue-router'

// Example components
import HomeView from '../views/HomeView.vue'
import AboutView from '../views/AboutView.vue'
import ProductListView from '../views/ProductListView.vue'

const routes = [
    { path: '/', name: 'Home', component: HomeView },
    { path: '/about', name: 'About', component: AboutView },
    { path: '/products', name: 'Products', component: ProductListView }
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router
