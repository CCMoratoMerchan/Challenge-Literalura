package com.cc.Challenge.Literalura;

import com.cc.Challenge.Literalura.service.AutorService;
import com.cc.Challenge.Literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@SpringBootApplication
public class CatalogoLibrosApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(LibroService libroService, AutorService autorService) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			while (true) {
				mostrarMenu();
				int opcion = Integer.parseInt(scanner.nextLine());
				switch (opcion) {
					case 1 -> {
						System.out.print("Ingrese el título del libro: ");
						String titulo = scanner.nextLine();
						libroService.buscarLibroPorTitulo(titulo);
					}
					case 2 -> libroService.listarLibros();
					case 3 -> autorService.listarAutores();
					case 4 -> {
						System.out.print("Ingrese el año: ");
						int anio = Integer.parseInt(scanner.nextLine());
						autorService.listarAutoresVivosEnAnio(anio);
					}
					case 5 -> libroService.listarLibrosPorIdioma();
					case 0 -> {
						System.out.println("Saliendo del programa...");
						return;
					}
					default -> System.out.println("Opción no válida. Intente nuevamente.");
				}
			}
		};
	}

	private void mostrarMenu() {
		System.out.println("\n=== Catálogo de Libros ===");
		System.out.println("1. Búsqueda de libro por título");
		System.out.println("2. Listar libros registrados");
		System.out.println("3. Listar autores registrados");
		System.out.println("4. Listar autores vivos en determinado año");
		System.out.println("5. Listar libros por idioma");
		System.out.println("0. Salir");
		System.out.print("Seleccione una opción: ");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}

