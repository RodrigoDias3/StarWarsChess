package pt.ulusofona.lp2.deisichess;

public class Guardiao extends Peca{

    String nomeTipo;

    int valor;

    String png;

    public Guardiao(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Guardião";
        valor = 2;
        criaPngPeca();
    }


    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        if (y0 != y1 && x0 == x1) { // movimento vertical
            if (y1 > y0) { // move para baixo
                if (y0 < y1-2){
                    return false;
                }
                for (int i = y0 + 1; i < y1; i++) {
                    if (mapa[x0][i] != null) { //se tem peça no caminho
                        return false;
                    }
                }
            } else { //move para cima
                if (y0 > y1+2){
                    return false;
                }
                for (int t = y0 - 1; t > y1; t--) {
                    if (mapa[x0][t] != null) { //se tem peça no caminho
                        return false;
                    }
                }
            }
            return true;
        }

        if (y0 == y1 && x0 != x1) { // movimento horizontal
            if (x1 > x0) { //move para a direita
                if (x0 < x1-2){
                    return false;
                }
                for (int i = x0 + 1; i < x1; i++) {
                    if (mapa[i][y0] != null) { //se tem peça no caminho
                        return false;
                    }
                }
            } else { // para a esquerda
                if (x0 > x1+2){
                    return false;
                }
                for (int t = x0 - 1; t > x1; t--) {
                    if (mapa[t][y0] != null) { //peça no caminho
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }

    @Override
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
            this.png = "guardiao_black.png";
        }else{
            this.png = "guardiao_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
