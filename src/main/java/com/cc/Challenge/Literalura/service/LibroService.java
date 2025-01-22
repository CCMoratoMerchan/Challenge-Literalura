package com.cc.Challenge.Literalura.service;


import com.cc.Challenge.Literalura.model.Libro;
import com.cc.Challenge.Literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public LibroService(LibroRepository libroRepository, RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.libroRepository = libroRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public void buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode results = root.get("results");
            if (results != null && results.isArray() && results.size() > 0) {
                JsonNode libroNode = results.get(0);
                Libro libro = new Libro();
                libro.setTitulo(libroNode.get("title").asText());
                libro.setNumeroDescargas(libroNode.get("download_count").asInt());
                libro.setIdiomas(objectMapper.convertValue(libroNode.get("languages"), List.class));
                libroRepository.save(libro);
                System.out.println("Libro guardado: " + libro);
            } else {
                System.out.println("No se encontraron libros con el título dado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarLibros() {
        List<Libro> libros = libroRepository.findAll();
        libros.forEach(System.out::println);
    }

    public void listarLibrosPorIdioma() {
        System.out.println("Idiomas disponibles:");
        List<Libro> libros = libroRepository.findAll();
        libros.stream().flatMap(libro -> libro.getIdiomas().stream()).distinct().forEach(System.out::println);
    }
}

