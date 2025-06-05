<template>
  <div class="container mt-4">
    <h4 class="mb-3 text-primary">Gestión de Administradores</h4>

    <AdminForm
      :adminEdit="adminEditado"
      @form-enviado="refrescar"
      @cancelar-edicion="cancelarEdicion"
    />

    <hr class="my-4" />
    <h5 class="mb-3 text-danger text-center">Lista de Administradores</h5>

    <!-- Botón para descargar TXT -->
    <div class="d-flex justify-content-end mb-3">
      <button class="btn btn-outline-secondary" @click="descargarTXT">
        <i class="bi bi-download me-2"></i> Descargar Registros
      </button>
    </div>

    <div class="table-responsive">
      <table class="table table-hover table-bordered shadow-sm">
        <thead class="table-info">
          <tr>
            <th class="text-center align-middle">Imagen</th>
            <th class="text-center align-middle">Usuario</th>
            <th class="text-center align-middle">Contraseña</th>
            <th class="text-center align-middle">Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="admin in admins" :key="admin.id" class="align-middle">
            <td class="text-center">
              <img
                v-if="admin.imagenURL"
                :src="admin.imagenURL"
                :alt="admin.usuario"
                class="img-thumbnail"
                width="80"
                height="60"
              />
              <span v-else class="text-muted">Sin imagen</span>
            </td>
            <td class="text-center">{{ admin.usuario }}</td>
            <td class="text-center">{{ admin.contraseña }}</td>
            <td class="text-center">
              <div class="d-flex justify-content-center">
                <button
                  class="btn btn-info btn-sm me-2"
                  @click="verAdmin(admin)"
                  title="Ver"
                >
                  <i class="bi bi-eye"></i>
                </button>
                <button
                  class="btn btn-warning btn-sm me-2"
                  @click="editarAdmin(admin)"
                  title="Editar"
                >
                  <i class="bi bi-pencil-square"></i>
                </button>
                <button
                  class="btn btn-danger btn-sm"
                  @click="eliminarAdmin(admin.id)"
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

    <!-- Modal de Vista de Administrador -->
    <div
      class="modal fade show"
      tabindex="-1"
      style="display: block; background-color: rgba(0, 0, 0, 0.5)"
      v-if="mostrarModal"
    >
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Detalles del Administrador</h5>
            <button type="button" class="btn-close" @click="cerrarModal"></button>
          </div>
          <div class="modal-body">
            <div class="text-center mb-3">
              <img
                v-if="adminSeleccionado?.imagenURL"
                :src="adminSeleccionado.imagenURL"
                :alt="adminSeleccionado.usuario"
                class="img-fluid rounded"
                style="max-height: 150px;"
              />
              <p v-else class="text-muted">Sin imagen disponible</p>
            </div>
            <p><strong>Usuario:</strong> {{ adminSeleccionado?.usuario }}</p>
            <p><strong>Contraseña:</strong> {{ adminSeleccionado?.contraseña }}</p>
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
import AdminForm from './AdminForm.vue'

const admins = ref([])
const adminEditado = ref(null)
const adminSeleccionado = ref(null)
const mostrarModal = ref(false)

const cargarAdmins = async () => {
  try {
    const res = await axios.get('/admin/traer_admin')
    admins.value = res.data
  } catch (error) {
    console.error('Error al cargar administradores:', error)
  }
}

const editarAdmin = (admin) => {
  adminEditado.value = { ...admin }
}

const eliminarAdmin = async (id) => {
  if (confirm('¿Estás seguro de eliminar este administrador?')) {
    try {
      await axios.delete(`/admin/eliminar/${id}`)
      cargarAdmins()
    } catch (error) {
      console.error('Error al eliminar administrador:', error)
    }
  }
}

const cancelarEdicion = () => {
  adminEditado.value = null
}

const refrescar = () => {
  cargarAdmins()
  cancelarEdicion()
}

const descargarTXT = () => {
  const contenido = admins.value.map(admin => 
    `Usuario: ${admin.usuario}\nContraseña: ${admin.contraseña}\nImagen: ${admin.imagenURL || 'No tiene'}\n---`
  ).join('\n\n')

  const blob = new Blob([contenido], { type: 'text/plain' })
  const url = URL.createObjectURL(blob)
  const link = document.createElement('a')
  link.href = url
  link.download = 'administradores.txt'
  document.body.appendChild(link)
  link.click()
  document.body.removeChild(link)
  URL.revokeObjectURL(url)
}

const verAdmin = (admin) => {
  adminSeleccionado.value = admin
  mostrarModal.value = true
}

const cerrarModal = () => {
  mostrarModal.value = false
  adminSeleccionado.value = null
}

onMounted(cargarAdmins)
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