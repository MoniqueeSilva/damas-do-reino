package com.damasdoreino.jogo;
import com.damasdoreino.pecas.Peca;

public class Casa {
    private final int linha;
    private final int coluna;
    private Peca peca;

    public Casa(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public Peca getPeca() {
        return peca;
    }

    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }

    public void removerPeca() {
        this.peca = null;
    }

    public boolean estaVazia() {
        return peca == null;
    }
}