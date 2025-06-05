import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': path.resolve(__dirname, './src'),
    },
  },
  server: {
    proxy: {
      '/home': 'http://localhost:8080',
      '/smartphone': 'http://localhost:8080',
      '/laptop': 'http://localhost:8080',
      '/tablet': 'http://localhost:8080',
      '/televisores': 'http://localhost:8080',
      '/audio': 'http://localhost:8080',
      '/admin': 'http://localhost:8080',
    },
    fs: {
      strict: false,
    }
  }
})
