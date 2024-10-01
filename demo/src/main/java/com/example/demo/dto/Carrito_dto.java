package com.example.demo.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Carrito_dto {
    private long id_Servicio;
    private long id_Carrito;
    private int cantidad;
    private Date FechaInicio;
    private Date FechaFinal;
}
