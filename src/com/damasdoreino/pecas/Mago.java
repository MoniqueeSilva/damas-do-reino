package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class Mago extends Peca {

    public Mago(CorPeca cor) {
        super(cor, TipoPeca.MAGO);
    }

    public boolean movimentoValido(
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        //Mago movimenta apenas na diagonal;
        return deltaLinha == deltaColuna;
    }

}