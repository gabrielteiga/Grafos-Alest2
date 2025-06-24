package teiga.gabriel.grafos.domain.services;

import org.springframework.stereotype.Service;
import teiga.gabriel.grafos.app.dto.ApiResponse;
import teiga.gabriel.grafos.domain.entities.aresta.Aresta;
import teiga.gabriel.grafos.domain.entities.grafo.Grafo;
import teiga.gabriel.grafos.domain.entities.grafo.GrafoRepository;
import teiga.gabriel.grafos.domain.entities.vertice.Cor;
import teiga.gabriel.grafos.domain.entities.vertice.Vertice;

import java.io.IOException;
import java.math.BigInteger;

@Service
public class GrafoService {
    private final GrafoRepository grafoRepository;
    public GrafoService(GrafoRepository grafoRepository) {
        this.grafoRepository = grafoRepository;
    }
    public ApiResponse executarCaso(String nomeCaso) throws IOException {
        Grafo grafo = this.carregaGrafo(nomeCaso);

        long startTime = System.nanoTime();
        BigInteger numberElements = this.algoritmo(grafo);
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;

        return new ApiResponse(numberElements, totalTime);
    }

    public BigInteger algoritmo(Grafo grafo){
        BigInteger numHidrogenios = BigInteger.ZERO;
        for (Aresta a : grafo.getGrafo().get("hidrogenio").getAdjacentes()){
            if (a.getVerticeDestino().getCor() == Cor.BRANCO){
                numHidrogenios = numHidrogenios.add((visita(a.getVerticeDestino())).multiply(a.getPeso()));
            }
        }
        return numHidrogenios;
    }

    private BigInteger visita(Vertice u){
        BigInteger resultado = BigInteger.ZERO;
        if(!u.getNomeElemento().equals("ouro")){
            u.setCor(Cor.CINZA);
            for (Aresta a: u.getAdjacentes()) {
                Vertice v = a.getVerticeDestino();
                if (v.getCor() == Cor.BRANCO) {
                    resultado = resultado.add(visita(v).multiply(a.getPeso()));
                } else if(v.getCor() == Cor.PRETO){
                    resultado = resultado.add(v.getPeso().multiply(a.getPeso()));
                }
            }
            u.setPeso(resultado);
        } else{
            u.setPeso(BigInteger.ONE);
        }
        u.setCor(Cor.PRETO);
        return u.getPeso();
    }

    public Grafo carregaGrafo(String nomeCaso) throws IOException {
        Grafo grafo = grafoRepository.carregaGrafo(nomeCaso);
        return grafo;
    }
}
