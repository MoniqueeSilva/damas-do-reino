package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Soldado extends Peca {

    public Soldado(CorPeca cor) {
        super(cor, TipoPeca.SOLDADO);
    }

    @Override
    public boolean movimentoValido(int origemX, int origemY,
                                   int destinoX, int destinoY) {

        int deltaX = destinoX - origemX;
        int deltaY = destinoY - origemY;

        if (Math.abs(deltaX) != 1) {
            return false;
        }

        if (getCor() == CorPeca.BRANCO) {
            return deltaY == 1;
        }

        return deltaY == -1;
    }

}