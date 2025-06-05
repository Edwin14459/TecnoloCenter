package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Tablet;
import com.proyecto.proyectoTec.repository.TabletRepository;

@Service
public class TabletServices {
    @Autowired
    private TabletRepository tabletRepository;

    public List<Tablet> listarTablets() {
        return tabletRepository.findAll();
    }

    public void guardarTablet(Tablet tablet) {
        tabletRepository.save(tablet);
    }

    public Tablet obtenerTablet(Integer id) {
        return tabletRepository.findById(id).get();
    }

    public void eliminarTablet(Tablet tablet) {
        tabletRepository.delete(tablet);
    }

    public void eliminarTablet(Integer id) {
        tabletRepository.deleteById(id);
    }
}
