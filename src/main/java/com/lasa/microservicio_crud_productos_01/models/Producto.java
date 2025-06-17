package com.lasa.microservicio_crud_productos_01.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {

    private String codigo;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;
}
