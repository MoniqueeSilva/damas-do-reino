package com.damasdoreino.pecas;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.jogo.Tabuleiro;

public class Soldado extends Peca {

    // Construtor público padrão
    public Soldado(CorPeca cor) {
        this(cor, TipoPeca.SOLDADO);
    }

    // Construtor protegido para ser usado pelas subclasses (como SoldadoReal)
    protected Soldado(CorPeca cor, TipoPeca tipo) {
        super(cor, tipo);
    }

    // Método que define apenas a direção válida (para frente)
    protected boolean isDirecaoValida(int deltaLinha) {
        if (getCor() == CorPeca.BRANCO) {
            return deltaLinha < 0; // Branco precisa de um delta negativo (para cima)
        }
        return deltaLinha > 0;     // Preto precisa de um delta positivo (para baixo)
    }

    @Override
    public boolean movimentoValido(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // Soldado anda apenas uma casa na diagonal, o destino deve estar vazio e a direção deve ser válida
        return deltaColuna == 1 
            && Math.abs(deltaLinha) == 1 
            && tabuleiro.casaEstaVazia(destinoLinha, destinoColuna) 
            && isDirecaoValida(deltaLinha);
    }

    @Override
    public boolean capturaValida(
            Tabuleiro tabuleiro,
            int origemLinha,
            int origemColuna,
            int destinoLinha,
            int destinoColuna) {

        int deltaLinha = destinoLinha - origemLinha;
        int deltaColuna = Math.abs(destinoColuna - origemColuna);

        // O salto é de exatamente 2 casas na diagonal
        if (deltaColuna != 2 || Math.abs(deltaLinha) != 2) return false;
        // O destino deve estar vazio para pousar
        if (!tabuleiro.casaEstaVazia(destinoLinha, destinoColuna)) return false;
        // A direção do salto deve ser válida
        if (!isDirecaoValida(deltaLinha)) return false;

        int linhaMeio = (origemLinha + destinoLinha) / 2;
        int colunaMeio = (origemColuna + destinoColuna) / 2;
        Peca inimigo = tabuleiro.getPeca(linhaMeio, colunaMeio);

        return inimigo != null && inimigo.getCor() != getCor();
    }

    @Override
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