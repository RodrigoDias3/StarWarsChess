package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Equipa {
    String nome;

    HashMap<Integer, Peca> pecas = new HashMap<>();
    private int nrCapturas = 0;
    private int nrJogadas = 0;
    private int nrJogadasInvalidas = 0;
    private boolean ganhou = false;

    public Equipa(String nome) {
        this.nome = nome;
    }

    int getNrJogadas() {
        return nrJogadas;
    }

    int getNrJogadasInvalidas() {
        return nrJogadasInvalidas;
    }


    void adicionarPeca(int id, Peca peca) {
        pecas.put(id, peca);
    }

    void addNrJogadasValidas() {
        nrJogadas++;
    }

    void addNrJogadasInvalidas() {
        nrJogadasInvalidas++;
    }

    void addNrCapturadas() {
        nrCapturas++;
    }

    void atualizarGanhou() {
        ganhou = true;
    }

    boolean getGanhou() {
        return ganhou;
    }

    int getPecasEmJogo() {
        int result = 0;
        for (Peca value : pecas.values()) {
            if (!value.getEstado().equals("capturado")) {
                result++;
            }
        }
        return result;
    }


    String[] equipaString() {
        String[] result = new String[4];
        result[0] = "Equipa das ";
        if (nome.equals("PRETA")) {
            result[0] += "Pretas";
        } else {
            result[0] += "Brancas";
        }
        result[1] = "" + nrCapturas;
        result[2] = "" + nrJogadas;
        result[3] = "" + nrJogadasInvalidas;
        return result;
    }
}