package com.example.grafos;


import java.util.*;

public class Grafo {
    private final int n;
    private final List<List<Aresta>> adj;

    public Grafo(int n) {
        this.n = n;
        this.adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
    }

    public void adicionarArestaNaoDirigida(int u, int v, double peso) {
    adj.get(u).add(new Aresta(u, v, peso));
    adj.get(v).add(new Aresta(v, u, peso));
}

// Crie este novo método para o Dijkstra
public void adicionarArestaDirigida(int u, int v, double peso) {
    adj.get(u).add(new Aresta(u, v, peso));
}

    public int getNumeroVertices() { return n; }
    public List<List<Aresta>> getAdjacencias() { return adj; }

    public List<Aresta> getTodasArestas() {
        List<Aresta> todas = new ArrayList<>();
        for (List<Aresta> lista : adj) todas.addAll(lista);
        return todas;
    }
}

