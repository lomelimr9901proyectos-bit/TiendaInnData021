package com.tienda.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import jakarta.websocket.server.PathParam;

import com.tienda.demo.dto.request.ProveedoresRequest;
import com.tienda.demo.dto.response.ProveedoresResponse;
import com.tienda.demo.service.implementation.ProveedoresService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/")
public class ProveedoresController {
    @Autowired
    ProveedoresService proveedoresService;

    @GetMapping("proveedores")
    public List<ProveedoresResponse> getAllProveedores() {
        return proveedoresService.getAllProveedores();    
    }
    
    @GetMapping("proveedorId")
    public ProveedoresResponse getProveedorById(@PathParam("idProveedor") Integer idProveedor) {
        return proveedoresService.getProveedorById(idProveedor);
    }

    @PostMapping("proveedorCreate")
    public ProveedoresResponse createProveedor(@RequestBody ProveedoresRequest proveedoresRequest) {
        return proveedoresService.createProveedor(proveedoresRequest);
    }

    @PutMapping("proveedorUpdate")
    public ProveedoresResponse updateProveedor(@PathParam("idProveedor") Integer idProveedor, @RequestBody ProveedoresRequest proveedoresRequest) {
        return proveedoresService.updateProveedor(idProveedor, proveedoresRequest);
    }

    @PutMapping("proveedorDelete")
    public String deleteProveedor(@PathParam("idProveedor") Integer idProveedor) {
        return proveedoresService.deleteProveedor(idProveedor);
    }
}


