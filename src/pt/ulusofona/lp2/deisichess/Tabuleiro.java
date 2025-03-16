package pt.ulusofona.lp2.deisichess;

import java.util.ArrayList;
import java.util.HashMap;

public class Tabuleiro {

    private boolean captura = false;
    private int dimTabuleiro;
    private int nrPecas;
    private int equipaIdAjogar = 10;
    Equipa[] equipas = new Equipa[2]; //copiar
    private Peca[][] mapa; //copiar

    int[][] tabuleiroInicialIds;
    private int nrJogadas = 0;
    HashMap<Integer, Peca> allPecas = new HashMap<>(); // copiarpeças

    ArrayList<String> jogadas = new ArrayList<>();

    public Tabuleiro(int dimTabuleiro) {
        this.dimTabuleiro = dimTabuleiro;
        equipas[0] = new Equipa("PRETA");
        equipas[1] = new Equipa("BRANCA");
        criarMapa();
        criaTabuleiroId();
    }


    void limpaNrJogadas() {
        nrJogadas = 0;
    }

    void setCaptura() {
        captura = true;
    }

    boolean getCaptura() {
        return captura;
    }

    int getNrPecas() {
        return nrPecas;
    }

    void criaTabuleiroId() {
        int[][] tabu = new int[dimTabuleiro][dimTabuleiro];
        for (int y = 0; y < dimTabuleiro; y++) {
            for (int x = 0; x < dimTabuleiro; x++) {
                tabu[x][y] = 0;
            }
        }
        tabuleiroInicialIds = tabu;
    }

    int[][] getTabuleiroInicialIds() {
        return tabuleiroInicialIds;
    }


    ArrayList<String> getJogadas() {
        return jogadas;
    }

    void addJogada(int x0, int y0, int x1, int y1) {
        jogadas.add("" + x0 + "," + y0 + ";" + x1 + "," + y1);
    }

    int getNrJogadas() {
        return nrJogadas;
    }

    Equipa getEquipa(int id) {
        if (id == 10) {
            return equipas[0];
        } else {
            return equipas[1];
        }
    }
    int getDimensao() {
        return dimTabuleiro;
    }

    void setNrPecas(int numero) {
        this.nrPecas = numero;
    }

    void adicionaPecaEquipa(int equipa, int id, Peca peca) {
        //10 se for preta 20 se for branca
        if (equipa == 10) {
            equipas[0].adicionarPeca(id, peca);
        } else {
            equipas[1].adicionarPeca(id, peca);
        }
    }

    void criarMapa() {
        mapa = new Peca[dimTabuleiro][dimTabuleiro];
    }

    Peca[][] getMapa() {
        return mapa;
    }

    public HashMap<Integer, Peca> getAllPecas() {
        return allPecas;
    }

    int getEquipaIdAjogar() {
        return equipaIdAjogar;
    }

    Peca getPeca(int id) {
        return allPecas.get(id);
    }

    String[] getInfoSquare(int x, int y) {
        if(x < 0 || y < 0 || x>= dimTabuleiro || y >= dimTabuleiro){ //bverifica se a cordenada está fora das dimensoes do tabuleiro
            return null;
        } else if (squareIsEmpty(x, y)) {  //não há peça ou seja retorna vazio
            String[] result = new String[0];
            return result;
        } else {
            return mapa[x][y].getSquareInfo(); //existe peça
        }

    }

    int getNrJogadasTotais() {
        return equipas[0].getNrJogadas() + equipas[1].getNrJogadas();
    }

    boolean squareIsEmpty(int x, int y) {
        if (x >= dimTabuleiro || y >= dimTabuleiro || x < 0 || y < 0) {
            return true;
        }

        return mapa[x][y] == null;
    }

    void atuazilarEquipaIdAjogar() {
        if (equipaIdAjogar == 10) {
            equipaIdAjogar = 20;
        } else {
            equipaIdAjogar = 10;
        }
    }

    ArrayList<String> stringGameResult() {
        ArrayList<String> result = new ArrayList<>();

        result.add("JOGO DE CRAZY CHESS");
        result.add("Resultado: " + equipaVencedora()); //get equipa que ganhou
        result.add("---");
        //resultado pretas
        result.add(equipas[0].equipaString()[0]); //nome
        result.add(equipas[0].equipaString()[1]); //capturas
        result.add(equipas[0].equipaString()[2]); //jogadas
        result.add(equipas[0].equipaString()[3]); //jogadasInvalidas
        //resultado brancas
        result.add(equipas[1].equipaString()[0]); //nome
        result.add(equipas[1].equipaString()[1]); //capturas
        result.add(equipas[1].equipaString()[2]); //jogadas
        result.add(equipas[1].equipaString()[3]); //jogadasInvalidas

        return result;
    }

    void joga(int x0, int y0, int x1, int y1) { // comer ou andar
        if (squareIsEmpty(x1, y1)) { //andar
            getMapa()[x1][y1] = getPecaByCordenada(x0, y0); //move a peça de sitio
            getMapa()[x0][y0] = null; //atualiza onde ela estava
            getPecaByCordenada(x1, y1).atualizarCoordenadas(x1, y1); //atualiza cordenadas da peça que moveu
            getEquipa(getPecaByCordenada(x1, y1).getEquipa()).addNrJogadasValidas(); //add jogada valida equipa
        } else { //comer
            getPecaByCordenada(x0, y0).addPontosPeca(getPecaByCordenada(x1, y1).getPontos());
            getMapa()[x0][y0].addCaptura(); // adiciona captura
            getMapa()[x1][y1].atulizaEstado(); //peça comida atualiza o estado
            getMapa()[x1][y1].apagaCoordenadas(); //torna peça invisivel
            getMapa()[x1][y1] = getPecaByCordenada(x0, y0); //move peça sitio
            getMapa()[x0][y0] = null; //limpa casa anteriro
            getPecaByCordenada(x1, y1).atualizarCoordenadas(x1, y1); //peça que comeu atualiza coordenadas
            getEquipa(getPecaByCordenada(x1, y1).getEquipa()).addNrJogadasValidas(); //add valida na equipa
            getEquipa(getPecaByCordenada(x1, y1).getEquipa()).addNrCapturadas(); //add captura na equipa

        }
        nrJogadas++; //numero de jogadas totais
        getPecaByCordenada(x1,y1).addNrJogadasValidas(); //adiciona na peça a jogada valida
        atuazilarEquipaIdAjogar(); //muda equipa a jogar
        for (Peca value : allPecas.values()) {
            value.atualizaNomeHeJ(getNrJogadasTotais()); //atualiza o nome do homer
        }
    }

    boolean verificaJogada(int x0, int y0, int x1, int y1) {
        if (x1 >= dimTabuleiro || y1 >= dimTabuleiro || x1 < 0 || y1 < 0) { //verifica dimensão do tabuleiro da cordenada de chegada
            return false;
        }
        if (x0 >= dimTabuleiro || y0 >= dimTabuleiro || x0 < 0 || y0 < 0) { //verifica dimensão do tabuleiro cordenada de partida
            return false;
        }
        if (squareIsEmpty(x0, y0)) { //se o sitio de partida está vazio
            return false;
        }
        if (getPecaByCordenada(x0, y0).getEquipa() != equipaIdAjogar) { //se foi a equipa correta a jogar a sua peça
            return false;
        }
        //se nada for verificado faz para a peça de partida a sua propria verificação
        return getPecaByCordenada(x0, y0).verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaEquipasDiferentes(int x0, int y0, int x1, int y1) {
        if (getPecaByCordenada(x0, y0) == null) {
            return false;
        }
        if (getPecaByCordenada(x1, y1) == null) {
            return false;
        }
        return getPecaByCordenada(x0, y0).getEquipa() != getPecaByCordenada(x1, y1).getEquipa();
    }

    String equipaVencedora() {
        if (equipas[0].getGanhou()) {
            return "VENCERAM AS PRETAS";
        } else if (equipas[1].getGanhou()) {
            return "VENCERAM AS BRANCAS";
        } else {
            return "EMPATE";
        }
    }

    Peca getPecaByCordenada(int x, int y) {
        return mapa[x][y];
    }

    boolean gameOver() {
        int contRei0 = 0;
        int contRei1 = 0;
        for (int x = 0; x < dimTabuleiro; x++) {
            for (int y = 0; y < dimTabuleiro; y++) {
                Peca peca = mapa[x][y];
                if (peca != null) {
                    if (peca.getEquipa() == 10 && peca.getTipoPeca() == 0) {
                        contRei0++;
                    } else if (peca.getEquipa() == 20 && peca.getTipoPeca() == 0) {
                        contRei1++;
                    }
                }
            }
        }
        //iteração para verificar quantidade de reis de cada equipa

        if ((equipas[1].getPecasEmJogo() == 1 && equipas[0].getPecasEmJogo() == 1) && (contRei0 == 1 && contRei1 == 1)) {
            return true;
            //verifica caso de empate
        } else if (contRei0 == 0) {
            //ganhou branco
            equipas[1].atualizarGanhou();
            return true;
        } else if (contRei1 == 0) {
            //ganhou preta
            equipas[0].atualizarGanhou();
            return true;
        } else {
            //continua jogo
            return false;
        }
    }

    public boolean verificaGuardiao(int x0, int y0, int x1, int y1) {
        for (int i = -1; i < 2;i++) {
            if ((x1 - 1 > 0 && y1 + i > 0) && (x1 - 1 < dimTabuleiro && y1 + i < dimTabuleiro) && mapa[x1 - 1][y1 + i] != null) {
                if(verificaEquipasDiferentes(x0, y0, x1 - 1, y1 + i) && mapa[x1 - 1][y1 + i].getTipoPeca() == 100){
                    return false; // nao pode comer
                }
            }
        }
        for (int i = -1; i < 2;i++) {
            if ((x1 + 1 > 0 && y1 + i > 0) && (x1 + 1 < dimTabuleiro && y1 + i < dimTabuleiro) && mapa[x1 + 1][y1 + i] != null) {
                if (verificaEquipasDiferentes(x0, y0, x1 + 1,y1 + i) && mapa[x1 + 1][y1 + i].getTipoPeca() == 100){
                    return false; // nao pode comer
                }
            }
        }
        if ((y1 + 1 > 0 && y1 + 1 < dimTabuleiro) && mapa[x1][y1 + 1] != null) {
            if (verificaEquipasDiferentes(x0, y0, x1,y1 + 1) && mapa[x1][y1 + 1].getTipoPeca() == 100){
                return false; // nao pode comer
            }
        }
        if ((y1 - 1 > 0 && y1 - 1 < dimTabuleiro) && mapa[x1][y1 - 1] != null) {
            if (verificaEquipasDiferentes(x0, y0, x1,y1 - 1) && mapa[x1][y1 - 1].getTipoPeca() == 100){
                return false; // nao pode comer
            }
        }
        return true; // pode comer
    }
}