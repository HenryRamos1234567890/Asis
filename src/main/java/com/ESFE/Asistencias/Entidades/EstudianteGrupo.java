package com.ESFE.Asistencias.Entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "estudiantes_grupos")
public class EstudianteGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public void setEstudiante(Estudiante estudiante) {
    }

}

