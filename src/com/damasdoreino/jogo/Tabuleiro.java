package com.damasdoreino.jogo;
import com.damasdoreino.pecas.Peca;

/*
 * Representa o tabuleiro 8x8 do jogo.
 * GRASP: Information Expert (conhece as posições de todas as peças) e Pure Fabrication.
 * SOLID: SRP (única responsabilidade: gerenciar o grid e as peças).
 */
public class Tabuleiro {
    private final int tamanho;
    private final Casa[][] casas;

    public Tabuleiro() {
        this.tamanho = 8;
        this.casas = new Casa[tamanho][tamanho];
        inicializarCasas();
    }

    /*Inicializa a matriz de casas do tabuleiro. */
    private void inicializarCasas() {
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                casas[linha][coluna] = new Casa(linha, coluna);
            }
        }
    }

    /*Verifica se as coordenadas estão dentro dos limites do tabuleiro. */
    public boolean posicaoValida(int linha, int coluna){
        return linha >= 0 && linha < tamanho &&
                coluna >= 0 && coluna < tamanho;
    }

    /*Retorna o objeto Casa em uma coordenada, ou null se inválida. */
    public Casa getCasa(int linha, int coluna){
        if(!posicaoValida(linha, coluna)){
            return null;
        }
        return casas[linha][coluna];
    }

    /*Tenta alocar uma peça em uma posição específica. */
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

    /*Remove a peça de uma determinada coordenada. */
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

    /*Move uma peça da origem para o destino, removendo-a da origem. */
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

    /*Verifica se uma casa está desocupada. */
    public boolean casaEstaVazia(int linha, int coluna){
        Casa casa = getCasa(linha, coluna);
        if(casa == null){
            return false;
        }
        return casa.estaVazia();
    }

    /*Obtém a peça alocada em uma coordenada. */
    public Peca getPeca(int linha, int coluna){
        Casa casa = getCasa(linha, coluna);
        if(casa == null){
            return null;
        }
        return casa.getPeca();
    }

    /*Limpa todo o tabuleiro, removendo todas as peças. */
    public void limparTabuleiro(){
        for (int linha = 0; linha < tamanho; linha++) {
            for (int coluna = 0; coluna < tamanho; coluna++) {
                casas[linha][coluna].removerPeca();
            }
        }
    }
}