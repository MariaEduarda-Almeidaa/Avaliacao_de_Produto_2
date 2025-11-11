package com.example.grafos;

import java.util.*;

public class Grafo {
    private final int n;
    private final List<List<Aresta>> adj;
    // Adicionei um contador de arestas para facilitar
    private int m = 0;

    public Grafo(int n) {
        this.n = n;
        this.adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
    }

    /**
     * Adiciona uma aresta DIRIGIDA (mão-única) de U para V.
     * Usado para o Dijkstra.
     */
    public void adicionarArestaDirigida(int u, int v, double peso) {
        adj.get(u).add(new Aresta(u, v, peso));
        this.m++; // Incrementa o contador de arestas
    }

    /**
     * Adiciona uma aresta NÃO-DIRIGIDA (mão-dupla) entre U e V.
     * Usado para Kruskal e Prim.
     */
    public void adicionarArestaNaoDirigida(int u, int v, double peso) {
        adj.get(u).add(new Aresta(u, v, peso));
        adj.get(v).add(new Aresta(v, u, peso));
        // Note: Kruskal/Prim lidam com duplicatas, mas para contar 'm' corretamente
        // seria melhor ter uma lógica diferente. Vamos focar no 'm' do grafo original.
    }
    
    // Método para Kruskal. Note que ele pega arestas duplicadas (v,u)
    // O algoritmo de Kruskal ignora isso (find/union), então está OK.
    public List<Aresta> getTodasArestas() {
        List<Aresta> todas = new ArrayList<>();
        for (List<Aresta> lista : adj) {
            todas.addAll(lista);
        }
        return todas;
    }

    public int getNumeroVertices() { return n; }
    public int getNumeroArestas() { return m; }
    public List<List<Aresta>> getAdjacencias() { return adj; }
}
