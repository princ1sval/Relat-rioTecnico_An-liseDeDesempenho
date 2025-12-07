public class Vetor {

    private int[] dados;   
    private int tamanho;   

    public Vetor(int capacidade) {
        dados = new int[capacidade];
        tamanho = 0;
    }

    // Inserção no final
    public boolean inserir(int valor) {
        if (tamanho == dados.length) {
            return false;
        }
        dados[tamanho] = valor;
        tamanho++;
        return true;
    }

    // Inserção no início
    public boolean inserirNoInicio(int valor) {
        if (tamanho == dados.length) {
            return false;
        }

        // desloca todos para a direita
        for (int i = tamanho; i > 0; i--) {
            dados[i] = dados[i - 1];
        }

        dados[0] = valor;
        tamanho++;
        return true;
    }

    // Busca sequencial
    public int buscaSequencial(int valor) {
        for (int i = 0; i < tamanho; i++) {
            if (dados[i] == valor) {
                return i;
            }
        }
        return -1;
    }

    public int get(int indice) {
        if (indice >= 0 && indice < tamanho) {
            return dados[indice];
        }
        throw new IndexOutOfBoundsException("Índice inválido: " + indice);
    }

    public int tamanho() {
        return tamanho;
    }
    //necessária para a busca binária
    public int buscaBinaria(int valor) {
       int inicio = 0;
       int fim = tamanho - 1;

    while (inicio <= fim) {
        int meio = (inicio + fim) / 2;

        if (dados[meio] == valor) {
            return meio; // encontrou
        }

        if (valor < dados[meio]) {
            fim = meio - 1; // procura à esquerda
        } else {
            inicio = meio + 1; // procura à direita
        }
    }

    return -1; // não encontrou
}

    
}
