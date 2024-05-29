package com.alura.literatura.models;

import com.alura.literatura.records.DatosAuthor;
import com.alura.literatura.records.DatosLibro;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "libro")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private List<String> idioma;

    private Integer descargas;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autores;

//    Constructor predeterminado
    public Libro() {
    }

//    Constructor para datosLibro
    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.title();
        this.autores = convertirAutores(datosLibro.Authors());
        this.idioma = datosLibro.idioma();
        this.descargas = Integer.valueOf(datosLibro.descargas());
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }
    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public List<String> getIdioma() {
        return idioma;
    }
    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }
    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }


    //    Convertir en lista de autores
    public List<Autor> convertirAutores(List<DatosAuthor> datos) {

        List<Autor> lista_autores = datos.stream()
                .map(datosautor -> new Autor(datosautor))
                .collect(Collectors.toList());
        return lista_autores;
    }

//    ToString
    @Override
    public String toString() {
        return "------------Libro------------" +"\n" +
                "Titulo: " + this.titulo + "\n"+
                "Autores: " + this.getAutores().stream()
                .map(autor -> autor.getNombre())
                .collect(Collectors.joining(","))
                + "\n" +
                "Idiomas: " + this.idioma.stream()
                .collect(Collectors.joining(","))+ "\n" +
                "Número de descargas: " + this.descargas + "\n" + "\n";
    }
}
