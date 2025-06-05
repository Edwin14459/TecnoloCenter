package com.proyecto.proyectoTec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.proyectoTec.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsuario(String usuario);
}