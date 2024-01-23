package com.fiap58.pedidos.core.domain.dto;

import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
public class DadosPedidosDto {
    private List<ProdutoCarrinho> produtos;
    private String nomeCliente;
    private Date dataPedido;
    private StatusPedido status;

    public DadosPedidosDto(Pedido pedido, List<PedidoProduto> pedidoProdutos){
        this.dataPedido = pedido.getDataPedido();
        this.produtos = this.retornaCarrinho(pedidoProdutos);
        this.nomeCliente = pedido.getCliente().getNome();
        this.status = pedido.getStatus();
    }

    public List<ProdutoCarrinho> retornaCarrinho(List<PedidoProduto> pedidoProdutos){
        List<ProdutoCarrinho> produtos = new ArrayList<>();
        for (PedidoProduto pedidoProduto : pedidoProdutos
        ) {
            produtos.add(new ProdutoCarrinho(pedidoProduto));
        }
        return produtos;
    }
}
