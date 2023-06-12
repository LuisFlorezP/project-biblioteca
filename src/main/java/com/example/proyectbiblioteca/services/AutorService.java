package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.autor.DataAutorDTO;
import com.example.proyectbiblioteca.dto.autor.ResponseAutorDTO;
import com.example.proyectbiblioteca.entities.Autor;
import com.example.proyectbiblioteca.mappers.AuthorMapper;
import com.example.proyectbiblioteca.repositories.AutorRepository;
import com.example.proyectbiblioteca.validations.AutorValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AutorService extends AutorValidations {

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
            if (autorPresente(autor)) {
                return authorMapper.toAuthor(autor.get());
            }
            throw new Exception("El autor no ha sido encontrado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public DataAutorDTO saveAutor(DataAutorDTO autor) throws Exception {
        try {
            if (verificarPseudonimo(autor.getPseudonimo())) {
                throw new Exception("El autor debe registrar un pseudónimo.");
            }
            Optional<Autor> autorPseudonimo = autorRepository.findByPseudonimo(autor.getPseudonimo());
            if (autorPresente(autorPseudonimo)) {
                throw new Exception("El autor debe registrar un pseudónimo único.");
            } else if (verificarEmail(autor.getEmail())) {
                throw new Exception("El autor debe registrar un correo que sea válido.");
            } else if (verificarNombreApellidoPseudonimo(autor.getNombre(), autor.getApellido(), autor.getPseudonimo())) {
                throw new Exception("El autor debe registrar nombre y apellido, ó pseudónimo.");
            } else if (verificarNacionalidad(autor.getIdNacionalidad())) {
                throw new Exception("El autor debe registrar una nacionalidad.");
            }
            return authorMapper.toDataAuthor(autorRepository.save(authorMapper.toAutorData(autor)));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public DataAutorDTO updateAutor(DataAutorDTO autor, Long id) throws Exception {
        try {
            Optional<Autor> search = autorRepository.findById(id);
            if (autorPresente(search)) {
                Optional<Autor> autorPseudonimo = autorRepository.findByPseudonimo(autor.getPseudonimo());
                if (autorPresente(autorPseudonimo) && pseudonimoPresenteIgualDiferente(autorPseudonimo.get(), search.get())) {
                    throw new Exception("El autor debe registrar un pseudónimo único.");
                } else if (verificarEmail(autor.getEmail())) {
                    throw new Exception("El autor debe registrar un correo que sea válido.");
                } else if (verificarNombreApellidoPseudonimo(autor.getNombre(), autor.getApellido(), autor.getPseudonimo())) {
                    throw new Exception("El autor debe registrar nombre y apellido, ó pseudónimo.");
                } else if (verificarNacionalidad(autor.getIdNacionalidad())) {
                    throw new Exception("El autor debe registrar una nacionalidad.");
                }
                Autor save = authorMapper.toAutorData(autor);
                Autor data = search.get();
                data.setNombre(save.getNombre());
                data.setApellido(save.getApellido());
                data.setPseudonimo(save.getPseudonimo());
                data.setNacionalidad(save.getNacionalidad());
                data.setEmail(save.getEmail());
                return authorMapper.toDataAuthor(autorRepository.save(data));
            }
            throw new Exception("El autor no ha sido encontrado según el id brindado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteAutor(Long id) throws Exception {
        try {
            if (autorPresente(autorRepository.findById(id))) {
                autorRepository.deleteById(id);
                return;
            }
            throw new Exception("El autor no ha sido encontrado, por ende no ha sido eliminado.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
