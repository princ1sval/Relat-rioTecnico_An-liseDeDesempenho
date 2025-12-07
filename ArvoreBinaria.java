public class ArvoreBinaria {

    // Classe nó
    private class Node {
        int valor;
        Node esquerda;
        Node direita;

        Node(int valor) {
            this.valor = valor;
        }
    }

    private Node raiz;

    // Inserção publica
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    // Inserção recursiva
    private Node inserirRec(Node atual, int valor) {
        if (atual == null) {
            return new Node(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRec(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRec(atual.direita, valor);
        }
        // se for igual, não insere
        return atual;
    }

    // Busca pública
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    // Busca recursiva
    private boolean buscarRec(Node atual, int valor) {
        if (atual == null) {
            return false;
        }

        if (valor == atual.valor) {
            return true;
        }

        return valor < atual.valor
                ? buscarRec(atual.esquerda, valor)
                : buscarRec(atual.direita, valor);
    }
}
