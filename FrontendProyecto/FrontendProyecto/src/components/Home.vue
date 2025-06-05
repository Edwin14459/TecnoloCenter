<template>
  <div class="discount-page">
    <!-- Encabezado con promociones -->
    <header class="header">
      <div class="logo-container">
        <h1 class="main-title">Ofertas en <span class="brand-highlight">Tecno|Center</span></h1>
      </div>

      <div class="promo-banner">
        <p class="platform-tagline">Los mejores productos tecnológicos con descuentos exclusivos</p>

        <div class="discount-highlights">
          <div class="discount-tag mega-discount">
            <span class="discount-value">HASTA 35%</span>
            <span class="discount-text">DE DESCUENTO</span>
          </div>

          <div class="discount-tag financing">
            <span class="discount-value">HASTA 18 MESES</span>
            <span class="discount-text">SIN INTERESES</span>
          </div>
        </div>
      </div>
    </header>

    <!-- Banner de envío gratis -->
    <div class="free-shipping-banner">
      <div class="shipping-icon"></div>
      <div class="shipping-text">
        <p class="shipping-main text-center">ENVÍO GRATIS</p>
        <p class="shipping-sub">EN COMPRAS SUPERIORES A $500.000</p>
      </div>
    </div>

    <!-- Sección de Smartphones -->
    <div class="products-section">
      <h2>Smartphones</h2>

      <div v-if="loadingSmartphones" class="loading-spinner">
        <p>Cargando smartphones...</p>
      </div>

      <div v-else-if="errorSmartphones" class="error-message">
        <p>Error al cargar smartphones. Por favor intente nuevamente.</p>
      </div>

      <div v-else class="products-grid">
        <div class="product-card" v-for="product in smartphones" :key="product.id" @click="viewProductDetail(product)">
          <img :src="product.imagenURL || 'https://via.placeholder.com/150'" :alt="`${product.marca} ${product.modelo}`" class="product-image">
          <h3>{{ product.marca }} {{ product.modelo }}</h3>
          <div class="specs-container">
            <p><strong>RAM:</strong> {{ product.ram }}GB</p>
            <p><strong>Almacenamiento:</strong> {{ product.almacenamiento }}GB</p>
          </div>
          <p class="specs">{{ truncateSpecs(product.especificaciones) }}</p>
          <div class="price-section">
            <p class="original-price" v-if="product.precioOriginal">${{ product.precioOriginal.toLocaleString() }}</p>
            <p class="price">${{ product.precio.toLocaleString() }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Sección de Laptops -->
    <div class="products-section">
      <h2>Laptops</h2>

      <div v-if="loadingLaptops" class="loading-spinner">
        <p>Cargando laptops...</p>
      </div>

      <div v-else-if="errorLaptops" class="error-message">
        <p>Error al cargar laptops. Por favor intente nuevamente.</p>
      </div>

      <div v-else class="products-grid">
        <div class="product-card" v-for="product in laptops" :key="product.id" @click="viewProductDetail(product)">
          <img :src="product.imagenURL || 'https://via.placeholder.com/150'" :alt="`${product.marca} ${product.modelo}`" class="product-image">
          <h3>{{ product.marca }} {{ product.modelo }}</h3>
          <div class="specs-container">
            <p><strong>Pantalla:</strong> {{ product.tamaño }}"</p>
            <p><strong>RAM:</strong> {{ product.ram }}GB</p>
          </div>
          <p class="specs">{{ truncateSpecs(product.especificaciones) }}</p>
          <div class="price-section">
            <p class="original-price" v-if="product.precioOriginal">${{ product.precioOriginal.toLocaleString() }}</p>
            <p class="price">${{ product.precio.toLocaleString() }}</p>
            <p class="discount-percent" v-if="product.descuento">{{ product.descuento }}% OFF</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Sección de Tablets -->
    <div class="products-section">
      <h2>Tablets</h2>

      <div v-if="loadingTablets" class="loading-spinner">
        <p>Cargando tablets...</p>
      </div>

      <div v-else-if="errorTablets" class="error-message">
        <p>Error al cargar tablets. Por favor intente nuevamente.</p>
      </div>

      <div v-else class="products-grid">
        <div class="product-card" v-for="product in tablets" :key="product.id" @click="viewProductDetail(product)">
          <img :src="product.imagenURL || 'https://via.placeholder.com/150'" :alt="`${product.marca} ${product.modelo}`" class="product-image">
          <h3>{{ product.marca }} {{ product.modelo }}</h3>
          <div class="specs-container">
            <p><strong>Pantalla:</strong> {{ product.tamaño }}"</p>
            <p><strong>Almacenamiento:</strong> {{ product.almacenamiento }}GB</p>
          </div>
          <p class="specs">{{ truncateSpecs(product.especificaciones) }}</p>
          <div class="price-section">
            <p class="original-price" v-if="product.precioOriginal">${{ product.precioOriginal.toLocaleString() }}</p>
            <p class="price">${{ product.precio.toLocaleString() }}</p>
            <p class="discount-percent" v-if="product.descuento">{{ product.descuento }}% OFF</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Sección de Audios -->
    <div class="products-section">
      <h2>Equipos de Audio</h2>

      <div v-if="loadingAudios" class="loading-spinner">
        <p>Cargando equipos de audio...</p>
      </div>

      <div v-else-if="errorAudios" class="error-message">
        <p>Error al cargar equipos de audio. Por favor intente nuevamente.</p>
      </div>

      <div v-else class="products-grid">
        <div class="product-card" v-for="product in audios" :key="product.id" @click="viewProductDetail(product)">
          <img :src="product.imagenURL || 'https://via.placeholder.com/150'" :alt="`${product.marca} ${product.modelo}`" class="product-image">
          <h3>{{ product.marca }} {{ product.modelo }}</h3>
          <div class="specs-container">
            <p><strong>Tipo:</strong> {{ product.tipo || 'Audio' }}</p>
            <p><strong>Modelo:</strong> {{ product.modelo }}</p>
          </div>
          <p class="specs">{{ truncateSpecs(product.especificaciones) }}</p>
          <div class="price-section">
            <p class="original-price" v-if="product.precioOriginal">${{ product.precioOriginal.toLocaleString() }}</p>
            <p class="price">${{ product.precio.toLocaleString() }}</p>
            <p class="discount-percent" v-if="product.descuento">{{ product.descuento }}% OFF</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal de detalle del producto -->
    <div class="product-modal" v-if="selectedProduct">
      <div class="modal-content">
        <span class="close" @click="selectedProduct = null">&times;</span>
        <h2>{{ selectedProduct.marca }} {{ selectedProduct.modelo }}</h2>

        <div class="modal-image-container">
          <img :src="selectedProduct.imagenURL || 'https://via.placeholder.com/300'" :alt="selectedProduct.modelo" class="modal-product-image">
          <div class="modal-badge" v-if="selectedProduct.oferta">OFERTA EXCLUSIVA</div>
        </div>

        <div class="modal-specs">
          <template v-if="selectedProduct.ram">
            <p><strong>RAM:</strong> {{ selectedProduct.ram }}GB</p>
          </template>
          <template v-if="selectedProduct.almacenamiento">
            <p><strong>Almacenamiento:</strong> {{ selectedProduct.almacenamiento }}GB</p>
          </template>
          <template v-if="selectedProduct.tamaño">
            <p><strong>Tamaño:</strong> {{ selectedProduct.tamaño }}</p>
          </template>
          <template v-if="selectedProduct.potencia">
            <p><strong>Potencia:</strong> {{ selectedProduct.potencia }}</p>
          </template>
          <p><strong>Especificaciones:</strong> {{ selectedProduct.especificaciones }}</p>
        </div>

        <div class="modal-price-section">
          <p class="original-price" v-if="selectedProduct.precioOriginal">
            Antes: <del>${{ selectedProduct.precioOriginal.toLocaleString() }}</del>
          </p>
          <p class="price">Ahora: ${{ selectedProduct.precio.toLocaleString() }}</p>
          <p class="discount-percent" v-if="selectedProduct.descuento">
            {{ selectedProduct.descuento }}% DE DESCUENTO
          </p>
        </div>


      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      smartphones: [],
      laptops: [],
      tablets: [],
      audios: [],
      loadingSmartphones: false,
      loadingLaptops: false,
      loadingTablets: false,
      loadingAudios: false,
      errorSmartphones: false,
      errorLaptops: false,
      errorTablets: false,
      errorAudios: false,
      selectedProduct: null
    };
  },
  methods: {
    async cargarSmartphones() {
      this.loadingSmartphones = true;
      this.errorSmartphones = false;
      try {
        const res = await axios.get('/smartphone/traer_smartphone');
        this.smartphones = this.aplicarDescuentos(res.data).slice(0, 3);
      } catch (error) {
        console.error('Error al cargar smartphones:', error);
        this.errorSmartphones = true;
      } finally {
        this.loadingSmartphones = false;
      }
    },
    async cargarLaptops() {
      this.loadingLaptops = true;
      this.errorLaptops = false;
      try {
        const res = await axios.get('/laptop/traer_laptop');
        this.laptops = this.aplicarDescuentos(res.data).slice(0, 3);
      } catch (error) {
        console.error('Error al cargar laptops:', error);
        this.errorLaptops = true;
      } finally {
        this.loadingLaptops = false;
      }
    },
    async cargarTablets() {
      this.loadingTablets = true;
      this.errorTablets = false;
      try {
        const res = await axios.get('/tablet/traer_tablet');
        this.tablets = this.aplicarDescuentos(res.data).slice(0, 3);
      } catch (error) {
        console.error('Error al cargar tablets:', error);
        this.errorTablets = true;
      } finally {
        this.loadingTablets = false;
      }
    },
    async cargarAudios() {
      this.loadingAudios = true;
      this.errorAudios = false;
      try {
        const res = await axios.get('/audio/traer_audio');
        this.audios = this.aplicarDescuentos(res.data).slice(0, 3);
      } catch (error) {
        console.error('Error al cargar audios:', error);
        this.errorAudios = true;
      } finally {
        this.loadingAudios = false;
      }
    },
    aplicarDescuentos(productos) {
      return productos.map(item => ({
        ...item,
        oferta: Math.random() > 0.5,
        descuento: Math.random() > 0.5 ? Math.floor(Math.random() * 35) + 5 : 0,
        precioOriginal: Math.random() > 0.5 ? 
          Math.round(item.precio * (1 + (Math.random() * 0.3 + 0.1))) : null
      }));
    },
    viewProductDetail(product) {
      this.selectedProduct = product;
    },
    truncateSpecs(specs) {
      if (!specs) return '';
      return specs.length > 100 ? specs.substring(0, 100) + '...' : specs;
    },
    addToCart(product) {
      // Lógica para agregar al carrito
      console.log('Agregado al carrito:', product);
      alert(`${product.marca} ${product.modelo} añadido al carrito`);
      this.selectedProduct = null;
    }
  },
  mounted() {
    this.cargarSmartphones();
    this.cargarLaptops();
    this.cargarTablets();
    this.cargarAudios();
  }
};
</script>

<style scoped>
/* Estilos base similares al componente original */
.discount-page {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.header {
  background: linear-gradient(135deg, #2c3e50, #3498db);
  color: white;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
  text-align: center;
}

.main-title {
  font-size: 2.2rem;
  margin-bottom: 10px;
}

.brand-highlight {
  color: #f1c40f;
  font-weight: bold;
}

.platform-tagline {
  font-size: 1.1rem;
  margin-bottom: 15px;
}

.discount-highlights {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 15px;
}

.discount-tag {
  padding: 10px 20px;
  border-radius: 8px;
  font-weight: bold;
  text-align: center;
  display: flex;
  flex-direction: column;
}

.mega-discount {
  background-color: #e74c3c;
}

.financing {
  background-color: #27ae60;
}

.discount-value {
  font-size: 1.5rem;
  line-height: 1;
}

.discount-text {
  font-size: 0.9rem;
}

.free-shipping-banner {
  background-color: #f1c40f;
  color: #2c3e50;
  padding: 10px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
  gap: 10px;
}

.shipping-main {
  font-weight: bold;
  font-size: 1.3rem;
  margin: 0;
}

.shipping-sub {
  font-size: 0.8rem;
  margin: 0;
}

.products-section {
  margin-bottom: 40px;
  padding: 15px;
  background-color: #f8f9fa;
  border-radius: 10px;
}

.products-section h2 {
  text-align: center;
  color: #2c3e50;
  margin-bottom: 20px;
  font-size: 1.8rem;
  padding-bottom: 10px;
  border-bottom: 2px solid #3498db;
}

.products-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(280px, 1fr));
  gap: 25px;
}

.product-card {
  background: white;
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.3s, box-shadow 0.3s;
  position: relative;
  cursor: pointer;
}

.product-card:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.product-badge {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: #e74c3c;
  color: white;
  padding: 5px 10px;
  border-radius: 4px;
  font-size: 0.8rem;
  font-weight: bold;
}

.product-image {
  width: 100%;
  height: 180px;
  object-fit: contain;
  margin-bottom: 15px;
  border-radius: 6px;
  background-color: #f5f5f5;
}

.product-card h3 {
  color: #2c3e50;
  margin: 10px 0;
  font-size: 1.2rem;
}

.specs-container {
  display: flex;
  gap: 15px;
  margin: 10px 0;
  font-size: 0.9rem;
}

.specs {
  color: #7f8c8d;
  font-size: 0.9em;
  margin: 10px 0;
  line-height: 1.4;
}

.price-section {
  margin: 15px 0;
}

.original-price {
  color: #95a5a6;
  text-decoration: line-through;
  font-size: 0.9rem;
}

.price {
  color: #2c3e50;
  font-weight: bold;
  font-size: 1.3rem;
  margin: 5px 0;
}

.discount-percent {
  color: #27ae60;
  font-weight: bold;
  font-size: 0.9rem;
}

/* Estilos del modal */
.product-modal {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  z-index: 1000;
}

.modal-content {
  background: white;
  padding: 30px;
  border-radius: 10px;
  max-width: 600px;
  width: 90%;
  max-height: 90vh;
  overflow-y: auto;
  position: relative;
}

.close {
  position: absolute;
  top: 15px;
  right: 20px;
  font-size: 1.8rem;
  cursor: pointer;
  color: #7f8c8d;
}

.modal-image-container {
  position: relative;
  margin: 20px 0;
}

.modal-product-image {
  width: 100%;
  max-height: 300px;
  object-fit: contain;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  background-color: #f5f5f5;
}

.modal-badge {
  position: absolute;
  top: 10px;
  right: 10px;
  background-color: #e74c3c;
  color: white;
  padding: 8px 15px;
  border-radius: 4px;
  font-size: 1rem;
  font-weight: bold;
}

.modal-specs {
  margin: 20px 0;
}

.modal-specs p {
  margin: 8px 0;
}

.modal-price-section {
  margin: 25px 0;
}

.modal-actions {
  display: flex;
  gap: 15px;
  margin-top: 20px;
}

.buy-now {
  background: linear-gradient(to right, #e74c3c, #c0392b);
  color: white;
  border: none;
  padding: 14px;
  font-size: 16px;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s;
  flex: 1;
}

.buy-now:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(231, 76, 60, 0.3);
}

/* Estilos para loading y error */
.loading-spinner {
  text-align: center;
  padding: 2rem;
  color: #3498db;
  font-size: 1.2rem;
}

.error-message {
  text-align: center;
  padding: 2rem;
  color: #e74c3c;
  font-size: 1.2rem;
}

/* Responsive */
@media (max-width: 768px) {
  .products-grid {
    grid-template-columns: 1fr;
  }

  .discount-highlights {
    flex-direction: column;
    align-items: center;
  }

  .modal-actions {
    flex-direction: column;
  }
}
</style>