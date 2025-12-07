public class BubbleSort {

    public static void ordenar(int[] vetor, int tamanho) {
        boolean trocou;

        for (int i = 0; i < tamanho - 1; i++) {
            trocou = false;

            for (int j = 0; j < tamanho - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                    trocou = true;
                }
            }

            if (!trocou) {
                break;
            }
        }
    }
}
