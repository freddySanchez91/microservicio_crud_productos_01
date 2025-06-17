package com.lasa.microservicio_crud_productos_01.reactiveservice;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IReactiveProductService {

    Flux<Producto> catalogo();
    Flux<Producto> productosCategoria(String categoria);
    Mono<Producto> productoCodigo(String codigo);
    Mono<Producto> altaProducto(Producto producto);
    Mono<Producto> eliminarProducto(String codigo);
    Mono<Producto> actualizarPrecio(String codigo, double precio);
}
