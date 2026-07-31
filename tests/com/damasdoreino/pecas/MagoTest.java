package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Testes unitários para a peça Mago.
 * Valida a regra de captura à distância e o movimento diagonal livre.
 */
public class MagoTest {

    private Tabuleiro tabuleiro;
    private Mago mago;

    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        mago = new Mago(CorPeca.BRANCO);
    }

    @Test
    public void movimentoDeveSerValidoDiagonalCaminhoLivre() {
        tabuleiro.colocarPeca(mago, 7, 0);
        // Valida o movimento diagonal
        assertTrue(mago.movimentoValido(tabuleiro, 7, 0, 4, 3));
    }

    @Test
    public void capturaDeveSerValidaQuandoUmInimigoNoCaminho() {
        tabuleiro.colocarPeca(mago, 7, 0);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 5, 2);
        // Valida a captura à distância: destino vazio e exatamente um inimigo no caminho
        assertTrue(mago.capturaValida(tabuleiro, 7, 0, 3, 4));
    }

    @Test
    public void executarCapturaDeveRemoverInimigoSemMoverPeca() {
        tabuleiro.colocarPeca(mago, 7, 0);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 5, 2);
        
        mago.executarCaptura(tabuleiro, 7, 0, 3, 4);
        
        // Valida que o inimigo foi removido do caminho e o mago permaneceu na origem
        assertTrue(tabuleiro.casaEstaVazia(5, 2)); 
        assertEquals(mago, tabuleiro.getPeca(7, 0)); 
    }
}