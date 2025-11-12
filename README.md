# Avaliação de Algoritmos de Grafos

Este repositório contém uma implementação em Java de vários algoritmos de grafos usados para avaliação comparativa e experimentos. O objetivo principal do projeto é oferecer um ambiente para executar, comparar e analisar algoritmos clássicos de grafos sobre instâncias reais (ou geradas) e coletar os resultados em CSV.

## Propósito

- Implementar e comparar algoritmos de grafos (caminhos mínimos, árvores geradoras, fluxo, etc.).
- Fornecer utilitários para leitura de grafos e geração de relatórios em CSV.
- Facilitar experimentos para trabalhos acadêmicos sobre teoria dos grafos, análise de desempenho e propriedades de redes.

## Algoritmos implementados

No diretório `src/main/java/com/example/algoritmos` você encontrará implementações para:

- `Dijkstra` — Caminho mínimo em grafos com pesos não-negativos.
- `Dinic` — Algoritmo para fluxo máximo em redes.
- `Kruskal` — Árvore geradora mínima via união por arestas ordenadas.
- `Prim` — Árvore geradora mínima via crescimento a partir de um vértice.

## Estrutura do repositório

- `pom.xml` — Configuração do Maven.
- `dados/` — Arquivos de instâncias de grafos (ex.: `USA-road-d.BAY.gr`, `USA-road-d.NY.gr`, ...).
- `src/main/java/com/example/` — Código-fonte Java:
  - `algoritmos/` — Implementações dos algoritmos.
  - `grafos/` — Estruturas de grafo (por exemplo `Grafo.java`, `Aresta.java`).
  - `utils/` — Utilitários (leitura de grafos, geração de CSV, etc.).
  - `ui/` — Classes para visualização (ex.: `VisualizadorTabela.java`).
  - `Main.java` — Classe principal que amarra a execução/experimentos.
- `resultados.csv` — Exemplo/arquivo de saída com resultados agregados.

## Como executar

Recomenda-se usar o Maven (projeto já contém `pom.xml`). As instruções a seguir são genéricas e funcionam no Windows PowerShell.

1) Compilar o projeto:

```powershell
mvn compile
```

2) Empacotar (opcional):

```powershell
mvn package
```

3) Executar a classe principal diretamente (após `mvn package`):

```powershell
java -cp target/classes com.example.Main
```

Observação: se você preferir executar via um plugin do Maven (por exemplo `exec:java`), ajuste conforme sua configuração do `pom.xml`.

## Dados de entrada

Coloque arquivos de grafo no formato esperado pelo leitor em `dados/`. O projeto já inclui alguns arquivos de exemplo na pasta `dados/`. Verifique a classe `LeitorGrafo` em `src/main/java/com/example/utils/LeitorGrafo.java` para entender o formato aceito (por exemplo, listas de arestas, cabeçalho com número de vértices, etc.).

## Saída

Os resultados dos experimentos podem ser gerados em `resultados.csv` através do utilitário `GeradorCSV`. O CSV contém métricas e medidas (por exemplo, tempos de execução, tamanho da solução, custo total), dependendo de como `Main` foi implementado.

