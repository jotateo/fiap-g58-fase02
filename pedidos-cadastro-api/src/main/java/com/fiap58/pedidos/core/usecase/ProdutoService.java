package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.gateway.CategoriaRepository;
import com.fiap58.pedidos.gateway.ProdutoRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosProdutoDtoEntrada;
import com.fiap58.pedidos.presenters.dto.saida.DadosProdutoDto;
import com.fiap58.pedidos.core.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fiap58.pedidos.core.specifications.ProdutoSpecification.temCategoria;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto buscarProduto(long id) {return repository.getReferenceById(id);}

    public List<Produto> listarProdutos() {
        return repository.findAll();
    }

    public DadosProdutoDto inserirProduto(DadosProdutoDtoEntrada dto) {
        Produto produto = new Produto(dto.nome(), dto.descricao(), dto.precoAtual());
        var categoria = categoriaRepository.findById(dto.idCategoria()).get();
        produto.setCategoria(categoria);

        Produto produtoSalvo = repository.save(produto);

        return mapperProdutoDto(produtoSalvo);
    }

    private DadosProdutoDto mapperProdutoDto(Produto produto){
        return new DadosProdutoDto(produto);
    }

    public void deleteProduto(Long id) {
        repository.deleteById(id);
    }

    public Produto updateProduto(Long id, DadosProdutoDto dto) {
        // Por enquanto, so atualiza nome e preço.
        Produto produto = repository.getReferenceById(id);
        produto.setNome(dto.nome().isEmpty()? produto.getNome(): dto.nome());
        produto.setPrecoAtual(dto.precoAtual() == null ? produto.getPrecoAtual(): dto.precoAtual());

        repository.save(produto);

        return produto;
    }

    public List<Produto> buscarProdutoPorCategoria(String nomeCategoria) {
        return repository.findAll(temCategoria(nomeCategoria));
    }
}
