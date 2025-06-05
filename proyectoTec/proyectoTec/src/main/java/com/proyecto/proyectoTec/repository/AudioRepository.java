package com.proyecto.proyectoTec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.proyecto.proyectoTec.model.Audio;

public interface AudioRepository extends JpaRepository<Audio, Integer> {

}