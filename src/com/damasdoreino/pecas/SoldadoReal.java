package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

public class SoldadoReal extends Soldado {

    public SoldadoReal(CorPeca cor) {
        // Chama o construtor protegido do pai passando o tipo correto
        super(cor, TipoPeca.SOLDADO_REAL);
    }

    protected boolean isDirecaoValida(int deltaLinha) {
        return true; // Ambas as direções são válidas
    }
}