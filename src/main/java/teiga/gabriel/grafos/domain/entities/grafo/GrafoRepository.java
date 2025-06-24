package teiga.gabriel.grafos.domain.entities.grafo;

import java.io.IOException;

public interface GrafoRepository {
    Grafo carregaGrafo(String caso) throws IOException;
}
