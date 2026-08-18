package com.tienda.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tienda.demo.entity.Empleados;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmpleadosRepository extends JpaRepository<Empleados, Integer> {
    
    public List<Empleados> findBySalario(Double salario);

    @Query(value = "SELECT * FROM empleados e WHERE active = :active", nativeQuery = true)
    public List<Empleados> usuariosInactivos(Boolean active);

}
