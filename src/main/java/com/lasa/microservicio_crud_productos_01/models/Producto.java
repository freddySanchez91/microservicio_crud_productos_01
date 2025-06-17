package com.lasa.microservicio_crud_productos_01.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {

    private String codProducto;
    private String nombre;
    private String catgoria;
    private double precio;
    private int stock;
}
