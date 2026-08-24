package com.tienda.demo.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda.demo.dto.response.ProveedoresResponse;
import com.tienda.demo.entity.Proveedores;
import com.tienda.demo.repository.ProveedoresRepository;

import java.util.List;
import java.util.Optional;

import com.tienda.demo.dto.request.ProveedoresRequest;
import com.tienda.demo.entity.Proveedores;
@ExtendWith(MockitoExtension.class)
public class ProveedoresServiceTest {
    @InjectMocks  //crea una dependencia de un repositorio o una dependencia externa, es como autoWired para hacer inyeccion de dependencias

    ProveedoresService proveedoresService;


    @Mock
    ProveedoresRepository proveedoresRepository;
    Proveedores proveedor1 = new Proveedores(1, "Coca-Cola", "Juan Perez", "cocacola@gmail.com", "4931362585", true);
    Proveedores proveedor2 = new Proveedores(2, "Pepsi", "Maria Lopez", "pepsi@gmail.com", "4931362586", true);
    Proveedores proveedor3 = new Proveedores(3, "Bimbo", "Carlos Sanchez", "Sabritas", "4931362587", true);
    
    ProveedoresResponse proveedorResponse1 = new ProveedoresResponse(proveedor1.getNombreEmpresa(), proveedor1.getTelefono());
    ProveedoresResponse proveedorResponse2 = new ProveedoresResponse(proveedor2.getNombreEmpresa(), proveedor2.getTelefono());
    ProveedoresResponse proveedorResponse3 = new ProveedoresResponse(proveedor3.getNombreEmpresa(), proveedor3.getTelefono());
    @Test
    void testSumar() {
        Double res = proveedoresService.sumar(2.0, 3.0);
        //when(proveedoresService.sumar(2.0, 3.0)).thenReturn(res);
        assertEquals(5.0, res);
    }

    @Test
    void testGetAllProveedores() {
        List<ProveedoresResponse> proveedoresList = List.of(proveedorResponse1, proveedorResponse2, proveedorResponse3);
        List<Proveedores> proveedores = proveedoresRepository.findAll();
        when(proveedoresRepository.findAll()).thenReturn(proveedoresList.stream().map(
            proveedorResponse -> {
                Proveedores proveedor = new Proveedores();
                proveedor.setNombreEmpresa(proveedorResponse.getNombreEmpresa());
                proveedor.setTelefono(proveedorResponse.getTelefono());
                return proveedor;
            }
        ).toList());
        List<ProveedoresResponse> result = proveedoresService.getAllProveedores();
        assertEquals(result, proveedoresList);
    }

    @Test
    void testUpdateProveedor() {
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(proveedor1));
        Proveedores actualizar = new Proveedores(1, "Coca-Cola", "Mario Ramirez", "cocacola2@gmail.com", "4934567897", true );
        proveedor1.setContacto(actualizar.getContacto());
        proveedor1.setTelefono(actualizar.getTelefono());
        ProveedoresRequest actualizarRequest = new ProveedoresRequest();
        actualizarRequest.setNombreEmpresa(actualizar.getNombreEmpresa());
        actualizarRequest.setContacto(actualizar.getContacto());
        actualizarRequest.setCorreoElectronico(actualizar.getCorreoElectronico());
        actualizarRequest.setTelefono(actualizar.getTelefono());
        when(proveedoresRepository.save(proveedor1)).thenReturn(proveedor1);

        //cambiando proveedor 1 a request
        ProveedoresRequest proveedor1Request = new ProveedoresRequest();
        proveedor1Request.setNombreEmpresa(proveedor1.getNombreEmpresa());
        proveedor1Request.setContacto(proveedor1.getContacto());
        proveedor1Request.setCorreoElectronico(proveedor1.getCorreoElectronico());
        proveedor1Request.setTelefono(proveedor1.getTelefono());
        
        ProveedoresResponse proveedorResponseActual = new ProveedoresResponse();
        proveedorResponseActual.setNombreEmpresa(proveedor1.getNombreEmpresa());
        proveedorResponseActual.setTelefono(proveedor1.getTelefono());

        ProveedoresResponse proveedorEsperado = proveedoresService.updateProveedor(1, actualizarRequest);

        assertEquals(proveedorEsperado, proveedorResponseActual);
    }

 




}
