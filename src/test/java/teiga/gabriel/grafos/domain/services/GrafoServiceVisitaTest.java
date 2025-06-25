package teiga.gabriel.grafos.domain.services;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.lifecycle.BeforeProperty;
import teiga.gabriel.grafos.domain.entities.vertice.Vertice;
import teiga.gabriel.grafos.domain.entities.vertice.Cor;

import java.lang.reflect.Method;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class GrafoServiceVisitaTest {

    private GrafoService service;
    private Method visitaMethod;

    @BeforeProperty
    void setup() throws Exception {
        service = new GrafoService(null);
        // Obtém o método privado via técnica de Reflection
        visitaMethod = GrafoService.class
                .getDeclaredMethod("visita", Vertice.class);
        visitaMethod.setAccessible(true);
    }

    @Property
    void visita_deveMultiplicarRecursaoCorretaParaVerticeOuro(
            @ForAll @IntRange(min = 1, max = 1000) int pesoInt
    ) throws Exception {
        // instancia o vértice ouro e valida a cor se ele não foi pintado
        // grafo mínimo: X -> ouro (peso 5)
        Vertice ouro = new Vertice("ouro");
        Vertice x    = new Vertice("X");
        x.adicionaAdjacente(ouro, BigInteger.valueOf(pesoInt));

        // chama o método visita
        BigInteger result = (BigInteger) visitaMethod.invoke(service, x);

        // assertions
        // visita é uma função recursiva que vai visitando até o último nodo não visitado em profundidade
        assertEquals(BigInteger.valueOf(pesoInt), result);
        // Depois de visita, X.peso = 5 e cor PRETO (visitado)
        assertEquals(BigInteger.valueOf(pesoInt), x.getPeso());
        assertEquals(Cor.PRETO, x.getCor());
        // E ouro também deve ter sido marcado PRETO e peso=1
        assertEquals(Cor.PRETO, ouro.getCor());
        assertEquals(BigInteger.ONE, ouro.getPeso());
    }
}
