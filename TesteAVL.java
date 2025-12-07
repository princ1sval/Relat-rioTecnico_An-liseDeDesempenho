import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class TesteAVL {

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

    // ============================================================
    // MEDICAO DA INSERCAO NA AVL
    // ============================================================
    public static double medirInsercao(int[] dados) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {
            ArvoreAVL avl = new ArvoreAVL();

            long inicio = System.nanoTime();
            for (int v : dados) avl.inserir(v);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    // ============================================================
    // MEDICAO DA BUSCA NA AVL
    // ============================================================
    public static double medirBusca(int[] dados, int valor) {
        long soma = 0;

        for (int repeticao = 0; repeticao < 5; repeticao++) {

            ArvoreAVL avl = new ArvoreAVL();
            for (int v : dados) avl.inserir(v);

            long inicio = System.nanoTime();
            avl.buscar(valor);
            long fim = System.nanoTime();

            soma += (fim - inicio);
        }

        return (soma / 5.0) / 1_000_000.0;
    }

    // ============================================================
    // TESTE DE UM CENARIO (TAMANHO + TIPO)
    // ============================================================
    public static void testarCenario(int tamanho, String tipo) throws IOException {
        int[] dados;

        if (tipo.equals("ORDENADO")) dados = gerarOrdenado(tamanho);
        else if (tipo.equals("INVERSO")) dados = gerarInverso(tamanho);
        else dados = gerarAleatorio(tamanho);

        escrever("\n============================================");
        escrever("\n   ARVORE AVL - " + tamanho + " elementos - " + tipo);
        escrever("\n============================================\n");

        // INSERCAO
        double tempoInsercao = medirInsercao(dados);
        escrever("Insercao (media): " + tempoInsercao + " ms\n");

        // BUSCAS
        int primeiro = dados[0];
        int ultimo = dados[dados.length - 1];
        int meio = dados[dados.length / 2];

        Random r = new Random();
        int ale1 = dados[r.nextInt(dados.length)];
        int ale2 = dados[r.nextInt(dados.length)];
        int ale3 = dados[r.nextInt(dados.length)];
        int inexistente = -1;

        escrever("Busca:");
        escrever("  primeiro: " + medirBusca(dados, primeiro) + " ms");
        escrever("  ultimo: " + medirBusca(dados, ultimo) + " ms");
        escrever("  meio: " + medirBusca(dados, meio) + " ms");
        escrever("  aleatorio1: " + medirBusca(dados, ale1) + " ms");
        escrever("  aleatorio2: " + medirBusca(dados, ale2) + " ms");
        escrever("  aleatorio3: " + medirBusca(dados, ale3) + " ms");
        escrever("  inexsstente: " + medirBusca(dados, inexistente) + " ms\n");

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
            arquivo = new FileWriter("resultados_avl.txt");

            int[] tamanhos = {100, 1000, 10000};
            String[] tipos = {"ORDENADO", "INVERSO", "ALEATORIO"};

            for (int t : tamanhos) {
                for (String tipo : tipos) {
                    testarCenario(t, tipo);
                }
            }

            arquivo.close();
            System.out.println("\nArquivo 'resultados_avl.txt' gerado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
