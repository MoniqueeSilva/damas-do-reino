package com.damasdoreino.jogo;

import com.damasdoreino.enums.CorPeca;

/*
 * Representa um jogador da partida.
 * GRASP: Pure Fabrication (entidade de domínio para armazenar estado).
 */
public class Jogador {

    private final String nome;
    private final CorPeca cor;

    /*Cria um novo jogador com nome e cor associada. */
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