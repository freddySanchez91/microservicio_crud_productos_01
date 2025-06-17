package com.lasa.microservicio_crud_productos_01.services;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductService{

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
    public List<Producto> catalogo() {
        return productos;
    }

    @Override
    public List<Producto> productosCategoria(String categoria) {
        return productos.stream().filter(producto -> producto.getCategoria().equals(categoria)).toList();
    }

    @Override
    public Producto productoCodigo(String codigo) {
        return productos.stream().filter(producto -> producto.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    @Override
    public void altaProducto(Producto producto) {
        if(productoCodigo(producto.getCodigo()) == null){
            productos.add(producto);
        }
    }

    @Override
    public Producto eliminarProducto(String codigo) {
        Producto producto =  productoCodigo(codigo);
        if(producto != null){
            productos.removeIf(p -> p.getCodigo().equals(codigo));
        }
        return producto;
    }

    @Override
    public Producto actualizarPrecio(String codigo, double precio) {
        Producto producto =  productoCodigo(codigo);
        if(producto != null){
            producto.setPrecio(precio);
            productos.removeIf(p->p.getCodigo().equals(codigo));
            productos.add(producto);
        }
        return producto;
    }
}
