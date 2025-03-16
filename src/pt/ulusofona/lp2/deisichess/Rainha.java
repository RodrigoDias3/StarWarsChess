package pt.ulusofona.lp2.deisichess;

public class Rainha extends Peca {
    String nomeTipo;
    int valor;

    String png;


    Rainha(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Rainha";
        valor = 8;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica se existe peça no local e verifica se a peça é rainha
        if (mapa[x1][y1] != null && mapa[x1][y1].getTipoPeca() == 1) {
            return false;
        } else if (mapa[x1][y1] != null && mapa[x1][y1].getTipoPeca() == 7) { // verifica se é joker se for rainha nao pode comer
            Joker joker = (Joker) mapa[x1][y1];
            if (joker.getNomeASSUME().equals("Rainha")){
                return false;
            }
        }
        if (y0 != y1 && x0 == x1) {   // Jogada Vertical
            if (y1 > y0) {  // Jogar Para Baixo
                if (y0 < y1 - 5) { // Verifica se esta no limite para a baixo
                    return false;
                }
                for (int i = y0 + 1; i < y1; i++) { //verifica se ha peças no caminho
                    if (mapa[x0][i] != null) {
                        return false;
                    }
                }
            } else {   // Jogar Para Cima
                if (y0 > y1 + 5) { // Verifica se esta no limite para a cima
                    return false;
                }
                for (int t = y0 - 1; t > y1; t--) { //verifica se ha peças no caminho
                    if (mapa[x0][t] != null) {
                        return false;
                    }
                }
            }

            return true;
        } else if (x0 != x1 && y0 == y1) {   // Jogada Horizontal
            if (x1 > x0) {  // Jogar Para a direita
                if (x0 < x1 - 5) { // Verifica se esta no limite para a direita
                    return false;
                }
                for (int i = x0 + 1; i < x1; i++) { //verifica se ha peças no caminho
                    if (mapa[i][y0] != null) {
                        return false;
                    }
                }
            } else {   // Jogar Para a esquerda
                if (x0 > x1 + 5) {  // Verifica se esta no limite para a esquerda
                    return false;
                }
                for (int t = x0 - 1; t > x1; t--) {  //verifica se ha peças no caminho
                    if (mapa[t][y0] != null) {
                        return false;
                    }
                }
            }

            return true;
        } else if (Math.abs(x0 - x1) == Math.abs(y0 - y1)) {  // Jogada Vertical
            if (x1 > x0 && y1 > y0) {     // diagonal direita inferior
                boolean encontrouPeca = false;

                for (int i = 1; i <= 5; i++) {  // percorre a diagonal inferior direita
                    if ((x0 + i == x1 && y0 + i == y1)) {
                        return !encontrouPeca;
                    }
                    if (mapa[x0 + i][y0 + i] != null) {
                        encontrouPeca = true;
                    }
                }
                return false;
            } else if (x1 > x0) {   // diagonal direita superior
                boolean encontrouPeca = false;

                for (int i = 1; i <= 5; i++) {
                    if ((x0 + i == x1 && y0 - i == y1)) {
                        return !encontrouPeca;
                    }
                    if (mapa[x0 + i][y0 - i] != null) {
                        encontrouPeca = true;
                    }
                }
                return false;
            } else if (y1 > y0) { // diagonal esquerda inferior
                boolean encontrouPeca = false;

                for (int i = 1; i <= 5; i++) {
                    if ((x0 - i == x1 && y0 + i == y1)) {
                        return !encontrouPeca;
                    }
                    if (mapa[x0 - i][y0 + i] != null) {
                        encontrouPeca = true;
                    }
                }
                return false;
            } else { // diagonal esquerda superior
                boolean encontrouPeca = false;

                for (int i = 1; i <= 5; i++) {
                    if ((x0 - i == x1 && y0 - i == y1)) {
                        return !encontrouPeca;
                    }
                    if (mapa[x0 - i][y0 - i] != null) {
                        encontrouPeca = true;
                    }
                }
                return false;
            }
        }

        return false;
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
            this.png = "rainha_black.png";
        }else{
            this.png = "rainha_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
