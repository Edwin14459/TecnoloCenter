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
import com.proyecto.proyectoTec.model.Audio;
import com.proyecto.proyectoTec.services.AudioServices;


@RestController
@RequestMapping("/audio")
@CrossOrigin(origins = "*")

public class AudioController {

    @Autowired
    private AudioServices audioServices;

    @GetMapping("/traer_audio")
    public List<Audio> listarAudio() {
        return audioServices.listarAudio();
    }

    @SuppressWarnings("rawtypes")
    @PostMapping("/insertar")
    public ResponseEntity guardarAudio(@RequestBody Audio audio) {

        try {
            audioServices.guardarAudio(audio);
            return ResponseEntity.status(HttpStatus.CREATED).body("Audio guardado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear  el Audio " + e.getMessage());

        }
    }

    @PutMapping("editar/{id}")
    public ResponseEntity<?> actualizarAudio(@RequestBody Audio audio, @PathVariable Integer id) {
        try {
            Audio AudioExiste = audioServices.obtenerAudio(id);
            AudioExiste.setMarca(audio.getMarca());
            AudioExiste.setModelo(audio.getModelo());
            AudioExiste.setEspecificaciones(audio.getEspecificaciones());
            AudioExiste.setPrecio(audio.getPrecio());
            AudioExiste.setImagenURL(audio.getImagenURL());

            audioServices.guardarAudio(AudioExiste);
            String mensaje = "El audio con ID " + id + " " + AudioExiste.getMarca()
                    + " ha sido editado correctamente.";
            return ResponseEntity.ok(mensaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el Audio con ID " + id);
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarAudio(@PathVariable Integer id) {
        try {
            audioServices.eliminarAudio(id);
            return ResponseEntity.ok("Audio eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: No se encontró el audio con ID " + id);
        }
    }

}
