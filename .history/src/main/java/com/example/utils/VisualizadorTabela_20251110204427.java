package com.example.utils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class VisualizadorTabela extends JFrame {

    public VisualizadorTabela(String caminhoArquivo) {
        // Configurações da Janela
        super("Resultados dos Algoritmos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 400); // Tamanho da janela
        setLocationRelativeTo(null); // Centraliza na tela

        // Modelo da tabela (Dados)
        DefaultTableModel modelo = new DefaultTableModel();
        JTable tabela = new JTable(modelo);

        // Ler o CSV e preencher a tabela
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                
                if (primeiraLinha) {
                    // Adiciona os nomes das colunas (Cabeçalho)
                    for (String coluna : dados) {
                        modelo.addColumn(coluna);
                    }
                    primeiraLinha = false;
                } else {
                    // Adiciona as linhas de dados
                    modelo.addRow(dados);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler CSV: " + e.getMessage());
        }

        // Adiciona a tabela em uma barra de rolagem (caso tenha muitos dados)
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Mostra a janela
        setVisible(true);
    }
}
