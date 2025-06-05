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
import com.proyecto.proyectoTec.model.Tablet;
import com.proyecto.proyectoTec.services.TabletServices;

@RestController
@RequestMapping("/tablet")
@CrossOrigin(origins = "*")

public class TabletController {

    @Autowired
    private TabletServices tabletServices;

    @GetMapping("/traer_tablet")
    public List<Tablet> listarTablets() {
        return tabletServices.listarTablets();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/insertar")
    public ResponseEntity guardarTablet(@RequestBody Tablet tablet) {

        try {
            tabletServices.guardarTablet(tablet);
            return ResponseEntity.status(HttpStatus.CREATED).body("Tablet guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear  la tableta " + e.getMessage());

        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarTablet(@RequestBody Tablet tablet, @PathVariable Integer id) {
        try {
            Tablet tabletExiste = tabletServices.obtenerTablet(id);
            tabletExiste.setMarca(tablet.getMarca());
            tabletExiste.setModelo(tablet.getModelo());
            tabletExiste.setTamaño(tablet.getTamaño());
            tabletExiste.setRam(tablet.getRam());
            tabletExiste.setAlmacenamiento(tablet.getAlmacenamiento());
            tabletExiste.setEspecificaciones(tablet.getEspecificaciones());
            tabletExiste.setPrecio(tablet.getPrecio());
            tabletExiste.setImagenURL(tablet.getImagenURL());

            tabletServices.guardarTablet(tabletExiste);
            String mensaje = "La Tableta con ID " + id + " " + tabletExiste.getMarca()
                    + " ha sido editado correctamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró la Tableta con ID " + id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTablet(@PathVariable Integer id) {
        try {
            tabletServices.eliminarTablet(id);
            return ResponseEntity.ok("Servicio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró la tableta con ID " + id);
        }
    }

}

