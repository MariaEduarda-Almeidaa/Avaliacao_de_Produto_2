package com.example.utils;


import java.io.FileWriter;
import java.io.IOException;

public class GeradorCSV {
    private final String caminho;
    private final StringBuilder sb = new StringBuilder();

    public GeradorCSV(String caminho) {
        this.caminho = caminho;
        sb.append("n,m,CM_custo,CM_tempo,AGM_Kruskal_custo,AGM_Kruskal_tempo,AGM_Prim_custo,AGM_Prim_tempo\n");
    }

    public void adicionarLinha(int n, int m, double cmCusto, double cmTempo,
            double kCusto, double kTempo, double pCusto, double pTempo) {
        sb.append(String.format("%d,%d,%.2f,%.3f,%.2f,%.3f,%.2f,%.3f\n",
                n, m, cmCusto, cmTempo, kCusto, kTempo, pCusto, pTempo));
    }

    public void salvar() throws IOException {
        try (FileWriter fw = new FileWriter(caminho)) {
            fw.write(sb.toString());
        }
    }
}
