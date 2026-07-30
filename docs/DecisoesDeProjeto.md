# Decisões de Projeto

Este documento registra as principais decisões arquiteturais tomadas durante o desenvolvimento do projeto.

---

# Decisão 01

## Utilização da classe abstrata `Peca`

**Justificativa**

As peças do jogo possuem características comuns (cor e tipo), mas regras de movimentação diferentes.

A classe abstrata concentra o comportamento compartilhado e delega a validação do movimento para cada especialização.

**Princípios aplicados**

- Herança
- Polimorfismo
- Especialista da Informação (GRASP)
- DRY
- KISS

---

# Decisão 02

## Especialização das peças

**Justificativa**

Cada tipo de peça (`Soldado`, `SoldadoReal`, `Cavaleiro` e `Mago`) implementa apenas sua própria regra de movimentação.

Assim, novas peças podem ser adicionadas sem modificar as existentes.

**Princípios aplicados**

- Open/Closed Principle (SOLID)
- Polimorfismo
- Alta Coesão

---

# Decisão 03

## Utilização de Enum para Cor e Tipo da Peça

**Justificativa**

As informações de cor e tipo passaram a ser representadas por enums, eliminando constantes inteiras e tornando o código mais legível e seguro.

**Princípios aplicados**

- Type Safety
- DRY
- KISS

---

# Decisão 04

## Classe `Casa` como responsável pela ocupação

**Justificativa**

Cada casa conhece apenas sua posição e qual peça está ocupando aquele espaço, encapsulando operações de inserção, remoção e consulta.

**Princípios aplicados**

- Especialista da Informação (GRASP)
- Alta Coesão

---

# Decisão 05

## Classe `Tabuleiro` responsável pela estrutura do jogo

**Justificativa**

O tabuleiro concentra apenas operações relacionadas às casas e aos limites da matriz, sem assumir responsabilidades de controle da partida.

**Princípios aplicados**

- Especialista da Informação (GRASP)
- Baixo Acoplamento
- Alta Coesão

---

# Decisão 06

## Classe `Jogador` representa apenas o participante da partida

**Justificativa**

O jogador conhece apenas sua identidade e a cor das peças que controla.

A lógica de movimentação permanece nas peças e no jogo, evitando acoplamento com o tabuleiro.

**Princípios aplicados**

- Single Responsibility Principle (SOLID)
- Especialista da Informação (GRASP)
- Baixo Acoplamento

---

# Decisão 07

## Desenvolvimento incremental utilizando GitFlow

**Justificativa**

Cada funcionalidade foi implementada em uma branch específica e integrada posteriormente à branch `develop`, mantendo um histórico de commits pequeno, incremental e rastreável entre os integrantes.

**Princípios aplicados**

- Desenvolvimento incremental
- Rastreabilidade
- Colaboração