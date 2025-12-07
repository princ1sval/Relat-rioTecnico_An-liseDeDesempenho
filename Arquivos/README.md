📘 README – Analise de Estruturas de Dados em Java

Este repositório contém a implementação e os testes de desempenho das principais estruturas de dados estudadas na disciplina: Vetor, Árvore Binária de Busca (ABB) e Árvore AVL.
O projeto também inclui algoritmos de ordenação utilizados durante os testes.

⚙️ Como Compilar e Executar o Projeto
✔️ 1. Compilação via Terminal

Abra o terminal dentro da pasta do projeto.

Compile todos os arquivos .java utilizando o comando:

javac *.java


Isso gera os arquivos .class necessários para execução.

✔️ 2. Execução dos Testes

Após compilar, execute o teste desejado:

Testes do Vetor (inserção, busca e ordenação)
java TesteVetor

Testes da Árvore Binária de Busca (ABB)
java TesteABB

Testes da Árvore AVL (balanceada)
java TesteAVL


Cada teste gera um arquivo .txt contendo os resultados coletados durante a execução, incluindo tempos médios de inserção e busca em diferentes cenários (ordenado, inverso e aleatório).

✔️ 3. Execução pelo jGRASP

O projeto também pode ser executado diretamente no jGRASP:

Abra o jGRASP.

Vá em File > Open e selecione o arquivo de teste desejado:

TesteVetor.java

TesteABB.java

TesteAVL.java

Clique em Compile (ícone do martelo).

Após a mensagem "operation completed", clique em Run (ícone do boneco vermelho).

O jGRASP exibirá a saída no console e criará o arquivo de resultados.

🗂 Estrutura de Arquivos do Projeto

A seguir está a estrutura do projeto e a função de cada arquivo:

/ (pasta raiz)
│
├── Vetor.java
│     • Implementação da estrutura de vetor.
│     • Contém métodos de inserção, busca sequencial e busca binária.
│
├── TesteVetor.java
│     • Responsável por executar testes de:
│         - Inserção
│         - Busca sequencial
│         - Busca binária
│         - Ordenação (BubbleSort e MergeSort)
│
├── ArvoreBinaria.java
│     • Implementação da Árvore Binária de Busca (ABB).
│     • Métodos de inserção e busca.
│
├── TesteABB.java
│     • Executa testes de desempenho da ABB em três cenários:
│         - Ordem crescente
│         - Ordem decrescente
│         - Ordem aleatória
│
├── ArvoreAVL.java
│     • Implementação da Árvore AVL com rotações:
│         - Rotação simples à direita
│         - Rotação simples à esquerda
│         - Rotações duplas
│     • Mantém a árvore sempre balanceada.
│
├── TesteAVL.java
│     • Executa testes de inserção e busca na AVL.
│     • Compara desempenho com ABB.
│
├── BubbleSort.java
│     • Algoritmo de ordenação simples (O(n²)).
│     • Usado nos testes com vetores.
│
└── MergeSort.java
      • Algoritmo eficiente de divisão e conquista (O(n log n)).
      • Usado como comparação no vetor.
