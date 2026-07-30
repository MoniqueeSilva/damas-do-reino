package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

public abstract class Peca {

    private final CorPeca cor;
    private final TipoPeca tipo;

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

    //Cada peça conhece sua própria regra de movimento;
    public abstract boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna);

}