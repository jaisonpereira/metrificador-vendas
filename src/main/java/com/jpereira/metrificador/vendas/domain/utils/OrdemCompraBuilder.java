package com.jpereira.metrificador.vendas.domain.utils;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.CompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.OrdemCompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos.ProdutoDto;
import com.jpereira.metrificador.vendas.domain.dto.domain.Compra;
import com.jpereira.metrificador.vendas.domain.dto.domain.OrdemCompra;
import com.jpereira.metrificador.vendas.domain.dto.domain.Produto;

import java.math.BigDecimal;
import java.util.List;

public class OrdemCompraBuilder {

    private static Produto getProductByCodigo(Long codigo, List<ProdutoDto> produtos) {
        ProdutoDto dto = produtos.stream()
                .filter(product -> product.getCodigo().equals(codigo))
                .findFirst().orElse(null);
        if (dto != null) {
            Produto produto = new Produto();
            produto.setCodigo(codigo);
            produto.setAnoCompra(dto.getAnoCompra());
            produto.setPreco(dto.getPreco());
            produto.setSafra(dto.getSafra());
            produto.setTipoVinho(dto.getTipoVinho());
            return produto;
        }
        return null;
    }

    public  static OrdemCompra build(OrdemCompraDto dto, List<ProdutoDto> produtos) {
        OrdemCompra ordemCompra = new OrdemCompra();
        ordemCompra.setCpf(dto.getCpf());
        ordemCompra.setNome(dto.getNome());
        ordemCompra.setValorTotalComprasCliente(new BigDecimal("0"));
        for (CompraDto compraDto : dto.getCompras()) {
            Compra compra = new Compra();
            compra.setCodigo(Long.parseLong(compraDto.getCodigo()));
            compra.setProduto(getProductByCodigo(compra.getCodigo(), produtos));
            compra.setQuantidade(compraDto.getQuantidade());
            compra.setValorTotal(compra.getProduto().getPreco().multiply(new BigDecimal(compra.getQuantidade().toString())));
            ordemCompra.setValorTotalComprasCliente(ordemCompra.getValorTotalComprasCliente().add(compra.getValorTotal()));
            ordemCompra.getCompras().add(compra);
        }

        return ordemCompra;
    }

}
