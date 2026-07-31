package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

/**
 * Define o contrato para todas as peças do jogo.
 * GRASP: Especialista na Informação e Polimorfismo.
 * SOLID: OCP (aberta para extensão, fechada para modificação) e LSP.
 */
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

    /** Cada peça conhece sua própria regra de movimento. */
    public abstract boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna);

    /** Cada peça conhece sua regra de captura. */
    public abstract boolean capturaValida(
        Tabuleiro tabuleiro,
        int origemLinha,
        int origemColuna,
        int destinoLinha,
        int destinoColuna);

    /** Cada peça sabe executar sua própria captura. Aplicação do GRASP Polimorfismo. */
    public abstract void executarCaptura(
        Tabuleiro tabuleiro,
        int origemLinha,
        int origemColuna,
        int destinoLinha,
        int destinoColuna);
}