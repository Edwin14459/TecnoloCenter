<template>
  <div class="card p-4 shadow-lg rounded-4 mt-5 border-0" style="max-width: 500px; margin: auto;">
    <h4 class="mb-4 text-center text-primary fw-bold">
      {{ esEdicion ? 'Editar Tablet' : 'Registrar Tablet' }}
    </h4>
    <form @submit.prevent="submitForm">
      <div class="form-floating mb-3" v-for="(label, key) in campos" :key="key">
        <input
          v-model="tablet[key]"
          :type="key === 'precio' ? 'text' : 'text'"
          class="form-control"
          :id="key"
          :placeholder="label"
          required
          @input="key === 'precio' ? validarNumero('precio') : null"
        />
        <label :for="key">{{ label }}</label>
        <div v-if="errores[key]" class="text-danger small mt-1">{{ errores[key] }}</div>
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary w-50 me-2" :disabled="!!errores.precio">
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

    <!-- Toast -->
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
  tabletEdit: Object
})
const emit = defineEmits(['form-enviado', 'cancelar-edicion'])

const tablet = reactive({
  marca: '',
  modelo: '',
  tamaño: '',
  almacenamiento: '',
  ram: '',
  especificaciones: '',
  precio: '',
  imagenURL: ''
})

const campos = {
  marca: 'Marca',
  modelo: 'Modelo',
  tamaño: 'Tamaño',
  almacenamiento: 'Almacenamiento',
  ram: 'RAM',
  especificaciones: 'Especificaciones',
  precio: 'Precio',
  imagenURL: 'URL de la Imagen'
}

const errores = reactive({
  precio: ''
})

const esEdicion = computed(() => !!props.tabletEdit)

watch(() => props.tabletEdit, (nuevo) => {
  if (nuevo) Object.assign(tablet, nuevo)
}, { immediate: true })

const validarNumero = (campo) => {
  tablet[campo] = tablet[campo].replace(/[^0-9.]/g, '')
  const partes = tablet[campo].split('.')
  if (partes.length > 2) tablet[campo] = partes[0] + '.' + partes.slice(1).join('')
  errores[campo] = isNaN(Number(tablet[campo])) ? 'Por favor ingrese un número válido' : ''
}

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
    const datos = {
      ...tablet,
      precio: Number(tablet.precio)
    }

    if (esEdicion.value) {
      await axios.put(`/tablet/editar/${props.tabletEdit.id}`, datos)
      showToast('Tablet actualizada correctamente')
    } else {
      await axios.post('/tablet/insertar', datos)
      showToast('Tablet registrada correctamente')
    }

    emit('form-enviado')
    Object.keys(tablet).forEach(k => tablet[k] = '')
  } catch (error) {
    console.error('Error al guardar tablet:', error)
    showToast('Hubo un error al guardar la tablet', 'danger')
  }
}
</script>
