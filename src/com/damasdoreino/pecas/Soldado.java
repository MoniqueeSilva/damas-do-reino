package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Implementação da peça Soldado.
 * GRASP: Polimorfismo (sobrescreve métodos da classe base).
 * SOLID: LSP (pode ser substituído por SoldadoReal sem quebrar o jogo).
 */
public class Soldado extends Peca {

    /*Construtor padrão para o Soldado comum. */
    public Soldado(CorPeca cor) {
        this(cor, TipoPeca.SOLDADO);
    }

    /*Construtor protegido para ser usado por subclasse SoldadoReal. */
    protected Soldado(CorPeca cor, TipoPeca tipo) {
        super(cor, tipo);
    }

    /*Valida se o movimento está indo na direção permitida (apenas para frente). */
    protected boolean isDirecaoValida(int deltaLinha) {
        if (getCor() == CorPeca.BRANCO) {
            return deltaLinha < 0;
        }
        return deltaLinha > 0;     
    }

   public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida andar 1 casa na diagonal, destino vazio e direção correta.
        return deltaColuna == 1 
            && Math.abs(deltaLinha) == 1 
            && tabuleiro.casaEstaVazia(destinoLinha, destinoColuna) 
            && isDirecaoValida(deltaLinha);
    }

    public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida salto de 2 casas, destino vazio e direção correta.
        if (deltaColuna != 2 || Math.abs(deltaLinha) != 2) return false;
        if (!tabuleiro.casaEstaVazia(destinoLinha, destinoColuna)) return false;
        if (!isDirecaoValida(deltaLinha)) return false;

        int linhaMeio = (origemLinha + destinoLinha) / 2;
        int colunaMeio = (origemColuna + destinoColuna) / 2;
        Peca inimigo = tabuleiro.getPeca(linhaMeio, colunaMeio);

        return inimigo != null && inimigo.getCor() != getCor();
    }

   public void executarCaptura(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {
        
        int linhaMeio = (origemLinha + destinoLinha) / 2;
        int colunaMeio = (origemColuna + destinoColuna) / 2;

        tabuleiro.removerPeca(linhaMeio, colunaMeio);
        tabuleiro.moverPeca(origemLinha, origemColuna, destinoLinha, destinoColuna);
    }
}