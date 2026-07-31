package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Implementação da peça Mago.
 * GRASP: Polimorfismo.
 * Realiza captura à distância: remove a peça inimiga do caminho sem se mover.
 */
public class Mago extends Peca {

    public Mago(CorPeca cor) {
        super(cor, TipoPeca.MAGO);
    }

   public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Valida movimento diagonal livre (como o bispo do xadrez).
        if (deltaLinha != deltaColuna || deltaLinha == 0) return false;
        return tabuleiro.casaEstaVazia(destinoLinha, destinoColuna);
    }

    public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = destinoColuna - origemColuna;

        // Valida a direção diagonal e se o destino está vazio (pois o mago não se move na captura).
        if (Math.abs(deltaLinha) != Math.abs(deltaColuna) || deltaLinha == 0) return false;
        if (!tabuleiro.casaEstaVazia(destinoLinha, destinoColuna)) return false;

        int passoLinha = Integer.signum(deltaLinha);
        int passoColuna = Integer.signum(deltaColuna);
        int linha = origemLinha + passoLinha;
        int coluna = origemColuna + passoColuna;
        Peca inimigo = null;

        // Percorre a diagonal procurando exatamente uma peça inimiga.
        while (linha != destinoLinha) {
            Peca peca = tabuleiro.getPeca(linha, coluna);
            if (peca != null) {
                if (peca.getCor() == getCor()) return false; 
                if (inimigo != null) return false;          
                inimigo = peca;
            }
            linha += passoLinha;
            coluna += passoColuna;
        }

        return inimigo != null;
    }

    public void executarCaptura(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = destinoColuna - origemColuna;
        int passoLinha = Integer.signum(deltaLinha);
        int passoColuna = Integer.signum(deltaColuna);
        int linha = origemLinha + passoLinha;
        int coluna = origemColuna + passoColuna;

        // Encontra e remove a peça inimiga. O mago permanece na sua posição original.
        while (linha != destinoLinha) {
            if (tabuleiro.getPeca(linha, coluna) != null) {
                tabuleiro.removerPeca(linha, coluna); 
                break; 
            }
            linha += passoLinha;
            coluna += passoColuna;
        }
    }
}