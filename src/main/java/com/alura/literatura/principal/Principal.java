package com.alura.literatura.principal;

import com.alura.literatura.models.Autor;
import com.alura.literatura.models.Libro;
import com.alura.literatura.records.Datos;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumirAPI;
import com.alura.literatura.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner input = new Scanner(System.in);
    final private String URLBASE = "https://gutendex.com/books/";

    private ConsumirAPI consumoapi = new ConsumirAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

    //    Repositorios
    private LibroRepository librorepository;
    private AutorRepository autorrepository;

    //    Listas
    private List<Libro> lista_libros;
    private List<Autor> lista_autores;

    public Principal(LibroRepository repository, AutorRepository autorrepository) {
        this.librorepository = repository;
        this.autorrepository = autorrepository;
    }

    public void Principal() throws IOException, InterruptedException {

        Integer opc = -1;

        while (opc != 0) {

            mostrar_menu();

            System.out.println("Ingrese un numero:");
            opc = input.nextInt();
            input.nextLine();


            switch (opc) {
                case 1:
                    buscarLibroPorTitulo();
                    break;

                case 2:
                    buscarLibrosRegistrados();
                    break;

                case 3:
                    buscarAutoresRegistrados();
                    break;

                case 4:
                    buscarAutoresVivosPorAnio();
                    break;

                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Nos vemos");
                    break;
                default:
                    System.out.println("No elegiste ninguna de las opciones.\n");
            }
        }
    }


//    Mostrar menú
    public void mostrar_menu() {
        System.out.println("""
                1) Buscar Libro por título
                2) Listar libros registrados
                3) Listar autores registrados
                4) Listar autores vivos en un determinado año
                5) Listar libros por idioma
                0) Salir   
                \n""");
    }



    public void buscarLibroPorTitulo() throws IOException, InterruptedException {

        System.out.println("Ingresa el nombre del libro a buscar: ");
        String libroBusqueda = input.nextLine();

        String json = consumoapi.obtenerDatos(URLBASE + "?search=" + libroBusqueda.replace(" ", "+"));
        System.out.println(URLBASE + "?search=" + libroBusqueda.replace(" ", "+"));
        Datos libro_encontrado = conversor.convertirDatos(json, Datos.class);


//        Convertimos a Libro model
        List<Libro> listaDeLibros = libro_encontrado.results().stream()
                .filter(datolibro -> datolibro.title().toLowerCase().contains(libroBusqueda.toLowerCase()))
                .map(datolibro -> new Libro(datolibro))
                .collect(Collectors.toList());

//        Encontramos y filtramos el primer libro
        Optional<Libro> libroEncontrado = listaDeLibros.stream()
                .findFirst();

        if (libroEncontrado.isPresent()) {
            Libro libro = libroEncontrado.get();

            try {
//            Crear registro base de datos
                librorepository.save(libro);
                System.out.println("Libro guardado.");
                System.out.println(libro);
            } catch (DataIntegrityViolationException e) {
                System.out.println("Libro ya registrado con anterioridad.");
            }

        } else {
            System.out.println("No se encontro ningun libro.");
        }
    }

//    Buscar libros registrados en la database
    public void buscarLibrosRegistrados() {
        lista_libros = librorepository.findAll();

        System.out.println("Libros: ");
        lista_libros.forEach(System.out::println);
    }

//    Buscar autores registrados en la database
    public void buscarAutoresRegistrados() {
        lista_autores = autorrepository.findAll();

        System.out.println("Autores: ");
        lista_autores.forEach(System.out::println);
    }

//    Buscar autores por año
    public void buscarAutoresVivosPorAnio() {
        System.out.println("Ingrese el año a buscar autores: ");
        Integer anio = input.nextInt();
        input.nextLine();

        lista_autores = autorrepository.buscarAutoresVivosPorAnio(anio);
        System.out.println("Autores: ");
        lista_autores.forEach(System.out::println);
    }

//    Listar libros por Idioma
    public void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para listar los libros);
    
                es - Español
                en - Inglés
                pr - Portugués
                """);

        String idioma = input.nextLine();
        List<Libro> lista_libros = librorepository.listarLibrosPorIdioma(idioma);

        lista_libros.forEach(System.out::println);
    }

}
