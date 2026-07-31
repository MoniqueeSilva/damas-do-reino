package com.damasdoreino.jogo;

import java.util.Scanner;

import com.damasdoreino.console.ExibirTabuleiro;
import com.damasdoreino.enums.TipoPeca;
import com.damasdoreino.enums.CorPeca;
import com.damasdoreino.pecas.Cavaleiro;
import com.damasdoreino.pecas.Mago;
import com.damasdoreino.pecas.Peca;
import com.damasdoreino.pecas.Soldado;
import com.damasdoreino.pecas.SoldadoReal;

/*
 * Orquestra a partida e a interação entre Tabuleiro e Peças.
 * GRASP: Controller (controla o fluxo do sistema) e Creator (cria Tabuleiro e peças).
 * SOLID: DIP (inversão de dependência) - Depende da abstração Peca, não das implementações concretas.
 */
public class Jogo {
    private Tabuleiro tabuleiro;
    private ExibirTabuleiro exibirTabuleiro;
    private CorPeca turnoAtual;
    private Scanner scanner;

    public Jogo() {
        this.tabuleiro = new Tabuleiro();
        this.exibirTabuleiro = new ExibirTabuleiro();
        this.turnoAtual = CorPeca.BRANCO;
        this.scanner = new Scanner(System.in);
    }

    /*Inicia uma nova partida. */
    public void iniciar() {
        posicionarPecas();
        while (!jogoTerminou()) {
            exibirTabuleiro.imprimir(tabuleiro);
            realizarTurno();
        }
        System.out.println("FIM DE JOGO!");
    }

    /*Posiciona as peças no tabuleiro conforme a configuração inicial do jogo. */
    private void posicionarPecas() {
        // Pretas
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 0, 1);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.PRETO), 0, 3);
        tabuleiro.colocarPeca(new Mago(CorPeca.PRETO), 0, 5);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 0, 7);

        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.PRETO), 1, 0);
        tabuleiro.colocarPeca(new Mago(CorPeca.PRETO), 1, 2);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 1, 4);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.PRETO), 1, 6);

        tabuleiro.colocarPeca(new Mago(CorPeca.PRETO), 2, 1);
        tabuleiro.colocarPeca(new Soldado(CorPeca.PRETO), 2, 3);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.PRETO), 2, 5);
        tabuleiro.colocarPeca(new Mago(CorPeca.PRETO), 2, 7);

        // Brancas
        tabuleiro.colocarPeca(new Mago(CorPeca.BRANCO), 5, 0);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.BRANCO), 5, 2);
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 5, 4);
        tabuleiro.colocarPeca(new Mago(CorPeca.BRANCO), 5, 6);

        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.BRANCO), 6, 1);
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 6, 3);
        tabuleiro.colocarPeca(new Mago(CorPeca.BRANCO), 6, 5);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.BRANCO), 6, 7);

        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 7, 0);
        tabuleiro.colocarPeca(new Mago(CorPeca.BRANCO), 7, 2);
        tabuleiro.colocarPeca(new Cavaleiro(CorPeca.BRANCO), 7, 4);
        tabuleiro.colocarPeca(new Soldado(CorPeca.BRANCO), 7, 6);
    }

    /*Executa um turno, coletando a entrada do usuário e chamando o método mover. */
    private void realizarTurno() {
        System.out.println();
        System.out.println("Turno: " + turnoAtual);

        System.out.print("Linha de origem: ");
        int origemLinha = scanner.nextInt();

        System.out.print("Coluna de origem: ");
        int origemColuna = scanner.nextInt();

        System.out.print("Linha de destino: ");
        int destinoLinha = scanner.nextInt();

        System.out.print("Coluna de destino: ");
        int destinoColuna = scanner.nextInt();

        boolean moveu = mover(origemLinha, origemColuna, destinoLinha, destinoColuna);
        if (moveu) {
            System.out.println("\n Movimento realizado.");
        } else {
            System.out.println("\n Movimento inválido.");
        }
    }

    /*Move ou realiza captura de uma peça. Aplica GRASP e polimorfismo ao delegar a validação para a própria peça. */
    public boolean mover(int origemLinha, int origemColuna, int destinoLinha, int destinoColuna) {
        Peca peca = selecionarPeca(origemLinha, origemColuna);
        if (peca == null) {
            System.out.println("Não existe peça nessa posição.");
            return false;
        }
        if (peca.getCor() != turnoAtual) {
            System.out.println("Você só pode mover peças do seu reino.");
            return false;
        }
        
        // 1. Prioridade: verifica se é uma CAPTURA válida
        if (peca.capturaValida(tabuleiro, origemLinha, origemColuna, destinoLinha, destinoColuna)) {
            peca.executarCaptura(tabuleiro, origemLinha, origemColuna, destinoLinha, destinoColuna);
            promoverSoldado(destinoLinha, destinoColuna);
            trocarTurno();
            return true;
        }

        // 2. Se não for captura, verifica MOVIMENTO normal
        if (!peca.movimentoValido(tabuleiro, origemLinha, origemColuna, destinoLinha, destinoColuna)) {
            System.out.println("Movimento inválido para essa peça.");
            return false;
        }

        boolean moveu = tabuleiro.moverPeca(origemLinha, origemColuna, destinoLinha, destinoColuna);
        if (!moveu) {
            System.out.println("Não foi possível mover a peça.");
            return false;
        }
        promoverSoldado(destinoLinha, destinoColuna);
        trocarTurno();
        return true;
    }

    /*Alterna o turno entre Branco e Preto. */
    private void trocarTurno() {
        if (turnoAtual == CorPeca.BRANCO) {
            turnoAtual = CorPeca.PRETO;
        } else {
            turnoAtual = CorPeca.BRANCO;
        }
    }

    /*Retorna a peça da posição informada. */
    public Peca selecionarPeca(int linha, int coluna) {
        return tabuleiro.getPeca(linha, coluna);
    }

    /*Promove um Soldado para Soldado Real quando atinge a linha final adversária. */
    private void promoverSoldado(int linha, int coluna) {
        Peca peca = tabuleiro.getPeca(linha, coluna);
        if (peca == null) {
            return;
        }
        if (peca.getTipo() != TipoPeca.SOLDADO){
            return;
        }
        if (peca.getCor() == CorPeca.BRANCO && linha == 0) {
            tabuleiro.removerPeca(linha, coluna);
            tabuleiro.colocarPeca(new SoldadoReal(CorPeca.BRANCO), linha, coluna);
            System.out.println("⭐ Soldado Branco promovido!");
        } else if (peca.getCor() == CorPeca.PRETO && linha == 7) {
            tabuleiro.removerPeca(linha, coluna);
            tabuleiro.colocarPeca(new SoldadoReal(CorPeca.PRETO), linha, coluna);
            System.out.println("⭐ Soldado Preto promovido!");
        }
    }

    /*Verifica se ainda existem peças da cor informada no tabuleiro. */
    private boolean possuiPecas(CorPeca cor) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro.getPeca(linha, coluna);
                if (peca != null && peca.getCor() == cor) {
                    return true;
                }
            }
        }
        return false;
    }

    /*Verifica a condição de afogamento (se o jogador atual não tem movimentos válidos). */
    private boolean temMovimentosValidos(CorPeca cor) {
        for (int linha = 0; linha < 8; linha++) {
            for (int coluna = 0; coluna < 8; coluna++) {
                Peca peca = tabuleiro.getPeca(linha, coluna);
                if (peca != null && peca.getCor() == cor) {
                    // Varre o tabuleiro procurando um movimento ou captura válida
                    for (int destinoLinha = 0; destinoLinha < 8; destinoLinha++) {
                        for (int destinoColuna = 0; destinoColuna < 8; destinoColuna++) {
                            if (peca.movimentoValido(tabuleiro, linha, coluna, destinoLinha, destinoColuna) ||
                                peca.capturaValida(tabuleiro, linha, coluna, destinoLinha, destinoColuna)) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    /*Verifica as condições de vitória (eliminação de todas as peças adversárias ou afogamento). */
    public boolean jogoTerminou() {
        if (!possuiPecas(CorPeca.BRANCO)) {
            System.out.println("\n🏆 Reino Preto venceu!");
            return true;
        }
        if (!possuiPecas(CorPeca.PRETO)) {
            System.out.println("\n🏆 Reino Branco venceu!");
            return true;
        }
        // Verifica afogamento do jogador atual
        if (!temMovimentosValidos(turnoAtual)) {
            System.out.println("\n🚫 " + turnoAtual + " não tem jogadas válidas! O adversário venceu!");
            return true;
        }
        return false;
    }

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public CorPeca getTurnoAtual() {
        return turnoAtual;
    }
}