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
import com.proyecto.proyectoTec.model.Laptop;
import com.proyecto.proyectoTec.services.LaptopServices;

@RestController
@RequestMapping("/laptop")
@CrossOrigin(origins = "*")

public class LaptopController {

    @Autowired
    private LaptopServices laptopServices;

    @GetMapping("/traer_laptop")
    public List<Laptop> listarLaptops() {
        return laptopServices.listarLaptops();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/insertar")
    public ResponseEntity guardarLaptop(@RequestBody Laptop laptop) {

        try {
            laptopServices.guardarLaptop(laptop);
            return ResponseEntity.status(HttpStatus.CREATED).body("Laptop guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear  la laptop " + e.getMessage());

        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarLaptop(@RequestBody Laptop laptop, @PathVariable Integer id) {
        try {
            Laptop laptopExiste = laptopServices.obtenerLaptop(id);
            laptopExiste.setMarca(laptop.getMarca());
            laptopExiste.setModelo(laptop.getModelo());
            laptopExiste.setTamaño(laptop.getTamaño());
            laptopExiste.setAlmacenamiento(laptop.getAlmacenamiento());
            laptopExiste.setRam(laptop.getRam());
            laptopExiste.setEspecificaciones(laptop.getEspecificaciones());
            laptopExiste.setPrecio(laptop.getPrecio());
            laptopExiste.setImagenURL(laptop.getImagenURL());

            laptopServices.guardarLaptop(laptopExiste);
            String mensaje = "La laptop con ID " + id + " " + laptopExiste.getMarca()
                    + " ha sido editado correctamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró la laptop con ID " + id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarLaptop(@PathVariable Integer id) {
        try {
            laptopServices.eliminarLaptop(id);
            return ResponseEntity.ok("Servicio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró la laptop con ID " + id);
        }
    }

}
