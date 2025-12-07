import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TesteVetor {

    private static FileWriter arquivo;

    // ============================================================
    // METODOS DE GERACAO DE VETORES
    // ============================================================
    public static int[] gerarOrdenado(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) v[i] = i + 1;
        return v;
    }

    public static int[] gerarInverso(int n) {
        int[] v = new int[n];
        for (int i = 0; i < n; i++) v[i] = n - i;
        return v;
    }

    public static int[] gerarAleatorio(int n) {
        int[] v = new int[n];
        Random r = new Random();
        for (int i = 0; i < n; i++) v[i] = r.nextInt(n * 10);
        return v;
    }

    public static int[] copiar(int[] origem) {
        int[] copia = new int[origem.length];
        for (int i = 0; i < origem.length; i++) copia[i] = origem[i];
        return copia;
    }

    // ============================================================
    // MEDICAO DO TEMPO DE INSERCAO
    // ============================================================
    public static double medirInsercao(int[] dados) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            Vetor vetor = new Vetor(dados.length);

            long inicio = System.nanoTime();
            for (int valor : dados) vetor.inserir(valor);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    // ============================================================
    // MEDICAO DAS BUSCAS
    // ============================================================
    public static double medirBuscaSequencial(int[] dados, int valor) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            Vetor vetor = new Vetor(dados.length);
            for (int v : dados) vetor.inserir(v);

            long inicio = System.nanoTime();
            vetor.buscaSequencial(valor);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    public static double medirBuscaBinaria(int[] dados, int valor) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            int[] copia = copiar(dados);

            MergeSort.ordenar(copia, 0, copia.length - 1);

            Vetor vetor = new Vetor(copia.length);
            for (int v : copia) vetor.inserir(v);

            long inicio = System.nanoTime();
            vetor.buscaBinaria(valor);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    // ============================================================
    // MEDICAO DAS ORDENACOES
    // ============================================================
    public static double medirBubble(int[] dados) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            int[] copia = copiar(dados);

            long inicio = System.nanoTime();
            BubbleSort.ordenar(copia, copia.length);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    public static double medirMerge(int[] dados) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            int[] copia = copiar(dados);

            long inicio = System.nanoTime();
            MergeSort.ordenar(copia, 0, copia.length - 1);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    // ============================================================
    // TESTE COMPLETO DE UM CENARIO (TAMANHO + TIPO)
    // ============================================================
    public static void testarCenario(int tamanho, String tipo) throws IOException {
        int[] dados;

        if (tipo.equals("ORDENADO")) dados = gerarOrdenado(tamanho);
        else if (tipo.equals("INVERSO")) dados = gerarInverso(tamanho);
        else dados = gerarAleatorio(tamanho);

        escrever("\n============================================");
        escrever("\n   VETOR - " + tamanho + " elementos - " + tipo);
        escrever("\n============================================\n");

        double tempoInsercao = medirInsercao(dados);
        escrever("Insercao (media): " + tempoInsercao + " ms\n");

        int primeiro = dados[0];
        int ultimo = dados[dados.length - 1];
        int meio = dados[dados.length / 2];

        Random r = new Random();
        int ale1 = dados[r.nextInt(dados.length)];
        int ale2 = dados[r.nextInt(dados.length)];
        int ale3 = dados[r.nextInt(dados.length)];
        int inexistente = -1;

        escrever("Busca Sequencial:");
        escrever("  primeiro: " + medirBuscaSequencial(dados, primeiro) + " ms");
        escrever("  ultimo: " + medirBuscaSequencial(dados, ultimo) + " ms");
        escrever("  meio: " + medirBuscaSequencial(dados, meio) + " ms");
        escrever("  aleatorio1: " + medirBuscaSequencial(dados, ale1) + " ms");
        escrever("  aleatorio2: " + medirBuscaSequencial(dados, ale2) + " ms");
        escrever("  aleatorio3: " + medirBuscaSequencial(dados, ale3) + " ms");
        escrever("  inexistente: " + medirBuscaSequencial(dados, inexistente) + " ms\n");

        escrever("Busca Binaria:");
        escrever("  primeiro: " + medirBuscaBinaria(dados, primeiro) + " ms");
        escrever("  ultimo: " + medirBuscaBinaria(dados, ultimo) + " ms");
        escrever("  meio: " + medirBuscaBinaria(dados, meio) + " ms");
        escrever("  aleatorio1: " + medirBuscaBinaria(dados, ale1) + " ms");
        escrever("  aleatorio2: " + medirBuscaBinaria(dados, ale2) + " ms");
        escrever("  aleatorio3: " + medirBuscaBinaria(dados, ale3) + " ms");
        escrever("  inexistente: " + medirBuscaBinaria(dados, inexistente) + " ms\n");

        escrever("Ordenacao:");
        escrever("  BubbleSort: " + medirBubble(dados) + " ms");
        escrever("  MergeSort: " + medirMerge(dados) + " ms\n");

        escrever("--------------------------------------------\n");
    }

    // ============================================================
    // ESCREVER NO TXT E NO CONSOLE
    // ============================================================
    public static void escrever(String texto) throws IOException {
        System.out.println(texto);
        arquivo.write(texto + "\n");
    }

    // ============================================================
    // MAIN
    // ============================================================
    public static void main(String[] args) {
        try {
            arquivo = new FileWriter("resultados_vetor.txt");

            int[] tamanhos = {100, 1000, 10000};
            String[] tipos = {"ORDENADO", "INVERSO", "ALEATORIO"};

            for (int t : tamanhos) {
                for (String tipo : tipos) {
                    testarCenario(t, tipo);
                }
            }

            arquivo.close();
            System.out.println("\nArquivo 'resultados_vetor.txt' gerado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
