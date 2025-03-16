package pt.ulusofona.lp2.deisichess;

public class TorreVert extends Peca {
    String nomeTipo;
    int valor;

    String png;

    TorreVert(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "TorreVert";
        valor = 3;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica se não mudou de coluna ou se ficou no mesmo sitio
        if (x0 != x1 || y0 == y1) {
            return false;
        }
        if (y1 > y0) { // move para baixo
            for (int i = y0 + 1; i < y1; i++) {
                if (mapa[x0][i] != null) { //se tem peça no caminho
                    return false;
                }
            }
        } else { //move para cima
            for (int t = y0 - 1; t > y1; t--) {
                if (mapa[x0][t] != null) { //se tem peça no caminho
                    return false;
                }
            }
        }

        return true;
    }

    public void atualizaNomeHeJ(int nrJogadas) {

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
            this.png = "torre_v_black.png";
        }else{
            this.png = "torre_v_white.png";
        }
    }
    @Override
    public String getPng(){
        return this.png;
    }
}
