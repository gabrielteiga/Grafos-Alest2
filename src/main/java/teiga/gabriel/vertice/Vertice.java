package teiga.gabriel.vertice;

import teiga.gabriel.aresta.Aresta;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Vertice {
    private String nomeElemento;
    private Cor cor;
    private List<Aresta> adjacentes;
    private BigInteger peso;

    public Vertice(String nomeElemento) {
        this.nomeElemento = nomeElemento;
        this.cor = Cor.BRANCO;
        this.adjacentes = new ArrayList<>();
    }

    public String getNomeElemento() {
        return nomeElemento;
    }

    public Cor getCor() {
        return cor;
    }

    public List<Aresta> getAdjacentes() {
        return adjacentes;
    }

    public void adicionaAdjacente(Vertice destino, BigInteger peso){
        this.adjacentes.add(new Aresta(destino, peso));
    }

    public void adicionaAdjacente(Aresta aresta){
        this.adjacentes.add(aresta);
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    public BigInteger getPeso() {
        return peso;
    }

     public void setPeso(BigInteger peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "nomeElemento='" + nomeElemento + '\'' +
                ", cor=" + cor +
                ", adjacentes=" + adjacentes +
                '}';
    }
}
