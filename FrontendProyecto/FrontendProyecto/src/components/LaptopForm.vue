<template>
  <div class="card p-4 shadow-lg rounded-4 mt-5 border-0" style="max-width: 500px; margin: auto;">
    <h4 class="mb-4 text-center text-primary fw-bold">
      {{ esEdicion ? 'Editar Laptop' : 'Registrar Laptop' }}
    </h4>
    <form @submit.prevent="submitForm">
      <!-- Campos existentes... -->
      <!-- Marca -->
      <div class="form-floating mb-3">
        <input v-model="laptop.marca" type="text" class="form-control" id="marca" placeholder="Marca" required />
        <label for="marca">Marca</label>
      </div>

      <!-- Modelo -->
      <div class="form-floating mb-3">
        <input v-model="laptop.modelo" type="text" class="form-control" id="modelo" placeholder="Modelo" required />
        <label for="modelo">Modelo</label>
      </div>

      <!-- Tamaño -->
      <div class="form-floating mb-3">
        <input v-model="laptop.tamaño" type="text" class="form-control" id="tamano" placeholder="Tamaño" required />
        <label for="tamano">Tamaño</label>
      </div>

      <!-- Almacenamiento -->
      <div class="form-floating mb-3">
        <input v-model="laptop.almacenamiento" type="text" class="form-control" id="almacenamiento" placeholder="Almacenamiento" required />
        <label for="almacenamiento">Almacenamiento</label>
      </div>

      <!-- RAM -->
      <div class="form-floating mb-3">
        <input v-model="laptop.ram" type="text" class="form-control" id="ram" placeholder="RAM" required />
        <label for="ram">RAM</label>
      </div>

      <!-- Especificaciones -->
      <div class="form-floating mb-3">
        <input v-model="laptop.especificaciones" type="text" class="form-control" id="especificaciones" placeholder="Especificaciones" required />
        <label for="especificaciones">Especificaciones</label>
      </div>

      <!-- Precio -->
      <div class="form-floating mb-3">
        <input
          v-model="laptop.precio"
          type="text"
          class="form-control"
          id="precio"
          placeholder="Precio"
          required
          @input="validarNumero('precio')"
        />
        <label for="precio">Precio</label>
        <div v-if="errores.precio" class="text-danger small mt-1">{{ errores.precio }}</div>
      </div>

      <!-- Imagen URL -->
      <div class="form-floating mb-4">
        <input
          v-model="laptop.imagenURL"
          type="url"
          class="form-control"
          id="imagenURL"
          placeholder="URL de imagen"
        />
        <label for="imagenURL">URL de la Imagen</label>
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
  laptopEdit: {
    type: Object,
    default: null,
  },
})

const emit = defineEmits(['form-enviado', 'cancelar-edicion'])

const laptop = reactive({
  marca: '',
  modelo: '',
  tamaño: '',
  almacenamiento: '',
  ram: '',
  especificaciones: '',
  precio: '',
  imagenURL: '',
})

const errores = reactive({ precio: '' })

const esEdicion = computed(() => !!props.laptopEdit)

watch(() => props.laptopEdit, (nuevo) => {
  if (nuevo) Object.assign(laptop, nuevo)
}, { immediate: true })

const validarNumero = (campo) => {
  laptop[campo] = laptop[campo].replace(/[^0-9.]/g, '')
  const partes = laptop[campo].split('.')
  if (partes.length > 2) {
    laptop[campo] = partes[0] + '.' + partes.slice(1).join('')
  }

  if (laptop[campo] && isNaN(Number(laptop[campo]))) {
    errores[campo] = 'Por favor ingrese un número válido'
  } else {
    errores[campo] = ''
  }
}

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
      ...laptop,
      precio: Number(laptop.precio)
    }

    if (esEdicion.value) {
      await axios.put(`/laptop/editar/${props.laptopEdit.id}`, datos)
      showToast('Laptop actualizada correctamente')
    } else {
      await axios.post('/laptop/insertar', datos)
      showToast('Laptop registrada correctamente')
    }

    emit('form-enviado')
    Object.keys(laptop).forEach(k => laptop[k] = '')
  } catch (error) {
    console.error('Error al guardar laptop:', error)
    showToast('Hubo un error al guardar la laptop', 'danger')
  }
}
</script>
