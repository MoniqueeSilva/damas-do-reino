# Decisões de Projeto

Este documento registra as principais decisões arquiteturais tomadas durante o desenvolvimento do projeto.

---

## Decisão 01

### Utilização da classe abstrata `Peca`

**Justificativa**

As peças do jogo possuem características comuns (cor e tipo), mas regras de movimentação e captura diferentes. A classe abstrata concentra o comportamento compartilhado e delega a validação para cada especialização.

**Princípios aplicados**

- Herança
- Polimorfismo
- Especialista da Informação (GRASP)
- DRY
- KISS

---

## Decisão 02

### Especialização das peças e polimorfismo

**Justificativa**

Cada tipo de peça (`Soldado`, `Cavaleiro` e `Mago`) implementa suas próprias regras de movimento e captura, através da sobrescrita dos métodos `movimentoValido()`, `capturaValida()` e `executarCaptura()`. Isso eliminou a necessidade de `switch` para validação de tipo na classe `Jogo`.

**Princípios aplicados**

- Open/Closed Principle (SOLID)
- Polimorfismo (GRASP)
- Alta Coesão
- Baixo Acoplamento

---

## Decisão 03

### Utilização de Enum para Cor e Tipo da Peça

**Justificativa**

As informações de cor e tipo passaram a ser representadas por enums, eliminando constantes inteiras e tornando o código mais legível e seguro.

**Princípios aplicados**

- Type Safety
- DRY
- KISS

---

## Decisão 04

### Herança do `SoldadoReal` a partir de `Soldado`

**Justificativa**

A peça `SoldadoReal` (promovida) possui exatamente as mesmas regras de movimento e captura do `Soldado`, com a única exceção de que ela pode se mover para trás. Ao invés de reescrever todo o código de validação de captura e movimento, fizemos `SoldadoReal extends Soldado`, sobrescrevendo apenas o método de validação de direção.

**Princípios aplicados**

- Princípio da Substituição de Liskov (SOLID - LSP)
- DRY (Don't Repeat Yourself)
- Herança
- Reutilização de código

---

## Decisão 05

### Classe `Casa` como responsável pela ocupação

**Justificativa**

Cada casa conhece apenas sua posição e qual peça está ocupando aquele espaço, encapsulando operações de inserção, remoção e consulta.

**Princípios aplicados**

- Especialista da Informação (GRASP)
- Alta Coesão

---

## Decisão 06

### Classe `Tabuleiro` responsável pela estrutura do jogo

**Justificativa**

O tabuleiro concentra apenas operações relacionadas às casas e aos limites da matriz, sem assumir responsabilidades de controle da partida.

**Princípios aplicados**

- Especialista da Informação (GRASP)
- Baixo Acoplamento
- Alta Coesão

---

## Decisão 07

### Classe `Jogador` representa apenas o participante da partida

**Justificativa**

O jogador conhece apenas sua identidade e a cor das peças que controla. A lógica de movimentação permanece nas peças e no jogo, evitando acoplamento com o tabuleiro.

**Princípios aplicados**

- Responsabilidade Única (SOLID - SRP)
- Especialista da Informação (GRASP)
- Baixo Acoplamento

---

## Decisão 08

### Classe `Jogo` como Controladora e Criadora das partidas

**Justificativa**

A classe `Jogo` centraliza o fluxo da partida. Ela é responsável por orquestrar o turno, mas não sabe *como* as peças se movem (essa responsabilidade é delegada à `Peca`). Além disso, ela atua como criadora, sendo responsável por instanciar o `Tabuleiro`, configurar o estado inicial do jogo através do posicionamento das peças e gerenciar a interação com o usuário (via console).

**Princípios aplicados**

- Controlador (GRASP)
- Criador (GRASP)
- Inversão de Dependência (SOLID - DIP)
- Responsabilidade Única (SOLID - SRP)

---

## Decisão 09

### Classe `ExibirTabuleiro` para desacoplamento da interface

**Justificativa**

A classe `ExibirTabuleiro` é uma invenção pura responsável exclusivamente pela renderização visual do estado do jogo no console. Ela não possui estado mutável e apenas traduz os dados do `Tabuleiro` para caracteres visuais. Isso garante que a camada de domínio (classes de peças, lógica do jogo) fique completamente desacoplada da camada de interface, permitindo que futuras alterações na apresentação sejam feitas sem impactar as regras de negócio.

**Princípios aplicados**

- Invenção Pura (GRASP)
- Indireção (GRASP)
- Responsabilidade Única (SOLID - SRP)

---

## Decisão 10

### Utilização de Testes Automatizados para validação arquitetural

**Justificativa**

Os testes unitários (JUnit) foram implementados para validar, de forma isolada, o comportamento de cada peça (`Soldado`, `Mago`, etc.) e as regras de orquestração da classe `Jogo`. Eles garantem que o polimorfismo e a especialização de cada classe estejam funcionando conforme o esperado, testando matematicamente as validações de movimento, captura, promoção, condições de vitória por eliminação e por afogamento, assegurando a integridade do projeto durante refatorações.

**Princípios aplicados**

- Rastreabilidade
- Validação de Polimorfismo
- Corretude arquitetural

---

## Decisão 11

### Desenvolvimento incremental utilizando GitFlow

**Justificativa**

Cada funcionalidade foi implementada em uma branch específica (`feat/...`, `refactor/...`) e integrada posteriormente à branch `develop`, mantendo um histórico de commits pequeno, incremental e rastreável entre os integrantes.

**Princípios aplicados**

- Desenvolvimento incremental
- Rastreabilidade
- Colaboração

---

## Decisão 12

### Captura não obrigatória

**Justificativa**

O grupo optou por **não implementar a regra de captura obrigatória** das damas tradicionais por dois motivos principais. Primeiro, a implementação dessa regra exigiria um algoritmo de varredura prévia e recursão para gerenciar "capturas em cadeia" (comer várias peças em sequência), o que adicionaria uma complexidade significativa ao fluxo do jogo. Segundo, a disciplina tem como foco a aplicação de Padrões de Projeto (GRASP, SOLID e GoF), e não a criação de um motor de damas competitivo. Portanto, optamos por deixar a captura como uma ação opcional do jogador, garantindo que a classe `Jogo` permaneça como um simples **Controlador (GRASP)**, sem violar o **Princípio da Responsabilidade Única (SOLID - SRP)**, mantendo o código aderente ao KISS.

**Princípios aplicados**

- KISS (Keep It Simple, Stupid)
- Responsabilidade Única (SOLID - SRP)
- Controlador (GRASP)
- Alta Coesão