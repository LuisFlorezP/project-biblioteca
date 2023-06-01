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
        if (autorRepository.findByPseudonimo(autor.getPseudonimo()).isPresent() || verificarEmail(autor.getEmail()) || verificarNombreApellidoPseudonimo(autor) || verificarNacionalidad(autor.getNacionalidad())) {
            return Optional.empty();
        }
        return Optional.of(autorRepository.save(autor));
    }

    public Autor updateAutor(Autor autor, Long id) {
        Optional<Autor> autorDB = autorRepository.findByPseudonimo(autor.getPseudonimo());
        if (autorDB.isPresent() || verificarEmail(autor.getEmail()) || autorDB.get().getPseudonimo().equals(autor.getPseudonimo()) || verificarNombreApellidoPseudonimo(autor)) {
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
        if (autorRepository.findById(id).isPresent()) {
            autorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
