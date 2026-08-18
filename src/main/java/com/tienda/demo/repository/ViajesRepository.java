package com.tienda.demo.repository;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tienda.demo.dto.terceros.Viajes;

@FeignClient(name="viajes", url ="https://6721642e98bbb4d93ca84a26.mockapi.io/api/v4")
public interface ViajesRepository {
    @GetMapping("/tickets")
    public List<Viajes> readAll();
    
    @GetMapping("/tickets/{id}")
    public Viajes readById(@PathVariable Integer id);

    @PostMapping("/tickets")
    public Viajes create(Viajes viajes);
}
