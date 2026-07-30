package com.damasdoreino.jogo;

import com.damasdoreino.enums.CorPeca;

public class Jogador {

    private final String nome;
    private final CorPeca cor;

    public Jogador(String nome, CorPeca cor) {
        this.nome = nome;
        this.cor = cor;
    }

    public String getNome() {
        return nome;
    }

    public CorPeca getCor() {
        return cor;
    }

}