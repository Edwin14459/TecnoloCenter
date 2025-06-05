<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Tablets</h4>

    <TabletForm
      :tabletEdit="tabletEditada"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />
    <h5 class="mb-3 text-danger text-center">Lista de Tablets</h5>

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
          <tr v-for="tablet in tablets" :key="tablet.id" class="align-middle">
            <td class="text-center">
              <img :src="tablet.imagenURL" alt="Imagen Tablet" style="max-width: 80px; max-height: 80px;" v-if="tablet.imagenURL" />
              <span v-else class="text-muted small">Sin imagen</span>
            </td>
            <td class="text-center">{{ tablet.marca }}</td>
            <td class="text-center">{{ tablet.modelo }}</td>
            <td class="text-center">{{ tablet.tamaño }}</td>
            <td class="text-center">{{ tablet.almacenamiento }}</td>
            <td class="text-center">{{ tablet.ram }}</td>
            <td class="text-center text-truncate" style="max-width: 150px;" :title="tablet.especificaciones">
              {{ tablet.especificaciones }}
            </td>
            <td class="text-center">${{ tablet.precio.toLocaleString() }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button class="btn btn-info btn-sm me-2" @click="verTablet(tablet)" title="Ver"><i class="bi bi-eye"></i></button>
                <button class="btn btn-warning btn-sm me-2" @click="editarTablet(tablet)" title="Editar"><i class="bi bi-pencil-square"></i></button>
                <button class="btn btn-danger btn-sm" @click="eliminarTablet(tablet.id)" title="Eliminar"><i class="bi bi-trash"></i></button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal -->
    <div class="modal fade show" tabindex="-1" style="display: block; background-color: rgba(0, 0, 0, 0.5)" v-if="mostrarModal">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles de la Tablet</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <img :src="tabletSeleccionada?.imagenURL" alt="Tablet" class="img-fluid mb-3" v-if="tabletSeleccionada?.imagenURL" />
            <p><strong>Marca:</strong> {{ tabletSeleccionada?.marca }}</p>
            <p><strong>Modelo:</strong> {{ tabletSeleccionada?.modelo }}</p>
            <p><strong>Tamaño:</strong> {{ tabletSeleccionada?.tamaño }}</p>
            <p><strong>Almacenamiento:</strong> {{ tabletSeleccionada?.almacenamiento }}</p>
            <p><strong>RAM:</strong> {{ tabletSeleccionada?.ram }}</p>
            <p><strong>Especificaciones:</strong> {{ tabletSeleccionada?.especificaciones }}</p>
            <p><strong>Precio:</strong> ${{ tabletSeleccionada?.precio?.toLocaleString() }}</p>
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
import TabletForm from './TabletForm.vue'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const tablets = ref([])
const tabletEditada = ref(null)
const tabletSeleccionada = ref(null)
const mostrarModal = ref(false)

const cargarTablets = async () => {
  try {
    const res = await axios.get('/tablet/traer_tablet')
    tablets.value = res.data
  } catch (error) {
    console.error('Error al cargar tablets:', error)
  }
}

const editarTablet = (tablet) => {
  tabletEditada.value = { ...tablet }
}

const eliminarTablet = async (id) => {
  if (confirm('¿Estás seguro de eliminar esta tablet?')) {
    try {
      await axios.delete(`/tablet/eliminar/${id}`)
      cargarTablets()
    } catch (error) {
      console.error('Error al eliminar tablet:', error)
    }
  }
}

const cancelarEdicion = () => {
  tabletEditada.value = null
}

const refrescar = () => {
  cargarTablets()
  cancelarEdicion()
}

const descargarPDF = () => {
  const doc = new jsPDF()
  doc.setFontSize(16)
  doc.text('Listado de Tablets', 14, 15)
  autoTable(doc, {
    startY: 20,
    head: [['Marca', 'Modelo', 'Tamaño', 'Almacenamiento', 'RAM', 'Especificaciones', 'Precio']],
    body: tablets.value.map(t => [
      t.marca,
      t.modelo,
      t.tamaño,
      t.almacenamiento,
      t.ram,
      t.especificaciones,
      `$${t.precio.toLocaleString()}`
    ]),
    styles: { fontSize: 10 },
    headStyles: { fillColor: [0, 123, 255] },
  })
  doc.save('tablets.pdf')
}

const verTablet = (tablet) => {
  tabletSeleccionada.value = tablet
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  tabletSeleccionada.value = null
}

onMounted(cargarTablets)
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
