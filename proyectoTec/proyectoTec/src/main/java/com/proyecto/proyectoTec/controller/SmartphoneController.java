package com.proyecto.proyectoTec.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import com.proyecto.proyectoTec.model.Smartphone;
import com.proyecto.proyectoTec.services.SmartphoneServices;

@RestController
@RequestMapping("/smartphone")
@CrossOrigin(origins = "*")

public class SmartphoneController {

    @Autowired
    private SmartphoneServices smartphoneServices;

    @GetMapping("/traer_smartphone")
    public List<Smartphone> listarSmartphone() {
        return smartphoneServices.listarSmartphone();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/insertar")
    public ResponseEntity guardarSmartphone(@RequestBody Smartphone smartphone) {

        try {
            smartphoneServices.guardarSmartphone(smartphone);
            return ResponseEntity.status(HttpStatus.CREATED).body("Smartphone guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear  el Smartphone " + e.getMessage());

        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarSmartphone(@RequestBody Smartphone smartphone, @PathVariable Integer id) {
        try {
            Smartphone smartphoneExiste = smartphoneServices.obtenerSmartphone(id);
            smartphoneExiste.setMarca(smartphone.getMarca());
            smartphoneExiste.setModelo(smartphone.getModelo());
            smartphoneExiste.setRam(smartphone.getRam());
            smartphoneExiste.setAlmacenamiento(smartphone.getAlmacenamiento());
            smartphoneExiste.setEspecificaciones(smartphone.getEspecificaciones());
            smartphoneExiste.setPrecio(smartphone.getPrecio());
            smartphoneExiste.setImagenURL(smartphone.getImagenURL());

            smartphoneServices.guardarSmartphone(smartphoneExiste);
            String mensaje = "El servicio con ID " + id + " " + smartphoneExiste.getMarca()
                    + " ha sido editado correctamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el Smartphone con ID " + id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarSmartphone(@PathVariable Integer id) {
        try {
            smartphoneServices.eliminarSmartphone(id);
            return ResponseEntity.ok("Servicio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el servicio con ID " + id);
        }
    }

}
