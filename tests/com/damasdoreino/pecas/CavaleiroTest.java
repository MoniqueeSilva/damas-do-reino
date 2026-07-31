package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

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
        // 2 para cima, 1 para direita
        assertTrue(cavaleiro.movimentoValido(tabuleiro, 4, 4, 2, 5));
    }

    @Test
    public void capturaDeveSerValidaQuandoDestinoContemInimigo() {
        tabuleiro.colocarPeca(cavaleiro, 4, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 2, 5);
        // Captura no destino, não precisa saltar
        assertTrue(cavaleiro.capturaValida(tabuleiro, 4, 4, 2, 5));
    }

    @Test
    public void executarCapturaDeveOcuparDestino() {
        tabuleiro.colocarPeca(cavaleiro, 4, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 2, 5);
        
        cavaleiro.executarCaptura(tabuleiro, 4, 4, 2, 5);
        
        assertTrue(tabuleiro.casaEstaVazia(4, 4)); // Origem vazia
        assertTrue(tabuleiro.casaEstaVazia(2, 5) == false); // Destino tem peça (o cavaleiro)
        assertEquals(cavaleiro, tabuleiro.getPeca(2, 5));
    }
}