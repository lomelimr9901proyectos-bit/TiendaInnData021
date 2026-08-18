package com.tienda.demo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tienda.demo.dto.request.ProveedoresRequest;
import com.tienda.demo.dto.response.ProveedoresResponse;
import com.tienda.demo.entity.Proveedores;
import com.tienda.demo.repository.ProveedoresRepository;
import com.tienda.demo.service.IProveedores;

@Service
public class ProveedoresService implements IProveedores {
    @Autowired
    ProveedoresRepository proveedoresRepository;

    ProveedoresService(ProveedoresRepository proveedoresRepository) {
        this.proveedoresRepository = proveedoresRepository;
    }
    
    @Override
    public List<ProveedoresResponse> getAllProveedores() {
        List<Proveedores> proveedores = proveedoresRepository.findAll();
        return proveedores.stream().map(
            proveedor -> {
                ProveedoresResponse response = new ProveedoresResponse();
                response.setNombreEmpresa(proveedor.getNombreEmpresa());
                response.setTelefono(proveedor.getTelefono());
                return response;
            }
        ).toList();
    }

    @Override
    public ProveedoresResponse getProveedorById(Integer idProveedor) {
        Proveedores proveedor = proveedoresRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            return null;
        }
        ProveedoresResponse response = new ProveedoresResponse();
        response.setNombreEmpresa(proveedor.getNombreEmpresa());
        response.setTelefono(proveedor.getTelefono());
        return response;
    }

    @Override
    public ProveedoresResponse createProveedor(ProveedoresRequest proveedoresRequest) {
        Proveedores proveedor = new Proveedores();
        proveedor.setNombreEmpresa(proveedoresRequest.getNombreEmpresa());
        proveedor.setTelefono(proveedoresRequest.getTelefono());
        proveedor.setContacto(proveedoresRequest.getContacto());
        proveedor.setCorreoElectronico(proveedoresRequest.getCorreoElectronico());
        proveedor.setActive(true);
        Proveedores savedProveedor = proveedoresRepository.save(proveedor);
        ProveedoresResponse response = new ProveedoresResponse();
        response.setNombreEmpresa(savedProveedor.getNombreEmpresa());
        response.setTelefono(savedProveedor.getTelefono());
        return response;
    }

    @Override
    public ProveedoresResponse updateProveedor(Integer idProveedor, ProveedoresRequest proveedoresRequest) {
        Optional<Proveedores> optionalProveedor = proveedoresRepository.findById(idProveedor);
        if (optionalProveedor.isPresent()) {
            Proveedores proveedor = optionalProveedor.get();
            proveedor.setNombreEmpresa(proveedoresRequest.getNombreEmpresa());
            proveedor.setContacto(proveedoresRequest.getContacto());
            proveedor.setCorreoElectronico(proveedoresRequest.getCorreoElectronico());
            proveedor.setTelefono(proveedoresRequest.getTelefono());
            Proveedores updatedProveedor = proveedoresRepository.save(proveedor);
            ProveedoresResponse response = new ProveedoresResponse();
            response.setNombreEmpresa(updatedProveedor.getNombreEmpresa());
            response.setTelefono(updatedProveedor.getTelefono());
            return response;
        } else {
            return null;
        }
    }

    @Override
    public String deleteProveedor(Integer idProveedor) {
        Optional<Proveedores> optionalProveedor = proveedoresRepository.findById(idProveedor);
        if (optionalProveedor.isPresent()){
            Proveedores proveedor = optionalProveedor.get();
            proveedor.setActive(false);
            proveedoresRepository.save(proveedor);
            return "Proveedor eliminado correctamente";
        } else {
            return "Proveedor no encontrado";
        }
    }
    
}
