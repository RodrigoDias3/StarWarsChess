package pt.ulusofona.lp2.deisichess;

public abstract class Peca {
    protected int equipa;
    protected int id;
    protected int tipoPeca;
    protected String alcunha;
    protected int[] coordenadas = new int[2];
    protected String estado = "capturado";

    int pontosDeCaptura = 0;
    int nrCapturas = 0;
    int nrJogadasValidas = 0;
    int nrJogadasInvalidas = 0;

    public Peca(int id, int tipoPeca, int equipa, String alcunha) {
        this.id = id;
        this.tipoPeca = tipoPeca;
        this.alcunha = alcunha;
        this.equipa = equipa;
    }

    int getId() {
        return id;
    }

    void addCaptura(){
        nrCapturas++;
    }

    void addNrJogadasInvalidas(){
        nrJogadasInvalidas++;
    }

    void addNrJogadasValidas(){nrJogadasValidas++;}

    void addPontosPeca(int pontos){
        this.pontosDeCaptura += pontos;
    }

    int getTipoPeca() {
        return tipoPeca;
    }

    String getAlcunha() {
        return alcunha;
    }

    String getEstado() {
        return estado;
    }
    public String getEquipaNome(){
        if(equipa == 10){
            return "PRETA";
        }else{
            return "BRANCA";
        }
    }

    public int getNrCapturas() {
        return this.nrCapturas;
    }

    public int getNrJogadasInvalidas() {
        return this.nrJogadasInvalidas;
    }

    public int getNrJogadasValidas(){return this.nrJogadasValidas;}

    public int getPontosDeCaptura(){return this.pontosDeCaptura;}

    public abstract boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa);

    public abstract void atualizaNomeHeJ(int nrJogadas);

    public abstract int getPontos();

    void apagaCoordenadas() {
        coordenadas = null;
    }

    boolean verificaEstadoPeca() {
        return estado.equals("capturado");
    }

    void atualizarCoordenadas(int x, int y) {
        coordenadas[0] = x;
        coordenadas[1] = y;
    }

    void atulizaEstado() {
        if (estado.equals("capturado")) {
            estado = "em jogo";
        } else {
            estado = "capturado";
        }
    }

    int getEquipa() {
        return equipa;
    }

    String[] getInfoPiece() {
        String[] result = new String[7];
        result[0] = id + ""; //id
        result[1] = tipoPeca + ""; //tipo
        result[2] = equipa + ""; //equipa
        result[3] = alcunha; //alcunha
        result[4] = estado; //capturado ou em jogo
        if (verificaEstadoPeca()) { //se estiver capturada
            result[5] = "";
            result[6] = "";
        } else {
            result[5] = coordenadas[0] + ""; //x
            result[6] = coordenadas[1] + ""; //y
        }

        return result;
    }

    public abstract String stringPeca();

    String[] getSquareInfo() {
        String[] result = new String[5];
        result[0] = id + ""; //id
        result[1] = tipoPeca + ""; //tipo
        result[2] = equipa + ""; //equipa
        result[3] = alcunha; //nome
        result[4] = getPng(); //imagem
        return result;
    }

    public abstract String nomeTipo();

    public abstract void criaPngPeca();


    public abstract String getPng();
}