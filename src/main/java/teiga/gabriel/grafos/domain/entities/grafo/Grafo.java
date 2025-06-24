/** 
 * @author Gabriel Palominos Teiga
 * */

package teiga.gabriel.grafos.domain.entities.grafo;

import teiga.gabriel.grafos.domain.entities.vertice.Vertice;

import java.util.*;

public class Grafo {
    private String caso;
    private Map<String, Vertice> grafo;

    public Grafo(String nomeCaso){
        this.grafo = new HashMap<>();
        this.caso = nomeCaso;
    }

    public Map<String, Vertice> getGrafo() {
        return grafo;
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
