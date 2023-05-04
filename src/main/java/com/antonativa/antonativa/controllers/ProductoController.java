package com.antonativa.antonativa.controllers;

import com.antonativa.antonativa.models.Producto;
import com.antonativa.antonativa.models.Settings;
import com.antonativa.antonativa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {
    @Autowired
    ProductoRepository productoRepository;

    @GetMapping()
    public ResponseEntity<Collection<Producto>> listaProductos() {return ResponseEntity.ok((Collection<Producto>) productoRepository.findAll());}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        if (productoRepository.findById(id).isPresent()) {
            productoRepository.deleteById(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/listarporfecha/{fecha1}/{fecha2}")
    public Collection<Producto> listProductosByFechas(@PathVariable LocalDateTime fecha1, @PathVariable LocalDateTime fecha2) {
        return productoRepository.listProductosByFecha(fecha1, fecha2);
    }

}
