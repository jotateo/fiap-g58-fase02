package com.fiap58.pedidos.adapter.repository;

import com.fiap58.pedidos.core.domain.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>  {
}
