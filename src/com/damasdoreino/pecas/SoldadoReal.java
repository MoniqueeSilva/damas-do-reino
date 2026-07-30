package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

public class SoldadoReal extends Peca {

    public SoldadoReal(CorPeca cor) {
        super(cor, TipoPeca.SOLDADO_REAL);
    }

    public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        //Soldado Real movimenta uma casa na diagonal em qualquer direção;
        return deltaLinha == 1 && deltaColuna == 1;
    }

}