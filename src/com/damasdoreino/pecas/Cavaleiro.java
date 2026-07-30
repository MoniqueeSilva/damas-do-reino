package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Cavaleiro extends Peca {

    public Cavaleiro(CorPeca cor) {
        super(cor, TipoPeca.CAVALEIRO);
    }

    public boolean movimentoValido(
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        //Cavaleiro move em L;
        return (deltaLinha == 2 && deltaColuna == 1)
                || (deltaLinha == 1 && deltaColuna == 2);
    }

}