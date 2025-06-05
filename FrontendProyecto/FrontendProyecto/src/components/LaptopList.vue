<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Laptops</h4>

    <LaptopForm
      :laptopEdit="laptopEditado"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />
    <h5 class="mb-3 text-danger text-center">Lista de Laptops</h5>

    <div class="d-flex justify-content-end mb-3">
      <button class="btn btn-outline-success" @click="descargarPDF">
        <i class="bi bi-download me-2"></i> Descargar Registros en PDF
      </button>
    </div>

    <div class="table-responsive">
      <table class="table table-hover table-bordered shadow-sm">
        <thead class="table-info">
          <tr>
            <th class="text-center align-middle">Imagen</th>
            <th class="text-center align-middle">Marca</th>
            <th class="text-center align-middle">Modelo</th>
            <th class="text-center align-middle">Tamaño</th>
            <th class="text-center align-middle">Almacenamiento</th>
            <th class="text-center align-middle">RAM</th>
            <th class="text-center align-middle">Especificaciones</th>
            <th class="text-center align-middle">Precio</th>
            <th class="text-center align-middle">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="laptop in laptops" :key="laptop.id" class="align-middle">
            <td class="text-center">
              <img :src="laptop.imagenURL" alt="Imagen Laptop" style="max-width: 80px; max-height: 80px;" v-if="laptop.imagenURL"/>
              <span v-else class="text-muted small">Sin imagen</span>
            </td>
            <td class="text-center">{{ laptop.marca }}</td>
            <td class="text-center">{{ laptop.modelo }}</td>
            <td class="text-center">{{ laptop.tamaño }}</td>
            <td class="text-center">{{ laptop.almacenamiento }}</td>
            <td class="text-center">{{ laptop.ram }}</td>
            <td class="text-center text-truncate" style="max-width: 200px;" :title="laptop.especificaciones">
              {{ laptop.especificaciones }}
            </td>
            <td class="text-center">${{ laptop.precio.toLocaleString() }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button class="btn btn-info btn-sm me-2" @click="verLaptop(laptop)" title="Ver">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-warning btn-sm me-2" @click="editarLaptop(laptop)" title="Editar">
                  <i class="bi bi-pencil-square"></i>
                </button>
                <button class="btn btn-danger btn-sm" @click="eliminarLaptop(laptop.id)" title="Eliminar">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div v-if="mostrarModal" class="modal fade show" tabindex="-1" style="display: block; background-color: rgba(0,0,0,0.5)">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles de la Laptop</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <img :src="laptopSeleccionada?.imagenURL" alt="Imagen Laptop" class="img-fluid mb-3" v-if="laptopSeleccionada?.imagenURL" />
            <p><strong>Marca:</strong> {{ laptopSeleccionada?.marca }}</p>
            <p><strong>Modelo:</strong> {{ laptopSeleccionada?.modelo }}</p>
            <p><strong>Tamaño:</strong> {{ laptopSeleccionada?.tamaño }}</p>
            <p><strong>Almacenamiento:</strong> {{ laptopSeleccionada?.almacenamiento }}</p>
            <p><strong>RAM:</strong> {{ laptopSeleccionada?.ram }}</p>
            <p><strong>Especificaciones:</strong> {{ laptopSeleccionada?.especificaciones }}</p>
            <p><strong>Precio:</strong> ${{ laptopSeleccionada?.precio?.toLocaleString() }}</p>
          </div>
          <div class="modal-footer">
            <button class="btn btn-secondary" @click="cerrarModal">Cerrar</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import LaptopForm from './LaptopForm.vue'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const laptops = ref([])
const laptopEditado = ref(null)
const laptopSeleccionada = ref(null)
const mostrarModal = ref(false)

const cargarLaptops = async () => {
  try {
    const res = await axios.get('/laptop/traer_laptop')
    laptops.value = res.data
  } catch (error) {
    console.error('Error al cargar laptops:', error)
  }
}

const editarLaptop = (laptop) => {
  laptopEditado.value = { ...laptop }
}

const eliminarLaptop = async (id) => {
  if (confirm('¿Estás seguro de eliminar esta laptop?')) {
    try {
      await axios.delete(`/laptop/eliminar/${id}`)
      cargarLaptops()
    } catch (error) {
      console.error('Error al eliminar laptop:', error)
    }
  }
}

const cancelarEdicion = () => {
  laptopEditado.value = null
}

const refrescar = () => {
  cargarLaptops()
  cancelarEdicion()
}

const descargarPDF = () => {
  const doc = new jsPDF()
  doc.setFontSize(16)
  doc.text('Listado de Laptops', 14, 15)
  autoTable(doc, {
    startY: 20,
    head: [['Marca', 'Modelo', 'Tamaño', 'Almacenamiento', 'RAM', 'Especificaciones', 'Precio']],
    body: laptops.value.map(l => [
      l.marca, l.modelo, l.tamaño, l.almacenamiento, l.ram, l.especificaciones, `$${l.precio.toLocaleString()}`
    ]),
    styles: { fontSize: 10 },
    headStyles: { fillColor: [0, 123, 255] },
  })
  doc.save('laptops.pdf')
}

const verLaptop = (laptop) => {
  laptopSeleccionada.value = laptop
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  laptopSeleccionada.value = null
}

onMounted(cargarLaptops)
</script>

<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1050;
  width: 100%;
  height: 100%;
  overflow: auto;
}
</style>
