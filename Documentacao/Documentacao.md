# Sistema de Gestão de Minimercado
## Sumário
1. [VISÃO GERAL DO SISTEMA](#1-visão-geral-do-sistema)
2. [LEVANTAMENTO DE REQUISITOS](#2-levantamento-de-requisitos)

   2.1. [REQUISITOS FUNCIONAIS](#requisitos)
   
   2.2. [REQUISITOS NÃO FUNCIONAIS](#requisitos-não-funcionais)
   
   2.3. [REQUISITOS SUPLEMENTARES](#requisitos-suplementares)
3. [DETALHAMENTO DE REQUISITOS](#3-detalhamento-dos-requisitos)
4. [CASOS DE USO](#4-casos-de-uso)
5. [CASO DE USO EXPANDIDO](#5-caso-de-uso-expandido)
6. [DIAGRAMA DE SEQUÊNCIA](#6-diagrama-de-sequência)
7. [MODELO CONCEITUAL](#7-modelo-conceitual)
8. [DIAGRAMA DE COMUNICAÇÃO](#8-diagrama-de-comunicação)
9. [DIAGRAMA DE CLASSES](#9-diagrama-de-classes)



## 1. Visão Geral
Este projeto propõe um sistema de gestão para minimercados focando em controle de vendas, estoque e relacionamento com clientes. O sistema permite cadastro de produtos por lote atribuindo códigos QR únicos para cada unidade, prevenindo venda de produtos vencidos e duplicidade nas leituras. O processo de venda ocorre via leitura de QR codes com validações em tempo real de validade e disponibilidade, suportando pagamento em dinheiro e venda a prazo (fiado), enquanto PIX e cartão são processados por máquina de cartão externa. A funcionalidade de fiado é restrita a clientes cadastrados com critérios de idade mínima, status ativo e limite de crédito. O sistema controla dívidas, inclui alertas automatizados para produtos vencidos e pagamentos em atraso, além de controle de caixa e relatórios gerenciais.

## 2. LEVANTAMENTO DE REQUISITOS

## Requisitos
1. Gerenciar Produtos
2. Realizar Vendas
3. Gerenciar Clientes
4. Gerenciar Fiado
5. Gerenciar Caixa
6. Emitir Relatórios
7. Autenticar Usuários

### Requisitos Não Funcionais
1. O sistema deve gerar QR Codes únicos para cada lote de produtos cadastrado, permitindo identificação e controle das movimentações no estoque.

2. O sistema deve emitir alertas quando lotes de produtos estiverem entre 10 a 30 dias do vencimento, considerando apenas lotes ativos e sem duplicar alertas no mesmo período.

3. O sistema deve atualizar automaticamente a quantidade disponível no estoque após a conclusão de uma venda.

4. O sistema não deve permitir o cadastro de produtos com quantidade igual ou inferior a zero, sem data de validade ou próximos da data de vencimento.

5. O sistema deve validar a autenticidade e integridade do QR Code durante o processo de venda, impedindo QR Codes inválidos, duplicados ou não cadastrados.

6. O sistema deve verificar a validade e a disponibilidade do produto antes de concluir a venda, impedindo a comercialização de itens vencidos ou sem estoque.

7. O sistema deve suportar diferentes formas de pagamento, incluindo dinheiro, PIX, cartão de crédito, cartão de débito e prazo (fiado), não permitindo a conclusão de uma venda sem forma de pagamento registrada.

8. O sistema deve calcular automaticamente o valor do troco em pagamentos realizados com dinheiro físico, considerando apenas valores válidos e positivos.

9. O sistema deve permitir a remoção de itens adicionados à venda antes da sua finalização, mediante senha do usuário.

10. O sistema deve gerar nota fiscal ao final de cada venda concluída, impedindo a emissão duplicada para uma mesma venda.

11. O sistema não deve permitir cadastros duplicados de clientes.

12. A conta do cliente deve possuir uma senha de 8 caracteres alfanuméricos para autorizar compras realizadas por terceiros.

13. O sistema deve permitir vendas fiado apenas para clientes cadastrados e maiores de 18 anos, gerando nota de fiado para assinatura.

14. O sistema deve impedir vendas na modalidade fiado para clientes que possuam notas vencidas em aberto.

15. O sistema deve emitir alertas sobre notas de fiado próximas do vencimento, sem duplicar alertas no mesmo período de notificação.

16. O sistema deve registrar e exibir o histórico de compras, dívidas e pagamentos de cada cliente, mantendo o histórico mesmo após a quitação.

17. O sistema deve permitir o registro de pagamento total ou parcial das notas de fiado, atualizando o saldo devedor, não permitindo valores nulos, negativos ou superiores ao saldo da nota.

18. O sistema deve permitir definir o dia de vencimento das notas de fiado individualmente para cada cliente, sem afetar notas já emitidas.

19. O sistema deve aplicar automaticamente multa de 2% sobre o valor devido quando uma nota de fiado permanecer em atraso por mais de 2 dias.

20. O sistema deve bloquear automaticamente clientes com mais de 20 dias de atraso no pagamento, mantendo o bloqueio até a quitação.

21. O sistema deve registrar todas as movimentações financeiras do estabelecimento, identificando cada uma como entrada ou saída.

22. O sistema deve exigir a definição do valor inicial de troco para abertura de caixa, não permitindo múltiplas aberturas para o mesmo caixa nem o fechamento sem abertura prévia.

23. O sistema deve gerar resumo financeiro ao fim do expediente, contendo totais de entradas, saídas e saldo apurado.

24. O sistema deve permitir ao gerente definir um saldo mínimo a ser mantido no caixa para abertura do próximo dia, sem afetar fechamentos já concluídos.

25. O sistema deve exigir autenticação por login e senha, diferenciando permissões conforme o perfil do usuário, não permitindo login duplicado.

### Requisitos Suplementares
1. O sistema deve ser desenvolvido usando a linguagem Java e o banco de dados MySQL/MariaDB.
2. O sistema deve funcionar em máquinas com no mínimo 4GB de RAM.
3. O sistema deve responder a operações de venda em menos de 2 segundos.
4. O sistema deve garantir a integridade dos dados durante as vendas e pagamentos.
5. O sistema deve armazenar as senhas dos clientes cadastrados de forma segura.
6. O sistema deve registrar um log de ações realizadas por cada usuário para fins de auditoria.
7. O sistema deve ser tolerante a falhas durante operações internas, como registro de venda, baixa de estoque e movimentações de caixa, revertendo operações incompletas automaticamente (ACID).

## 3. Detalhamento dos Requisitos
| **RF1. Gerenciar Produtos** |
|:---|
| **Descrição:**<br>Este requisito estabelece a necessidade de um sistema abrangente para o gerenciamento de produtos do minimercado por meio de controle por lote. O sistema deve permitir o cadastro de produtos com identificação própria por lote, a consulta individual de produtos, a emissão de QR Codes únicos vinculados a cada lote, a atualização dos dados cadastrais e a remoção de lotes. Além disso, deve incluir funcionalidades para monitorar a validade dos produtos e emitir alertas automáticos quando lotes estiverem próximos do vencimento, bem como realizar a baixa automática no estoque após cada venda.|
| **Fontes:**<br>Gerente do estabelecimento e atendentes|
| **Usuários:**<br>Gerente e Atendente|
| **Informações de entrada:**<br>Dados do Produto: nome do produto, código do produto, número do lote, data de fabricação, data de vencimento, fornecedor, custo, quantidade e unidade de medida.<br>Critérios de Consulta: nome do produto, número do lote ou QR Code.<br>Dados de Atualização: novos dados para atualização do lote selecionado.<br>Critério de Remoção: identificação do lote e confirmação da remoção.|
| **Informações de saída:**<br>Confirmação de Cadastro: mensagem informando que o cadastro foi concluído com sucesso e atualização das informações no estoque.<br>Informações do Produto: nome, quantidade disponível, lotes cadastrados, datas de vencimento e valor do produto.<br>QR Code: código único vinculado ao lote cadastrado, permitindo consulta das informações do lote e do produto.<br>Alertas de Vencimento: notificações quando lotes estiverem entre 10 a 30 dias do vencimento.<br>Confirmação de Atualização e Remoção: mensagens confirmando as operações realizadas.|
| **Requisitos não funcionais:**<br>RNF 1: O sistema deve gerar QR Codes únicos para cada lote de produtos cadastrado, permitindo identificação e controle das movimentações no estoque.<br><br>RNF 2: O sistema deve emitir alertas quando lotes de produtos estiverem entre 10 a 30 dias do vencimento, considerando apenas lotes ativos e sem duplicar alertas no mesmo período.<br><br>RNF 3: O sistema deve atualizar automaticamente a quantidade disponível no estoque após a conclusão de uma venda.<br><br>RNF 4: O sistema não deve permitir o cadastro de produtos com quantidade igual ou inferior a zero, sem data de validade ou próximos da data de vencimento.|

---

| **RF2. Realizar Vendas** |
|:---|
| **Descrição:**<br>Este requisito define a necessidade de um sistema para gerenciar o processo de venda no minimercado. O sistema deve validar a autenticidade do QR Code do produto, verificar a validade e disponibilidade antes de concluir a venda, suportar diferentes formas de pagamento, calcular o troco automaticamente em pagamentos com dinheiro físico, permitir a remoção de itens antes da finalização e gerar nota fiscal ao término de cada venda.|
| **Fontes:**<br>Gerente do estabelecimento e atendentes|
| **Usuários:**<br>Atendente ou gerente|
| **Informações de entrada:**<br>QR Code do Produto: leitura do QR Code para validação durante a venda.<br>Dados da Venda: produtos vendidos, quantidades, valores e forma de pagamento selecionada.<br>Valor Recebido: valor entregue pelo cliente em dinheiro físico para cálculo do troco.<br>Solicitação de Remoção: identificação do item a ser removido da venda em andamento.|
| **Informações de saída:**<br>Validação do QR Code: permissão ou bloqueio da venda com base na validação do QR Code e nas informações do lote.<br>Verificação de Disponibilidade: permissão ou bloqueio da venda com base na validade e quantidade em estoque.<br>Cálculo do Troco: valor do troco exibido automaticamente.<br>Nota Fiscal: documento contendo os itens vendidos, valor total, impostos e forma de pagamento.<br>Atualização da Venda: remoção do item, atualização do valor total e restauração da disponibilidade.|
| **Requisitos não funcionais:**<br>RNF 5: O sistema deve validar a autenticidade e integridade do QR Code durante o processo de venda, impedindo QR Codes inválidos, duplicados ou não cadastrados.<br><br>RNF 6: O sistema deve verificar a validade e a disponibilidade do produto antes de concluir a venda, impedindo a comercialização de itens vencidos ou sem estoque.<br><br>RNF 7: O sistema deve suportar diferentes formas de pagamento, incluindo dinheiro, PIX, cartão de crédito, cartão de débito e prazo (fiado), não permitindo a conclusão de uma venda sem forma de pagamento registrada.<br><br>RNF 8: O sistema deve calcular automaticamente o valor do troco em pagamentos realizados com dinheiro físico, considerando apenas valores válidos e positivos.<br><br>RNF 9: O sistema deve permitir a remoção de itens adicionados à venda antes da sua finalização, mediante senha do usuário.<br><br>RNF 10: O sistema deve gerar nota fiscal ao final de cada venda concluída, impedindo a emissão duplicada para uma mesma venda.|

---

| **RF3. Gerenciar Clientes** |
|:---|
| **Descrição:**<br>Este requisito define a necessidade de um sistema para o cadastro e gerenciamento de clientes do minimercado, com a finalidade de gerenciar as notas de fiado e acompanhar suas dívidas. O sistema deve permitir o cadastro com dados pessoais, a definição de senha para autorizar compras realizadas por terceiros e a consulta da situação do cliente.|
| **Fontes:**<br>Gerente do estabelecimento|
| **Usuários:**<br>Atendente ou gerente|
| **Informações de entrada:**<br>Dados do Cliente: dados cadastrais do cliente, incluindo nome, CPF, telefone, endereço, dia de vencimento e senha.<br>Senha do Titular: senha definida pelo cliente para autorizar compras por terceiros.|
| **Informações de saída:**<br>Confirmação de Cadastro: mensagem informando o sucesso do cadastro.<br>Validação de Senha: resultado da validação da senha durante compras realizadas por terceiros.<br>Situação do Cliente: dados cadastrais e situação financeira disponíveis para consulta.|
| **Requisitos não funcionais:**<br>RNF 11: O sistema não deve permitir cadastros duplicados de clientes.<br><br>RNF 12: A conta do cliente deve possuir uma senha de 8 caracteres alfanuméricos para autorizar compras realizadas por terceiros.|

---

| **RF4. Gerenciar Fiado** |
|:---|
| **Descrição:**<br>Este requisito estabelece a necessidade de um sistema para gerenciar as operações de venda a prazo (fiado) do minimercado. O sistema deve gerar notas de fiado para assinatura do cliente, controlar o bloqueio de vendas fiado para clientes inadimplentes, emitir alertas sobre notas próximas do vencimento, registrar e exibir o histórico de compras e dívidas, permitir o registro de pagamentos totais ou parciais, definir o dia de vencimento das notas individualmente, aplicar multas por atraso e bloquear automaticamente clientes com atraso prolongado.|
| **Fontes:**<br>Gerente do estabelecimento|
| **Usuários:**<br>Atendente ou gerente|
| **Informações de entrada:**<br>Dados da Venda Fiado: forma de pagamento a prazo e dados do cliente associado.<br>Dados de Pagamento: nome do cliente, valor pago, data do pagamento, método de pagamento e nota associada.<br>Dia de Vencimento: dia definido pelo cliente durante o cadastro ou atualização cadastral.|
| **Informações de saída:**<br>Nota de Fiado: documento contendo dados da venda, valor devido, vencimento e espaço para assinatura do cliente.<br>Bloqueio de Venda: autorização ou bloqueio da venda fiado com base na existência de notas atrasadas.<br>Alertas de Vencimento: notificações sobre notas de fiado próximas do vencimento.<br>Histórico Financeiro: histórico de compras a prazo, dívidas e pagamentos realizados pelo cliente.<br>Atualização de Saldo: registro do pagamento e atualização do saldo devedor.<br>Multa Aplicada: cálculo e adição da multa ao saldo da nota em atraso.<br>Bloqueio de Cliente: bloqueio automático da conta a prazo do cliente inadimplente.|
| **Requisitos não funcionais:**<br>RNF 13: O sistema deve permitir vendas fiado apenas para clientes cadastrados e maiores de 18 anos, gerando nota de fiado para assinatura.<br><br>RNF 14: O sistema deve impedir vendas na modalidade fiado para clientes que possuam notas vencidas em aberto.<br><br>RNF 15: O sistema deve emitir alertas sobre notas de fiado próximas do vencimento, sem duplicar alertas no mesmo período de notificação.<br><br>RNF 16: O sistema deve registrar e exibir o histórico de compras, dívidas e pagamentos de cada cliente, mantendo o histórico mesmo após a quitação.<br><br>RNF 17: O sistema deve permitir o registro de pagamento total ou parcial das notas de fiado, atualizando o saldo devedor, não permitindo valores nulos, negativos ou superiores ao saldo da nota.<br><br>RNF 18: O sistema deve permitir definir o dia de vencimento das notas de fiado individualmente para cada cliente, sem afetar notas já emitidas.<br><br>RNF 19: O sistema deve aplicar automaticamente multa de 2% sobre o valor devido quando uma nota de fiado permanecer em atraso por mais de 2 dias.<br><br>RNF 20: O sistema deve bloquear automaticamente clientes com mais de 20 dias de atraso no pagamento, mantendo o bloqueio até a quitação.|

---

| **RF5. Gerenciar Caixa** |
|:---|
| **Descrição:**<br>Este requisito estabelece a necessidade de um sistema para gerenciar as operações financeiras do minimercado, incluindo o registro de todas as movimentações financeiras, a abertura do caixa com definição do valor inicial de troco, a geração de resumo financeiro ao fim do expediente e a definição de saldo mínimo para abertura do caixa no dia seguinte.|
| **Fontes:**<br>Gerente do estabelecimento|
| **Usuários:**<br>Atendente ou gerente|
| **Informações de entrada:**<br>Movimentações Financeiras: dados de todas as operações financeiras, incluindo pagamentos, recebimentos e movimentações de caixa.<br>Valor Inicial de Troco: valor informado antes da abertura do caixa.<br>Saldo Mínimo: valor mínimo definido pelo gerente para permanecer no caixa.|
| **Informações de saída:**<br>Histórico de Movimentações: registro de todas as movimentações financeiras, identificadas como entrada ou saída.<br>Abertura de Caixa: registro da abertura associada ao operador responsável.<br>Resumo Financeiro: documento contendo totais de entradas, saídas e saldo apurado do período.<br>Configuração de Saldo Mínimo: valor registrado e utilizado como referência nos processos de abertura e fechamento.|
| **Requisitos não funcionais:**<br>RNF 21: O sistema deve registrar todas as movimentações financeiras do estabelecimento, identificando cada uma como entrada ou saída.<br><br>RNF 22: O sistema deve exigir a definição do valor inicial de troco para abertura de caixa, não permitindo múltiplas aberturas para o mesmo caixa nem o fechamento sem abertura prévia.<br><br>RNF 23: O sistema deve gerar resumo financeiro ao fim do expediente, contendo totais de entradas, saídas e saldo apurado.<br><br>RNF 24: O sistema deve permitir ao gerente definir um saldo mínimo a ser mantido no caixa para abertura do próximo dia, sem afetar fechamentos já concluídos.|

---

| **RF6. Emitir Relatórios** |
|:---|
| **Descrição:**<br>Este requisito define a necessidade de o sistema gerar relatórios gerenciais para apoio à tomada de decisão. O sistema deve gerar relatórios de vendas por período, relatórios do estoque destacando produtos com baixa quantidade ou próximos do vencimento e relatórios de dívidas em aberto e pagamentos a prazo.|
| **Fontes:**<br>Gerente do estabelecimento|
| **Usuários:**<br>Gerente|
| **Informações de entrada:**<br>Período de Consulta: data de início e fim para geração dos relatórios de vendas e dívidas.<br>Critério de Consulta do Estoque: nome do produto, código do produto ou número do lote (opcional).|
| **Informações de saída:**<br>Relatório de Vendas: total arrecadado, métodos de pagamento e quantidade de transações realizadas no período, excluindo vendas canceladas.<br>Relatório de Estoque: informações dos produtos em estoque com destaque para produtos em baixa quantidade ou próximos do vencimento.<br>Relatório de Dívidas: dívidas em aberto e pagamentos a prazo, excluindo notas canceladas.|
| **Requisitos não funcionais:**<br>Não se aplica.|

---

| **RF7. Autenticar Usuários** |
|:---|
| **Descrição:**<br>Este requisito define a necessidade de o sistema exigir autenticação por meio de login e senha para acesso, diferenciando as permissões de acordo com o perfil do usuário, garantindo que operadores e gerente possuam acessos distintos conforme suas responsabilidades no estabelecimento.|
| **Fontes:**<br>Gerente do estabelecimento|
| **Usuários:**<br>Atendente ou gerente|
| **Informações de entrada:**<br>Credenciais de Acesso: login e senha do usuário.|
| **Informações de saída:**<br>Autenticação: o sistema autentica o usuário e concede acesso a funcionalidades referentes à posição do usuário no estabelecimento.|
| **Requisitos não funcionais:**<br>RNF 25: O sistema deve exigir autenticação por login e senha, diferenciando permissões conforme o perfil do usuário, não permitindo login duplicado.|

---

## 4. Casos de Uso

## 5. Caso de Uso Expandido

### UC Expandido 01: Gerenciar Produtos
#### Ator(es):
- Gerente: Responsável por gerenciar os produtos e lotes cadastrados no sistema.
#### Interessado(s) e Interesse(s):
- Gerente: Permitir o cadastro de novos produtos por lote, a consulta, atualização e remoção de lotes no sistema, garantindo o controle adequado do estoque.
- Sistema: Registrar e processar corretamente as informações de produtos e lotes, mantendo a integridade dos dados.
#### Pré-condições:
- O gerente deve estar autenticado no sistema.
- O sistema deve estar operacional.
#### Pós-condições:
- O produto é registrado no sistema e fica disponível para operações de consulta, atualização, remoção e venda.
#### Questões em Aberto:
- Não há questões em aberto identificadas.
#### Fluxo Principal:
1. [IN] O gerente solicita o cadastro de um produto.
2. [OUT] O sistema solicita as informações do produto.
3. [IN] O gerente informa: nome do produto, código do produto, número do lote, data de fabricação, data de vencimento, fornecedor, preço unitário, unidade de medida e quantidade do produto.
4. [OUT] O sistema registra o produto e disponibiliza seu identificador único. [E1]
#### Variantes:
- Consultar Produto: 1. [IN] O gerente solicita a consulta de um produto. 2. [OUT] O sistema solicita um critério de consulta. 3. [IN] O gerente informa um critério de identificação do produto. 4. [OUT] O sistema apresenta as informações do produto. [E2]

- Atualizar Produto: 1. [IN] O gerente solicita a atualização de um produto. 2. [OUT] O sistema solicita a identificação do produto. 3. [IN] O gerente informa o produto desejado. 4. [OUT] O sistema apresenta as informações atuais do produto. 5. [IN] O gerente informa as alterações desejadas. 6. [OUT] O sistema apresenta a confirmação da atualização. [E2]

- Remover Lote: 1. [IN] O gerente solicita a remoção de um lote. 2. [OUT] O sistema solicita a identificação do lote. 3. [IN] O gerente informa o lote desejado. 4. [OUT] O sistema apresenta as informações do lote. 5. [IN] O gerente confirma a remoção. 6. [OUT] O sistema apresenta a confirmação da remoção. [E2]
#### Exceções:
- E1: Produto próximo da data de vencimento:

   - O sistema informa que produtos próximos do vencimento não podem ser cadastrados.
   - O caso de uso é abortado.

- E2: Produto não encontrado:

   - O sistema informa que o produto não foi encontrado e retorna ao passo correspondente da operação.
#### Fluxo de Alternativa:
- Erro ao Cadastrar ou Atualizar Produto
   - Se houver erro no cadastro ou atualização (como dados inconsistentes), o sistema exibe uma mensagem de erro e solicita correção.
---

### UC Expandido 02: Gerenciar Clientes
#### Ator(es):
- Gerente: Responsável pelo cadastro e gerenciamento de clientes.
- Atendente: Funcionário autorizado a realizar operações de cadastro e consulta de clientes.
#### Interessado(s) e Interesse(s):
- Gerente e Atendente: Permitir o cadastro de clientes aptos à realização de compras a prazo (fiado), garantindo o controle adequado das informações cadastrais.
- Sistema: Registrar e processar corretamente as informações dos clientes, mantendo a integridade dos dados.
#### Pré-condições:
- O usuário deve estar autenticado no sistema.
- O sistema deve estar operacional.
#### Pós-condições:
- O cliente é registrado no sistema e fica disponível para consultas, atualizações e operações de fiado.
#### Questões em Aberto:
- Não há questões em aberto identificadas.
#### Fluxo Principal:
1. [IN] O usuário solicita o cadastro de um cliente.
2. [OUT] O sistema solicita as informações do cliente.
3. [IN] O usuário informa: nome, CPF, telefone, endereço, dia de vencimento e senha.
4. [OUT] O sistema registra o cliente e apresenta a confirmação do cadastro. [E1]
#### Variantes:
- Consultar Cliente: 1. [IN] O usuário solicita a consulta de um cliente. 2. [OUT] O sistema solicita um critério de consulta. 3. [IN] O usuário informa o nome ou CPF do cliente. 4. [OUT] O sistema apresenta as informações do cliente. [E2]

- Atualizar Cliente: 1. [IN] O usuário solicita a atualização de um cliente. 2. [OUT] O sistema solicita a identificação do cliente. 3. [IN] O usuário informa o cliente desejado. 4. [OUT] O sistema apresenta as informações atuais do cliente. 5. [IN] O usuário informa as alterações desejadas. 6. [OUT] O sistema apresenta a confirmação da atualização. [E2]
#### Exceções:
- E1: CPF já cadastrado:

   - O sistema informa que o CPF informado já está cadastrado e retorna ao passo 3.

- E2: Cliente não encontrado:

   - O sistema informa que o cliente não foi encontrado e retorna ao passo correspondente da operação.
#### Fluxo de Alternativa:
- Não se aplica.

---

## 6. Diagrama de sequência

### Diagrama de sequência para UC01 - Gerenciar Produtos;

![UC1_Gerenciar_Produtos_MVC](Diagramas/Diagramas_de_Sequencia/UC1_Gerenciar_Produtos_MVC.png)

### Diagrama de sequência para UC02 - Gerenciar Clientes;

![UC2_Gerenciar_Clientes_MVC](Diagramas/Diagramas_de_Sequencia/UC2_Gerenciar_Clientes_MVC.png)

### Diagrama de sequência para UC03 - Realizar Venda;

![UC3_Realizar_Venda_MVC](Diagramas/Diagramas_de_Sequencia/UC3_Realizar_Venda_MVC.png)

### Diagrama de sequência para UC04 - Gerenciar Fiados;

![UC4_Gerenciar_Fiados_MVC](Diagramas/Diagramas_de_Sequencia/UC4_Gerenciar_Fiados_MVC.png)

### Diagrama de sequência para UC05 - Gerenciar Caixa;

![UC5_Gerenciar_Caixa_MVC](Diagramas/Diagramas_de_Sequencia/UC5_Gerenciar_Caixa_MVC.png)

### Diagrama de sequência para UC06 - Relatório de Vendas;

![UC6_Relatorio_Vendas_MVC](Diagramas/Diagramas_de_Sequencia/UC6_Relatorio_Vendas_MVC.png)

### Diagrama de sequência para UC07 - Relatório de Estoque;

![UC7_Relatorio_Estoque_MVC](Diagramas/Diagramas_de_Sequencia/UC7_Relatorio_Estoque_MVC.png)

### Diagrama de sequência para UC08 - Relatório de Fiados;

![UC8_Relatorio_Fiados_MVC](Diagramas/Diagramas_de_Sequencia/UC8_Relatorio_Fiados_MVC.png)

---

## 7. Modelo conceitual

---

## 8. Diagrama de comunicação

---

## 9. Diagrama de classes
