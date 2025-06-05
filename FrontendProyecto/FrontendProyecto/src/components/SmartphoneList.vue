<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Smartphones</h4>

    <!-- Formulario siempre visible -->
    <SmartphoneForm
      :smartphoneEdit="smartphoneEditado"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />

    <h5 class="mb-3 text-danger text-center">Lista de Smartphones</h5>

    <!-- Botón para descargar registros en PDF -->
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
            <th class="text-center align-middle">RAM (GB)</th>
            <th class="text-center align-middle">Almacenamiento (GB)</th>
            <th class="text-center align-middle">Especificaciones</th>
            <th class="text-center align-middle">Precio</th>
            <th class="text-center align-middle">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="smartphone in smartphones"
            :key="smartphone.id"
            class="align-middle"
          >
            <td class="text-center">
              <img
                v-if="smartphone.imagenURL"
                :src="smartphone.imagenURL"
                :alt="smartphone.modelo"
                class="img-thumbnail"
                width="100"
                height="80"
              />
              <span v-else class="text-muted">Sin imagen</span>
            </td>
            <td class="text-center">{{ smartphone.marca }}</td>
            <td class="text-center">{{ smartphone.modelo }}</td>
            <td class="text-center">{{ smartphone.ram }}</td>
            <td class="text-center">{{ smartphone.almacenamiento }}</td>
            <td
              class="text-center text-truncate"
              style="max-width: 200px;"
              :title="smartphone.especificaciones"
            >
              {{ smartphone.especificaciones }}
            </td>
            <td class="text-center">${{ smartphone.precio.toLocaleString() }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button
                  class="btn btn-info btn-sm me-2"
                  @click="verSmartphone(smartphone)"
                  title="Ver"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm me-2"
                  @click="editarSmartphone(smartphone)"
                  title="Editar"
                >
                  <i class="bi bi-pencil-square"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  @click="eliminarSmartphone(smartphone.id)"
                  title="Eliminar"
                >
                  <i class="bi bi-trash"></i>
                </button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- Modal Detalles Smartphone -->
    <div
      class="modal fade show"
      tabindex="-1"
      style="display: block; background-color: rgba(0, 0, 0, 0.5)"
      v-if="mostrarModal"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles del Smartphone</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <div class="text-center mb-3">
              <img
                v-if="smartphoneSeleccionado?.imagenURL"
                :src="smartphoneSeleccionado.imagenURL"
                :alt="smartphoneSeleccionado.modelo"
                class="img-fluid rounded"
                style="max-height: 200px;"
              />
              <p v-else class="text-muted">Sin imagen disponible</p>
            </div>
            <p><strong>Marca:</strong> {{ smartphoneSeleccionado?.marca }}</p>
            <p><strong>Modelo:</strong> {{ smartphoneSeleccionado?.modelo }}</p>
            <p><strong>RAM:</strong> {{ smartphoneSeleccionado?.ram }} GB</p>
            <p><strong>Almacenamiento:</strong> {{ smartphoneSeleccionado?.almacenamiento }} GB</p>
            <p><strong>Especificaciones:</strong> {{ smartphoneSeleccionado?.especificaciones }}</p>
            <p><strong>Precio:</strong> ${{ smartphoneSeleccionado?.precio?.toLocaleString() }}</p>
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
import SmartphoneForm from './SmartphoneForm.vue'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const smartphones = ref([])
const smartphoneEditado = ref(null)
const smartphoneSeleccionado = ref(null)
const mostrarModal = ref(false)

const cargarSmartphones = async () => {
  try {
    const res = await axios.get('/smartphone/traer_smartphone')
    smartphones.value = res.data
  } catch (error) {
    console.error('Error al cargar smartphones:', error)
  }
}

const editarSmartphone = (smartphone) => {
  smartphoneEditado.value = { ...smartphone }
}

const eliminarSmartphone = async (id) => {
  if (confirm('¿Estás seguro de eliminar este smartphone?')) {
    try {
      await axios.delete(`/smartphone/eliminar/${id}`)
      cargarSmartphones()
    } catch (error) {
      console.error('Error al eliminar smartphone:', error)
    }
  }
}

const cancelarEdicion = () => {
  smartphoneEditado.value = null
}

const refrescar = () => {
  cargarSmartphones()
  cancelarEdicion()
}

const descargarPDF = () => {
  const doc = new jsPDF()

  doc.setFontSize(16)
  doc.text('Listado de Smartphones', 14, 15)

  autoTable(doc, {
    startY: 20,
    head: [['Marca', 'Modelo', 'RAM (GB)', 'Almacenamiento (GB)', 'Especificaciones', 'Precio']],
    body: smartphones.value.map(s => [
      s.marca,
      s.modelo,
      s.ram,
      s.almacenamiento,
      s.especificaciones,
      `$${s.precio.toLocaleString()}`
    ]),
    styles: { fontSize: 10 },
    headStyles: { fillColor: [0, 123, 255] },
  })

  doc.save('smartphones.pdf')
}

const verSmartphone = (smartphone) => {
  smartphoneSeleccionado.value = smartphone
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  smartphoneSeleccionado.value = null
}

onMounted(cargarSmartphones)
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
.img-thumbnail {
  object-fit: cover;
}
</style>
