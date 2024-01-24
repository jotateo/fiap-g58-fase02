package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import com.fiap58.pedidos.core.usecase.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;

    @Operation(description = "Busca categoria por Id")
    @GetMapping("/{id}")
    public Categoria getCategoria(@PathVariable long id) {
        return service.buscarCategoria(id);
    }
}
