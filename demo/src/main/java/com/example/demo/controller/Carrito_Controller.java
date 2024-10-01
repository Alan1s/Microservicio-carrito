package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Carrito;
import com.example.demo.service.Carrito_service;


import java.util.List;

@RestController
@RequestMapping("/api/v1/carrito")

public class Carrito_Controller {
 @Autowired
    private Carrito_service carritoService;

    // 1. Agregar un servicio al carrito
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarServicio(@RequestParam Long idServicio, @RequestParam int cantidad) {
        boolean agregado = carritoService.agregarServicio(idServicio, cantidad);
        if (agregado) {
            return new ResponseEntity<>("Servicio agregado correctamente al carrito", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error al agregar el servicio", HttpStatus.BAD_REQUEST);
    }

    // 2. Calcular el monto total del carrito
    @GetMapping("/calcular-monto")
    public ResponseEntity<Double> calcularMontoTotal() {
        double total = carritoService.calcularMontoTotal();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // 3. Eliminar un servicio del carrito por su ID
    @DeleteMapping("/eliminar/{idServicio}")
    public ResponseEntity<String> eliminarServicio(@PathVariable Long idServicio) {
        boolean eliminado = carritoService.eliminarServicio(idServicio);
        if (eliminado) {
            return new ResponseEntity<>("Servicio eliminado del carrito", HttpStatus.OK);
        }
        return new ResponseEntity<>("Servicio no encontrado en el carrito", HttpStatus.NOT_FOUND);
    }

    // 4. Pagar el monto total del carrito
    @PostMapping("/pagar")
    public ResponseEntity<String> pagarMontoTotal() {
        boolean pagado = carritoService.pagarMontoTotal();
        if (pagado) {
            return new ResponseEntity<>("Pago realizado con éxito. El carrito ha sido vaciado.", HttpStatus.OK);
        }
        return new ResponseEntity<>("Error al pagar, el carrito está vacío", HttpStatus.BAD_REQUEST);
    }

    // 5. Eliminar todos los servicios del carrito
    @DeleteMapping("/eliminar-todos")
    public ResponseEntity<String> eliminarTodosLosServicios() {
        carritoService.eliminarTodosLosServicios();
        return new ResponseEntity<>("Todos los servicios han sido eliminados del carrito", HttpStatus.OK);
    }

    // 6. Obtener todos los servicios en el carrito (opcional para verificar contenido)
    @GetMapping("/listar")
    public ResponseEntity<List<Carrito>> obtenerServiciosEnCarrito() {
        List<Carrito> servicios = carritoService.obtenerServiciosEnCarrito();
        return new ResponseEntity<>(servicios, HttpStatus.OK);
    }
}
