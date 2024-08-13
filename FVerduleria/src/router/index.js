import { createRouter, createWebHistory } from 'vue-router'
import home from '../views/home.vue'
import producto from '../views/ProductoView.vue'
import lote from '../views/LoteView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: home
    },
    {
      path: '/producto',
      name: 'producto',
      component: producto
    },
    {
      path: '/lote',
      name: 'lote',
      component: lote
    },
  ]
})

export default router
