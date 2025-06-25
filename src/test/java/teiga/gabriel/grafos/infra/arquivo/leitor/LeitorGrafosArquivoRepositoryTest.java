package teiga.gabriel.grafos.infra.arquivo.leitor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import teiga.gabriel.grafos.domain.entities.aresta.Aresta;
import teiga.gabriel.grafos.domain.entities.grafo.Grafo;
import teiga.gabriel.grafos.domain.entities.vertice.Vertice;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LeitorGrafosArquivoRepositoryTest {
    private LeitorGrafosArquivoRepository grafosRepository;

    @BeforeEach
    void setup() {
        this.grafosRepository = new LeitorGrafosArquivoRepository();
    }

    @ParameterizedTest
    @CsvSource({
            "casoj40, hidrogenio, prata",
            "casoj40, hidrogenio, carbono",
            "casoj40, hidrogenio, laziopton",
    })
    void carregaGrafo_ComArquivosDeTesteDentroDeResources(
        String nomeCaso,
        String origem,
        String destino
    ) throws IOException {
        Grafo grafo = grafosRepository.carregaGrafo(nomeCaso);
        Map<String, Vertice> mapa = grafo.getGrafo();
        assertTrue(mapa.containsKey(destino));

        // Verifica se o elemento foi carregado com sucesso e se a aresta foi gerada
        Vertice vOrigem = mapa.get(origem);
        Aresta aresta = findAresta(vOrigem, destino);
        assertTrue(aresta.getPeso().compareTo(BigInteger.ZERO) > 0);
    }

    Aresta findAresta(Vertice vOrigem, String vDestino) {
        return vOrigem.getAdjacentes().stream()
                .filter(x -> x.getVerticeDestino().getNomeElemento().equals(vDestino))
                .findFirst()
                .orElseThrow();
    }
}
