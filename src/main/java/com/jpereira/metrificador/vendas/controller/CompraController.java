package com.jpereira.metrificador.vendas.controller;

import com.jpereira.metrificador.vendas.domain.dto.domain.OrdemCompra;
import com.jpereira.metrificador.vendas.domain.service.OrdemCompraService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompraController {

    private final OrdemCompraService serviceOrdemCompra;

    public CompraController(OrdemCompraService serviceOrdemCompra) {
        this.serviceOrdemCompra = serviceOrdemCompra;
    }

    @GetMapping("/compras")
    public List<OrdemCompra> recuperaCompras() {
        return this.serviceOrdemCompra.getCompras();
    }

    @GetMapping("/maior-compra/{ano}")
    public OrdemCompra maiorCompra(@PathVariable("ano") String ano) {
        return this.serviceOrdemCompra.maiorCompraAno(Integer.parseInt(ano));
    }

    @GetMapping("/clientes-fieis")
    public List<OrdemCompra> clienteFieis() {
        return this.serviceOrdemCompra.getOrdensClientesFieis();
    }

    @GetMapping("/recomendacao/cliente/tipo")
    public String tipoVinho() {
        return this.serviceOrdemCompra.getTipoDeVinhoMaisVendido();
    }


}
