package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Carrito;
import com.example.demo.repositories.CarritoRepository;


@Service
public class Carrito_service {
 @Autowired
    private CarritoRepository carritoRepository;  // Repositorio del carrito

    private List<Carrito> carrito = new ArrayList<>();  // Lista para almacenar los servicios del carrito

    // 1. Agregar un servicio al carrito (basado en id_Servicio y cantidad)
    public boolean agregarServicio(Long idServicio, int cantidad) {
        Carrito itemCarrito = new Carrito();
        itemCarrito.setId_Servicio(idServicio);  // Relacionar el servicio
        itemCarrito.setCantidad(cantidad);  // Asignar la cantidad
        itemCarrito.setFechaInicio(new java.sql.Date(System.currentTimeMillis()));  // Fecha actual como inicio

        carritoRepository.save(itemCarrito);  // Guardar en la base de datos
        carrito.add(itemCarrito);  // Agregar a la lista local del carrito
        return true;
    }

    // 2. Calcular el monto total basado en los datos del carrito (sin repositorio de servicios)
    public double calcularMontoTotal() {
        double total = 0;
        for (Carrito item : carrito) {
            // Simulación de obtener el precio de un servicio por su ID, ya que no tienes repositorio de servicio
            double precioServicio = obtenerPrecioServicio(item.getId_Servicio());
            total += precioServicio * item.getCantidad();  // Sumar precio * cantidad
        }
        return total;
    }

    // Simula la obtención del precio de un servicio dado su ID (puedes modificar esto según tu lógica)
    private double obtenerPrecioServicio(Long idServicio) {
        // Supón que este método obtiene el precio con base en el ID del servicio.
        // Aquí puedes implementar una lógica simulada o bien poner precios fijos según IDs.
        if (idServicio == 1) return 100.0;
        if (idServicio == 2) return 150.0;
        if (idServicio == 3) return 200.0;
        return 50.0;  // Precio por defecto para otros servicios
    }

    // 3. Eliminar un servicio del carrito por su ID
    public boolean eliminarServicio(Long idServicio) {
        Optional<Carrito> itemCarrito = carritoRepository.findById(idServicio);
        if (itemCarrito.isPresent()) {
            carritoRepository.delete(itemCarrito.get());  // Eliminar de la base de datos
            carrito.removeIf(item -> item.getId_Servicio() == idServicio);  // Eliminar de la lista local
            return true;
        }
        return false;  // Retorna false si no se encuentra el servicio
    }

    // 4. Pagar el monto total del carrito
    public boolean pagarMontoTotal() {
        double montoTotal = calcularMontoTotal();
        if (montoTotal > 0) {
            // Simular el pago (puedes integrar una lógica de pago real aquí)
            carritoRepository.deleteAll();  // Limpiar carrito en la base de datos después del pago
            carrito.clear();  // Limpiar el carrito en la lista local
            return true;
        }
        return false;  // Retorna false si el carrito está vacío
    }

    // 5. Eliminar todos los servicios del carrito
    public void eliminarTodosLosServicios() {
        carritoRepository.deleteAll();  // Eliminar todos los servicios de la base de datos
        carrito.clear();  // Limpiar la lista local del carrito
    }

    // Obtener todos los servicios en el carrito (opcional)
    public List<Carrito> obtenerServiciosEnCarrito() {
        return carritoRepository.findAll();  // Retorna todos los servicios del carrito desde la base de datos
    }
}
