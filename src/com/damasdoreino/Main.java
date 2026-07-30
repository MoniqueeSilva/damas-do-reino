package com.damasdoreino;

import com.damasdoreino.console.ExibirTabuleiro;
import com.damasdoreino.jogo.Tabuleiro;

public class Main {

    public static void main(String[] args) {
        Tabuleiro tabuleiro = new Tabuleiro();
        ExibirTabuleiro exibir = new ExibirTabuleiro();
        exibir.imprimir(tabuleiro);

    }
}