package com.alura.literatura.repository;


import com.alura.literatura.DTO.AutorDTO;
import com.alura.literatura.models.Autor;
import com.alura.literatura.models.Libro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query(value = "SELECT * FROM libro WHERE :language = ANY(idioma)", nativeQuery = true)
    List<Libro> listarLibrosPorIdioma(String language);
}
