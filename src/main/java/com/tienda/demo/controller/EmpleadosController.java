package com.tienda.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tienda.demo.dto.request.EmpleadosRequest;
import com.tienda.demo.dto.response.EmpleadosResponse;
import com.tienda.demo.service.implementation.EmpleadosService;

import jakarta.websocket.server.PathParam;



@RestController
@RequestMapping("/api/v1/")
public class EmpleadosController {
    
    @Autowired
    EmpleadosService empleadosService;

    @GetMapping("empleados")
    public List<EmpleadosResponse> getAllEmpleados() {
        return empleadosService.getAllEmpleados();
    }
    
    @GetMapping("empleadoId")
    public EmpleadosResponse getEmpleadoById(@PathParam("idEmpleado") Integer idEmpleado) {
        return empleadosService.getEmpleadoById(idEmpleado);
    }

    @PostMapping("empleados")
    public EmpleadosResponse createEmpleado(@RequestBody EmpleadosRequest empleadoRequest) {
        return empleadosService.createEmpleado(empleadoRequest);
    }

    @PutMapping("empleados")
    public EmpleadosResponse updateEmpleado(@PathParam("idEmpleado") Integer idEmpleado, @RequestBody EmpleadosRequest empleadoRequest){
        return empleadosService.updateEmpleado(idEmpleado, empleadoRequest);
    }
    
    @PutMapping("empleadoDelete")
    public String deleteEmpleado(@PathParam("idEmpleado") Integer idEmpleado) {
        return empleadosService.deleteEmpleado(idEmpleado);
    }

    @GetMapping("empleadosSalario")
    public EmpleadosResponse findBySalario(@PathParam("salario") Double salario) {
        return empleadosService.findBySalario(salario);
    }

    @GetMapping("inactivos")
    public List<EmpleadosResponse> usuariosInactivos(@RequestParam("active") Boolean active) {
        return empleadosService.usuariosInactivos(active);
    }
}
