# Algumas Instruções: 

 - É preciso de o **Java instalado**;
 - Para o programa rodar você deve executar o arquivo **"Program"** (na IDE ou no terminal), que está na packpage **"application"**;
 - Os arquivos vindos da web são baixados e extraídos em uma pasta que é criada automaticamente na pasta na raiz do projeto em seu computador, **"dados_brutos".**
 - Os arquivos processados são criados na pasta **dados_processados** em seu computador.

 # ANS Data Analyzer

 Este projeto é uma ferramenta de engenharia de dados desenvolvida em Java para processar, validar e analisar registros financeiros de operadoras de saúde pública (ANS). 
 O foco principal do sistema é a ingestão de arquivos brutos, processamento em memória e geração de relatórios estruturados, aplicando conceitos avançados de Programação 
 Orientada a Objetos e processamento de fluxos de dados (Streams).

 # Funcionalidades Principais

 - **Ingestão de Dados:** Automação de coleta e leitura de arquivos (Scraping/Processamento).
 - **Modelagem de Domínio:** Estrutura robusta utilizando o princípio Information Expert, garantindo que cada classe (Company, Expense) gerencie sua própria lógica e cálculo.
 - **Processamento de Alta Performance:** Utilização de Map<String, Company> como índice em memória para garantir $O(1)$ na busca de operadoras (sem necessidade de bancos de dados para este MVP).
 - **Análise Estatística:** Implementação de cálculos como Média e Desvio Padrão para análise de dispersão de gastos.
 - **Relatórios Flexíveis:** Uso de Java Streams para ordenação, filtragem (ex: remoção de gastos zerados/glosas indevidas) e formatação de saída.
 - **Integridade:** Cláusulas de guarda e logs detalhados para registro de exceções (logs de erro).

# Arquitetura

O sistema foi construído visando o desacoplamento. Cada módulo de infraestrutura (Leitura/Escrita) é independente da regra de negócio, permitindo que a aplicação seja facilmente testada e escalada.

 - **Validação:** Camada responsável por garantir a integridade dos dados antes da carga.
 - **Processamento:** Engine que cruza despesas e operadoras via chave primária (ANS).
 - **Relatórios:** Módulo de análise que gera o output final em CSV.

# Qualidade de Código (Teste)

O projeto contém testes unitários básicos utilizando JUnit 5, garantindo que as lógicas de cálculo de média e desvio padrão permaneçam corretas durante futuras refatorações.
