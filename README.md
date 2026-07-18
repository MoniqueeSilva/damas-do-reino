# ♟️ Damas dos Reinos

> Projeto desenvolvido para a disciplina de **Padrões de Projeto**, utilizando **Java** para implementar uma variação estratégica do jogo de damas, aplicando princípios de Programação Orientada a Objetos e padrões de projeto durante o desenvolvimento.

---

# 📖 Sobre o Projeto

O **Damas dos Reinos** é uma adaptação do jogo tradicional de damas que introduz diferentes tipos de peças, cada uma com regras próprias de movimentação e captura.

Mais do que reproduzir a mecânica do jogo, este projeto tem como objetivo colocar em prática conceitos de **Engenharia de Software**, especialmente a utilização de **Padrões de Projeto** para construir um sistema modular, reutilizável, desacoplado e de fácil manutenção.

Durante o desenvolvimento, diferentes padrões serão aplicados para resolver problemas recorrentes de projeto, melhorar a organização do código e facilitar futuras extensões do jogo.

---

# 🎯 Objetivos

* Desenvolver uma versão estratégica do jogo de damas.
* Aplicar conceitos de Programação Orientada a Objetos (POO).
* Implementar diferentes **Padrões de Projeto** para solucionar problemas de arquitetura e organização do código.
* Construir um sistema de fácil manutenção e evolução.
* Desenvolver testes automatizados para validar as regras do jogo.
* Utilizar Git e GitHub com histórico de commits incremental.

---

# 🏛️ Padrões de Projeto

Este projeto foi desenvolvido com foco na aplicação dos conceitos estudados na disciplina de **Padrões de Projeto**.

Ao longo do desenvolvimento serão utilizados padrões de projeto sempre que fizerem sentido para resolver problemas específicos da aplicação.

---

# 🕹️ Regras do Jogo

## Tabuleiro

* Tabuleiro 8 × 8.
* Apenas as 32 casas escuras são utilizadas.
* Dois jogadores:

  * ⚪ Reino Branco
  * ⚫ Reino Negro

Cada jogador inicia a partida com:

* 4 Soldados
* 4 Cavaleiros
* 4 Magos

Totalizando **12 peças** por jogador.

---

# ♟️ Tipos de Peças

## 🛡️ Soldado

### Movimento

* Move uma casa na diagonal para frente.

### Captura

* Captura por salto, seguindo as regras tradicionais da dama.

### Promoção

Ao alcançar a última fileira do lado adversário, torna-se um **Soldado Real**, podendo mover-se na diagonal tanto para frente quanto para trás.

---

## 🐴 Cavaleiro

Inspirado no cavalo do xadrez.

### Movimento

* Move em "L":

  * duas casas em uma direção;
  * uma casa perpendicular.

### Captura

Captura por ocupação da casa de destino, sem necessidade de salto.

---

## 🔮 Mago

Inspirado no bispo do xadrez.

### Movimento

Move qualquer quantidade de casas na diagonal, desde que o caminho esteja livre.

### Captura

Possui ataque à distância.

Caso exista exatamente uma peça inimiga na diagonal (sem peças próprias antes dela), o Mago pode eliminá-la permanecendo em sua posição.

---

# 🔄 Fluxo da Partida

Durante cada turno:

1. O jogador escolhe uma peça.
2. Realiza um movimento válido.
3. Caso exista captura, a peça adversária é removida.
4. Verifica-se promoção do Soldado.
5. O turno passa para o adversário.

---

# 🏆 Condições de Vitória

A partida termina quando:

* um jogador elimina todas as peças adversárias;

ou

* o adversário fica sem nenhum movimento válido disponível.

---

# ✅ Testes Automatizados

Serão implementados testes para validar:

* Movimento de cada tipo de peça;
* Captura de cada tipo de peça;
* Promoção do Soldado;
* Condições de vitória;
* Situações de afogamento;
* Regras específicas implementadas pelos padrões de projeto.

---

# 📌 Requisitos Técnicos

* Java
* Execução via console
* Sem frameworks de arquitetura
* Aplicação de Padrões de Projeto
* Testes automatizados
* Utilização de Git e GitHub

---

# 🚀 Como Executar

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/damas-dos-reinos.git
```

Acesse a pasta:

```bash
cd damas-dos-reinos
```

Compile:

```bash
javac src/Main.java
```

Execute:

```bash
java Main
```

---

# 👥 Equipe

* Monique da Silva
* Júlio Soares

---

# 🎓 Projeto Acadêmico

Este projeto foi desenvolvido como requisito da disciplina de **Padrões de Projeto**, tendo como foco a aplicação prática de **Padrões** em um sistema orientado a objetos.

Além da implementação das regras do jogo, o projeto busca demonstrar como padrões de projeto podem ser utilizados para tornar o software mais organizado, flexível, reutilizável e de fácil manutenção, seguindo boas práticas de engenharia de software.
