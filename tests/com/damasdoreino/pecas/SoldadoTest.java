package com.damasdoreino.pecas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.jogo.Tabuleiro;

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
        // Branco anda para cima (linha diminui)
        assertTrue(soldadoBranco.movimentoValido(tabuleiro, 5, 4, 4, 5));
    }

    @Test
    public void movimentoDeveSerInvalidoQuandoDiagonalTras() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        // Branco não pode andar para trás
        assertFalse(soldadoBranco.movimentoValido(tabuleiro, 5, 4, 6, 5));
    }

    @Test
    public void capturaDeveSerValidaQuandoInimigoAdjacenteEDestinoVazio() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 4, 5);
        // Destino deve estar vazio para cair
        assertTrue(soldadoBranco.capturaValida(tabuleiro, 5, 4, 3, 6));
    }

    @Test
    public void executarCapturaDeveRemoverInimigoEMoverPeca() {
        tabuleiro.colocarPeca(soldadoBranco, 5, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 4, 5);
        
        assertTrue(tabuleiro.getPeca(4, 5) != null); // Tem inimigo
        assertTrue(tabuleiro.casaEstaVazia(3, 6)); // Destino vazio

        soldadoBranco.executarCaptura(tabuleiro, 5, 4, 3, 6);

        assertTrue(tabuleiro.casaEstaVazia(5, 4)); // Origem vazia
        assertTrue(tabuleiro.casaEstaVazia(4, 5)); // Inimigo removido
        assertEquals(soldadoBranco, tabuleiro.getPeca(3, 6)); // Peça no destino
    }
}