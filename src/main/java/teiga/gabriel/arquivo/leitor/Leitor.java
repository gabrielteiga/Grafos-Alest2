package teiga.gabriel.arquivo.leitor;

import teiga.gabriel.vertice.Vertice;
import teiga.gabriel.grafo.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class Leitor {
    public void carregaGrafoPorArquivo(String path, Grafo grafo) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));

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

                    if (grafo.getGrafo().containsKey(origem) && grafo.getGrafo().containsKey(destino)){
                        grafo.getGrafo().get(origem).adicionaAdjacente(grafo.getGrafo().get(destino), custo);
                    } else if (!grafo.getGrafo().containsKey(origem) && grafo.getGrafo().containsKey(destino)){
                        Vertice v = new Vertice(origem);
                        v.adicionaAdjacente(grafo.getGrafo().get(destino), custo);
                        grafo.getGrafo().put(origem, v);
                    } else if (grafo.getGrafo().containsKey(origem) && !grafo.getGrafo().containsKey(destino)){
                        Vertice v = new Vertice(destino);
                        grafo.getGrafo().get(origem).adicionaAdjacente(v, custo);
                        grafo.getGrafo().put(destino, v);
                    } else{
                        Vertice v1 = new Vertice(origem);
                        Vertice v2 = new Vertice(destino);
                        v1.adicionaAdjacente(v2, custo);
                        grafo.getGrafo().put(origem, v1);
                        grafo.getGrafo().put(destino, v2);
                    }
                }
            }
        }

        br.close();
    }


}
