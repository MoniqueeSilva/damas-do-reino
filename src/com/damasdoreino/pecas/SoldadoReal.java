package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;

/*
 * Implementação da peça SoldadoReal (promovida).
 * SOLID: Aplicação do Princípio de Substituição de Liskov (LSP).
 * Herda de Soldado para reaproveitar a lógica de movimento, alterando apenas a direção.
 */
public class SoldadoReal extends Soldado {

    public SoldadoReal(CorPeca cor) {
        super(cor, TipoPeca.SOLDADO_REAL);
    }

    /*Sobrescrita do método herdado para permitir movimento em ambas as direções. */
    protected boolean isDirecaoValida(int deltaLinha) {
        return true; 
    }
}