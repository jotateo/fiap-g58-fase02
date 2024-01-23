package com.fiap58.pedidos.core.domain.services;

import com.fiap58.pedidos.adapter.repository.*;
import com.fiap58.pedidos.core.domain.dto.DadosPedidosDto;
import com.fiap58.pedidos.core.domain.dto.DadosPedidosEntrada;
import com.fiap58.pedidos.core.domain.dto.ProdutoCarrinho;
import com.fiap58.pedidos.core.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
            dadosPedidosDto.add(mapperDadosPedidoDto(pedido));
        }
        return dadosPedidosDto;
    }

    private DadosPedidosDto mapperDadosPedidoDto(Pedido pedido){
        List<PedidoProduto> pedidoProdutos = this.retornaTabelaJuncao(pedido);
        List<PedidoProduto> produtosDoPedido = pedidoProdutos.stream()
                .filter(pedidoProduto -> pedidoProduto.getPedido().getIdPedido() == pedido.getIdPedido())
                .collect(Collectors.toList());
        return new DadosPedidosDto(pedido, produtosDoPedido);
    }

    private List<Pedido> retornarTodosPedidos() {
        return repository.findAll();
    }
    
    private Pedido retornaPedido(Long id){

        return repository.findById(id).orElseThrow();
    }

    public List<PedidoProduto> retornaTabelaJuncao(Pedido pedido){
        return pedidoProdutoRepository.findAllByIdPedido(pedido.getIdPedido());
    }

    public DadosPedidosDto atualizarPedido(Long id) throws Exception {
        Pedido pedido = this.retornaPedido(id);
        if (pedido.getStatus().equals(StatusPedido.RECEBIDO)){
            verificaPagamento();
        }
        defineProximoStatus(pedido);
        return mapperDadosPedidoDto(pedido);
    }

    private void defineProximoStatus(Pedido pedido){
        if(pedido.getStatus().equals(StatusPedido.RECEBIDO)) {
            pedido.setStatus(StatusPedido.EM_PREPARACAO);
        } else if (pedido.getStatus().equals(StatusPedido.EM_PREPARACAO)){
            pedido.setStatus(StatusPedido.PRONTO);
        } else {
            pedido.setStatus(StatusPedido.FINALIZADO);
        }
    }

    private void verificaPagamento() throws Exception {
        // Fazer lógica para verificação de pagamento
        if(true)
            return;
        throw new Exception("Pagamento não identificado");
    }

}
