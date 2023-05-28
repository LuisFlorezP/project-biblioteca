package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.repositories.AutorRepository;
import com.example.proyectbiblioteca.validations.GenerateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService extends GenerateValidation {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> getAllAutor() {
        return autorRepository.findAll();
    }

    public Optional<Autor> getAutor(Long id) {
        return autorRepository.findById(id);
    }

    public Optional<Autor> saveAutor(Autor autor) {
        if (verificarEmail(autor.getEmail())) {
            return Optional.empty();
        }
        if (autorRepository.findByPseudonimo(autor.getPseudonimo()).isPresent()) {
            return Optional.empty();
        }
        if (verificarNombreApellidoPseudonimo(autor, true)) {
            return Optional.empty();
        }
        return Optional.of(autorRepository.save(autor));
    }


    public Autor updateAutor(Autor autor, Long id) {
        if (verificarEmail(autor.getEmail())) {
            return null;
        }
        Optional<Autor> autorDB = autorRepository.findByPseudonimo(autor.getPseudonimo());
        if (autorDB.isPresent() && verificarMismoPseudonimo(autorDB.get().getPseudonimo(), autor.getPseudonimo())) {
            return null;
        }
        if (verificarNombreApellidoPseudonimo(autor, false)) {
            return null;
        }
        return  autorRepository.findById(id)
                .map(
                        data -> {
                            data.setNombre(autor.getNombre());
                            data.setApellido(autor.getApellido());
                            data.setPseudonimo(autor.getPseudonimo());
                            data.setEmail(autor.getEmail());
                            return autorRepository.save(data);
                        }
                ).orElse(null);
    }

    public boolean deleteAutor(Long id) {
        if (autorRepository.findById(id).equals(Optional.empty())) {
            return false;
        }
        autorRepository.deleteById(id);
        return true;
    }
}
