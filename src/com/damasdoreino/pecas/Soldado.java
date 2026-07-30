package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Soldado extends Peca {

    public Soldado(CorPeca cor) {
        super(cor, TipoPeca.SOLDADO);
    }

    public boolean movimentoValido(
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        //Soldado anda apenas uma casa na diagonal;
        if (deltaColuna != 1) {
            return false;
        }

        //Cada cor possui um sentido de avanço;
        if (getCor() == CorPeca.BRANCO) {
            return deltaLinha == -1;
        }

        return deltaLinha == 1;
    }

}