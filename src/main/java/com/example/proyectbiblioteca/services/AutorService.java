package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.autor.ResponseAutorDTO;
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

    public List<ResponseAutorDTO> getAllAutor() throws Exception {
        try {
            return authorMapper.toAuthors(autorRepository.findAll());
        } catch (Exception e) {
            throw new Exception("No se pudieron encontrar los registros de Autores.");
        }
    }

    public ResponseAutorDTO getAutor(Long id) throws Exception {
        try {
            Optional<Autor> autor = autorRepository.findById(id);
            if (autor.isPresent()) {
                return authorMapper.toAuthor(autor.get());
            }
            throw new Exception("El autor no ha sido encontrado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public ResponseAutorDTO saveAutor(Autor autor) throws Exception {
        try {
            Optional<Autor> autorPseudonimo = autorRepository.findByPseudonimo(autor.getPseudonimo());
            if (autorPseudonimo.isPresent()) {
                throw new Exception("El autor debe registrar un pseudónimo único.");
            } else if (verificarEmail(autor.getEmail())) {
                throw new Exception("El autor debe registrar un correo que sea válido.");
            } else if (verificarNombreApellidoPseudonimo(autor)) {
                throw new Exception("El autor debe registrar nombre y apellido, ó pseudónimo.");
            } else if (verificarNacionalidad(autor.getNacionalidad())) {
                throw new Exception("El autor debe registrar una nacionalidad.");
            }
            return authorMapper.toAuthor(autorRepository.save(autor));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ResponseAutorDTO updateAutor(Autor autor, Long id) throws Exception {
        try {
            Optional<Autor> search = autorRepository.findById(id);
            if (search.isPresent()) {
                Optional<Autor> autorPseudonimo = autorRepository.findByPseudonimo(autor.getPseudonimo());
                if (autorPseudonimo.isPresent() && !search.get().getPseudonimo().equals(autor.getPseudonimo())) {
                    throw new Exception("El autor debe registrar un pseudónimo único.");
                } else if (verificarEmail(autor.getEmail())) {
                    throw new Exception("El autor debe registrar un correo que sea válido.");
                } else if (verificarNombreApellidoPseudonimo(autor)) {
                    throw new Exception("El autor debe registrar nombre y apellido, ó pseudónimo.");
                } else if (verificarNacionalidad(autor.getNacionalidad())) {
                    throw new Exception("El autor debe registrar una nacionalidad.");
                }
                search.map(data -> {
                    data.setNombre(autor.getNombre());
                    data.setApellido(autor.getApellido());
                    data.setPseudonimo(autor.getPseudonimo());
                    data.setNacionalidad(autor.getNacionalidad());
                    data.setEmail(autor.getEmail());
                    return authorMapper.toAuthor(autorRepository.save(data));
                });
            }
            throw new Exception("El autor no ha sido encontrado según el id brindado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteAutor(Long id) throws Exception {
        try {
            if (autorRepository.findById(id).isPresent()) {
                autorRepository.deleteById(id);
                return;
            }
            throw new Exception("El autor no ha sido encontrado, por ende no ha sido eliminado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
