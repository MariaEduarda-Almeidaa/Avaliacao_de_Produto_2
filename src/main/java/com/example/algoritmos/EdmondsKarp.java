package com.example.algoritmos;

import com.example.grafos.Aresta;
import com.example.grafos.Grafo;

import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

    /**
     * Tenta encontrar um caminho da fonte (s) ao sumidouro (t) usando BFS.
     * @param residual Grafo de capacidade residual (matriz)
     * @param s Fonte
     * @param t Sumidouro
     * @param pai Array para armazenar o caminho
     * @return true se um caminho foi encontrado, false caso contrário
     */
    private static boolean bfs(double[][] residual, int s, int t, int[] pai) {
        int n = residual.length - 1;
        boolean[] visitado = new boolean[n + 1];
        Queue<Integer> fila = new LinkedList<>();

        fila.add(s);
        visitado[s] = true;
        pai[s] = -1; // -1 indica o início do caminho

        while (!fila.isEmpty()) {
            int u = fila.poll();
            for (int v = 1; v <= n; v++) {
                // Se v não foi visitado e há capacidade residual
                if (!visitado[v] && residual[u][v] > 0) {
                    fila.add(v);
                    visitado[v] = true;
                    pai[v] = u; // Armazena o "pai" de v no caminho
                }
            }
        }
        // Retorna true se conseguimos chegar ao sumidouro (t)
        return visitado[t];
    }

    /**
     * Executa o algoritmo de Edmonds-Karp.
     * @param g Grafo (dirigido, g_CM)
     * @param s Vértice fonte (início)
     * @param t Vértice sumidouro (fim)
     * @return O fluxo máximo
     */
    public static double executar(Grafo g, int s, int t) {
        int n = g.getNumeroVertices();

        // 1. Criar a matriz de capacidade residual
        // Esta matriz armazena o "quanto ainda pode passar" por cada aresta
        double[][] residual = new double[n + 1][n + 1];
        
        // 2. Preencher a matriz com as capacidades iniciais do grafo
        // Usamos o 'peso' da aresta como 'capacidade'
        for (int u = 1; u <= n; u++) {
            for (Aresta a : g.getAdjacencias().get(u)) {
                residual[u][a.getDestino()] += a.getPeso();
            }
        }

        int[] pai = new int[n + 1]; // Array para armazenar o caminho (usado pelo BFS)
        double fluxoMaximo = 0;

        // 3. Loop principal: Enquanto existir um caminho da fonte ao sumidouro...
        while (bfs(residual, s, t, pai)) {
            // Encontra a capacidade gargalo (o menor fluxo) no caminho
            double fluxoCaminho = Double.POSITIVE_INFINITY;
            for (int v = t; v != s; v = pai[v]) {
                int u = pai[v];
                fluxoCaminho = Math.min(fluxoCaminho, residual[u][v]);
            }

            // Atualiza as capacidades residuais
            for (int v = t; v != s; v = pai[v]) {
                int u = pai[v];
                residual[u][v] -= fluxoCaminho; // Diminui capacidade da aresta direta
                residual[v][u] += fluxoCaminho; // Aumenta capacidade da aresta reversa
            }

            // Adiciona o fluxo do caminho ao fluxo máximo total
            fluxoMaximo += fluxoCaminho;
        }

        return fluxoMaximo;
    }
}