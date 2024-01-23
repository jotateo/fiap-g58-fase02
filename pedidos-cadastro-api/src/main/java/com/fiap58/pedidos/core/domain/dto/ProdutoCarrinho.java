package com.fiap58.pedidos.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;

public record ProdutoCarrinho(

        Long idProduto,
        String nome,
        int quantidade,
        String observacao
) {
    public ProdutoCarrinho(PedidoProduto pedidoProduto){
        this(pedidoProduto.getProduto().getIdProduto(),
                pedidoProduto.getProduto().getNome(),
                pedidoProduto.getQuantidade(),
                pedidoProduto.getObservacao() != null
                        ? pedidoProduto.getObservacao()
                        : "");
    }
}
