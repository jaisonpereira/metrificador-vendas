package com.jpereira.metrificador.vendas.domain.resource;

import com.jpereira.metrificador.vendas.domain.dto.api.requests.compras.OrdemCompraDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class OrdemCompraResource {
    private final RestTemplate restTemplate;

    @Value("${app.rest.url.ordens}")
    private String url;

    public OrdemCompraResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<OrdemCompraDto> fetch() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(this.url);
        OrdemCompraDto[] ordens = restTemplate.getForObject(builder.toUriString(), OrdemCompraDto[].class);
        return Arrays.asList(ordens);
    }



}
