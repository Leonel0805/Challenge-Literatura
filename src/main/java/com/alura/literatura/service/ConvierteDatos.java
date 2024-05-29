package com.alura.literatura.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos {

    private ObjectMapper objectmapper = new ObjectMapper();

    //    creamos el metodo
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return objectmapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
