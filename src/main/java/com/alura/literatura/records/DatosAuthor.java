package com.alura.literatura.records;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAuthor(

        @JsonAlias("name") String name,
        @JsonAlias("birth_year") String birth,
        @JsonAlias("death_year") String death

) {
}
