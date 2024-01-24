package com.fiap58.pedidos.controller;

import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.usecase.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(description = "Faz a inserção de um novo cliente")
    @PostMapping("/inserir")
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente checkCliente = service.buscarClientePorCpf(cliente.getCpf());
        if (checkCliente != null) {
            return ResponseEntity.status(HttpStatus.FOUND).body(checkCliente);
        }
        Cliente novoCliente = service.cadastrarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @Operation(description = "Lista todos os clientes")
    @GetMapping("/list")
    public ResponseEntity<List<DadosClienteDto>> listarClientes(){
        List<DadosClienteDto> clientes = service.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @Operation(description = "Busca um cliente por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        Cliente cliente = service.buscarClientePorId(id);
        if (cliente != null){
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(description = "Busca um cliente por CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarClientePorCpf(@PathVariable String cpf) {
        Cliente cliente = service.buscarClientePorCpf(cpf);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
