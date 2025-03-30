package edu.com.alumnosapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumnos_cursos",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"alumno_id", "curso_id"})})
public class AlumnoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumno_curso_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "alumno_id",nullable = false)
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "curso_id",nullable = false)
    private Curso curso;

    @Column(name = "estado")
    private String estado;

}