package pt.ulusofona.lp2.deisichess;

public class PadreDaVila extends Peca {
    String nomeTipo;
    int valor;
    String png;


    PadreDaVila(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Padre da Vila";
        valor = 3;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        if (x0 == x1 || y0 == y1 || Math.abs(x0 - x1) != Math.abs(y0 - y1)) {
            return false;
        }
        if (x1 > x0 && y1 > y0) {     // diagonal direita inferior
            boolean encontrouPeca = false;

            for (int i = 1; i <= 3; i++) {
                if ((x0 + i == x1 && y0 + i == y1)) { //quando chega á posição pode ir (|\)
                    return !encontrouPeca;
                }
                if (mapa[x0 + i][y0 + i] != null) { // verifica se há peça
                    encontrouPeca = true;
                }
            }
            return false;
        } else if (x1 > x0) {   // diagonal direita superior (|/)
            boolean encontrouPeca = false;

            for (int i = 1; i <= 3; i++) {
                if ((x0 + i == x1 && y0 - i == y1)) { //quando chega á posição pode ir
                    return !encontrouPeca;
                }
                if (mapa[x0 + i][y0 - i] != null) { // verifica se há peça
                    encontrouPeca = true;
                }
            }
            return false;
        } else if (y1 > y0) { // diagonal esquerda inferior (/|)
            boolean encontrouPeca = false;

            for (int i = 1; i <= 3; i++) {
                if ((x0 - i == x1 && y0 + i == y1)) { //quando chega á posição pode ir
                    return !encontrouPeca;
                }
                if (mapa[x0 - i][y0 + i] != null) { //se há peça
                    encontrouPeca = true;
                }
            }
            return false;
        } else { // diagonal esquerda superior (\|)
            boolean encontrouPeca = false;

            for (int i = 1; i <= 3; i++) {
                if ((x0 - i == x1 && y0 - i == y1)) { //quando chega á posição pode ir
                    return !encontrouPeca;
                }
                if (mapa[x0 - i][y0 - i] != null) { //se há peça
                    encontrouPeca = true;
                }
            }
            return false;
        }


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
            this.png = "padre_vila_black.png";
        }else{
            this.png = "padre_vila_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
