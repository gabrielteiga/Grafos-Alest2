/** @author Gabriel Teiga
 *  Algoritmo que verifica, a partir de um grafo direcionado, quantos hidrogenios sao necessarios para que seja criado um ouro.
 *
 *  algoritmo(){
 *      Para cada Vertice u adjacente ao Vertice Hidrogenio{
 *          se u.cor == BRANCO{
 *              pilha.empilha(visita(u));
 *          }
 *      }
 *      enquanto pilha não estiver vazia{
 *          resultado += pilha.desempilha();
 *      }
 *      return resultado;
 *  }
 *
 *  visita(Vertice v){
 *      v.emAnalise();
 *      resultado = 0;
 *
 *      para cada Vertice u adjacente ao Vertice v{
 *          se u.cor == BRANCO{
 *               resultado = resultado + (visita(u) * aresta.peso);
 *          } se u.cor == VERMELHO{
 *              resultado = resultado + (u.peso * aresta.peso);
 *          }
 *      }
 *      v.foiVisitado();
 *      se v.nomeElemento.equals("ouro"){
 *          v.peso = 1;
 *      } else{
 *          v.peso = resultado;
 *      }
 *      return resultado;
 *   }
 *
 *   Por padrão, cada vértice tem seu peso inicializado com a cor Branca, ele será modificado após ser visitado.
 * */

package teiga.gabriel.grafo;

import teiga.gabriel.vertice.Vertice;
import teiga.gabriel.aresta.Aresta;
import teiga.gabriel.arquivo.leitor.Leitor;
import teiga.gabriel.vertice.Cor;

import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Grafo {

    public static final String CASO = "casoj40";
    private Map<String, Vertice> grafo;

    public Grafo(){
        this.grafo = new HashMap<>();
    }

    public Map<String, Vertice> getGrafo() {
        return grafo;
    }

    public void carregaGrafo() throws IOException {
        Leitor leitor = new Leitor();
        String path = "src/main/resources/casos/" + CASO + ".txt";
        leitor.carregaGrafoPorArquivo(path, this);
    }

    public BigInteger algoritmo(){
        BigInteger numHidrogenios = BigInteger.ZERO;
        for (Aresta a : grafo.get("hidrogenio").getAdjacentes()){
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
}

/** GABARITO
 * casoj40.txt  : 4307147029
 * casoj80.txt  : 1837259296514
 * casoj120.txt : 41319177130862
 * casoj180.txt : 730891056116294701
 * casoj240.txt : 2176101481583381992858700875
 * casoj280.txt : 496528762406562461415574517585
 * casoj320.txt : 11126892164228586232591528972
 * casoj360.txt : 110166908492776937745352185216088225418
 * */
