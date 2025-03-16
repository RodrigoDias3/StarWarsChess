package pt.ulusofona.lp2.deisichess;

public class InvalidGameInputException extends Exception {

    int linhaErro;
    int nrArgumentosLinha;

    public InvalidGameInputException(int linha, int nrArgumentosLinha) {
        this.linhaErro = linha;
        this.nrArgumentosLinha = nrArgumentosLinha;
    }

    public int getLineWithError() {
        //retorna o numero da linha do ficheiro com erro
        return linhaErro;
    }

    public String getProblemDescription() {
        String result = "";
        if (nrArgumentosLinha > 4) {
            result += "DADOS A MAIS (Esperava: 4 ; Obtive: " + nrArgumentosLinha + ")";
        } else {
            result += "DADOS A MENOS (Esperava: 4 ; Obtive: " + nrArgumentosLinha + ")";
        }
        return result;
    }
}
