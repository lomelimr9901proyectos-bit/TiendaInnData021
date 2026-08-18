package com.tienda.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ProveedoresRequest {
    private String nombreEmpresa;
    private String contacto;
    private String correoElectronico;
    private String telefono;
}
