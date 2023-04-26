package com.antonativa.antonativa.controllers;


import com.antonativa.antonativa.models.Etiqueta;
import com.antonativa.antonativa.models.EtiquetaDTO;
import com.antonativa.antonativa.models.Producto;
import com.antonativa.antonativa.repository.EtiquetaRepository;
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
@RequestMapping("/api")
@CrossOrigin
public class EtiquetaController {

    @Autowired
    public EtiquetaService etiquetaService;
    @Autowired
    EtiquetaRepository etiquetaRepository;

    @Autowired
    ProductoRepository productoRepository;

    @PostMapping("/guardar")
    /*public Etiqueta saveEtiqueta(@RequestBody Etiqueta etiqueta) {
        return etiquetaService.save(etiqueta);
    }*/
    public ResponseEntity<?> guardarEtiqueta(@RequestBody EtiquetaDTO etiquetaDto) {
        etiquetaService.createEtiqueta(etiquetaDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/listar")
    public Collection<EtiquetaDTO> listEtiquetas() {return etiquetaService.getAllEtiqueta();}

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {

        etiquetaService.eliminar(id);

        return "Se ha eliminado exitosamente la etiqueta";

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
