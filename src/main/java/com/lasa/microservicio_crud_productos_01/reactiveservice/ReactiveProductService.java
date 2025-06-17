package com.lasa.microservicio_crud_productos_01.reactiveservice;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReactiveProductService implements IReactiveProductService{

    private static List<Producto> productos = new ArrayList<>(List.of(
            new Producto("MX-1001", "Smartphone Samsung Galaxy A54", "Smartphones", 6499.00, 25),
            new Producto("MX-1002", "Pantalla LG 55'' 4K UHD", "Pantallas", 8999.00, 10),
            new Producto("MX-1003", "Nintendo Switch OLED", "Videojuegos", 7399.00, 15),
            new Producto("MX-1004", "Laptop HP Ryzen 5 16GB RAM", "Computadoras", 13499.00, 7),
            new Producto("MX-1005", "Audífonos Inalámbricos Xiaomi Redmi Buds 4", "Accesorios", 899.00, 40),
            new Producto("MX-1006", "Barra de Sonido Samsung 2.1", "Audio", 2899.00, 12),
            new Producto("MX-1007", "Roku Express HD", "Streaming", 849.00, 30),
            new Producto("MX-1008", "Alexa Echo Dot 5ta Generación", "Asistentes Inteligentes", 1499.00, 20),
            new Producto("MX-1009", "Tableta Lenovo M10 HD 10.1''", "Tabletas", 3799.00, 14),
            new Producto("MX-1010", "Cámara de Seguridad TP-Link Tapo C200", "Seguridad", 699.00, 18)
    ));

    @Override
    public Flux<Producto> catalogo() {
        return Flux.fromIterable(productos);
    }

    @Override
    public Flux<Producto> productosCategoria(String categoria) {
        return Flux.fromIterable(productos).filter(p -> p.getCategoria().equals(categoria));
    }

    @Override
    public Mono<Producto> productoCodigo(String codigo) {
        return catalogo().filter(p -> p.getCodigo().equals(codigo)).next();
    }

    @Override
    public Mono<Producto> altaProducto(Producto producto) {
        boolean existe = productos.stream()
                .anyMatch(p -> p.getCodigo().equals(producto.getCodigo()));
        if (existe) {
            return Mono.empty(); // Ya existe, no agregamos
        } else {
            productos.add(producto);
            return Mono.just(producto);
        }
    }

    @Override
    public Mono<Producto> eliminarProducto(String codigo) {
        return productoCodigo(codigo)
                .flatMap(p -> {
                    productos.removeIf(prod -> prod.getCodigo().equals(codigo));
                    return Mono.just(p);
                });
    }

    @Override
    public Mono<Producto> actualizarPrecio(String codigo, double precio) {
        return productoCodigo(codigo).flatMap(p -> {
            p.setPrecio(precio);
            productos.removeIf(prod -> prod.getCodigo().equals(codigo));
            productos.add(p);
            return Mono.just(p);
        });
    }
}
