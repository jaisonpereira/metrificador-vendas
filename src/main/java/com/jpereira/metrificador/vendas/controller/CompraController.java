package com.jpereira.metrificador.vendas.controller;

import com.jpereira.metrificador.vendas.domain.resource.OrdemCompraResource;
import com.jpereira.metrificador.vendas.domain.resource.ProdutoResource;
import com.jpereira.metrificador.vendas.domain.service.OrdemCompraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompraController {

    private OrdemCompraService serviceOrdemCompra;

    public CompraController(OrdemCompraService serviceOrdemCompra) {
        this.serviceOrdemCompra = serviceOrdemCompra;
    }

    @GetMapping("/compras")
    public Object recuperaCompras() {
        return this.serviceOrdemCompra.getCompras();
    }

}
