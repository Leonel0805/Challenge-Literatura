package com.alura.literatura.records;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(

        @JsonAlias("title") String title,

        @JsonAlias("authors") List<DatosAuthor> Authors,

        @JsonAlias("languages") List<String> idioma,

        @JsonAlias("download_count") String descargas


) {
}
