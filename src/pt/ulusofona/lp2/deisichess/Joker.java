package pt.ulusofona.lp2.deisichess;

public class Joker extends Peca {
    String nomeTipo;

    String nomeASSUME = "Rainha";
    int valor;

    String png;

    Joker(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Joker/" + nomeASSUME;
        valor = 4;
        criaPngPeca();
    }

    String getNomeASSUME() {
        return nomeASSUME;
    }

    boolean verificaJogadaRainha(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        Peca rainha = new Rainha(1,1,10,"");
        return rainha.verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaJogadaPoneiMagico(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        Peca PoneiMagico = new PoneiMagico(1,2,10,"");
        return PoneiMagico.verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaJogadaPadreDaVila(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        Peca PadreDaVila = new PadreDaVila(1,3,10,"");
        return PadreDaVila.verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaJogadaTorreVert(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        Peca TorreVert = new TorreVert(1,5,10,"");
        return TorreVert.verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaJogadaTorreHor(int x0, int y0, int x1, int y1, Peca[][] mapa) {
       Peca TorreeHort = new TorreHor(1,4,10,"");
       return TorreeHort.verificaJogada(x0, y0, x1, y1, mapa);
    }

    boolean verificaJogadaHomerSimpson(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        Peca HomerSimpson = new HomerSimpson(1,6,10,"");
        return HomerSimpson.verificaJogada(x0, y0, x1, y1, mapa);
    }

    void atualizaNomeTipo() {
        nomeTipo = "Joker/" + nomeASSUME;
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        return switch (nomeASSUME) {
            case "Rainha" -> verificaJogadaRainha(x0, y0, x1, y1, mapa);
            case "Ponei Mágico" -> verificaJogadaPoneiMagico(x0, y0, x1, y1, mapa);
            case "Padre da Vila" -> verificaJogadaPadreDaVila(x0, y0, x1, y1, mapa);
            case "TorreHor" -> verificaJogadaTorreHor(x0, y0, x1, y1, mapa);
            case "TorreVert" -> verificaJogadaTorreVert(x0, y0, x1, y1, mapa);
            default -> verificaJogadaHomerSimpson(x0, y0, x1, y1, mapa);
        };
    }

    @Override
    public void atualizaNomeHeJ(int nrJogadas) {
        switch (nomeASSUME) {
            case "Rainha" -> nomeASSUME = "Ponei Mágico";
            case "Ponei Mágico" -> nomeASSUME = "Padre da Vila";
            case "Padre da Vila" -> nomeASSUME = "TorreHor";
            case "TorreHor" -> nomeASSUME = "TorreVert";
            case "TorreVert" -> nomeASSUME = "Homer Simpson";
            case "Homer Simpson" -> nomeASSUME = "Rainha";
        }
        atualizaNomeTipo();
    }

    @Override
    public int getPontos() {
        return valor;
    }

    @Override
    public String stringPeca() {
        if (coordenadas == null || estado.equals("capturado")) {
            return id + " | " + nomeTipo + " | " + valor + " | " + equipa + " | " + alcunha + " @ (n/a)";
        } else {
            return id + " | " + nomeTipo + " | " + valor + " | " + equipa + " | " + alcunha + " @ (" + coordenadas[0] + ", " + coordenadas[1] + ")";
        }
    }
    @Override
    public String nomeTipo() {
        return nomeTipo;
    }

    @Override
    public void criaPngPeca() {
        if(this.equipa == 10){
            this.png = "joker_black.png";
        }else{
            this.png = "joker_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
