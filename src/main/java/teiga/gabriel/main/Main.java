package teiga.gabriel.main;

import teiga.gabriel.grafo.Grafo;

import java.io.IOException;
import java.math.BigInteger;


public class Main {
    // Colocar o nome do caso (sem a extensão ".txt"), presente no path: /src/main/resources/casos/. Exemplo abaixo...
    public static final String CASO = "casoj360";

    public static void main(String[] args) throws IOException {
            Grafo grafo = new Grafo(CASO);
            grafo.carregaGrafo();

            long startTime = System.nanoTime();
            BigInteger numberElements = grafo.algoritmo();
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;

            System.out.println("Resultado: " + numberElements + " - Tempo de Execução: " + totalTime);
    }
}
