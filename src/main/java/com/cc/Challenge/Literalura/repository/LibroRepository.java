package com.cc.Challenge.Literalura.repository;

import com.cc.Challenge.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByIdiomasContains(String idioma);
}
