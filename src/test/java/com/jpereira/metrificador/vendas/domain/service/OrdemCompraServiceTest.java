package com.jpereira.metrificador.vendas.domain.service;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.CompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.OrdemCompraDto;
import com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos.ProdutoDto;
import com.jpereira.metrificador.vendas.domain.dto.domain.OrdemCompra;
import com.jpereira.metrificador.vendas.domain.resource.OrdemCompraResource;
import com.jpereira.metrificador.vendas.domain.resource.ProdutoResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdemCompraServiceTest {

    @Mock
    private ProdutoResource resourceProduto;

    @Mock
    private OrdemCompraResource resourceOrdemCompra;

    @InjectMocks
    private OrdemCompraService ordemCompraService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCompras() {
        List<OrdemCompraDto> ordensDto = new ArrayList<>();
        List<ProdutoDto> produtosDto = new ArrayList<>();
        when(resourceOrdemCompra.fetch()).thenReturn(ordensDto);
        when(resourceProduto.fetch()).thenReturn(produtosDto);

        List<OrdemCompra> result = ordemCompraService.getCompras();

        assertNotNull(result);
        verify(resourceOrdemCompra, times(1)).fetch();
        verify(resourceProduto, times(1)).fetch();
    }

    private List<ProdutoDto> getProdutosDto() {
        List<ProdutoDto> produtosDto = new ArrayList<>();
        ProdutoDto dto = new ProdutoDto();
        dto.setAnoCompra(2021);
        dto.setPreco(new BigDecimal("25.00"));
        dto.setCodigo(1L);
        dto.setTipoVinho("Tinto");
        produtosDto.add(dto);
        return produtosDto;
    }


    private List<OrdemCompraDto> getOrdensMock() {
        List<OrdemCompraDto> ordensDto = new ArrayList<>();
        OrdemCompraDto dto = new OrdemCompraDto();
        dto.setNome("Cliente Teste");
        dto.setCpf("12345678900");

        CompraDto compra = new CompraDto();
        compra.setCodigo("1");
        compra.setQuantidade(2L);
        dto.getCompras().add(compra);
        ordensDto.add(dto);
        return ordensDto;
    }


    @Test
    public void testGetOrdensClientesFieis() {


        when(resourceOrdemCompra.fetch()).thenReturn(this.getOrdensMock());
        when(resourceProduto.fetch()).thenReturn(this.getProdutosDto());

        List<OrdemCompra> result = ordemCompraService.getOrdensClientesFieis();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(new BigDecimal("50.00"), result.get(0).getValorTotalComprasCliente());
    }

    @Test
    public void testGetTipoDeVinhoMaisVendido() {
        when(resourceOrdemCompra.fetch()).thenReturn(this.getOrdensMock());
        when(resourceProduto.fetch()).thenReturn(this.getProdutosDto());

        String result = ordemCompraService.getTipoDeVinhoMaisVendido();

        assertEquals("Tinto", result);
    }

    @Test
    public void testMaiorCompraAno() {
        when(resourceOrdemCompra.fetch()).thenReturn(this.getOrdensMock());
        when(resourceProduto.fetch()).thenReturn(this.getProdutosDto());
        OrdemCompra result = ordemCompraService.maiorCompraAno(2021);

        assertNotNull(result);
        assertEquals("Cliente Teste", result.getNome());
        assertEquals("12345678900", result.getCpf());
        assertEquals(new BigDecimal("50.00"), result.getValorTotalComprasCliente());
    }
}
