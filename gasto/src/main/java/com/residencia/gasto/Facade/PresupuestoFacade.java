package com.residencia.gasto.Facade;
import com.residencia.gasto.model.Presupuesto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class PresupuestoFacade {
    @Value("${getPresupuesto.url}")
    private String getPresupuestoUrl;


    private final RestTemplate restTemplate;

    public Presupuesto[] getPresupuesto(String anio) {

        try {
            String url = String.format(getPresupuestoUrl, anio);
            log.info("Getting presupuesto with Año {}. Request to {}", anio, url);
            ResponseEntity<Presupuesto[]> response= restTemplate.getForEntity(url, Presupuesto[].class);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error("Client Error: {}, Presupuesto with ID {}", e.getStatusCode(), anio);
            return null;
        } catch (HttpServerErrorException e) {
            log.error("Server Error: {}, Presupuesto with ID {}", e.getStatusCode(), anio);
            return null;
        } catch (Exception e) {
            log.error("Error: {}, Presupuesto with ID {}", e.getMessage(), anio);
            return null;
        }
    }
}
