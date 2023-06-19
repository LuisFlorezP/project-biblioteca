package com.example.proyectbiblioteca.dto.autor;

public class RequestAuthorDTO extends AuthorDTO {

    private String nombre;
    private String apellido;
    private String pseudonimo;
    private Long idNacionalidad;
    private String email;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPseudonimo() {
        return pseudonimo;
    }

    public void setPseudonimo(String pseudonimo) {
        this.pseudonimo = pseudonimo;
    }

    public Long getIdNacionalidad() {
        return idNacionalidad;
    }

    public void setIdNacionalidad(Long idNacionalidad) {
        this.idNacionalidad = idNacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
