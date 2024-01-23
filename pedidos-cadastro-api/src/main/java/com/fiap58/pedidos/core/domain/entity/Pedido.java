package com.fiap58.pedidos.core.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"produtos"})
@Entity
@Table(name = "Pedidos")
@Getter
@Setter
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long idPedido;

    public Pedido(){};


    @ManyToOne
    @JoinColumn(name = "ID_CLIENTE")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<PedidoProduto> produtos;

    @Column(name = "DATA_PEDIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;

    @Column(name = "DATA_FINALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFinalizado;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    public Pedido(Long idPedido, Cliente cliente) {
        this.idPedido = idPedido;
        this.cliente = cliente;
        this.dataPedido = new Date();
        this.status = StatusPedido.RECEBIDO;
    }
}
