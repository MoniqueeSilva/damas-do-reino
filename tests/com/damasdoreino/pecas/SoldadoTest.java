package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Testes unitários para o Soldado comum.
 * Valida as regras da peça base: movimento para frente e captura por salto.
 */
public class SoldadoTest {

    private Tabuleiro tabuleiro;
    private Soldado soldadoBranco;

    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        soldadoBranco = new Soldado(CorPeca.BRANCO);
    }

    @Test
    public void movimentoDeveSerValidoQuandoDiagonalFrenteVazia() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        // Valida o movimento para cima, 1 casa diagonal, destino vazio
        assertTrue(soldadoBranco.movimentoValido(tabuleiro, 5, 4, 4, 5));
    }

    @Test
    public void movimentoDeveSerInvalidoQuandoDiagonalTras() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        // Valida que o soldado não pode andar para trás
        assertFalse(soldadoBranco.movimentoValido(tabuleiro, 5, 4, 6, 5));
    }

    @Test
    public void capturaDeveSerValidaQuandoInimigoAdjacenteEDestinoVazio() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 4, 5);
        // Valida o salto de 2 casas com uma peça inimiga no meio e destino vazio
        assertTrue(soldadoBranco.capturaValida(tabuleiro, 5, 4, 3, 6));
    }

    @Test
    public void executarCapturaDeveRemoverInimigoEMoverPeca() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 4, 5);

        soldadoBranco.executarCaptura(tabuleiro, 5, 4, 3, 6);

        // Valida a execução: origem vazia, inimigo removido do meio, peça no destino
        assertTrue(tabuleiro.casaEstaVazia(5, 4)); 
        assertTrue(tabuleiro.casaEstaVazia(4, 5)); 
        assertEquals(soldadoBranco, tabuleiro.getPeca(3, 6)); 
    }
}