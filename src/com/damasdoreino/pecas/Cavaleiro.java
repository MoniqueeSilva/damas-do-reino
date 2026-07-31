package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

public class Cavaleiro extends Peca {

    public Cavaleiro(CorPeca cor) {
        super(cor, TipoPeca.CAVALEIRO);
    }

    @Override
    public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Cavaleiro move em L (2x1 ou 1x2)
        if (!((deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2))) return false;
        // Movimento normal exige que o destino esteja vazio
        return tabuleiro.casaEstaVazia(destinoLinha, destinoColuna);
    }

    @Override
    public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida se o movimento é em L (não chama movimentoValido, para evitar conflito de casas vazias)
        if (!((deltaLinha == 2 && deltaColuna == 1) || (deltaLinha == 1 && deltaColuna == 2))) return false;

        // A captura de cavaleiro é por "ocupação": verifica se tem uma peça inimiga no destino
        Peca inimigo = tabuleiro.getPeca(destinoLinha, destinoColuna);
        return inimigo != null && inimigo.getCor() != getCor();
    }

    @Override
    public void executarCaptura(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        // Remove a peça inimiga que estava no destino e ocupa o lugar
        tabuleiro.removerPeca(destinoLinha, destinoColuna);
        tabuleiro.moverPeca(origemLinha, origemColuna, destinoLinha, destinoColuna);
    }
}