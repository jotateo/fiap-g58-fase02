package com.fiap58.pedidos.core.domain.dto;

import com.fiap58.pedidos.core.domain.entity.Categoria;

import java.math.BigDecimal;
import java.time.Instant;

public record DadosProdutoDto(
        Long idCategoria,
        String nome,
        String descricao,
        BigDecimal precoAtual

) {

}
