package com.proyecto.proyectoTec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.proyectoTec.model.Admin;
import com.proyecto.proyectoTec.services.AdminServices;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @GetMapping("/traer_admin")
    public List<Admin> listarAdmin() {
        return adminServices.listarAdmin();
    }

    @PostMapping("/insertar")
    public ResponseEntity<?> guardarAdmin(@RequestBody Admin admin) {
        try {
            adminServices.guardarAdmin(admin);
            return ResponseEntity.status(HttpStatus.CREATED).body("Administrador guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear al administrador " + e.getMessage());
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> actualizarAdmin(@RequestBody Admin admin, @PathVariable Integer id) {
        try {
            Admin adminExiste = adminServices.obtenerAdmin(id);
            adminExiste.setUsuario(admin.getUsuario());
            adminExiste.setContraseña(admin.getContraseña());
            adminExiste.setImagenURL(admin.getImagenURL());
            adminServices.guardarAdmin(adminExiste);
            
            return ResponseEntity.ok("Administrador actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al editar: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAdmin(@PathVariable Integer id) {
        try {
            adminServices.eliminarAdmin(id);
            return ResponseEntity.ok("Administrador eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error al eliminar: " + e.getMessage());
        }
    }

    // LOGIN DE ADMINISTRADOR
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin) {
        Admin encontrado = adminServices.buscarPorUsuario(admin.getUsuario());

        if (encontrado != null && encontrado.getContraseña().equals(admin.getContraseña())) {
            return ResponseEntity.ok(encontrado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
    }
}