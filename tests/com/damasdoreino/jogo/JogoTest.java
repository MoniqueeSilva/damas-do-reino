package com.damasdoreino.jogo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.pecas.*;

/*
 * Testes de integração e regras de negócio do Jogo.
 * Valida a promoção de peças, condições de vitória por eliminação e afogamento.
 */
public class JogoTest {

    @Test
    public void promocaoDeveOcorrerQuandoSoldadoAtingeFilaFinal() {
        // Cria um cenário controlado para forçar a promoção do Soldado para SoldadoReal
        Jogo jogo = new Jogo();
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        Soldado soldado = new Soldado(CorPeca.BRANCO);
        
        // Posiciona o soldado na penúltima linha (1,3) para movê-lo à última linha (0,4)
        tabuleiro.colocarPeca(soldado, 1, 3);
        
        // Simula a jogada e verifica se a promoção ocorreu (polimorfismo na criação da peça)
        boolean moveu = jogo.mover(1, 3, 0, 4);
        
        assertTrue(moveu);
        Peca pecaPromovida = tabuleiro.getPeca(0, 4);
        assertNotNull(pecaPromovida);
        assertEquals(TipoPeca.SOLDADO_REAL, pecaPromovida.getTipo());
        assertEquals(CorPeca.BRANCO, pecaPromovida.getCor());
    }

    @Test
    public void fimDeJogoDeveOcorrerPorEliminacao() {
        Jogo jogo = new Jogo();
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        
        // Simula a eliminação de todas as peças do adversário
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 5, 4);
        
        // Valida que o jogo identificou a vitória por falta de peças inimigas
        assertTrue(jogo.jogoTerminou());
    }

    @Test
    public void fimDeJogoDeveOcorrerPorAfogamento() {
        Jogo jogo = new Jogo();
        Tabuleiro tabuleiro = jogo.getTabuleiro();

        // Limpa o tabuleiro para criar um cenário de afogamento (sem movimentos válidos)
        tabuleiro.limparTabuleiro();

        // Posiciona um Soldado Branco no canto (0,0). Qualquer tentativa de movimento ou captura
        // cairia fora do tabuleiro (linha -1), configurando o afogamento.
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 0, 0);

        // Valida que o jogo identificou a condição de afogamento
        assertTrue(jogo.jogoTerminou());
    }
}