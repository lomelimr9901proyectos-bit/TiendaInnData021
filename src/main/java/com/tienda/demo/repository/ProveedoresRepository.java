package com.tienda.demo.repository;
import com.tienda.demo.entity.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedoresRepository extends JpaRepository<Proveedores, Integer> {
    
}
