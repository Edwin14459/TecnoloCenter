package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Laptop;
import com.proyecto.proyectoTec.repository.LaptopRepository;

@Service
public class LaptopServices {
    @Autowired
    private LaptopRepository laptopRepository;

    public List<Laptop> listarLaptops() {
        return laptopRepository.findAll();
    }

    public void guardarLaptop(Laptop laptop) {
        laptopRepository.save(laptop);
    }

    public Laptop obtenerLaptop(Integer id) {
        return laptopRepository.findById(id).get();
    }

    public void eliminarLaptop(Laptop laptop) {
        laptopRepository.delete(laptop);
    }

    public void eliminarLaptop(Integer id) {
        laptopRepository.deleteById(id);
    }
}
