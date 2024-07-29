package com.jpereira.metrificador.vendas.domain.dto.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Produto {

    private Long codigo;
    private String tipoVinho;
    private BigDecimal preco;
    private String safra;
    private Integer anoCompra;
}
