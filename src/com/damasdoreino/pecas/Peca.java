package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public abstract class Peca {

    private CorPeca cor;
    private TipoPeca tipo;

    public Peca(CorPeca cor, TipoPeca tipo) {
        this.cor = cor;
        this.tipo = tipo;
    }

    public CorPeca getCor() {
        return cor;
    }

    public TipoPeca getTipo() {
        return tipo;
    }

    //Cada peça implementará sua própria regra de movimento.
    public abstract boolean movimentoValido(int origemX, int origemY, int destinoX, int destinoY);

}