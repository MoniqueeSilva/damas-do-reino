package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Implementação da peça Cavaleiro.
 * GRASP: Polimorfismo (sobrescreve os métodos de validação).
 * A captura ocorre por ocupação do espaço do inimigo, sem necessidade de salto.
 */
public class Cavaleiro extends Peca {

    public Cavaleiro(CorPeca cor) {
        super(cor, TipoPeca.CAVALEIRO);
    }

    public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida movimento em L (2x1 ou 1x2) com destino vazio.
        if (!((deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2))) return false;
        return tabuleiro.casaEstaVazia(destinoLinha, destinoColuna);
    }

   public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida se o movimento é em L. Não chama movimentoValido pois o destino está ocupado.
        if (!((deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2))) return false;

        Peca inimigo = tabuleiro.getPeca(destinoLinha, destinoColuna);
        return inimigo != null && inimigo.getCor() != getCor();
    }

   public void executarCaptura(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        // Remove o inimigo que estava no destino e ocupa a casa.
        tabuleiro.removerPeca(destinoLinha, destinoColuna);
        tabuleiro.moverPeca(origemLinha, origemColuna, destinoLinha, destinoColuna);
    }
}