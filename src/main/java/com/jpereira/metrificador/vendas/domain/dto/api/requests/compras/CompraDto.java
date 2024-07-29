package com.jpereira.metrificador.vendas.domain.dto.api.requests.compras;

import lombok.Data;

@Data
public class CompraDto {
    private String codigo;
    private Long quantidade;
}
