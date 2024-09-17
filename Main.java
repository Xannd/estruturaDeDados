import java.io.*;
import java.util.LinkedList;


public class Main {
    private static TabelaHash tabelaHash = new TabelaHash();

    public static void main(String[] args) {
        try {
            lerArquivoTexto("input/texto.txt");

            gerarIndiceRemissivo("input/palavrasChave.txt");

            gerarArquivoSaida("output/indiceRemissivo.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void lerArquivoTexto(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int numeroLinha = 1;

        while ((linha = leitor.readLine()) != null) {
            String[] palavras =  linha.split("[^a-zA-Z0-9'-]+");  // Dividir por espaços e pontuação
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

    private static void gerarArquivoSaida(String caminhoArquivoSaida) throws IOException {
        BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoSaida));

        for (char letra = 'a'; letra <= 'z'; letra++) {
            LinkedList<Palavra> lista = tabelaHash.getListaPorLetra(letra);
            for (Palavra palavra : lista) {
                escritor.write(palavra.toString());
                escritor.newLine();
            }
        }
        escritor.close();
    }
}