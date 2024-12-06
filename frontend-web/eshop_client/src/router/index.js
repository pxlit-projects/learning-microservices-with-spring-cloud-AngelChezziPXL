import { createRouter, createWebHistory } from 'vue-router';
import { useAuthStore } from '@/stores/AuthStore.js'; // Adjust the path according to your project structure
import LoginView from "@/views/LoginView.vue";
import ProductView from "@/views/ProductView.vue";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: LoginView,
  },
  {
    path: '/products',
    name: 'ProductView',
    component: ProductView,
    meta: { requiresAuth: true }, // Mark this route as requiring authentication
  },
  // Example of a protected route:
  // {
  //   path: '/host-only-page',
  //   name: 'HostOnlyPage',
  //   component: () => import('@/views/HostOnlyView.vue'),
  //   meta: { requiredRole: 'host' }
  // },
  // {
  //   path: '/admin-only-page',
  //   name: 'AdminOnlyPage',
  //   component: () => import('@/views/AdminOnlyView.vue'),
  //   meta: { requiredRole: 'admin' }
  // }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

router.beforeEach((to, from, next) => {
  const authStore = useAuthStore();
  if (to.meta.requiresAuth && !authStore.isAuthenticated) {
    // Redirect to login if not authenticated
    next({ path: '/' });
  } else {
    next();
  }
});



export default router;
