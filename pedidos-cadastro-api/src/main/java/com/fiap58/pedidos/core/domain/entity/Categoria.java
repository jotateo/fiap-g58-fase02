package com.fiap58.pedidos.core.domain.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties({"produtos"})
@Entity
@Table(name = "Categorias")
@Getter
@Setter
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Long idCategoria;

    @Column(name = "NOME_CATEGORIA", nullable = false, length = 50)
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

    public Categoria() {};
    public Categoria(String nome){
        this.nomeCategoria = nome;
    }

}
