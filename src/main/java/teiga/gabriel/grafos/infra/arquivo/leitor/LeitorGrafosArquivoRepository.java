package teiga.gabriel.grafos.infra.arquivo.leitor;

import org.springframework.stereotype.Repository;
import teiga.gabriel.grafos.domain.entities.grafo.GrafoRepository;
import teiga.gabriel.grafos.domain.entities.vertice.Vertice;
import teiga.gabriel.grafos.domain.entities.grafo.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;

@Repository
public class LeitorGrafosArquivoRepository implements GrafoRepository {
    public LeitorGrafosArquivoRepository(){}

    public Grafo carregaGrafo(String nomeCaso) throws IOException {
        Grafo grafo = new Grafo(nomeCaso);

        String path = "src/main/resources/casos/" + nomeCaso + ".txt";
        BufferedReader br = new BufferedReader(new FileReader(path));
        Map<String, Vertice> mapaAdjacencias = grafo.getGrafo();

        String linha;
        while((linha = br.readLine()) != null){
            String[] transformacao = linha.split(" -> ");
            String[] verticeOrigem = transformacao[0].split(" ");
            String[] verticeDestino = transformacao[1].split(" ");

            for (int i=0; i<verticeOrigem.length; i++){
                if (i % 2 == 0 || verticeOrigem[i].equals("0")){
                    String origem = verticeOrigem[i+1];
                    String destino = verticeDestino[1];
                    BigInteger custo = new BigInteger(verticeOrigem[i]);

                    if (mapaAdjacencias.containsKey(origem) && mapaAdjacencias.containsKey(destino)){
                        mapaAdjacencias.get(origem).adicionaAdjacente(mapaAdjacencias.get(destino), custo);

                    } else if (!mapaAdjacencias.containsKey(origem) && mapaAdjacencias.containsKey(destino)){
                        Vertice v = new Vertice(origem);
                        v.adicionaAdjacente(mapaAdjacencias.get(destino), custo);
                        mapaAdjacencias.put(origem, v);

                    } else if (mapaAdjacencias.containsKey(origem) && !mapaAdjacencias.containsKey(destino)){
                        Vertice v = new Vertice(destino);
                        mapaAdjacencias.get(origem).adicionaAdjacente(v, custo);
                        mapaAdjacencias.put(destino, v);

                    } else{
                        Vertice v1 = new Vertice(origem);
                        Vertice v2 = new Vertice(destino);
                        v1.adicionaAdjacente(v2, custo);
                        mapaAdjacencias.put(origem, v1);
                        mapaAdjacencias.put(destino, v2);
                    }
                }
            }
        }

        br.close();
        return grafo;
    }


}
