package com.tienda.demo.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmpleadosRequest {
    private String nombre;
    private String apellido;
    private String puesto;
    private Double salario;
}
