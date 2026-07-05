## Sumário

1. [VISÃO GERAL](#1-visão-geral)
2. [DIAGRAMA DE ATIVIDADES]
3. [LEVANTAMENTO DE REQUISITOS](#3-levantamento-de-requisitos)
  
   3.1. [REQUISITOS FUNCIONAIS](#31-requisitos-funcionais)
  
   3.2. [REQUISITOS NÃO FUNCIONAIS]

4. [DETALHATAMENTO DE REQUISITOS]
5. [CASOS DE USO EXPANDIDOS]
6. [DIAGRAMA DE SEQUÊNCIA]


---
## 1. VISÃO GERAL
Este projeto propõe um sistema de gestão para minimercados focando em controle de vendas, estoque e relacionamento com clientes. O sistema permite cadastro de produtos por lote atribuindo códigos QR únicos para cada unidade, prevenindo venda de produtos vencidos e duplicidade nas leituras. O processo de venda ocorre via leitura de QR codes com validações em tempo real de validade e disponibilidade, suportando pagamento em dinheiro e venda a prazo (fiado), enquanto PIX e cartão são processados por máquina de cartão externa. A funcionalidade de fiado é restrita a clientes cadastrados com critérios de idade mínima, status ativo e limite de crédito. O sistema controla dívidas, inclui alertas automatizados para produtos vencidos e pagamentos em atraso, além de controle de caixa e relatórios gerenciais.

---
## 2. DIAGRAMA DE ATIVIDADES

---

## 3. LEVANTAMENTO DE REQUISITOS

---
### 3.1. REQUISITOS FUNCIONAIS

1. Cadastrar produtos por lote
2. Consultar produto individualmente
3. Emitir QR Code únicos por lote
4. Alertar sobre lotes próximos ao vencimento
5. Baixa automática no estoque após venda
6. Atualizar cadastros de lotes
7. Remover lotes cadastrados
8. Validar autenticidade e integridade do QR Code durante a venda
9. Verificar validade e disponibilidade do produto na venda
10. Oferecer suporte a diferentes formas de pagamento
11. Calcular troco automaticamente
12. Remover item da venda antes da finalização
13. Gerar nota fiscal após a venda
14. Gerar nota para assinatura em caso de venda fiada
15. Impedir venda fiada para clientes com notas atrasadas
16. Cadastrar clientes para gerenciar notas de fiado
17. Definir senha para compras não realizadas pelo titular
18. Alertar sobre notas de fiado perto do vencimento
19. Registrar e exibir histórico de compras e dívidas do cliente
20. Registrar pagamento total ou parcial das notas de fiado
21. Definir dia de vencimento das notas de fiado para cada cliente
22. Aplicar multa de 2% após 2 dias de atraso
23. Bloquear clientes com mais de 20 dias sem pagar
24. Registrar todas as movimentações financeiras
25. Definir o valor inicial de troco para abertura de caixa
26. Gerar resumo financeiro ao fim do expediente
27. Definir saldo mínimo para abertura de caixa no dia seguinte
28. Gerar relatórios de vendas por período
29. Gerar relatório do estoque destacando produtos com baixa quantidade ou perto do vencimento
30. Gerar relatório de dívidas em aberto e pagamentos a prazo
31. Exigir login com perfis distintos para operadores e gerente

### 3.2 REQUISITOS NÃO FUNCIONAIS

1. O sistema deve ser desenvolvido usando a linguagem Java e o banco de dados MySQL/MariaDB.
2. O sistema deve funcionar em máquinas com no mínimo 4GB de RAM.
3. O sistema deve responder a operações de venda em menos de 2 segundos.
4. O sistema deve garantir a integridade dos dados durante as vendas e pagamentos.
5. O sistema deve armazenar as senhas dos clientes cadastrados de forma segura.
6. O sistema deve registrar um log de ações realizadas por cada usuário para fins de auditoria.
7. O sistema deve tratar exceções sem encerrar abruptamente, registrando elas em log com data, hora e contexto da operação. (a averiguar…)
8. O sistema deve ser tolerante a falhas durante operações internas, como registro de venda, baixa de estoque e movimentações de caixa, revertendo operações incompletas automaticamente (ACID).

## 4. DETALHATAMENTO DE REQUISITOS
