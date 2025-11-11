package com.example;

import com.example.algoritmos.Dijkstra;
import com.example.algoritmos.Kruskal;
import com.example.algoritmos.Prim;
import com.example.grafos.*;
import com.example.utils.LeitorGrafo;
import com.example.utils.GeradorCSV;

public class Main {
    public static void main(String[] args) throws Exception {
        String[] instancias = {
            "arquivos/USA-road-d.NY.gr",
            "arquivos/USA-road-d.BAY.gr",
            "arquivos/USA-road-d.COL.gr"
        };

        GeradorCSV csv = new GeradorCSV("resultados.csv");

        for (String caminho : instancias) {
            System.out.println("\n==> Processando: " + caminho);
            long inicio, fim;

            // --- LÓGICA CORRIGIDA ---
            
            // 1. Cria um Grafo DIRECIONADO para o Caminho Mínimo (CM)
            // O "true" significa isDirigido = true
            Grafo g_CM = LeitorGrafo.lerArquivo(caminho, true);

            // 2. Cria um Grafo NÃO-DIRECIONADO para a Árvore Geradora Mínima (AGM)
            // O "false" significa isDirigido = false
            Grafo g_AGM = LeitorGrafo.lerArquivo(caminho, false);

            // Pegamos N e M do grafo original (dirigido)
            int n = g_CM.getNumeroVertices();
            int m = g_CM.getNumeroArestas(); // Use o novo método getNumeroArestas()

            // Dijkstra (CM) - Usa o grafo DIRIGIDO
            inicio = System.nanoTime();
            double[] dist = Dijkstra.executar(g_CM, 1);
            fim = System.nanoTime();
            double cmTempo = (fim - inicio) / 1e9;
            double cmCusto = Dijkstra.custoTotal(dist);

            // Kruskal (AGM) - Usa o grafo NÃO-DIRIGIDO
            inicio = System.nanoTime();
            double kCusto = Kruskal.executar(g_AGM);
            fim = System.nanoTime();
            double kTempo = (fim - inicio) / 1e9;

            // Prim (AGM) - Usa o grafo NÃO-DIRIGIDO
            inicio = System.nanoTime();
            double pCusto = Prim.executar(g_AGM);
            fim = System.nanoTime();
            double pTempo = (fim - inicio) / 1e9;

            // --- Fim da Correção ---

            // Adiciona a linha (agora com o 'm' correto)
            csv.adicionarLinha(n, m, cmCusto, cmTempo, kCusto, kTempo, pCusto, pTempo);

            System.out.printf("CM: custo=%.2f tempo=%.3fs\n", cmCusto, cmTempo);
            System.out.printf("Kruskal: custo=%.2f tempo=%.3fs\n", kCusto, kTempo);
            System.out.printf("Prim: custo=%.2f tempo=%.3fs\n", pCusto, pTempo);
        }

        csv.salvar();
        System.out.println("\nTabela salva em resultados.csv ✅");

        // Abre o visualizador
        System.out.println("Abrindo visualizador...");
        javax.swing.SwingUtilities.invokeLater(() -> {
            new com.example.utils.VisualizadorTabela("resultados.csv");
        });
    }
}
