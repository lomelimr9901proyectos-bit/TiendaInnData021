package com.tienda.demo.service;

import java.util.List;

import com.tienda.demo.dto.request.ProveedoresRequest;
import com.tienda.demo.dto.response.ProveedoresResponse;

public interface IProveedores {
    public List<ProveedoresResponse> getAllProveedores();
    public ProveedoresResponse getProveedorById(Integer idProveedor);
    public ProveedoresResponse createProveedor(ProveedoresRequest proveedoresRequest);
    public ProveedoresResponse updateProveedor(Integer idProveedor, ProveedoresRequest proveedoresRequest);
    public String deleteProveedor(Integer idProveedor);
}
