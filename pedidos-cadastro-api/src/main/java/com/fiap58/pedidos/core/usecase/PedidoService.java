package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.adapter.repository.*;
import com.fiap58.pedidos.presenters.dto.saida.DadosPedidosDto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosPedidosEntrada;
import com.fiap58.pedidos.presenters.dto.entrada.ProdutoCarrinho;
import com.fiap58.pedidos.core.domain.entity.*;
import com.fiap58.pedidos.gateway.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private pedidoProdutoService pedidoProdutoService;


    public DadosPedidosDto inserirPedidoFila(DadosPedidosEntrada dto) {
        Cliente cliente;
        if(dto.clienteId() != null){
            cliente = clienteService.buscarClientePorId(dto.clienteId());
        } else {
            cliente = null;
        }
        Pedido pedido = new Pedido(null, cliente);
        Pedido pedidoCriado = repository.save(pedido);
        List<PedidoProduto> pedidosProdutos = processaCarrinhoPedido(dto.carrinho(), pedidoCriado);
        return new DadosPedidosDto(pedido, pedidosProdutos);
    }

    private List<PedidoProduto> processaCarrinhoPedido(List<ProdutoCarrinho> carrinhoProdutos,
                                                       Pedido pedidoCriado){
        List<PedidoProduto> pedidosProdutos = new ArrayList<>();
        for (ProdutoCarrinho carrinho : carrinhoProdutos) {
            Produto produto = produtoService.buscarProduto(carrinho.idProduto());
            PedidoProduto pedidoProduto = new PedidoProduto(null, pedidoCriado,
                    produto, carrinho.quantidade(), produto.getPrecoAtual(),
                    carrinho.observacao());
            pedidoProdutoService.inserirPedidoProduto(pedidoProduto);
            pedidosProdutos.add(pedidoProduto);
        }
        return pedidosProdutos;
    }

    public List<DadosPedidosDto> listarPedidos(){
        List<Pedido> pedidos = this.retornarTodosPedidos();
        List<DadosPedidosDto> dadosPedidosDto = new ArrayList<>();

        for (Pedido pedido: pedidos
             ) {
            dadosPedidosDto.add(mapperDadosPedidoDto(pedido));
        }
        return ordenaDadosPedidoDto(dadosPedidosDto);
    }

    private List<DadosPedidosDto>  ordenaDadosPedidoDto(List<DadosPedidosDto> listaInicial){

        List<DadosPedidosDto> lista = listaInicial.stream()
                .filter(pedidos -> !pedidos.getStatus().equals(StatusPedido.FINALIZADO))
                .sorted(Comparator.comparing(DadosPedidosDto::getDataPedido))
                .sorted((p1, p2) -> p2.getStatus().getValor() - p1.getStatus().getValor())
                .collect(Collectors.toList());

        return lista;
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
        return pedidoProdutoService.retornaPedidoProduto(pedido.getIdPedido());
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
            pedido.setDataFinalizado(new Date());
        }
    }

    private void verificaPagamento() throws Exception {
        // Fazer lógica para verificação de pagamento
        if(true)
            return;
        throw new Exception("Pagamento não identificado");
    }

}
