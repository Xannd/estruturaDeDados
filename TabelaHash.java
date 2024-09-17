import java.io.*;
import java.util.LinkedList;

class TabelaHash {
    private final LinkedList<Palavra>[] tabela;
    private final int TAMANHO = 26;

    @SuppressWarnings("unchecked")
    public TabelaHash() {
        tabela = new LinkedList[TAMANHO];
        for (int i = 0; i < TAMANHO; i++) {
            tabela[i] = new LinkedList<>();
        }
    }

    private int hash(String palavra) {
        return Character.toLowerCase(palavra.charAt(0)) - 'a';
    }

    public void adicionarPalavra(String palavra, int linha) {
        int indice = hash(palavra);
        for (Palavra p : tabela[indice]) {
            if (p.palavra.equals(palavra)) {
                p.adicionarOcorrencia(linha);
                return;
            }
        }
        Palavra novaPalavra = new Palavra(palavra);
        novaPalavra.adicionarOcorrencia(linha);
        tabela[indice].add(novaPalavra);
    }

    public Palavra buscarPalavra(String palavra) {
        int indice = hash(palavra);
        for (Palavra p : tabela[indice]) {
            if (p.palavra.equals(palavra)) {
                return p;
            }
        }
        return null;
    }

    public LinkedList<Palavra> getListaPorLetra(char letra) {
        int indice = Character.toLowerCase(letra) - 'a';
        return tabela[indice];
    }
}