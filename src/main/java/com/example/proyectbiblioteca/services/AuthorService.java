package com.example.proyectbiblioteca.services;

import com.example.proyectbiblioteca.dto.author.RequestAuthorDTO;
import com.example.proyectbiblioteca.dto.author.ResponseAuthorDTO;
import com.example.proyectbiblioteca.mappers.AuthorMapper;
import com.example.proyectbiblioteca.entities.Author;
import com.example.proyectbiblioteca.repositories.AuthorRepository;
import com.example.proyectbiblioteca.validations.AuthorValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService extends AuthorValidations {

    @Autowired
    private AuthorRepository repository;
    @Autowired
    private AuthorMapper mapper;

    public List<ResponseAuthorDTO> getAllAuthors() throws Exception {
        try {
            return mapper.authorsToResponsesAuthorsDtos(repository.findAll());
        } catch (Exception e) {
            throw new Exception("The Author entity records could not be read.");
        }
    }

    public ResponseAuthorDTO getAuthor(Long id) throws Exception {
        try {
            Optional<Author> author = repository.findById(id);
            if (authorPresent(author)) {
                return mapper.authorToResponseAuthorDto(author.get());
            }
            throw new Exception("The Author entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public RequestAuthorDTO saveAuthor(Author author) throws Exception {
        try {
            if (verifyPseudonimo(author.getPseudonimo())) {
                throw new Exception("The author must register pseudonym.");
            }
            Optional<Author> authorPseudonimo = repository.findByPseudonimo(author.getPseudonimo());
            if (authorPresent(authorPseudonimo)) {
                throw new Exception("The author must register unique pseudonym.");
            } else if (verifyEmail(author.getEmail())) {
                throw new Exception("The author must register valid email.");
            } else if (verifyNombreApellidoPseudonimo(author.getNombre(), author.getApellido(), author.getPseudonimo())) {
                throw new Exception("The author must register name and last name, or pseudonym.");
            } else if (verifyNacionalidad(author.getNacionalidad().getId())) {
                throw new Exception("The author must register nationality.");
            }
            return mapper.authorToRequestAuthorDto(repository.save(author));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public RequestAuthorDTO updateAuthor(Author author, Long id) throws Exception {
        try {
            Optional<Author> search = repository.findById(id);
            if (authorPresent(search)) {
                Optional<Author> authorPseudonimo = repository.findByPseudonimo(author.getPseudonimo());
                if (authorPresent(authorPseudonimo) && pseudonimoPresentEqualDifferent(authorPseudonimo.get(), search.get())) {
                    throw new Exception("The author must register unique pseudonym.");
                } else if (verifyEmail(author.getEmail())) {
                    throw new Exception("The author must register valid email.");
                } else if (verifyNombreApellidoPseudonimo(author.getNombre(), author.getApellido(), author.getPseudonimo())) {
                    throw new Exception("The author must register name and last name, or pseudonym.");
                } else if (verifyNacionalidad(author.getNacionalidad().getId())) {
                    throw new Exception("The author must register nationality.");
                }
                Author data = search.get();
                data.setNombre(author.getNombre());
                data.setApellido(author.getApellido());
                data.setPseudonimo(author.getPseudonimo());
                data.setNacionalidad(author.getNacionalidad());
                data.setEmail(author.getEmail());
                return mapper.authorToRequestAuthorDto(repository.save(data));
            }
            throw new Exception("The Author entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void deleteAuthor(Long id) throws Exception {
        try {
            if (authorPresent(repository.findById(id))) {
                repository.deleteById(id);
                return;
            }
            throw new Exception("The Author entity record was not found.");
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
