package teiga.gabriel.arquivo.escrita;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.LinkedList;

public class Escritor {
    private String pathOutput;
    private String caso;

    public Escritor(String pathOutput, String caso){
        this.pathOutput = pathOutput;
        this.caso = caso;
    }

    public void escreveNoArquivo(BigInteger conteudo, long tempoExec){
        try {
            FileWriter file = new FileWriter(this.pathOutput, true);
            BufferedWriter output = new BufferedWriter(file);

            output.write(this.caso + " - Resultado: " + conteudo.toString() + " Hidrogênios - Tempo de Execução: " + tempoExec + "ns");
            output.newLine();

            output.close();
            file.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
