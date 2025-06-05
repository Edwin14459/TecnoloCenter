package com.proyecto.proyectoTec.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.proyectoTec.model.Audio;
import com.proyecto.proyectoTec.repository.AudioRepository;

@Service
public class AudioServices {
    @Autowired
    private AudioRepository audioRepository;

    public List<Audio> listarAudio() {
        return audioRepository.findAll();
    }

    public void guardarAudio(Audio audio) {
        audioRepository.save(audio);
    }

    public Audio obtenerAudio(Integer id) {
        return audioRepository.findById(id).get();
    }

    public void eliminarAudio(Audio audio) {
        audioRepository.delete(audio);
    }

    public void eliminarAudio(Integer id) {
        audioRepository.deleteById(id);
    }
}
