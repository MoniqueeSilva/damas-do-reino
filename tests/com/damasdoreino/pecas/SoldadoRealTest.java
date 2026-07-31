package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

/*
 * Testes unitários para o SoldadoReal.
 * Valida a herança e o princípio LSP: reaproveita o código do Soldado,
 * mas habilita o movimento em ambas as direções.
 */
public class SoldadoRealTest {

    private Tabuleiro tabuleiro;
    private SoldadoReal soldadoReal;

    @BeforeEach
    public void setUp() {
        tabuleiro = new Tabuleiro();
        soldadoReal = new SoldadoReal(CorPeca.BRANCO);
    }

    @Test
    public void movimentoDeveSerValidoDiagonalFrente() {
        tabuleiro.colocarPeca(soldadoReal, 5, 4);
        // Valida movimento para frente (herdado do Soldado)
        assertTrue(soldadoReal.movimentoValido(tabuleiro, 5, 4, 4, 5));
    }

    @Test
    public void movimentoDeveSerValidoDiagonalTras() {
        tabuleiro.colocarPeca(soldadoReal, 5, 4);
        // Valida a sobrescrita de comportamento: agora pode andar para trás
        assertTrue(soldadoReal.movimentoValido(tabuleiro, 5, 4, 6, 5));
    }

    @Test
    public void capturaDeveSerValidaEmQualquerDirecao() {
        tabuleiro.colocarPeca(soldadoReal, 5, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 6, 5);
        // Valida a captura (salto) em qualquer direção devido à sobrescrita da direção
        assertTrue(soldadoReal.capturaValida(tabuleiro, 5, 4, 7, 6));
    }
}