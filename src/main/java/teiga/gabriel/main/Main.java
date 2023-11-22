package teiga.gabriel.main;

import teiga.gabriel.arquivo.escrita.Escritor;
import teiga.gabriel.grafo.Grafo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<BigInteger> resultadosTotais = new ArrayList<>();

        for (int i=0 ; i < 10; i ++) {
            Grafo grafo = new Grafo();
            grafo.carregaGrafo();

            long startTime = System.nanoTime();
            BigInteger resultado = grafo.algoritmo();
            long endTime = System.nanoTime();
            long tempoTotal = endTime - startTime;

            Escritor escritor = new Escritor("src/main/resources/saidas/saida-" + Grafo.CASO + ".txt", Grafo.CASO);
            escritor.escreveNoArquivo(resultado, tempoTotal);
            resultadosTotais.add(BigInteger.valueOf(tempoTotal));
        }

        BigInteger soma = BigInteger.ZERO;
        for (BigInteger num : resultadosTotais){
            soma = soma.add(num);
        }

        BigInteger media = soma.divide(BigInteger.valueOf(resultadosTotais.size()));
        System.out.println(media);
    }
}
