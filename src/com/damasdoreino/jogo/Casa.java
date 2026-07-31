package com.damasdoreino.jogo;
import com.damasdoreino.pecas.Peca;

/*
 * Representa uma célula do tabuleiro (Grid).
 * GRASP: Pure Fabrication (abstração de baixo nível) e Indireção.
 */
public class Casa {
    private final int linha;
    private final int coluna;
    private Peca peca;

    /*Construtor padrão que define a posição da casa. */
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

    /*Retorna a peça atualmente contida na casa. */
    public Peca getPeca() {
        return peca;
    }

    /*Aloca uma peça na casa. */
    public void colocarPeca(Peca peca) {
        this.peca = peca;
    }

    /*Remove a peça atualmente alocada na casa. */
    public void removerPeca() {
        this.peca = null;
    }

    /*Verifica se a casa está desocupada. */
    public boolean estaVazia() {
        return peca == null;
    }
}