package com.jpereira.metrificador.vendas.domain.service;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.OrdemCompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos.ProdutoDto;
import com.jpereira.metrificador.vendas.domain.dto.domain.Compra;
import com.jpereira.metrificador.vendas.domain.dto.domain.OrdemCompra;
import com.jpereira.metrificador.vendas.domain.resource.OrdemCompraResource;
import com.jpereira.metrificador.vendas.domain.resource.ProdutoResource;
import com.jpereira.metrificador.vendas.domain.utils.OrdemCompraBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrdemCompraService {

    private final ProdutoResource resourceProduto;
    private final OrdemCompraResource resourceOrdemCompra;

    public OrdemCompraService(ProdutoResource resourceProduto, OrdemCompraResource resourceOrdemCompra) {
        this.resourceProduto = resourceProduto;
        this.resourceOrdemCompra = resourceOrdemCompra;
    }

    public List<OrdemCompra> getCompras() {
        return this.getComprasSortByValorTotalCompra();
    }

    public List<OrdemCompra> getOrdensClientesFieis() {
     return this.getCompras().stream()
                .sorted(Comparator.comparing(OrdemCompra::getValorTotalComprasCliente).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    public String getTipoDeVinhoMaisVendido() {
        List<OrdemCompra> ordens = this.getCompras();

        Map<String, Long> tipoVinhoQuantidade = ordens.stream()
                .flatMap(ordem -> ordem.getCompras().stream())
                .collect(Collectors.groupingBy(compra -> compra.getProduto().getTipoVinho(),
                        Collectors.summingLong(Compra::getQuantidade)));

        return  Collections.max(tipoVinhoQuantidade.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public OrdemCompra maiorCompraAno(Integer ano) {
        BigDecimal valorMaior = BigDecimal.ZERO;
        OrdemCompra maxOrdem = null;
        for (OrdemCompra ordemCompra : this.getCompras().stream()
                .filter(cliente -> cliente.getCompras().stream()
                        .anyMatch(compra -> compra.getProduto().getAnoCompra().equals(ano)))
                .collect(Collectors.toList())) {
            for (Compra compra : ordemCompra.getCompras().stream()
                    .filter(compra -> compra.getProduto().getAnoCompra().equals(ano))
                    .collect(Collectors.toList())) {
                int comparacao = compra.getValorTotal().compareTo(valorMaior);
                if (comparacao > 0) {
                    valorMaior = compra.getValorTotal();
                    maxOrdem = new OrdemCompra();
                    maxOrdem.setNome(ordemCompra.getNome());
                    maxOrdem.setCpf(ordemCompra.getCpf());
                    maxOrdem.getCompras().add(compra);
                    maxOrdem.setValorTotalComprasCliente(valorMaior);
                }
            }
        }
        if (maxOrdem == null) {
            log.info("Nenhum ordem de compra encontrado para o ano " + ano);
        }
        return maxOrdem;
    }

    private List<OrdemCompra> getComprasSortByValorTotalCompra() {
        return this.fetchCompras().stream()
                .sorted(Comparator.comparing(ordem -> ordem.getCompras().stream()
                        .map(Compra::getValorTotal)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)))
                .collect(Collectors.toList());
    }

    private List<OrdemCompra> fetchCompras() {
        List<OrdemCompraDto> ordensDto = this.resourceOrdemCompra.fetch();
        List<ProdutoDto> produtosDto = this.resourceProduto.fetch();
        return ordensDto == null ?
                new ArrayList<>() :
                ordensDto.stream()
                        .map(dto -> OrdemCompraBuilder.build(dto, produtosDto))
                        .collect(Collectors.toList());
    }

}
