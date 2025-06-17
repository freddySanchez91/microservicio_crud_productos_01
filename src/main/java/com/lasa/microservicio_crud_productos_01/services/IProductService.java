package com.lasa.microservicio_crud_productos_01.services;

import com.lasa.microservicio_crud_productos_01.models.Producto;

import java.util.List;

public interface IProductService {

    List<Producto> catalogo();
    List<Producto> productosCategoria(String categoria);
    Producto productoCodigo(String codigo);
    void altaProducto(Producto producto);
    Producto eliminarProducto(String codigo);
    Producto actualizarPrecio(String codigo, double precio);
}
