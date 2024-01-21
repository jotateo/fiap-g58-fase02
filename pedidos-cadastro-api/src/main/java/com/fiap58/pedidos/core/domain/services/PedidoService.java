package com.fiap58.pedidos.core.domain.services;

import com.fiap58.pedidos.adapter.repository.*;
import com.fiap58.pedidos.core.domain.dto.DadosPedidosDto;
import com.fiap58.pedidos.core.domain.dto.DadosPedidosEntrada;
import com.fiap58.pedidos.core.domain.dto.ProdutoCarrinho;
import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.PedidoProduto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private PedidoProdutoRepository pedidoProdutoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public DadosPedidosDto inserirPedidoFila(DadosPedidosEntrada dto) {
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElse(null);
        Pedido pedido = new Pedido(null, cliente);
        Pedido pedidoCriado = repository.save(pedido);

        List<PedidoProduto> pedidosProdutos = new ArrayList<>();
        for (ProdutoCarrinho carrinho : dto.carrinho()) {
            Produto produto = produtoRepository.getReferenceById(carrinho.idProduto());
            PedidoProduto pedidoProduto = new PedidoProduto(null, pedidoCriado,
                    produto, carrinho.quantidade(), produto.getPrecoAtual(),
                    carrinho.observacao());
            pedidoProdutoRepository.save(pedidoProduto);
            pedidosProdutos.add(pedidoProduto);
        }

        DadosPedidosDto retornoPedidoCriado = new DadosPedidosDto(pedido, pedidosProdutos);


        return retornoPedidoCriado;
    }

    public List<DadosPedidosDto> listarPedidos(){
        List<Pedido> pedidos = this.retornarTodosPedidos();
        List<DadosPedidosDto> dadosPedidosDto = new ArrayList<>();


        for (Pedido pedido: pedidos
             ) {
            List<PedidoProduto> pedidoProdutos = this.retornaTabelaJuncao(pedido);
            List<PedidoProduto> produtosDoPedido = pedidoProdutos.stream()
                    .filter(pedidoProduto -> pedidoProduto.getPedido().getIdPedido() == pedido.getIdPedido())
                    .collect(Collectors.toList());
            dadosPedidosDto.add(new DadosPedidosDto(pedido, produtosDoPedido));
        }
        return dadosPedidosDto;
    }

    public List<Pedido> retornarTodosPedidos() {
        return repository.findAll();
    }

    public List<PedidoProduto> retornaTabelaJuncao(Pedido pedido){
        return pedidoProdutoRepository.findAllByIdPedido(pedido.getIdPedido());
    }

//    this.idPedido = idPedido;
//        this.cliente = cliente;
//        this.produtos = produtos;
//        this.quantidade = quantidade;
//        this.precoVenda = precoVenda;
//        this.observacao = observacao;
//        this.dataPedido = new Date();
}
