package com.jpereira.metrificador.vendas.domain.resource;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.produtos.ProdutoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoResource {

    private final RestTemplate restTemplate;

    @Value("${app.rest.url.produtos}")
    private String url;

    public ProdutoResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ProdutoDto> fetch() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.url);
        ProdutoDto[] products = restTemplate.getForObject(builder.toUriString(), ProdutoDto[].class);
        return Arrays.asList(products);
    }

}
