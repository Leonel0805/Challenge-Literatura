package com.alura.literatura.service;

import com.alura.literatura.models.Autor;
import com.alura.literatura.models.Libro;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibroService {

    @Autowired
    private LibroRepository repository;

    @Autowired
    private AutorRepository autorRepository;
}
