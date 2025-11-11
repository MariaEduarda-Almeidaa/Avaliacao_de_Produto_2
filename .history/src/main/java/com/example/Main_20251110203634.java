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

            // LEIA O ARQUIVO DUAS VEZES:
            // 1. Grafo DIRECIONADO para Caminho Mínimo (CM)
            Grafo g_CM = LeitorGrafo.lerArquivo(caminho, true); 
            // 2. Grafo NÃO-DIRECIONADO para Árvore Geradora Mínima (AGM)
            Grafo g_AGM = LeitorGrafo.lerArquivo(caminho, false);

            int n = g_CM.getNumeroVertices();
            int m = g_CM.getTodasArestas().size();

            // Dijkstra
            inicio = System.nanoTime();
double[] dist = Dijkstra.executar(g_CM, 1);
fim = System.nanoTime();
            double cmTempo = (fim - inicio) / 1e9;
            double cmCusto = Dijkstra.custoTotal(dist);

            // Kruskal
            inicio = System.nanoTime();
            double kCusto = Kruskal.executar(g_AGM);
            fim = System.nanoTime();
            double kTempo = (fim - inicio) / 1e9;

            // Prim
            inicio = System.nanoTime();
            double pCusto = Prim.executar(g_AGM);
            fim = System.nanoTime();
            double pTempo = (fim - inicio) / 1e9;

            csv.adicionarLinha(n, m, cmCusto, cmTempo, kCusto, kTempo, pCusto, pTempo);

            System.out.printf("CM: custo=%.2f tempo=%.3fs\n", cmCusto, cmTempo);
            System.out.printf("Kruskal: custo=%.2f tempo=%.3fs\n", kCusto, kTempo);
            System.out.printf("Prim: custo=%.2f tempo=%.3fs\n", pCusto, pTempo);
        }

        csv.salvar();
        System.out.println("\nTabela salva em resultados.csv ✅");
    }
}
