package com.damasdoreino.jogo;
import com.damasdoreino.pecas.Peca;

public class Tabuleiro {
    private final int tamanho;
    private final Casa[][] casas;

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

    public boolean posicaoValida(int linha, int coluna){
        return linha >= 0 && linha < tamanho &&
                coluna >= 0 && coluna < tamanho;
    }

    public Casa getCasa(int linha, int coluna){
        if(!posicaoValida(linha, coluna)){
            return null;
        }
        return casas[linha][coluna];
    }

    public boolean colocarPeca(Peca peca, int linha, int coluna){
        if(!posicaoValida(linha, coluna)){
            return false;
        }
        Casa casa = getCasa(linha, coluna);
        if(!casa.estaVazia()){
            return false;
        }
        casa.colocarPeca(peca);
        return true;
    }

    public boolean removerPeca(int linha, int coluna) {
        if (!posicaoValida(linha, coluna)) {
            return false;
        }
        Casa casa = getCasa(linha, coluna);
            if (casa.estaVazia()) {
                return false;
            }
            casa.removerPeca();

            return true;
    }

    public boolean moverPeca(int origemLinha, int origemColuna, int destinoLinha, int destinoColuna) {
        if (!posicaoValida(origemLinha, origemColuna) || !posicaoValida(destinoLinha, destinoColuna)) {
            return false;
        }
        Casa origem = getCasa(origemLinha, origemColuna);
        Casa destino = getCasa(destinoLinha, destinoColuna);
        if (origem.estaVazia()) {
            return false;
        }
        if (!destino.estaVazia()) {
            return false;
        }

        Peca peca = origem.getPeca();
        destino.colocarPeca(peca);
        origem.removerPeca();
        return true;
    }

    public boolean casaEstaVazia(int linha, int coluna){
        Casa casa = getCasa(linha, coluna);
        if(casa == null){
            return false;
        }
        return casa.estaVazia();
    }

    public Peca getPeca(int linha, int coluna){
        Casa casa = getCasa(linha, coluna);
        if(casa == null){
            return null;
        }
        return casa.getPeca();
    }

    public void limparTabuleiro(){
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                casas[linha][coluna].removerPeca();
            }
        }
    }
}
