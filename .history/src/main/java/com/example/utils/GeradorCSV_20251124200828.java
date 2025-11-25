package com.example.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class GeradorCSV {
    private final String caminho;
    private final StringBuilder sb = new StringBuilder();

    public GeradorCSV(String caminho) {
        this.caminho = caminho;
        // ALTERAÇÃO AQUI: Adicionei "Instancia" no cabeçalho
        sb.append("Instancia,n,m,CM_custo,CM_tempo,AGM_custo,AGM_tempo,FM_custo,FM_tempo\n");
    }

    /**
     * * @param n 
     * @param m 
     * @param cmCusto 
     * @param cmTempo 
     * @param agmCusto 
     * @param agmTempo 
     * @param fmCusto 
     * @param fmTempo 
     */
    // ALTERAÇÃO AQUI: Adicionei o parâmetro 'String nome'
    public void adicionarLinha(String nome, int n, int m, double cmCusto, double cmTempo,
        double agmCusto, double agmTempo, double fmCusto, double fmTempo) {
        
        // ALTERAÇÃO AQUI: Adicionei o '%s' no início e a variável 'nome'
        sb.append(String.format(Locale.US, "%s,%d,%d,%.2f,%.6f,%.2f,%.6f,%.2f,%.6f\n",
            nome, n, m, cmCusto, cmTempo, agmCusto, agmTempo, fmCusto, fmTempo));
    }

    public void salvar() throws IOException {
        try (FileWriter fw = new FileWriter(caminho)) {
            fw.write(sb.toString());
        }
    }
}