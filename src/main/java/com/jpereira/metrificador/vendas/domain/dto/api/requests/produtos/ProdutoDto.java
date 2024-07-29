package com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoDto {
    private Long codigo;
    @JsonProperty("tipo_vinho")
    private String tipoVinho;
    private BigDecimal preco;
    private String safra;
    @JsonProperty("ano_compra")
    private Integer anoCompra;
}
