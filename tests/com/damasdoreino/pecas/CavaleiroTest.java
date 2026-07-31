package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Testes unitários para a peça Cavaleiro.
 * Valida o padrão GRASP Polimorfismo e o movimento/captura específicos do Cavaleiro.
 */
public class CavaleiroTest {

    private Tabuleiro tabuleiro;
    private Cavaleiro cavaleiro;

    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        cavaleiro = new Cavaleiro(CorPeca.BRANCO);
    }

    @Test
    public void movimentoDeveSerValidoFormatoL() {
        tabuleiro.colocarPeca(cavaleiro, 4, 4);
        // Valida o movimento em L (2 para cima, 1 para direita)
        assertTrue(cavaleiro.movimentoValido(tabuleiro, 4, 4, 2, 5));
    }

    @Test
    public void capturaDeveSerValidaQuandoDestinoContemInimigo() {
        tabuleiro.colocarPeca(cavaleiro, 4, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 2, 5);
        // Valida a captura por ocupação (não precisa saltar, apenas ocupar o destino com inimigo)
        assertTrue(cavaleiro.capturaValida(tabuleiro, 4, 4, 2, 5));
    }

    @Test
    public void executarCapturaDeveOcuparDestino() {
        tabuleiro.colocarPeca(cavaleiro, 4, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 2, 5);
        
        cavaleiro.executarCaptura(tabuleiro, 4, 4, 2, 5);
        
        // Valida que a peça inimiga foi removida e o cavaleiro ocupou o destino
        assertTrue(tabuleiro.casaEstaVazia(4, 4)); 
        assertEquals(cavaleiro, tabuleiro.getPeca(2, 5));
    }
}