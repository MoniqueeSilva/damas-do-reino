package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Mago extends Peca {

    public Mago(CorPeca cor) {
        super(cor, TipoPeca.MAGO);
    }

    @Override
    public boolean movimentoValido(int origemX, int origemY, int destinoX, int destinoY) {

        return Math.abs(destinoX - origemX) == Math.abs(destinoY - origemY);
    }

}