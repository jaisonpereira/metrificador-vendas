package com.jpereira.metrificador.vendas.domain.dto.api.requests.compras;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrdemCompraDto {

    private String nome;
    private String cpf;
    private List<CompraDto> compras = new ArrayList<>();

}
