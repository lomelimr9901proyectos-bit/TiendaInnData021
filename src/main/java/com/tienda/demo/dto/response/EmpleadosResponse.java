package com.tienda.demo.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmpleadosResponse {
    private String nombre;
    private String Puesto;
    private Double salario;
}
