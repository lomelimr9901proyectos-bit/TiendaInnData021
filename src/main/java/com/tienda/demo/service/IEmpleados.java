package com.tienda.demo.service;

import java.util.List;

import com.tienda.demo.dto.request.EmpleadosRequest;
import com.tienda.demo.dto.response.EmpleadosResponse;

public interface IEmpleados {
    public List<EmpleadosResponse> getAllEmpleados();
    public EmpleadosResponse getEmpleadoById(Integer idEmpleado);
    public EmpleadosResponse createEmpleado(EmpleadosRequest empleadosRequest);
    public EmpleadosResponse updateEmpleado(Integer idEmpleado, EmpleadosRequest empleadosRequest);
    public String deleteEmpleado(Integer idEmpleado);


    public EmpleadosResponse findBySalario(Double salario);
    public List<EmpleadosResponse> usuariosInactivos(Boolean active);
}
