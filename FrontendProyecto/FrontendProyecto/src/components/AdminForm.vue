<template>
  <div class="card p-4 shadow-lg rounded-4 mt-5 border-0" style="max-width: 500px; margin: auto;">
    <h4 class="mb-4 text-center text-primary fw-bold">
      {{ esEdicion ? 'Editar Administrador' : 'Registrar Administrador' }}
    </h4>
    <form @submit.prevent="submitForm">
      <div class="form-floating mb-3">
        <input
          v-model="admin.usuario"
          type="text"
          class="form-control"
          id="usuario"
          placeholder="Usuario"
          required
        />
        <label for="usuario">Usuario</label>
      </div>

      <div class="form-floating mb-3 position-relative">
        <input
          :type="mostrarContrasena ? 'text' : 'password'"
          v-model="admin.contraseña"
          class="form-control"
          id="contrasena"
          placeholder="Contraseña"
          required
        />
        <label for="contrasena">Contraseña</label>
        <button
          type="button"
          class="btn btn-sm position-absolute top-50 end-0 translate-middle-y me-3"
          @click="mostrarContrasena = !mostrarContrasena"
          style="z-index: 2; background: none; border: none;"
        >
          <i :class="mostrarContrasena ? 'bi bi-eye-slash-fill' : 'bi bi-eye-fill'"></i>
        </button>
      </div>

      <div class="form-floating mb-3">
        <input
          v-model="admin.imagenURL"
          type="url"
          class="form-control"
          id="imagenURL"
          placeholder="URL de la imagen"
        />
        <label for="imagenURL">URL de la Imagen</label>
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary w-50 me-2">
          {{ esEdicion ? 'Actualizar' : 'Guardar' }}
        </button>
        <button
          v-if="esEdicion"
          @click="$emit('cancelar-edicion')"
          type="button"
          class="btn btn-outline-secondary w-50 ms-2"
        >
          Cancelar
        </button>
      </div>
    </form>

    <!-- Toast Bootstrap -->
    <div class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
      <div class="toast align-items-center text-bg-success border-0" ref="toastRef" role="alert">
        <div class="d-flex">
          <div class="toast-body">{{ toastMessage }}</div>
          <button type="button" class="btn-close btn-close-white me-2 m-auto" @click="hideToast"></button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, watch, computed, onMounted } from 'vue'
import axios from 'axios'

const props = defineProps({
  adminEdit: Object
})
const emit = defineEmits(['form-enviado', 'cancelar-edicion'])

const admin = reactive({
  usuario: '',
  contraseña: '',
  imagenURL: ''
})

const mostrarContrasena = ref(false)
const esEdicion = computed(() => !!props.adminEdit)

watch(() => props.adminEdit, (nuevo) => {
  if (nuevo) Object.assign(admin, nuevo)
}, { immediate: true })

// Toast
const toastRef = ref(null)
const toastMessage = ref('')
let toastInstance = null

const showToast = (message, tipo = 'success') => {
  toastMessage.value = message
  toastRef.value.className = `toast align-items-center text-bg-${tipo} border-0`
  if (toastInstance) toastInstance.dispose()
  toastInstance = new bootstrap.Toast(toastRef.value)
  toastInstance.show()
}

const hideToast = () => {
  if (toastInstance) toastInstance.hide()
}

onMounted(() => {
  toastInstance = new bootstrap.Toast(toastRef.value)
})

const submitForm = async () => {
  try {
    if (esEdicion.value) {
      await axios.put(`/admin/editar/${props.adminEdit.id}`, admin)
      showToast('Administrador actualizado correctamente')
    } else {
      await axios.post('/admin/insertar', admin)
      showToast('Administrador registrado correctamente')
    }

    emit('form-enviado')
    Object.keys(admin).forEach(k => admin[k] = '')
    mostrarContrasena.value = false
  } catch (error) {
    console.error('Error al guardar administrador:', error)
    showToast('Hubo un error al guardar el administrador', 'danger')
  }
}
</script>