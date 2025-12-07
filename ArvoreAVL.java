public class ArvoreAVL {

    // Classe interna do nó
    private class Node {
        int valor;
        int altura;
        Node esquerda;
        Node direita;

        Node(int valor) {
            this.valor = valor;
            this.altura = 1; // altura inicial
        }
    }

    private Node raiz;

    // ------------------------------
    // ALTURA E FATOR DE BALANCEAMENTO
    // ------------------------------

    private int altura(Node n) {
        return n == null ? 0 : n.altura;
    }

    private int fatorBalanceamento(Node n) {
        return n == null ? 0 : altura(n.esquerda) - altura(n.direita);
    }

    private void atualizarAltura(Node n) {
        n.altura = 1 + Math.max(altura(n.esquerda), altura(n.direita));
    }

    // ------------------------------
    // ROTAÇÕES
    // ------------------------------

    // Rotação simples à direita
    private Node rotacaoDireita(Node y) {
        Node x = y.esquerda;
        Node T2 = x.direita;

        x.direita = y;
        y.esquerda = T2;

        atualizarAltura(y);
        atualizarAltura(x);

        return x;
    }

    // Rotação simples à esquerda
    private Node rotacaoEsquerda(Node x) {
        Node y = x.direita;
        Node T2 = y.esquerda;

        y.esquerda = x;
        x.direita = T2;

        atualizarAltura(x);
        atualizarAltura(y);

        return y;
    }

    // INSERÇÃO
    public void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    private Node inserirRec(Node atual, int valor) {
        if (atual == null) {
            return new Node(valor);
        }

        // Inserção normal da árvore binária de busca
        if (valor < atual.valor) {
            atual.esquerda = inserirRec(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRec(atual.direita, valor);
        } else {
            return atual; // valores iguais não são inseridos
        }

        // Atualiza altura
        atualizarAltura(atual);

        // Calcula fator de balanceamento
        int fb = fatorBalanceamento(atual);

        // CASOS DE DESBALANCEAMENTO
    
        // Caso 1: Esquerda-Esquerda (LL)
        if (fb > 1 && valor < atual.esquerda.valor) {
            return rotacaoDireita(atual);
        }

        // Caso 2: Direita-Direita (RR)
        if (fb < -1 && valor > atual.direita.valor) {
            return rotacaoEsquerda(atual);
        }

        // Caso 3: Esquerda-Direita (LR)
        if (fb > 1 && valor > atual.esquerda.valor) {
            atual.esquerda = rotacaoEsquerda(atual.esquerda);
            return rotacaoDireita(atual);
        }

        // Caso 4: Direita-Esquerda (RL)
        if (fb < -1 && valor < atual.direita.valor) {
            atual.direita = rotacaoDireita(atual.direita);
            return rotacaoEsquerda(atual);
        }

        return atual;
    }

    // BUSCA NORMAL (BST)
    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Node atual, int valor) {
        if (atual == null) return false;
        if (valor == atual.valor) return true;
        return valor < atual.valor
                ? buscarRec(atual.esquerda, valor)
                : buscarRec(atual.direita, valor);
    }
}
