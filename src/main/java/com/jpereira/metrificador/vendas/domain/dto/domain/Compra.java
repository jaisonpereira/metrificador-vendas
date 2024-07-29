package com.jpereira.metrificador.vendas.domain.dto.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Compra {
    private Long codigo;
    private Produto produto;
    private BigDecimal valorTotal;
    private Long quantidade;
}
