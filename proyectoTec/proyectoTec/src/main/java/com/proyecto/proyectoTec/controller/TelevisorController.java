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
import com.proyecto.proyectoTec.model.Televisor;
import com.proyecto.proyectoTec.services.TelevisorServices;

@RestController
@RequestMapping("/televisores")
@CrossOrigin(origins = "*")

public class TelevisorController {

    @Autowired
    private TelevisorServices televisorServices;

    @GetMapping("/traer_televisores")
    public List<Televisor> listarTelevisor() {
        return televisorServices.listarTelevisor();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/insertar")
    public ResponseEntity guardarTelevisor(@RequestBody Televisor televisor) {

        try {
            televisorServices.guardarTelevisor(televisor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Televisor guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear  el televisor " + e.getMessage());

        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarTelevisor(@RequestBody Televisor televisor, @PathVariable Integer id) {
        try {
            Televisor televisorExiste = televisorServices.obtenerTelevisor(id);
            televisorExiste.setMarca(televisor.getMarca());
            televisorExiste.setModelo(televisor.getModelo());
            televisorExiste.setTamaño(televisor.getTamaño());
            televisorExiste.setEspecificaciones(televisor.getEspecificaciones());
            televisorExiste.setPrecio(televisor.getPrecio());
            televisorExiste.setImagenURL(televisor.getImagenURL());

            televisorServices.guardarTelevisor(televisorExiste);
            String mensaje = "La televisor con ID " + id + " " + televisorExiste.getMarca()
                    + " ha sido editado correctamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el televisor  con ID " + id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTelevisor(@PathVariable Integer id) {
        try {
            televisorServices.eliminarTelevisor(id);
            return ResponseEntity.ok("Televisor eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el teleevisor con ID " + id);
        }
    }

}

