<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Televisores</h4>

    <TelevisorForm
      :televisorEdit="televisorEditado"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />
    <h5 class="mb-3 text-danger text-center">Lista de Televisores</h5>

    <!-- Botón para descargar PDF -->
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
            <th class="text-center align-middle">Especificaciones</th>
            <th class="text-center align-middle">Precio</th>
            <th class="text-center align-middle">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="televisor in televisores" :key="televisor.id" class="align-middle">
            <td class="text-center">
              <img v-if="televisor.imagenURL" :src="televisor.imagenURL" alt="Imagen" style="width: 70px; height: auto;" />
              <span v-else class="text-muted small">Sin imagen</span>
            </td>
            <td class="text-center">{{ televisor.marca }}</td>
            <td class="text-center">{{ televisor.modelo }}</td>
            <td class="text-center">{{ televisor.tamaño }}</td>
            <td class="text-center text-truncate" style="max-width: 200px;" :title="televisor.especificaciones">
              {{ televisor.especificaciones }}
            </td>
            <td class="text-center">${{ televisor.precio.toLocaleString() }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button class="btn btn-info btn-sm me-2" @click="verTelevisor(televisor)" title="Ver">
                  <i class="bi bi-eye"></i>
                </button>
                <button class="btn btn-warning btn-sm me-2" @click="editarTelevisor(televisor)" title="Editar">
                  <i class="bi bi-pencil-square"></i>
                </button>
                <button class="btn btn-danger btn-sm" @click="eliminarTelevisor(televisor.id)" title="Eliminar">
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal Detalle Televisor -->
    <div
      class="modal fade show"
      tabindex="-1"
      style="display: block; background-color: rgba(0, 0, 0, 0.5)"
      v-if="mostrarModal"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles del Televisor</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <img v-if="televisorSeleccionado?.imagenURL" :src="televisorSeleccionado.imagenURL" alt="Imagen" class="img-fluid mb-3 rounded" />
            <p><strong>Marca:</strong> {{ televisorSeleccionado?.marca }}</p>
            <p><strong>Modelo:</strong> {{ televisorSeleccionado?.modelo }}</p>
            <p><strong>Tamaño:</strong> {{ televisorSeleccionado?.tamaño }}</p>
            <p><strong>Especificaciones:</strong> {{ televisorSeleccionado?.especificaciones }}</p>
            <p><strong>Precio:</strong> ${{ televisorSeleccionado?.precio?.toLocaleString() }}</p>
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
import TelevisorForm from './TelevisorForm.vue'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const televisores = ref([])
const televisorEditado = ref(null)
const televisorSeleccionado = ref(null)
const mostrarModal = ref(false)

const cargarTelevisores = async () => {
  try {
    const res = await axios.get('/televisores/traer_televisores')
    televisores.value = res.data
  } catch (error) {
    console.error('Error al cargar televisores:', error)
  }
}

const editarTelevisor = (televisor) => {
  televisorEditado.value = { ...televisor }
}

const eliminarTelevisor = async (id) => {
  if (confirm('¿Estás seguro de eliminar este televisor?')) {
    try {
      await axios.delete(`/televisores/eliminar/${id}`)
      cargarTelevisores()
    } catch (error) {
      console.error('Error al eliminar televisor:', error)
    }
  }
}

const cancelarEdicion = () => {
  televisorEditado.value = null
}

const refrescar = () => {
  cargarTelevisores()
  cancelarEdicion()
}

const descargarPDF = () => {
  const doc = new jsPDF()
  doc.setFontSize(16)
  doc.text('Listado de Televisores', 14, 15)
  autoTable(doc, {
    startY: 20,
    head: [['Marca', 'Modelo', 'Tamaño', 'Especificaciones', 'Precio']],
    body: televisores.value.map(tv => [
      tv.marca,
      tv.modelo,
      tv.tamaño,
      tv.especificaciones,
      `$${tv.precio.toLocaleString()}`
    ]),
    styles: { fontSize: 10 },
    headStyles: { fillColor: [0, 123, 255] },
  })
  doc.save('televisores.pdf')
}

const verTelevisor = (televisor) => {
  televisorSeleccionado.value = televisor
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  televisorSeleccionado.value = null
}

onMounted(cargarTelevisores)
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
