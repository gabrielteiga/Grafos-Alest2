package teiga.gabriel.grafos.app.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.CsvSources;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.boot.test.web.client.TestRestTemplate;
import teiga.gabriel.grafos.app.dto.ApiRequest;
import teiga.gabriel.grafos.app.dto.ApiResponse;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerRealIntegrationTest {

    @LocalServerPort
    int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @ParameterizedTest
    @CsvSource({
            "casoj40, 4307147029",
            "casoj80, 1837259296514",
            "casoj120, 41319177130862",
            "casoj180, 730891056116294701",
            "casoj240, 2176101481583381992858700875",
            "casoj280, 496528762406562461415574517585",
    })
    void quandoPost_RetornaResultadoReal(
            String casoNome,
            String hidrogeniosEsperados
    ) {
        // montando URL completa
        String url = "http://localhost:" + port + "/api/graph";
        BigInteger resultadoEsperado = new BigInteger(hidrogeniosEsperados);

        // montando request
        ApiRequest request = new ApiRequest(casoNome);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ApiRequest> http = new HttpEntity<>(request, headers);

        // POST no endpoint
        ResponseEntity<ApiResponse> response = restTemplate
                .exchange(url, HttpMethod.POST, http, ApiResponse.class);

        // validações
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        ApiResponse body = response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.result()).isEqualTo(resultadoEsperado);

        // o tempo deve ser um número positivo
        assertThat(body.timeToExecute()).isGreaterThanOrEqualTo(0L);
    }
}
