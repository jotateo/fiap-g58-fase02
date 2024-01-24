package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.gateway.CategoriaRepository;
import com.fiap58.pedidos.core.domain.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria buscarCategoria(Long id){
        return repository.findById(id).get();
    }
}
