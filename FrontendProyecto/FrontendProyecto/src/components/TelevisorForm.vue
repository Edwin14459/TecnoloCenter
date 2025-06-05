<template>
  <div class="card p-4 shadow-lg rounded-4 mt-5 border-0" style="max-width: 500px; margin: auto;">
    <h4 class="mb-4 text-center text-primary fw-bold">
      {{ esEdicion ? 'Editar Televisor' : 'Registrar Televisor' }}
    </h4>
    <form @submit.prevent="submitForm">
      <div class="form-floating mb-3">
        <input v-model="televisor.marca" type="text" class="form-control" id="marca" placeholder="Marca" required />
        <label for="marca">Marca</label>
      </div>

      <div class="form-floating mb-3">
        <input v-model="televisor.modelo" type="text" class="form-control" id="modelo" placeholder="Modelo" required />
        <label for="modelo">Modelo</label>
      </div>

      <div class="form-floating mb-3">
        <input v-model="televisor.tamaño" type="text" class="form-control" id="tamano" placeholder="Tamaño" required />
        <label for="tamano">Tamaño</label>
      </div>

      <div class="form-floating mb-3">
        <input v-model="televisor.especificaciones" type="text" class="form-control" id="especificaciones"
          placeholder="Especificaciones" required />
        <label for="especificaciones">Especificaciones</label>
      </div>

      <div class="form-floating mb-4">
        <input v-model="televisor.precio" type="text" class="form-control" id="precio" placeholder="Precio" required
          @input="validarNumero('precio')" />
        <label for="precio">Precio</label>
        <div v-if="errores.precio" class="text-danger small mt-1">{{ errores.precio }}</div>
      </div>

      <div class="form-floating mb-3">
        <input v-model="televisor.imagenURL" type="text" class="form-control" id="imagenURL"
          placeholder="URL de la imagen" />
        <label for="imagenURL">URL de la Imagen</label>
      </div>

      <div class="d-flex justify-content-between">
        <button type="submit" class="btn btn-primary w-50 me-2" :disabled="!!errores.precio">
          {{ esEdicion ? 'Actualizar' : 'Guardar' }}
        </button>
        <button v-if="esEdicion" @click="$emit('cancelar-edicion')" type="button"
          class="btn btn-outline-secondary w-50 ms-2">
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
  televisorEdit: Object
})
const emit = defineEmits(['form-enviado', 'cancelar-edicion'])

const televisor = reactive({
  marca: '',
  modelo: '',
  tamaño: '',
  especificaciones: '',
  imagenURL: '',
  precio: ''
})

const errores = reactive({
  precio: ''
})

const esEdicion = computed(() => !!props.televisorEdit)

watch(() => props.televisorEdit, (nuevo) => {
  if (nuevo) Object.assign(televisor, nuevo)
}, { immediate: true })

const validarNumero = (campo) => {
  televisor[campo] = televisor[campo].replace(/[^0-9.]/g, '')
  const partes = televisor[campo].split('.')
  if (partes.length > 2) {
    televisor[campo] = partes[0] + '.' + partes.slice(1).join('')
  }
  errores[campo] = televisor[campo] && isNaN(Number(televisor[campo])) ? 'Por favor ingrese un número válido' : ''
}

// Toast control
const toastRef = ref(null)
const toastMessage = ref('')
let toastInstance = null

const showToast = (message) => {
  toastMessage.value = message
  if (toastInstance) toastInstance.dispose()
  toastInstance = new bootstrap.Toast(toastRef.value)
  toastInstance.show()
}
const hideToast = () => toastInstance?.hide()

onMounted(() => {
  toastInstance = new bootstrap.Toast(toastRef.value)
})

const submitForm = async () => {
  try {
    const datos = {
      ...televisor,
      precio: Number(televisor.precio)
    }

    if (esEdicion.value) {
      await axios.put(`/televisores/editar/${props.televisorEdit.id}`, datos)
      showToast('Televisor actualizado correctamente')
    } else {
      await axios.post('/televisores/insertar', datos)
      showToast('Televisor registrado correctamente')
    }

    emit('form-enviado')
    Object.keys(televisor).forEach(k => televisor[k] = '')
  } catch (error) {
    console.error('Error al guardar televisor:', error)
    toastMessage.value = 'Hubo un error al guardar el televisor'
    toastRef.value.classList.remove('text-bg-success')
    toastRef.value.classList.add('text-bg-danger')
    toastInstance.show()
  }
}
</script>
