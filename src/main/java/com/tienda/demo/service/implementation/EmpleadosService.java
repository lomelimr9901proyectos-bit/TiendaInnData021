package com.tienda.demo.service.implementation;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import com.tienda.demo.dto.request.EmpleadosRequest;
import com.tienda.demo.dto.response.EmpleadosResponse;
import com.tienda.demo.entity.Empleados;
import com.tienda.demo.repository.EmpleadosRepository;
import com.tienda.demo.service.IEmpleados;

@Slf4j
@Service
public class EmpleadosService implements IEmpleados {
    @Autowired
    EmpleadosRepository empleadosRepository;

    @Override
    public List<EmpleadosResponse> getAllEmpleados() {
        List<Empleados> empleadosResponse = empleadosRepository.findAll();
        return empleadosResponse.stream().map(
            empleado -> {
                EmpleadosResponse response = new EmpleadosResponse();
                response.setNombre(empleado.getNombre());
                response.setPuesto(empleado.getPuesto());
                response.setSalario(empleado.getSalario());
                return response;
            }
        ).toList();
    }

    @Override
    public EmpleadosResponse getEmpleadoById(Integer idEmpleado) {
        Empleados empleado = new Empleados();
        try {
            empleado = empleadosRepository.findById(idEmpleado).orElse(null);
        } catch(  Exception e) {
            
            log.error("Error al obtener el empleado por ID: " + e.getMessage());
            return null;
        }
        if(empleado == null){
            return null;
        } else {
            EmpleadosResponse response = new EmpleadosResponse();
            response.setNombre(empleado.getNombre());
            response.setPuesto(empleado.getPuesto());
            response.setSalario(empleado.getSalario());
            return response;
        }
    }

    @Override
    public EmpleadosResponse createEmpleado(EmpleadosRequest empleadosRequest) {
        Empleados newEmpleado = new Empleados();
        newEmpleado.setNombre(empleadosRequest.getNombre());
        newEmpleado.setApellido(empleadosRequest.getApellido());
        newEmpleado.setPuesto(empleadosRequest.getPuesto());
        newEmpleado.setSalario(empleadosRequest.getSalario());
        newEmpleado.setActive(true);
        empleadosRepository.save(newEmpleado);

        EmpleadosResponse empleadoResponse = new EmpleadosResponse();
        empleadoResponse.setNombre(newEmpleado.getNombre());
        empleadoResponse.setPuesto(newEmpleado.getPuesto());
        empleadoResponse.setSalario(newEmpleado.getSalario());
        return empleadoResponse;
    }

    @Override
    public EmpleadosResponse updateEmpleado(Integer idEmpleado, EmpleadosRequest empleadosRequest) {
        Optional<Empleados> empleadoUpdate = empleadosRepository.findById(idEmpleado);
        if(empleadoUpdate.isPresent()){
            Empleados empleado = empleadoUpdate.get();
            empleado.setNombre(empleadosRequest.getNombre());
            empleado.setApellido(empleadosRequest.getApellido());
            empleado.setPuesto(empleadosRequest.getPuesto());
            empleado.setSalario(empleadosRequest.getSalario());
            empleadosRepository.save(empleado);

            EmpleadosResponse empleadoResponse = new EmpleadosResponse();
            empleadoResponse.setNombre(empleado.getNombre());
            empleadoResponse.setPuesto(empleado.getPuesto());
            empleadoResponse.setSalario(empleado.getSalario());
            return  empleadoResponse;
        }else{
            return new EmpleadosResponse();
        }
    }

    @Override
    public String deleteEmpleado(Integer idEmpleado) {
        Optional <Empleados> empleadoABorrar = empleadosRepository.findById(idEmpleado);
        if(empleadoABorrar.isPresent()){
            Empleados empleado2 = empleadoABorrar.get();
            empleado2.setActive(false);
            empleadosRepository.save(empleado2);
            return "El empleado se ha deshabilitado temporalmente";
        }else{
            return "El empleado no se ha encontrado";
        }
    }

    @Override
    public EmpleadosResponse findBySalario(Double salario) {
        Empleados empleado = empleadosRepository.findBySalario(salario).stream().findFirst().orElse(null);
        if(empleado == null){
            return null;
        } else {
            EmpleadosResponse response = new EmpleadosResponse();
            response.setNombre(empleado.getNombre());
            response.setPuesto(empleado.getPuesto());
            response.setSalario(empleado.getSalario());
            return response;
        }
    }

    @Override
    public List<EmpleadosResponse> usuariosInactivos(Boolean active) {
        List<Empleados> empleados = empleadosRepository.findAll();
        if(active == false){
                return empleadosRepository.usuariosInactivos(active).stream().map(
                empleado -> {
                EmpleadosResponse empleadoResponse = new EmpleadosResponse();
                empleadoResponse.setNombre(empleado.getNombre());
                empleadoResponse.setPuesto(empleado.getPuesto());
                empleadoResponse.setSalario(empleado.getSalario());
                return empleadoResponse;
            }
        ).toList();
        }else{
            return null;
        }
    }
}
