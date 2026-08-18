package com.tienda.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.tienda.demo.service.IViajes;

import com.tienda.demo.dto.terceros.Viajes;

@RestController
@RequestMapping("/viajes")
public class ViajesController {
    @Autowired
    private IViajes viajesService;

    @GetMapping("/viajes")
    public List<Viajes> readAll() {
        return viajesService.readAll();
    }

    @GetMapping("/viajes/{id}")
    public Viajes readById(@PathVariable Integer id) {
        return viajesService.readById(id);
    }

    @PostMapping("/viajes")
    public Viajes create(@RequestBody Viajes viajes) {
        return viajesService.create(viajes);
    }
        
}
