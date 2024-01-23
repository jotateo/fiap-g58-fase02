package com.fiap58.pedidos.adapter.controller;

import com.fiap58.pedidos.core.domain.dto.DadosPedidosDto;
import com.fiap58.pedidos.core.domain.dto.DadosPedidosEntrada;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.services.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Operation(description = "Lista todos os pedidos")
    @GetMapping
    public ResponseEntity<List<DadosPedidosDto>> listarPedidos(){
        List<DadosPedidosDto> pedidos = service.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }

    @Operation(description = "Inicia Checkout")
    @PostMapping("/checkout")

    @Transactional
    public ResponseEntity<Long> incluirPedido(@RequestBody @Valid DadosPedidosEntrada dto){
        DadosPedidosDto dadosPedidosDto = service.inserirPedidoFila(dto);

        return ResponseEntity.ok(dadosPedidosDto.getId());
    }

    @Operation(description = "Atualiza o status do Pedido")
    @PatchMapping("/atualizar/{id}")
    @Transactional
    public ResponseEntity<DadosPedidosDto> atualizarStatus(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(service.atualizarPedido(id));
    }
}

