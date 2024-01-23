package com.fiap58.pedidos.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class DadosPedidosDto {
    @JsonIgnore
    private Long id;
    private List<ProdutoCarrinhoSaida> produtos;
    private String nomeCliente;
    private Date dataPedido;
    private StatusPedido status;

    public DadosPedidosDto(Pedido pedido, List<PedidoProduto> pedidoProdutos){
        this.id = pedido.getIdPedido();
        this.dataPedido = pedido.getDataPedido();
        this.produtos = this.retornaCarrinho(pedidoProdutos);
        this.nomeCliente = pedido.getCliente().getNome();
        this.status = pedido.getStatus();
    }

    public List<ProdutoCarrinhoSaida> retornaCarrinho(List<PedidoProduto> pedidoProdutos){
        List<ProdutoCarrinhoSaida> produtos = new ArrayList<>();
        for (PedidoProduto pedidoProduto : pedidoProdutos
        ) {
            produtos.add(new ProdutoCarrinhoSaida(pedidoProduto));
        }
        return produtos;
    }
}
