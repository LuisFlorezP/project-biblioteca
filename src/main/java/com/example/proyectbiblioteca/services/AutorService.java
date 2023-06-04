package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.Author;
import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.mappers.AuthorMapper;
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
    @Autowired
    private AuthorMapper authorMapper;

    public List<Author> getAllAutor() {
        return authorMapper.toAuthors(autorRepository.findAll());
    }

    public Author getAutor(Long id) {
        return autorRepository.findById(id)
                .map(autor -> authorMapper.toAuthor(autor))
                .orElse(null);
    }

    public Author saveAutor(Autor autor) {
        Optional<Autor> autorPseudonimo = autorRepository.findByPseudonimo(autor.getPseudonimo());
        if (autorPseudonimo.isPresent() || verificarEmail(autor.getEmail()) || verificarNombreApellidoPseudonimo(autor) || verificarNacionalidad(autor.getNacionalidad())) {
            return null;
        }
        return authorMapper.toAuthor(autorRepository.save(autor));
    }

    public Author updateAutor(Autor autor, Long id) {
        return  autorRepository.findById(id)
                .map(
                        data -> {
                            Optional<Autor> search = autorRepository.findByPseudonimo(autor.getPseudonimo());
                            if (search.isPresent() && !search.get().getPseudonimo().equals(autor.getPseudonimo()) || verificarEmail(autor.getEmail()) || verificarNombreApellidoPseudonimo(autor) || verificarNacionalidad(autor.getNacionalidad())) {
                                return null;
                            }
                            data.setNombre(autor.getNombre());
                            data.setApellido(autor.getApellido());
                            data.setPseudonimo(autor.getPseudonimo());
                            data.setNacionalidad(autor.getNacionalidad());
                            data.setEmail(autor.getEmail());
                            return authorMapper.toAuthor(autorRepository.save(data));
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
