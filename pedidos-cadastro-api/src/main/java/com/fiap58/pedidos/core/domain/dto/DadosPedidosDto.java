package com.fiap58.pedidos.core.domain.dto;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class DadosPedidosDto {
    private Date dataPedido;
    private List<ProdutoCarrinho> carrinho;
    private String nomeCliente;

    public DadosPedidosDto(Pedido pedido, List<PedidoProduto> pedidoProdutos){
        this.dataPedido = pedido.getDataPedido();
        this.carrinho = this.retornaCarrinho(pedidoProdutos);
        this.nomeCliente = pedido.getCliente().getNome();
    }

    public List<ProdutoCarrinho> retornaCarrinho(List<PedidoProduto> pedidoProdutos){
        List<ProdutoCarrinho> carrinho = new ArrayList<>();
        for (PedidoProduto pedidoProduto : pedidoProdutos
        ) {
            carrinho.add(new ProdutoCarrinho(pedidoProduto));
        }
        return carrinho;
    }
}
