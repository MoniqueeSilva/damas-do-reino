package com.damasdoreino.jogo;

public class Tabuleiro {
    private final int tamanho;
    private Casa[][] casas;

    public Tabuleiro() {
        this.tamanho = 8;
        this.casas = new Casa[tamanho][tamanho];
        inicializarCasas();
    }

    private void inicializarCasas() {
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                casas[linha][coluna] = new Casa(linha, coluna);
            }
        }
    }

    private boolean posicaoValida(int linha, int coluna){
        return linha >= 0 && linha < tamanho &&
                coluna >= 0 && coluna < tamanho;
    }

    public Casa getCasa(int linha, int coluna){
        if(!posicaoValida(linha, coluna)){
            return null;
        }
        return casas[linha][coluna];
    }
}
