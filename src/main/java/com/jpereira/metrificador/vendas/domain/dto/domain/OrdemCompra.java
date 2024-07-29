package com.jpereira.metrificador.vendas.domain.dto.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrdemCompra {

    private String nome;
    private String cpf;
    private List<Compra> compras = new ArrayList<>();
    private BigDecimal valorTotalComprasCliente;
}