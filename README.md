## Estrutura do projeto

```graphql
IndiceRemissivo/
│
│── Main.java              # Classe principal que executa o programa
│── Palavra.java           # Classe que representa a palavra e suas ocorrências
│── TabelaHash.java        # Implementação da tabela hash
│
├── input/
│   ├── texto.txt                    # Arquivo de entrada com o texto de procura
│   └── palavrasChave.txt           # Arquivo de entrada com as palavras-chave para fazer a pesquisa
│
└── output/
    └── indiceRemissivo.txt          # Arquivo gerado com o índice remissivo de todas as palavras
```

## Descrição dos Arquivos

### 1. **Main.java**
Este arquivo contém a classe principal que coordena a execução do programa. Ele faz as seguintes tarefas:
- **Ler os arquivos de entrada** (`texto.txt` e `palavrasChave.txt`).
- **Processar o texto**, armazenando as palavras em uma tabela hash.
- **Gerar o índice remissivo**, que consiste nas palavras-chave e suas respectivas ocorrências no texto.
- **Escrever o índice remissivo em um arquivo de saída** (`indiceRemissivo.txt`).

Funções principais:
- **lerArquivoTexto**: Processa o texto, dividindo as linhas em palavras, removendo a pontuação e inserindo as palavras na tabela hash.
- **gerarIndiceRemissivo**: Gera o índice remissivo buscando as palavras-chave na tabela hash e retornando suas ocorrências.
- **gerarArquivoSaida**: Gera o arquivo indiceRemissivo.txt com os indices das palavras-chave e salva na pasta output.

---

### 2. **Palavra.java**
Esta classe representa uma palavra e a lista de ocorrências onde ela aparece no texto. Cada palavra tem dois atributos:
- **`palavra`**: A palavra em si.
- **`ocorrencias`**: Uma lista encadeada que contém as linhas em que a palavra aparece.

Funções principais:
- **adicionarOcorrencia**: Retorna a lista de ocorrências da palavra.
- **toString**: Transforma o retorno da palavra em string.

---

### 3. **TabelaHash.java**
Implementa a tabela hash, que armazena as palavras do texto de acordo com sua primeira letra. Cada chave da tabela hash corresponde a uma letra, e o valor associado a essa chave é uma lista encadeada de objetos `Palavra`.

Funções principais:
- **adicionarPalavra**: Insere uma palavra na tabela hash. Se a palavra já existir, apenas adiciona a linha; caso contrário, cria uma nova entrada.
- **buscarPalavra**: Busca uma palavra na tabela hash com base em sua chave (primeira letra) e retorna o objeto `Palavra` correspondente, se existir.

---

## Como Executar o Projeto

1. **Compilar o arquivo Main.java (Logo após criará alguns arquivos.class)**:
   ```bash
   javac Main.java

2. **Execute o arquivo Main que foi compilado**:
   ```bash
   java Main
