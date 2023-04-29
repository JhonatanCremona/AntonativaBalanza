package com.antonativa.antonativa.controllers;

import com.antonativa.antonativa.models.Producto;
import com.antonativa.antonativa.models.Settings;
import com.antonativa.antonativa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/productos")
@CrossOrigin
public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/listar")
    public Collection<Producto> listProductos() {return (Collection<Producto>) productoRepository.findAll();}

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {

        productoRepository.deleteById(id);

        return "Se ha eliminado exitosamente el producto";

    }

    @GetMapping("/listarporfecha/{fecha1}/{fecha2}")
    public Collection<Producto> listProductosByFechas(@PathVariable LocalDateTime fecha1, @PathVariable LocalDateTime fecha2) {

        return productoRepository.listProductosByFecha(fecha1, fecha2);

    }

}
