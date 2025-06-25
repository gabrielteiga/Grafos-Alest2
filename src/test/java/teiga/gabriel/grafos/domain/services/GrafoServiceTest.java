package teiga.gabriel.grafos.domain.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import teiga.gabriel.grafos.app.dto.ApiResponse;
import teiga.gabriel.grafos.domain.entities.grafo.Grafo;
import teiga.gabriel.grafos.domain.entities.vertice.Vertice;
import teiga.gabriel.grafos.domain.entities.vertice.Cor;
import teiga.gabriel.grafos.domain.entities.aresta.Aresta;
import teiga.gabriel.grafos.domain.entities.grafo.GrafoRepository;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GrafoServiceTest {

    @Mock
    private GrafoRepository repository;

    @InjectMocks
    private GrafoService grafoService;

    @Test
    void executarCaso_deveRetornarApiResponseEChamarRepository() throws IOException {
        // Instanciando stub
        Grafo grafoStub = new Grafo("stub");
        Vertice ouro = new Vertice("ouro");
        Vertice h = new Vertice("hidrogenio");
        h.adicionaAdjacente(ouro, BigInteger.valueOf(3));
        grafoStub.getGrafo().put("hidrogenio", h);
        grafoStub.getGrafo().put("ouro", ouro);

        // Stub com o mesmo argumento que usaremos abaixo
        when(repository.carregaGrafo("exemplo")).thenReturn(grafoStub);

        // Executa o caso com o stub do grafo criado manualmente
        ApiResponse resp = grafoService.executarCaso("exemplo");

        // Verifica o resultado
        assertEquals(BigInteger.valueOf(3), resp.result());
        assertTrue(resp.timeToExecute() >= 0);

        verify(repository, times(1)).carregaGrafo("exemplo");
    }
}
