package com.lasa.microservicio_crud_productos_01.controller;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import com.lasa.microservicio_crud_productos_01.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductoController {

    @Autowired
    IProductService productService;

    @GetMapping(value = "productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Producto> productos(){
        return productService.catalogo();
    }

    @GetMapping(value="producto_categoria/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Producto> productosCategoria(@PathVariable("categoria") String categoria){
        return productService.productosCategoria(categoria);
    }

    @GetMapping(value="producto_codigo" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Producto productoCodigo(@RequestParam("codigo") String codigo){
        return productService.productoCodigo(codigo);
    }

    @PostMapping(value="alta_producto", produces=MediaType.APPLICATION_JSON_VALUE)
    public void altaProducto(@RequestBody Producto producto){
        productService.altaProducto(producto);
    }

    @DeleteMapping(value="eliminar_producto", produces = MediaType.APPLICATION_JSON_VALUE)
    public Producto eliminarProducto(@RequestParam("codigo") String codigo){
        return productService.eliminarProducto(codigo);
    }

    @PutMapping(value="actualizar_producto", produces = MediaType.APPLICATION_JSON_VALUE)
    public Producto actualizarProducto(@RequestParam("codigo") String codigo, @RequestParam("precio") double precio){
        return productService.actualizarPrecio(codigo, precio);
    }
}
