package pt.ulusofona.lp2.deisichess;

public class TorreHor extends Peca {
    String nomeTipo;
    int valor;

    String png;

    TorreHor(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "TorreHor";
        valor = 3;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica se nao mudou de linha ou a peça fica no mesmo sitio
        if (y0 != y1 || x0 == x1) {
            return false;
        }
        if (x1 > x0) { //move para a esquerda
            for (int i = x0 + 1; i < x1; i++) {
                if (mapa[i][y0] != null) { //se tem peça no caminho
                    return false;
                }
            }
        } else { // para a direita
            for (int t = x0 - 1; t > x1; t--) {
                if (mapa[t][y0] != null) { //peça no caminho
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
            this.png = "torre_h_black.png";
        }else{
            this.png = "torre_h_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
