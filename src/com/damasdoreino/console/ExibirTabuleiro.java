package com.damasdoreino.console;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Casa;
import com.damasdoreino.jogo.Tabuleiro;
import com.damasdoreino.pecas.Peca;

/*
 * Responsável pela renderização visual do tabuleiro no console.
 * GRASP: Invenção Pura e Indireção (desacopla a apresentação da lógica do jogo).
 * SOLID: SRP - única responsabilidade: exibir o tabuleiro.
 */
public class ExibirTabuleiro {

    /*Percorre o tabuleiro e imprime cada casa com o respectivo ícone. */
    public void imprimir(Tabuleiro tabuleiro) {

        System.out.println();
        System.out.println("      0  1  2  3  4  5  6  7");
        System.out.println("    ┌─────────────────────────┐");

        for (int linha = 0; linha < 8; linha++) {

            System.out.print(" " + linha + "  │ ");

            for (int coluna = 0; coluna < 8; coluna++) {

                Casa casa = tabuleiro.getCasa(linha, coluna);

                if (casa.estaVazia()) {

                    if ((linha + coluna) % 2 == 0) {
                        System.out.print("⬜ ");
                    } else {
                        System.out.print("🟫 ");
                    }

                } else {

                    Peca peca = casa.getPeca();
                    System.out.print(obterEmoji(peca) + " ");

                }

            }

            System.out.println("│");
        }

        System.out.println("    └─────────────────────────┘");
        System.out.println();
    }

   /*Traduz o tipo e a cor da peça para um emoji correspondente na interface. */
   private String obterEmoji(Peca peca) {
    switch (peca.getTipo()) {
        case SOLDADO:
            return peca.getCor() == CorPeca.BRANCO ? "⚪" : "⚫";

        case SOLDADO_REAL:
            return peca.getCor() == CorPeca.BRANCO ? "♔ " : "♚";

        case CAVALEIRO:
            return peca.getCor() == CorPeca.BRANCO ? "🐴" : "🐎";

        case MAGO:
            return peca.getCor() == CorPeca.BRANCO ? "🔮" : "✨";

        default:
            return "❓";
    }
}
}