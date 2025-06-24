package teiga.gabriel.grafos.domain.entities.aresta;

import teiga.gabriel.grafos.domain.entities.vertice.Vertice;

import java.math.BigInteger;

public class Aresta {
    private Vertice verticeDestino;
    private BigInteger peso;

    public Aresta(Vertice v, BigInteger p) {
        this.verticeDestino = v;
        this.peso = p;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public BigInteger getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Aresta{" +
                "verticeDestino=" + verticeDestino +
                ", peso=" + peso +
                '}';
    }
}
