package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Carrito;

public interface CarritoRepository extends JpaRepository<Carrito, Long> {
}
