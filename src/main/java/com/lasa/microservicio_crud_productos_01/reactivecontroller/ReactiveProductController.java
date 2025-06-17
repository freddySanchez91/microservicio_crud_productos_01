package com.lasa.microservicio_crud_productos_01.reactivecontroller;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import com.lasa.microservicio_crud_productos_01.reactiveservice.IReactiveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@RestController
public class ReactiveProductController {

    @Autowired
    IReactiveProductService reactiveProductService;

    @GetMapping(value="productos")
    public Flux<Producto> productos(){
        return reactiveProductService.catalogo();
    }

    @GetMapping(value="productos_categoria/{categoria}")
    public Flux<Producto> productosCategoria( @PathVariable("categoria") String categoria){
        return reactiveProductService.productosCategoria(categoria);
    }

    @GetMapping(value="producto_codigo")
    public Mono<Producto> productosCodigo(@RequestParam("codigo") String codigo){
        return reactiveProductService.productoCodigo(codigo);
    }

    @PostMapping(value="alta_producto_r", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Void> altaProducto(@RequestBody Producto producto){
        return reactiveProductService.altaProducto(producto);
    }

    @PutMapping(value="actualizar_producto")
    public Mono<Producto> actualizarProducto(@RequestParam("codigo") String codigo, @RequestParam("precio") double precio){
        return reactiveProductService.actualizarPrecio(codigo, precio );
    }

    @DeleteMapping(value="delete_producto")
    public Mono<Producto> eliminarProducto(@RequestParam("codigo") String codigo){
        return reactiveProductService.eliminarProducto(codigo);
    }
}
