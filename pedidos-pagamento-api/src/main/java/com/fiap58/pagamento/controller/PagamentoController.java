package com.fiap58.pagamento.controller;


import com.fiap58.pagamento.core.entity.Pagamento;
import com.fiap58.pagamento.dto.DadosPedidoDto;
import com.fiap58.pagamento.service.PagamentoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gerenciamento-pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService service;


    @Operation(description = "Cria pagamento a partir de um Pedido")
    @PostMapping("/criar-pagamento/pedido/{id}")
    public ResponseEntity<Pagamento> criarPagamento(@PathVariable long id){
        Pagamento pagamento = service.criarPagamento(id);
        return ResponseEntity.ok(pagamento);
    }

    @Operation(description = "Confirma pagamento e envia pedido para cozinha")
    @PostMapping("/pagamento/confirma/{id}")
    public ResponseEntity<DadosPedidoDto> confirmaPagamento(@PathVariable long id){
        DadosPedidoDto pagamento = service.confirmaPagamento(id);
        return ResponseEntity.ok(pagamento);
    }
}
