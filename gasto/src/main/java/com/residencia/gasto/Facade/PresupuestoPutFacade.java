package com.residencia.gasto.Facade;

import com.residencia.gasto.model.request.PresupuestoRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class PresupuestoPutFacade {
    @Value("${putPresupuesto.url}")
    private String putPresupuestoUrl;


    private final RestTemplate restTemplate;

    public void putPresupuesto(String id, PresupuestoRequest presupuestoRequest) {

        try {
            String url = String.format(putPresupuestoUrl, id);
            log.info("Updating presupuesto with ID {}. Request to {}", id, url);
            restTemplate.put(url,presupuestoRequest);
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Presupuesto with ID {}", e.getStatusCode(), id);

        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Presupuesto with ID {}", e.getStatusCode(), id);

        } catch (Exception e) {
            log.error("Error: {}, Presupuesto with ID {}", e.getMessage(), id);

        }
    }
}
