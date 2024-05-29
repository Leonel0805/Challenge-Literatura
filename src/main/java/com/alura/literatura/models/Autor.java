package com.alura.literatura.models;

import com.alura.literatura.records.DatosAuthor;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "autor")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> lista_libros;

//    Constructor predeterminado
    public Autor() {
    }

//    Constructor para DatosAutor
    public Autor(DatosAuthor datosautor) {
        this.lista_libros = new ArrayList<>();
        this.nombre = datosautor.name();
        try {
            this.fechaNacimiento = Integer.valueOf(datosautor.birth());
        } catch (NumberFormatException e) {
            this.fechaNacimiento = null;
        }
        try {
            this.fechaFallecimiento = Integer.valueOf(datosautor.death());
        } catch (NumberFormatException e) {
            this.fechaFallecimiento = null;
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }
    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLista_libros() {
        return lista_libros;
    }
    public void setLista_libros(List<Libro> lista_libros) {
        this.lista_libros = lista_libros;
    }

    //    toString
    @Override
    public String toString() {
        return "-----------Autor------- " + "\n" +
                "Nombre: " + nombre + "\n" +
                "FechaNacimiento: " + fechaNacimiento + "\n" +
                "FechaFallecimiento: " + fechaFallecimiento + "\n" +
                "Libros: " + this.lista_libros.stream()
                .map(libro -> libro.getTitulo())
                .collect(Collectors.joining("|")) + "\n"
                ;
    }


}


