package com.lasa.microservicio_crud_productos_01.reactivecontroller;

import com.lasa.microservicio_crud_productos_01.models.Producto;
import com.lasa.microservicio_crud_productos_01.reactiveservice.IReactiveProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.print.attribute.standard.Media;

@RestController
@RequestMapping("/reactive")
public class ReactiveProductController {

    @Autowired
    IReactiveProductService reactiveProductService;

    // ✅ Este sí es un stream continuo, se ajusta para SSE
    @GetMapping(value = "productos", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Producto>> productos() {
        return new ResponseEntity<>(reactiveProductService.catalogo(), HttpStatus.OK);
    }

    // ✅ También aplica para SSE
    @GetMapping(value = "productos_categoria/{categoria}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Producto>> productosCategoria(@PathVariable("categoria") String categoria) {
        return new ResponseEntity<>(reactiveProductService.productosCategoria(categoria), HttpStatus.OK);
    }

    // ❌ Mono: se queda en JSON (no necesita produces)
    @GetMapping(value = "producto_codigo")
    public ResponseEntity<Mono<Producto>> productosCodigo(@RequestParam("codigo") String codigo) {
        return new ResponseEntity<>( reactiveProductService.productoCodigo(codigo), HttpStatus.OK);
    }

    // ✅ POST en modo stream (lo dejaste bien)
    @PostMapping(value = "alta_producto")
    public Mono<ResponseEntity<Producto>> altaProducto(@RequestBody Producto producto) {
        return reactiveProductService.altaProducto(producto)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    // ❌ Mono: JSON
    @PutMapping(value = "actualizar_producto")
    public Mono<ResponseEntity<Producto>> actualizarProducto(@RequestParam("codigo") String codigo, @RequestParam("precio") double precio) {
        return reactiveProductService.actualizarPrecio(codigo, precio).map(p-> new ResponseEntity<>(p, HttpStatus.OK)).
                defaultIfEmpty(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    // ❌ Mono: JSON
    @DeleteMapping(value = "delete_producto")
    public Mono<ResponseEntity<Producto>> eliminarProducto(@RequestParam("codigo") String codigo) {
        return reactiveProductService.eliminarProducto(codigo).map(p-> new ResponseEntity<>(p, HttpStatus.OK))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build()));
    }
}
