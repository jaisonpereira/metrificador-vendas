package com.jpereira.metrificador.vendas.domain.service;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.OrdemCompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos.ProdutoDto;
import com.jpereira.metrificador.vendas.domain.dto.domain.OrdemCompra;
import com.jpereira.metrificador.vendas.domain.resource.OrdemCompraResource;
import com.jpereira.metrificador.vendas.domain.resource.ProdutoResource;
import com.jpereira.metrificador.vendas.domain.utils.OrdemCompraBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemCompraService {

    private ProdutoResource resourceProduto;
    private OrdemCompraResource resourceOrdemCompra;

    public OrdemCompraService(ProdutoResource resourceProduto, OrdemCompraResource resourceOrdemCompra) {
        this.resourceProduto = resourceProduto;
        this.resourceOrdemCompra = resourceOrdemCompra;
    }

    public List<OrdemCompra> getCompras() {
        List<OrdemCompraDto> ordensDto = this.resourceOrdemCompra.fetch();
        List<ProdutoDto> produtosDto = this.resourceProduto.fetch();
         return ordensDto == null ?
                   new ArrayList<>() :
                   ordensDto.stream()
                            .map(dto -> OrdemCompraBuilder.build(dto, produtosDto))
                            .collect(Collectors.toList());
    }

}
