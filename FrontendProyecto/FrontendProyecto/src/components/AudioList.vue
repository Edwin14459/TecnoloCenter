<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Audios</h4>

    <AudioForm
      :audioEdit="audioEditado"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />
    <h5 class="mb-3 text-danger text-center">Lista de Audios</h5>

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
            <th class="text-center align-middle">Especificaciones</th>
            <th class="text-center align-middle">Precio</th>
            <th class="text-center align-middle">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="audio in audios" :key="audio.id" class="align-middle">
            <td class="text-center">
              <img
                v-if="audio.imagenURL"
                :src="audio.imagenURL"
                :alt="audio.modelo"
                class="img-thumbnail"
                width="100"
                height="80"
              />
              <span v-else class="text-muted">Sin imagen</span>
            </td>
            <td class="text-center">{{ audio.marca }}</td>
            <td class="text-center">{{ audio.modelo }}</td>
            <td
              class="text-center text-truncate"
              style="max-width: 200px;"
              :title="audio.especificaciones"
            >
              {{ audio.especificaciones }}
            </td>
            <td class="text-center">${{ audio.precio.toLocaleString() }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button
                  class="btn btn-info btn-sm me-2"
                  @click="verAudio(audio)"
                  title="Ver"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm me-2"
                  @click="editarAudio(audio)"
                  title="Editar"
                >
                  <i class="bi bi-pencil-square"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  @click="eliminarAudio(audio.id)"
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

    <!-- Modal de Vista de Audio -->
    <div
      class="modal fade show"
      tabindex="-1"
      style="display: block; background-color: rgba(0, 0, 0, 0.5)"
      v-if="mostrarModal"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles del Audio</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <div class="text-center mb-3">
              <img
                v-if="audioSeleccionado?.imagenURL"
                :src="audioSeleccionado.imagenURL"
                :alt="audioSeleccionado.modelo"
                class="img-fluid rounded"
                style="max-height: 200px;"
              />
              <p v-else class="text-muted">Sin imagen disponible</p>
            </div>
            <p><strong>Marca:</strong> {{ audioSeleccionado?.marca }}</p>
            <p><strong>Modelo:</strong> {{ audioSeleccionado?.modelo }}</p>
            <p><strong>Especificaciones:</strong> {{ audioSeleccionado?.especificaciones }}</p>
            <p><strong>Precio:</strong> ${{ audioSeleccionado?.precio?.toLocaleString() }}</p>
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
import AudioForm from './AudioForm.vue'
import jsPDF from 'jspdf'
import autoTable from 'jspdf-autotable'

const audios = ref([])
const audioEditado = ref(null)
const audioSeleccionado = ref(null)
const mostrarModal = ref(false)

const cargarAudios = async () => {
  try {
    const res = await axios.get('/audio/traer_audio')
    audios.value = res.data
  } catch (error) {
    console.error('Error al cargar audios:', error)
  }
}

const editarAudio = (audio) => {
  audioEditado.value = { ...audio }
}

const eliminarAudio = async (id) => {
  if (confirm('¿Estás seguro de eliminar este audio?')) {
    try {
      await axios.delete(`/audio/eliminar/${id}`)
      cargarAudios()
    } catch (error) {
      console.error('Error al eliminar audio:', error)
    }
  }
}

const cancelarEdicion = () => {
  audioEditado.value = null
}

const refrescar = () => {
  cargarAudios()
  cancelarEdicion()
}

const descargarPDF = () => {
  const doc = new jsPDF()

  doc.setFontSize(16)
  doc.text('Listado de Audios', 14, 15)

  autoTable(doc, {
    startY: 20,
    head: [['Marca', 'Modelo', 'Especificaciones', 'Precio', 'Imagen']],
    body: audios.value.map(a => [
      a.marca,
      a.modelo,
      a.especificaciones,
      `$${a.precio.toLocaleString()}`,
      a.imagenURL || 'Sin imagen'
    ]),
    styles: { fontSize: 10 },
    headStyles: { fillColor: [0, 123, 255] },
  })

  doc.save('audios.pdf')
}

const verAudio = (audio) => {
  audioSeleccionado.value = audio
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  audioSeleccionado.value = null
}

onMounted(cargarAudios)
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