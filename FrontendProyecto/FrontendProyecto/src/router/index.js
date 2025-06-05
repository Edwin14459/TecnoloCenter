import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SmartphonesView from '../views/SmartphonesView.vue'
import LaptopsView from '../views/LaptopsView.vue'
import TabletsView from '../views/TabletsView.vue'
import TelevisoresView from '../views/TelevisoresView.vue'
import AudioView from '../views/AudioView.vue'
import AdminView from '../views/AdminView.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeView
  },
  {
    path: '/home',
    redirect: '/'
  },
  {
    path: '/smartphones',
    name: 'Smartphones',
    component: SmartphonesView
  },
  {
    path: '/laptops',
    name: 'Laptops',
    component: LaptopsView
  },
  {
    path: '/tablets',
    name: 'Tablets',
    component: TabletsView
  },
  {
    path: '/televisores',
    name: 'Televisores',
    component: TelevisoresView
  },
  {
    path: '/audio',
    name: 'Audio',
    component: AudioView
  },
  {
    path: '/admin',
    name: 'Admin',
    component: AdminView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router