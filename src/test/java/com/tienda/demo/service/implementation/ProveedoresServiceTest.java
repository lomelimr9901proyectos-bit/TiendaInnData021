package com.tienda.demo.service.implementation;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tienda.demo.dto.request.ProveedoresRequest;
import com.tienda.demo.dto.response.ProveedoresResponse;
import com.tienda.demo.entity.Proveedores;
import com.tienda.demo.repository.ProveedoresRepository;

@ExtendWith(MockitoExtension.class)
public class ProveedoresServiceTest {
    @InjectMocks
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

    @Test
    void testCreateProveedor() {
        ProveedoresRequest request = new ProveedoresRequest();
        request.setNombreEmpresa("Nestle");
        request.setContacto("Ana Torres");
        request.setCorreoElectronico("nestle@gmail.com");
        request.setTelefono("4931362588");

        Proveedores proveedorGuardado = new Proveedores();
        proveedorGuardado.setIdProveedor(4);
        proveedorGuardado.setNombreEmpresa("Nestle");
        proveedorGuardado.setContacto("Ana Torres");
        proveedorGuardado.setCorreoElectronico("nestle@gmail.com");
        proveedorGuardado.setTelefono("4931362588");
        proveedorGuardado.setActive(true);

        when(proveedoresRepository.save(any(Proveedores.class))).thenReturn(proveedorGuardado);

        ProveedoresResponse response = proveedoresService.createProveedor(request);

        assertEquals("Nestle", response.getNombreEmpresa());
        assertEquals("4931362588", response.getTelefono());

        ArgumentCaptor<Proveedores> captor = ArgumentCaptor.forClass(Proveedores.class);
        verify(proveedoresRepository).save(captor.capture());

        Proveedores proveedorEnviado = captor.getValue();
        assertEquals("Nestle", proveedorEnviado.getNombreEmpresa());
        assertEquals("Ana Torres", proveedorEnviado.getContacto());
        assertEquals("nestle@gmail.com", proveedorEnviado.getCorreoElectronico());
        assertEquals("4931362588", proveedorEnviado.getTelefono());
        assertTrue(proveedorEnviado.getActive());
    }

    @Test
    void testDeleteProveedor() {
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(proveedor1));
        String result = proveedoresService.deleteProveedor(1);
        assertEquals("Proveedor eliminado correctamente", result);
    }

    @Test
    void testGetProveedorById() {
        when(proveedoresRepository.findById(1)).thenReturn(Optional.of(proveedor1));
        ProveedoresResponse result = proveedoresService.getProveedorById(1);
        ProveedoresResponse expected = new ProveedoresResponse();
        expected.setNombreEmpresa(proveedor1.getNombreEmpresa());
        expected.setTelefono(proveedor1.getTelefono());
        assertEquals(expected, result);
    }
}
