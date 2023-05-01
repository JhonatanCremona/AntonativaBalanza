package com.antonativa.antonativa.controllers;

import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.EtiquetaDTO;
import com.antonativa.antonativa.models.Producto;
import com.antonativa.antonativa.repository.ProductoRepository;
import com.antonativa.antonativa.services.EtiquetaService;
import com.antonativa.antonativa.settings.impresion.ImpresionInmediata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
@RequestMapping("/etiquetas")
@CrossOrigin
public class EtiquetaController {
    @Autowired
    public EtiquetaService etiquetaService;
    @Autowired
    ProductoRepository productoRepository;

    @PostMapping("/guardar")
    public ResponseEntity<?> guardarEtiqueta(@RequestBody EtiquetaDTO etiquetaDto) {
        etiquetaService.createEtiqueta(etiquetaDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Collection<EtiquetaDTO>> listEtiquetas() {return ResponseEntity.ok(etiquetaService.getAllEtiqueta());}

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> response = null;
        if (etiquetaService.obtenerEtiqueta(id)) {
            etiquetaService.eliminar(id);
            response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Eliminado Plantilla");
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return response;
    }

    @GetMapping("/imprimir/{id}")
    public String imprimir(@PathVariable long id) {

        etiquetaService.setEstadoById(id);
        Etiqueta etiqueta = etiquetaService.findById(id);
        Producto producto;

        if (etiqueta.isEstado()) {

            etiqueta.setPesoNeto(etiquetaService.getPesoNeto());

                if(etiqueta.getPesoNeto() != null) {

                    producto = new Producto(etiqueta.getId(), etiqueta.getProducto(),
                            etiqueta.getLote(), etiqueta.getFechaVencimiento(),
                            etiqueta.getPesoNeto(), LocalDateTime.now(), etiqueta.getOperario());

                    productoRepository.save(producto);

                    //Se realiza la impresion
                    ImpresionInmediata.imprimirEtiqueta(producto);
                }
        }
        return "Impresion realizada";
    }

}
