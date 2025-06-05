package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Televisor;
import com.proyecto.proyectoTec.repository.TelevisorRepository;

@Service
public class TelevisorServices {
    @Autowired
    private TelevisorRepository televisorRepository;

    public List<Televisor> listarTelevisor() {
        return televisorRepository.findAll();
    }

    public void guardarTelevisor(Televisor televisor) {
        televisorRepository.save(televisor);
    }

    public Televisor obtenerTelevisor(Integer id) {
        return televisorRepository.findById(id).get();
    }

    public void eliminarTelevisor(Televisor televisor) {
        televisorRepository.delete(televisor);
    }

    public void eliminarTelevisor(Integer id) {
        televisorRepository.deleteById(id);
    }
}
