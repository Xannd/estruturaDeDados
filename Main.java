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
            String[] palavras = linha.split("[^a-zA-Z0-9'-]+");  // Mantém palavras com letras, hífens e apóstrofos juntas
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


// public class Main {
//     private static TabelaHash tabelaHash = new TabelaHash();
//     private static HashSet<String> palavrasChaveSet = new HashSet<>();  // Conjunto para armazenar palavras-chave

//     public static void main(String[] args) {
//         try {
//             // Ler o arquivo de texto (na pasta "arquivos")
//             lerArquivoTexto("arquivos/texto.txt");

//             // Carregar as palavras-chave do arquivo (na pasta "arquivos")
//             carregarPalavrasChave("arquivos/palavras-chave.txt");

//             // Gerar o arquivo de saída (na pasta "indice") apenas com as palavras-chave
//             gerarArquivoSaida("indice/indice-remissivo.txt");

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     // Método para ler o arquivo de texto e processar as palavras
//     private static void lerArquivoTexto(String caminhoArquivo) throws IOException {
//         BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
//         String linha;
//         int numeroLinha = 1;

//         while ((linha = leitor.readLine()) != null) {
//             String[] palavras = linha.split("[^a-zA-Z0-9'-]+");  // Mantém palavras com letras, hífens e apóstrofos juntas
//             for (String palavra : palavras) {
//                 if (!palavra.isEmpty()) {
//                     tabelaHash.adicionarPalavra(palavra.toLowerCase(), numeroLinha);
//                 }
//             }
//             numeroLinha++;
//         }
//         leitor.close();
//     }

//     // Método para carregar as palavras-chave do arquivo para um conjunto
//     private static void carregarPalavrasChave(String caminhoArquivoPalavrasChave) throws IOException {
//         BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivoPalavrasChave));
//         String linhaPalavraChave;

//         while ((linhaPalavraChave = leitor.readLine()) != null) {
//             String[] palavrasChave = linhaPalavraChave.split("[,\\s]+"); // Separar por vírgulas ou espaços
//             for (String palavraChave : palavrasChave) {
//                 palavrasChaveSet.add(palavraChave.toLowerCase());  // Armazenar as palavras-chave em um conjunto
//             }
//         }
//         leitor.close();
//     }

//     // Método para gerar o arquivo de saída apenas com as palavras-chave
//     private static void gerarArquivoSaida(String caminhoArquivoSaida) throws IOException {
//         BufferedWriter escritor = new BufferedWriter(new FileWriter(caminhoArquivoSaida));

//         for (String palavraChave : palavrasChaveSet) {
//             Palavra palavra = tabelaHash.buscarPalavra(palavraChave);
//             if (palavra != null) {
//                 escritor.write(palavra.toString());
//                 escritor.newLine();
//             } else {
//                 escritor.write(palavraChave + ": não encontrada");
//                 escritor.newLine();
//             }
//         }
//         escritor.close();
//     }
// }