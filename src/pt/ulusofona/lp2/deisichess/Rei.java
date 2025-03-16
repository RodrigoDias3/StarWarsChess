package pt.ulusofona.lp2.deisichess;

public class Rei extends Peca {
    String nomeTipo;
    int pontos;

    String png;

    Rei(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Rei";
        pontos = 1000;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica coluna lado esquerdo
        if ((x0 - 1 == x1 && y0 - 1 == y1) || (x0 - 1 == x1 && y0 == y1) || (x0 - 1 == x1 && y0 + 1 == y1)) {
            return true;
        }
        //verifica cima e em baixo
        if ((x0 == x1 && y0 - 1 == y1) || (x0 == x1 && y0 + 1 == y1)) {
            return true;
        }
        //verifica coluna lado direito
        if ((x0 + 1 == x1 && y0 - 1 == y1) || (x0 + 1 == x1 && y0 == y1) || (x0 + 1 == x1 && y0 + 1 == y1)) {
            return true;
        }

        return false;
    }

    public void atualizaNomeHeJ(int nrJogadas) {

    }

    @Override
    public int getPontos() {
        return pontos;
    }

    @Override
    public String stringPeca() {
        if (coordenadas == null || estado.equals("capturado")) {
            return id + " | " + nomeTipo + " | " + "(infinito)" + " | " + equipa + " | " + alcunha + " @ (n/a)";
        } else {
            return id + " | " + nomeTipo + " | " + "(infinito)" + " | " + equipa + " | " + alcunha + " @ (" + coordenadas[0] + ", " + coordenadas[1] + ")";
        }
    }

    @Override
    public String nomeTipo() {
        return nomeTipo;
    }

    @Override
    public void criaPngPeca() {
        if(this.equipa == 10){
            this.png = "crazy_emoji_black.png";
        }else{
            this.png = "crazy_emoji_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
