package edu.com.alumnosapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumno_curso")
public class AlumnoTaller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "at_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "alumno_id")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "taller_id")
    private Taller taller;

    @JoinColumn(name = "at_estado")
    private String estado;

    @JoinColumn(name = "at_fechainscripcion")
    private LocalDate fechaInscripcion;
}