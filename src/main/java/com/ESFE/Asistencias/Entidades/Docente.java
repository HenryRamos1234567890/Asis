package com.ESFE.Asistencias.Entidades;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "docentes")
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ingrese el nombre del docente")
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres")
    private String nombre;

    @NotBlank(message = "Ingrese el apellido del docente")
    @Size(max = 50, message = "El apellido no debe exceder los 50 caracteres")
    private String apellido;

    @NotBlank(message = "Ingrese el email del docente")
    @Email(message = "Ingrese un email válido")
    private String email;

    @NotBlank(message = "Ingrese el teléfono del docente")
    @Size(max = 15, message = "El teléfono no debe exceder los 15 caracteres")
    private String telefono;

    @Nullable
    private String escuela;

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Nullable
    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(@Nullable String escuela) {
        this.escuela = escuela;
    }
}
