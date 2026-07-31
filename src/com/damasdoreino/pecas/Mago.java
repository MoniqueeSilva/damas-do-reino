package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

public class Mago extends Peca {

    public Mago(CorPeca cor) {
        super(cor, TipoPeca.MAGO);
    }

    @Override
    public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = Math.abs(destinoLinha - origemLinha);
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Mago movimenta apenas na diagonal;
        if (deltaLinha != deltaColuna || deltaLinha == 0) return false;
        // O destino deve estar vazio
        return tabuleiro.casaEstaVazia(destinoLinha, destinoColuna);
    }

    @Override
    public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = destinoColuna - origemColuna;

        // Deve ser uma diagonal
        if (Math.abs(deltaLinha) != Math.abs(deltaColuna) || deltaLinha == 0) return false;

        // O destino da magia (onde o Mago "mira") deve estar vazio
        if (!tabuleiro.casaEstaVazia(destinoLinha, destinoColuna)) return false;

        int passoLinha = Integer.signum(deltaLinha);
        int passoColuna = Integer.signum(deltaColuna);
        int linha = origemLinha + passoLinha;
        int coluna = origemColuna + passoColuna;
        Peca inimigo = null;

        // Percorre a diagonal procurando uma peça inimiga
        while (linha != destinoLinha) {
            Peca peca = tabuleiro.getPeca(linha, coluna);
            if (peca != null) {
                if (peca.getCor() == getCor()) return false; // Amigo no caminho bloqueia
                if (inimigo != null) return false;          // Mais de um inimigo no caminho bloqueia
                inimigo = peca;
            }
            linha += passoLinha;
            coluna += passoColuna;
        }

        // Só é válido se tiver exatamente UMA peça inimiga no caminho
        return inimigo != null;
    }

    @Override
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

        // Percorre o caminho novamente para encontrar e remover o alvo inimigo
        while (linha != destinoLinha) {
            if (tabuleiro.getPeca(linha, coluna) != null) {
                tabuleiro.removerPeca(linha, coluna); // Remove o inimigo
                break; // Para a busca, o Mago não se move
            }
            linha += passoLinha;
            coluna += passoColuna;
        }
    }
}