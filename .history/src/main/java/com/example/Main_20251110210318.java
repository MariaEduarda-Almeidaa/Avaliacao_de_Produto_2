package com.example;

// Importa o novo algoritmo
import com.example.algoritmos.EdmondsKarp; 
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

            // 1. Grafo DIRIGIDO (para Dijkstra e Fluxo Máximo)
            Grafo g_CM = LeitorGrafo.lerArquivo(caminho, true);

            // 2. Grafo NÃO-DIRECIONADO (para AGMs)
            Grafo g_AGM = LeitorGrafo.lerArquivo(caminho, false);

            int n = g_CM.getNumeroVertices();
            int m = g_CM.getNumeroArestas();

            // Dijkstra (CM)
            inicio = System.nanoTime();
            double[] dist = Dijkstra.executar(g_CM, 1);
            fim = System.nanoTime();
            double cmTempo = (fim - inicio) / 1e9;
            double cmCusto = Dijkstra.custoTotal(dist);

            // Kruskal (AGM)
            inicio = System.nanoTime();
            double kCusto = Kruskal.executar(g_AGM);
            fim = System.nanoTime();
            double kTempo = (fim - inicio) / 1e9;

            // Prim (AGM)
            inicio = System.nanoTime();
            double pCusto = Prim.executar(g_AGM);
            fim = System.nanoTime();
            double pTempo = (fim - inicio) / 1e9;

            // --- NOVO: Edmonds-Karp (FM) ---
            // Vamos usar fonte=1 e sumidouro=N (o último vértice)
            int s = 1;
            int t = n;
            
            inicio = System.nanoTime();
            // Executa usando o grafo DIRIGIDO (g_CM)
            double fmCusto = EdmondsKarp.executar(g_CM, s, t); 
            fim = System.nanoTime();
            double fmTempo = (fim - inicio) / 1e9;

            // --- Fim do Novo Código ---

            // Adiciona a linha (com os novos dados de FM)
            csv.adicionarLinha(n, m, cmCusto, cmTempo, kCusto, kTempo, pCusto, pTempo, fmCusto, fmTempo);

            System.out.printf("CM: custo=%.2f tempo=%.3fs\n", cmCusto, cmTempo);
            System.out.printf("Kruskal: custo=%.2f tempo=%.3fs\n", kCusto, kTempo);
            System.out.printf("Prim: custo=%.2f tempo=%.3fs\n", pCusto, pTempo);
            // Imprime o resultado do FM
            System.out.printf("FM: custo=%.2f tempo=%.3fs\n", fmCusto, fmTempo);

        }

        csv.salvar();
        System.out.println("\nTabela salva em resultados.csv ✅");

        // Abre o visualizador (que vai se adaptar sozinho)
        System.out.println("Abrindo visualizador...");
        javax.swing.SwingUtilities.invokeLater(() -> {
            new com.example.utils.VisualizadorTabela("resultados.csv");
        });
    }
}