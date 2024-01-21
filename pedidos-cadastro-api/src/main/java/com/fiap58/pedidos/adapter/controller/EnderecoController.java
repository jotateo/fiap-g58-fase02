package com.fiap58.pedidos.adapter.controller;

import com.fiap58.pedidos.core.domain.entity.Endereco;
import com.fiap58.pedidos.core.domain.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

//    @Autowired
//    private EnderecoService service;
//
//    @PostMapping
//    public ResponseEntity<Endereco> adicionarEndereco(@RequestBody Endereco endereco) {
//        Endereco novoEndereco = service.adicionarEndereco(endereco);
//        return ResponseEntity.status(HttpStatus.CREATED).body(novoEndereco);
//    }
//    @GetMapping("/cliente/{idCliente}")
//    public ResponseEntity<List<Endereco>> buscarEnderecosPorCliente(@PathVariable Long idCliente) {
//        List<Endereco> enderecos = service.buscarEnderecosPorCliente(idCliente);
//        return ResponseEntity.ok(enderecos);
//    }
}
