import java.io.*;
import java.util.LinkedList;

public class Main {
    private static TabelaHash tabelaHash = new TabelaHash();

    public static void main(String[] args) {
        try {
            lerArquivoTexto("input/texto.txt");

            gerarIndiceRemissivo("input/palavrasChave.txt");

            gerarArquivoSaida("output/indiceRemissivo.txt", "input/palavrasChave.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lerArquivoTexto(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int numeroLinha = 1;

        while ((linha = leitor.readLine()) != null) {
            String[] palavras = linha.split("[^a-zA-Z0-9'-]+");  // Dividir por espaços e pontuação
            for (String palavra : palavras) {
                if (!palavra.isEmpty()) {
                    tabelaHash.adicionarPalavra(palavra.toLowerCase(), numeroLinha);
                }
            }
            numeroLinha++;
        }
        leitor.close();
    }

    private static void gerarIndiceRemissivo(String caminhoArquivoPalavrasChave) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoPalavrasChave));
        String linhaPalavraChave;

        while ((linhaPalavraChave = leitor.readLine()) != null) {
            String[] palavrasChave = linhaPalavraChave.split("[,\\s]+"); // Separar por vírgulas ou espaços
            for (String palavraChave : palavrasChave) {
                Palavra palavra = tabelaHash.buscarPalavra(palavraChave.toLowerCase());
                if (palavra != null) {
                    System.out.println(palavra);
                } else {
                    System.out.println(palavraChave + ": não encontrada");
                }
            }
        }
        leitor.close();
    }

    private static void gerarArquivoSaida(String caminhoArquivoSaida, String caminhoArquivoPalavrasChave) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoSaida));
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoPalavrasChave));
        String linhaPalavraChave;

        while ((linhaPalavraChave = leitor.readLine()) != null) {
            String[] palavrasChave = linhaPalavraChave.split("[,\\s]+");
            for (String palavraChave : palavrasChave) {
                Palavra palavra = tabelaHash.buscarPalavra(palavraChave.toLowerCase());
                if (palavra != null) {
                    escritor.write(palavra.toString());
                    escritor.newLine();
                } else {
                    escritor.write("Palavra " + palavraChave.toLowerCase() + " não foi encontrada.");
                    escritor.newLine();
                }
            }
        }
        escritor.close();
    }

    // Classe Palavra
    static class Palavra {
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

    // Classe TabelaHash
    static class TabelaHash {
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
}
