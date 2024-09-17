import java.io.*;
import java.util.LinkedList;

class Palavra {
    String palavra;
    LinkedList<Integer> ocorrencias;

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = new LinkedList<>();
    }

    public void adicionarOcorrencia(int linha) {
        ocorrencias.add(linha);
    }

    @Override
    public String toString() {
        return palavra + ": " + ocorrencias.toString();
    }
}