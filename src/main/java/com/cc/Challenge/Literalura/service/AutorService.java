package com.cc.Challenge.Literalura.service;


import com.cc.Challenge.Literalura.model.Autor;
import com.cc.Challenge.Literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    public void listarAutoresVivosEnAnio(int anio) {
        List<Autor> autores = autorRepository.findAll().stream()
                .filter(autor -> autor.getAnioFallecimiento() == null || autor.getAnioFallecimiento() > anio)
                .collect(Collectors.toList());
        autores.forEach(System.out::println);
    }
}
