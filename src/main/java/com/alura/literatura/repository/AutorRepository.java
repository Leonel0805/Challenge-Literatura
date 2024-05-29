package com.alura.literatura.repository;

import com.alura.literatura.models.Autor;
import com.alura.literatura.models.Libro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query(value = "SELECT * FROM autor WHERE fecha_nacimiento <= :year AND (fecha_fallecimiento IS NULL OR fecha_fallecimiento > :year)", nativeQuery = true)
    List<Autor> buscarAutoresVivosPorAnio(Integer year);
}
