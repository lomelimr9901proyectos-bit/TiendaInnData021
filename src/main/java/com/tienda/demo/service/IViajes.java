package com.tienda.demo.service;

import java.util.List;
import com.tienda.demo.dto.terceros.Viajes;

public interface IViajes {
    public List<Viajes> readAll();
    public Viajes readById(Integer id);
    public Viajes create(Viajes viajes);
}
