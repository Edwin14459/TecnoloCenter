package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Smartphone;
import com.proyecto.proyectoTec.repository.SmartphoneRepository;

@Service
public class SmartphoneServices {
    @Autowired
    private SmartphoneRepository smartphoneRepository;

    public List<Smartphone> listarSmartphone() {
        return smartphoneRepository.findAll();
    }

    public void guardarSmartphone(Smartphone smartphone) {
        smartphoneRepository.save(smartphone);
    }

    public Smartphone obtenerSmartphone(Integer id) {
        return smartphoneRepository.findById(id).get();
    }

    public void eliminarSmartphone(Smartphone smartphone) {
        smartphoneRepository.delete(smartphone);
    }

    public void eliminarSmartphone(Integer id) {
        smartphoneRepository.deleteById(id);
    }
}
