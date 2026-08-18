package com.tienda.demo.dto.terceros;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Viajes {
    private Integer id;
    private LocalDateTime fechaSalida;
    private String direccion;
    private Double precio;
}
