package com.damasdoreino.jogo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.pecas.*;

public class JogoTest {

    @Test
    public void promocaoDeveOcorrerQuandoSoldadoAtingeFilaFinal() {
        // 1. Criar um jogo e um tabuleiro controlado
        Jogo jogo = new Jogo();
        Tabuleiro tabuleiro = jogo.getTabuleiro();
        Soldado soldado = new Soldado(CorPeca.BRANCO);
        
        // 2. Posicionar o soldado na penúltima linha e fazer ele ir para a última (linha 0)
        tabuleiro.colocarPeca(soldado, 1, 3);
        
        // 3. Simular a jogada de promoção. No Jogo, isso acontece no método mover.
        // O método mover valida e chama promoverSoldado.
        // Vamos usar o próprio método público 'mover' do Jogo
        // Origem: (1,3), Destino: (0,4)
        
        // Forçamos o turno para branco, pois o jogo começa em branco mas podemos garantir
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
        
        // Posiciona apenas peças do jogador atual (Branco), sem peças pretas
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 5, 4);
        
        // Verifica se o jogo terminou (Não tem peças pretas)
        assertTrue(jogo.jogoTerminou());
    }

    @Test
    public void fimDeJogoDeveOcorrerPorAfogamento() {
        Jogo jogo = new Jogo();
        Tabuleiro tabuleiro = jogo.getTabuleiro();

        // 1. Limpa o tabuleiro para trabalhar com um cenário 100% controlado
        tabuleiro.limparTabuleiro();

        // 2. Coloca um único Soldado Branco no canto superior esquerdo (linha 0, coluna 0).
        // Regra do soldado: Anda apenas para frente na diagonal. Saindo de (0,0), qualquer 
        // movimento (seja ele normal ou captura) cairia fora do tabuleiro (linha -1).
        // Portanto, ele está PERFEITAMENTE imóvel.
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 0, 0);

        // 3. O turno atual é Branco, ele não tem movimento algum. Afogamento identificado!
        assertTrue(jogo.jogoTerminou());
    }
}