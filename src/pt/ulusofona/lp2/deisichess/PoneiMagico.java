package pt.ulusofona.lp2.deisichess;

public class PoneiMagico extends Peca {
    String nomeTipo;
    int valor;
    String png;

    PoneiMagico(int id, int tipoPeca, int equipa, String alcunha) {
        super(id, tipoPeca, equipa, alcunha);
        nomeTipo = "Ponei Mágico";
        valor = 5;
        criaPngPeca();
    }

    @Override
    public boolean verificaJogada(int x0, int y0, int x1, int y1, Peca[][] mapa) {
        //verifica se está a dar o mesmo sitio e se está numa diagonal
        if (x0 == x1 || y0 == y1 || Math.abs(x0 - x1) != Math.abs(y0 - y1)) {
            return false;
        }

        if (x1 == x0 + 2 && y1 == y0 + 2) {   // diagonal direita inferior
            boolean encontrouPecaCaminho1 = false;
            boolean encontrouPecaCaminho2 = false;

            for (int i = 1; i <= 2; i++) {
                if (x0 + i == x1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 + i][y0 + t] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }

                }
                if (mapa[x0 + i][y0] != null) {
                    encontrouPecaCaminho1 = true;
                    break;
                }
            }

            for (int i = 1; i <= 2; i++) {
                if (y0 + i == y1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 + t][y0 + i] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0][y0 + i] != null) {
                    encontrouPecaCaminho2 = true;
                    break;
                }
            }

            return !encontrouPecaCaminho1 || !encontrouPecaCaminho2;
        } else if (x1 == x0 + 2 && y1 == y0 - 2) {  // diagonal direita superior
            boolean encontrouPecaCaminho1 = false;
            boolean encontrouPecaCaminho2 = false;

            for (int i = 1; i <= 2; i++) {
                if (x0 + i == x1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 + i][y0 - t] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0 + i][y0] != null) {
                    encontrouPecaCaminho1 = true;
                    break;
                }
            }

            for (int i = 1; i <= 2; i++) {
                if (y0 - i == y1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 + t][y0 - i] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0][y0 - i] != null) {
                    encontrouPecaCaminho2 = true;
                    break;
                }
            }

            return !encontrouPecaCaminho1 || !encontrouPecaCaminho2;
        } else if (x1 == x0 - 2 && y1 == y0 + 2) {  // diagonal esquerda inferior
            boolean encontrouPecaCaminho1 = false;
            boolean encontrouPecaCaminho2 = false;

            for (int i = 1; i <= 2; i++) {
                if (x0 - i == x1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 - i][y0 + t] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0 - i][y0] != null) {
                    encontrouPecaCaminho1 = true;
                    break;
                }
            }

            for (int i = 1; i <= 2; i++) {
                if (y0 + i == y1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 - t][y0 + i] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0][y0 + i] != null) {
                    encontrouPecaCaminho2 = true;
                    break;
                }
            }

            return !encontrouPecaCaminho1 || !encontrouPecaCaminho2;
        } else if (x1 == x0 - 2 && y1 == y0 - 2) {  // diagonal esquerda superior
            boolean encontrouPecaCaminho1 = false;
            boolean encontrouPecaCaminho2 = false;

            for (int i = 1; i <= 2; i++) {
                if (x0 - i == x1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 - i][y0 - t] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0 - i][y0] != null) {
                    encontrouPecaCaminho1 = true;
                    break;
                }
            }

            for (int i = 1; i <= 2; i++) {
                if (y0 + i == y1) {
                    for (int t = 1; t <= 2; t++) {
                        if (mapa[x0 - t][y0 - i] != null) {
                            encontrouPecaCaminho1 = true;
                            break;
                        }
                    }
                }
                if (mapa[x0][y0 - i] != null) {
                    encontrouPecaCaminho2 = true;
                    break;
                }
            }

            return !encontrouPecaCaminho1 || !encontrouPecaCaminho2;
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
            this.png = "ponei_magico_black.png";
        }else{
            this.png = "ponei_magico_white.png";
        }
    }

    @Override
    public String getPng(){
        return this.png;
    }
}
