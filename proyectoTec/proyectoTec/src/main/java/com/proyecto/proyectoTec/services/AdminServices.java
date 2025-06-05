package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Admin;
import com.proyecto.proyectoTec.repository.AdminRepository;

@Service
public class AdminServices {

    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> listarAdmin() {
        return adminRepository.findAll();
    }

    public void guardarAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin obtenerAdmin(Integer id) {
        return adminRepository.findById(id).get();
    }

    public void eliminarAdmin(Admin admin) {
        adminRepository.delete(admin);
    }

    public void eliminarAdmin(Integer id) {
        adminRepository.deleteById(id);
    }

    public Admin buscarPorUsuario(String usuario) {
        return adminRepository.findByUsuario(usuario);
    }
}