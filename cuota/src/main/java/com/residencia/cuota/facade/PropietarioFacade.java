package com.residencia.cuota.facade;

import com.residencia.cuota.model.Propietario;
import jakarta.ws.rs.core.UriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class PropietarioFacade {

    @Value("${getPropietario.url}")
    private String getPropietarioUrl;

    private final RestTemplate restTemplate;

    public Propietario getPropietario(String id) {

        try {
            String url = String.format(getPropietarioUrl, id);
            log.info("Getting product with ID {}. Request to {}", id, url);
            return restTemplate.getForObject(url, Propietario.class);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Propietario with ID {}", e.getStatusCode(), id);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Propietario with ID {}", e.getStatusCode(), id);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Propietario with ID {}", e.getMessage(), id);
            return null;
        }
    }
}
