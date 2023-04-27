package com.antonativa.antonativa.controllers;

import com.antonativa.antonativa.models.Producto;
import com.antonativa.antonativa.models.Settings;
import com.antonativa.antonativa.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

public class ProductoController {

    @Autowired
    ProductoRepository productoRepository;

    @GetMapping("/listarproductos")
    public Collection<Producto> listProductos() {return (Collection<Producto>) productoRepository.findAll();}

    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {

        productoRepository.deleteById(id);

        return "Se ha eliminado exitosamente el producto";

    }

}
