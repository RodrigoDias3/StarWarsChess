package pt.ulusofona.lp2.deisichess;

public class HomerSimpson extends Peca {
    String nomeTipo;
    int valor;

    String png;

    HomerSimpson(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Doh! zzzzzz";
        valor = 2;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica se está a dormir
        if (nomeTipo.equals("Doh! zzzzzz") && mapa[x0][y0].getTipoPeca() != 7) {
            return false;
        }
        //verifica se pode na diagonal à esquerda superior e inferior
        if ((x0 - 1 == x1 && y0 - 1 == y1) || (x0 - 1 == x1 && y0 + 1 == y1)) {
            return true;
        }
        //verifica se pode na diagonal à  direita  superior e inferior
        if ((x0 + 1 == x1 && y0 - 1 == y1) || (x0 + 1 == x1 && y0 + 1 == y1)) {
            return true;
        }

        return false;
    }



    @Override
    public void atualizaNomeHeJ(int nrJogadas) {
        if (nrJogadas % 3 == 0) {
            nomeTipo = "Doh! zzzzzz";
        } else {
            nomeTipo = "Homer Simpson";
        }
    }

    @Override
    public int getPontos() {
        return valor;
    }

    @Override
    public String stringPeca() {
        if (coordenadas == null || estado.equals("capturado")) {
            return id + " | " + nomeTipo + " | " + valor + " | " + equipa + " | " + alcunha + " @ (n/a)";
        } else if (nomeTipo.equals("Doh! zzzzzz")) {
            return nomeTipo;
        } else {
            return id + " | " + nomeTipo + " | " + valor + " | " + equipa + " | " + alcunha + " @ (" + coordenadas[0] + ", " + coordenadas[1] + ")";

        }
    }
    @Override
    public String nomeTipo() {
        return "Homer Simpson";
    }

    @Override
    public void criaPngPeca() {
        if(this.equipa == 10){
            this.png = "homer_black.png";
        }else{
            this.png = "homer_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
