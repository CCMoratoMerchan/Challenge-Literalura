package com.cc.Challenge.Literalura.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Libro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String titulo;

        @ElementCollection
        private List<String> idiomas;

        private int numeroDescargas;

        @ManyToOne
        private Autor autor;
}

