package com.fiap58.pedidos.adapter.controller;

import com.fiap58.pedidos.core.domain.dto.DadosProdutoDto;
import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.domain.entity.Produto;
import com.fiap58.pedidos.core.domain.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Operation(description = "Busca produto por Id")
    @GetMapping("/{id}")
    public Produto buscarProduto(@PathVariable long id) {return service.buscarProduto(id);}

    @Operation(description = "Lista todos os produtos disponíveis")
    @GetMapping("/list")
    public List<Produto> listarProdutos(){
        return service.listarProdutos();
    }

    @Operation(description = "Busca produtos de uma determinada categoria")
    @GetMapping("/buscaPorCat/{nomeCategoria}")
    public List<Produto> listarProdutosPorCategoria(@PathVariable String nomeCategoria) {
        return service.buscarProdutoPorCategoria(nomeCategoria);
    }

    @Operation(description = "Faz a inclusão de um novo produto")
    @PostMapping
    @Transactional
    public ResponseEntity<DadosProdutoDto> incluirProduto(@RequestBody @Valid DadosProdutoDto dto) {

        DadosProdutoDto dadosProdutoDto = service.inserirProduto(dto);
        return ResponseEntity.ok(dadosProdutoDto);
    }

    @Operation(description = "Atualiza um produto existente")
    @PatchMapping("/{id}")
    @Transactional
    public void atualizarProduto(@PathVariable Long id, @RequestBody @Valid DadosProdutoDto dto) {

        service.updateProduto(id, dto);

    }

    @Operation(description = "Deleta um produto")
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteProduto(@PathVariable Long id) {

        service.deleteProduto(id);
    }



}
