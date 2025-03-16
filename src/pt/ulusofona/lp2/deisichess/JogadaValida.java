package pt.ulusofona.lp2.deisichess;

public class JogadaValida implements Comparable<JogadaValida> {
    int x;
    int y;
    int pontos;

    public JogadaValida(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setPontos(int pontos){
        this.pontos = pontos;
    }

    @Override
    public int compareTo(JogadaValida anotherJogadaValida) {
        if (this.pontos == anotherJogadaValida.pontos) {
            return 0;
        }
        if (this.pontos < anotherJogadaValida.pontos) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ") -> " + pontos;
    }
}
