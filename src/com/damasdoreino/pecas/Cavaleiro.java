package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Cavaleiro extends Peca {

    public Cavaleiro(CorPeca cor) {
        super(cor, TipoPeca.CAVALEIRO);
    }

    @Override
    public boolean movimentoValido(int origemX, int origemY,
                                   int destinoX, int destinoY) {

        int deltaX = Math.abs(destinoX - origemX);
        int deltaY = Math.abs(destinoY - origemY);

        return (deltaX == 2 && deltaY == 1)
                || (deltaX == 1 && deltaY == 2);
    }

}