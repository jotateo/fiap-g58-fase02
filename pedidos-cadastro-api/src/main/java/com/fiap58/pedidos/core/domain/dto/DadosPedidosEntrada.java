package com.fiap58.pedidos.core.domain.dto;

import com.fiap58.pedidos.core.domain.entity.Cliente;

import java.util.List;

public record DadosPedidosEntrada(
        List<ProdutoCarrinho> carrinho,
        Long clienteId
) {
    public DadosPedidosEntrada(List<ProdutoCarrinho> carrinho){
        this(carrinho, null);
    }

}
