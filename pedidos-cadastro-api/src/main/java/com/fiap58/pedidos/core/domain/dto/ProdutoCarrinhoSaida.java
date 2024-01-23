package com.fiap58.pedidos.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;

public record ProdutoCarrinhoSaida(

        @JsonIgnore
        Long idProduto,
        String nome,
        int quantidade,
        String observacao
) {
    public ProdutoCarrinhoSaida(PedidoProduto pedidoProduto){
        this(pedidoProduto.getProduto().getIdProduto(),
                pedidoProduto.getProduto().getNome(),
                pedidoProduto.getQuantidade(),
                pedidoProduto.getObservacao() != null
                        ? pedidoProduto.getObservacao()
                        : "");
    }
}
