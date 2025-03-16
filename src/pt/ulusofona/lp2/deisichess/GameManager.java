package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.*;


//X coluna ------ Y Linhas
public class GameManager {
    Tabuleiro tabuleiro;
    HashMap<Integer, File> tabuleiroHistory = new HashMap<Integer, File>();

    int ronda = 0;

    public GameManager() {
    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {
        Scanner scanner = null;
        int dimTabuleiro = 0;
        int numerPecas = 0;
        int linhaLocal = 2;

        try {
            scanner = new Scanner(file);
            // le a primeira linha e guarda o tamanho
            dimTabuleiro = Integer.parseInt(scanner.nextLine());
            tabuleiro = new Tabuleiro(dimTabuleiro);
            //le o numero de peças e guarda no tabuleiro
            numerPecas = Integer.parseInt(scanner.nextLine());
            tabuleiro.setNrPecas(numerPecas);

            for (int i = 0; i < numerPecas; i++) {
                linhaLocal++;
                String linha = scanner.nextLine();
                String[] linhaPartes = linha.split(":");
                if (linhaPartes.length == 4) {
                    int id = Integer.parseInt(linhaPartes[0]);
                    int tipo = Integer.parseInt(linhaPartes[1]);
                    int equipa = Integer.parseInt(linhaPartes[2]);
                    String alcunha = linhaPartes[3];
                    if (equipa == 10) {
                        Peca peca = criaPecaTipo(id, tipo, equipa, alcunha);
                        tabuleiro.adicionaPecaEquipa(equipa, id, peca); //adiciona peça à sua respetiva equipa
                        tabuleiro.getAllPecas().put(id, peca); //adiciona peca ao hashMap the todas as peças
                    } else {
                        Peca peca = criaPecaTipo(id, tipo, equipa, alcunha);
                        tabuleiro.adicionaPecaEquipa(equipa, id, peca); //adiciona peça à sua respetiva equipa
                        tabuleiro.getAllPecas().put(id, peca); //adiciona peca ao hashMap the todas as peças
                    }

                } else {
                    throw new InvalidGameInputException(linhaLocal, linhaPartes.length);
                }
            }
            for (int y = 0; y < dimTabuleiro; y++) {
                String linha = scanner.nextLine();
                String[] linhaPart = linha.split(":");
                for (int x = 0; x < dimTabuleiro; x++) {
                    if (Integer.parseInt(linhaPart[x]) != 0) {
                        tabuleiro.getPeca(Integer.parseInt(linhaPart[x])).atulizaEstado();
                        tabuleiro.getPeca(Integer.parseInt(linhaPart[x])).atualizarCoordenadas(x, y);
                        tabuleiro.getMapa()[x][y] = tabuleiro.getPeca(Integer.parseInt(linhaPart[x]));
                        tabuleiro.getTabuleiroInicialIds()[y][x] = Integer.parseInt(linhaPart[x]);
                    } else {
                        tabuleiro.getMapa()[x][y] = null;
                    }
                }
            }
            //Le parte do save caso exista
            if (scanner.hasNext()) {
                int numeroJogadas = Integer.parseInt(scanner.nextLine());
                int x0 = 0;
                int y0 = 0;
                int x1 = 0;
                int y1 = 0;
                for (int i = 0; i < numeroJogadas; i++) { //faz o move das jogadas
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");
                    String[] partCord0 = parts[0].split(",");
                    String[] partCord1 = parts[1].split(",");
                    x0 = Integer.parseInt(partCord0[0]);
                    y0 = Integer.parseInt(partCord0[1]);
                    x1 = Integer.parseInt(partCord1[0]);
                    y1 = Integer.parseInt(partCord1[1]);
                    move(x0, y0, x1, y1);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Ficheiro não existe");
        }
    }

    Peca criaPecaTipo(int id, int tipo, int equipa, String alcunha) {
        return switch (tipo) {
            case 0 -> new Rei(id, tipo, equipa, alcunha);
            case 1 -> new Rainha(id, tipo, equipa, alcunha);
            case 2 -> new PoneiMagico(id, tipo, equipa, alcunha);
            case 3 -> new PadreDaVila(id, tipo, equipa, alcunha);
            case 4 -> new TorreHor(id, tipo, equipa, alcunha);
            case 5 -> new TorreVert(id, tipo, equipa, alcunha);
            case 6 -> new HomerSimpson(id, tipo, equipa, alcunha);
            case 7 -> new Joker(id, tipo, equipa, alcunha);
            case 100 -> new Guardiao(id, tipo, equipa, alcunha);
            default -> null;
        };
    }

    public int getBoardSize() {
        return tabuleiro.getDimensao();
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        if ((tabuleiro.verificaJogada(x0, y0, x1, y1) && tabuleiro.squareIsEmpty(x1, y1))) { //jogar e nao come sendo que verifica se a jogada é possivel e se o sitio esta vazio
            File file = new File("tempo.txt"); //ficheiro com o estado anterior
            try {
                saveGame(file); //guarda estando no ficheiro
            } catch (IOException ignored) {}
            tabuleiroHistory.put(ronda, file); //ficheiro history
            ronda++;
            tabuleiro.joga(x0, y0, x1, y1); //alteração no mapa
            tabuleiro.addJogada(x0, y0, x1, y1); ////add jogada para depois ser guardado no file
            return true;
        } else if ((tabuleiro.verificaJogada(x0, y0, x1, y1) && tabuleiro.verificaEquipasDiferentes(x0, y0, x1, y1) && verificaGuardiao(x0, y0, x1, y1))) { //joga e come
            File file = new File("tempo.txt");
            try {
                saveGame(file);
            } catch (IOException ignored) {}
            tabuleiroHistory.put(ronda, file);
            ronda++;
            tabuleiro.joga(x0, y0, x1, y1); //alteração no mapa
            tabuleiro.setCaptura();// atualiza a captura para true do jogo
            tabuleiro.limpaNrJogadas(); //contador de jogadas limpo
            tabuleiro.addJogada(x0, y0, x1, y1); //add jogada para depois ser guardado no file
            return true;
        } else { //joga invalida
            tabuleiro.getEquipa(tabuleiro.getEquipaIdAjogar()).addNrJogadasInvalidas(); //jogada invalida na equipa
            if (!tabuleiro.squareIsEmpty(x0, y0)) { //caso exista peça ele adiciona jogada invalida nela
                tabuleiro.getPecaByCordenada(x0, y0).addNrJogadasInvalidas();
            }
            tabuleiro.addJogada(x0, y0, x1, y1); //add jogada para depois ser guardado no file
            return false;
        }
    }

    public boolean verificaGuardiao(int x0, int y0, int x1, int y1) {
        return tabuleiro.verificaGuardiao(x0, y0, x1, y1);
    }

    public String[] getSquareInfo(int x, int y) {
        return tabuleiro.getInfoSquare(x, y);
    } //info do quadrado

    public String[] getPieceInfo(int ID) {
        return tabuleiro.getPeca(ID).getInfoPiece();
    } //info da peça

    public String getPieceInfoAsString(int ID) {
        return tabuleiro.getPeca(ID).stringPeca();
    } //info da peça como string

    public int getCurrentTeamID() {
        return tabuleiro.getEquipaIdAjogar();
    } //equipa a jogar

    public boolean gameOver() { // so uma equipa, so 1 peca de cada, 10 jogadas
        if (!tabuleiro.getCaptura()) { //verificar se ja está empatado ou nao antes de ver a condição de captura, podem vir 1 peça de cada no tabuleiro
            return tabuleiro.gameOver();
        } else if (tabuleiro.getNrJogadas() >= 10) {
            //nao existiu nenhuma captura ainda e pa houve 10 jogadas então empate
            return true;
        } else {
            return tabuleiro.gameOver();
            //verifica quem ganhou
        }
    }

    public ArrayList<String> getGameResults() {
        return tabuleiro.stringGameResult();
    }

    public JPanel getAuthorsPanel() {
        JPanel result = new JPanel();

        ImageIcon creditos = new ImageIcon("src/images/creditos2.png");
        JLabel creditosLabel = new JLabel(creditos);

        result.add(creditosLabel);

        return result;
    }

    public void saveGame(File file) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("" + tabuleiro.getDimensao() + "\n");//escreve dimensao
            writer.write("" + tabuleiro.getNrPecas() + "\n");//escreve nrPecas
            for (Integer id : tabuleiro.getAllPecas().keySet()) {
                Peca p = tabuleiro.getPeca(id);
                writer.write(p.getId() + ":" + p.getTipoPeca() + ":" + p.getEquipa() + ":" + p.getAlcunha() + "\n");
            }
            for (int y = 0; y < tabuleiro.getDimensao(); y++) {
                for (int x = 0; x < tabuleiro.getDimensao(); x++) {
                    writer.write("" + tabuleiro.getTabuleiroInicialIds()[y][x] + ":");
                }
                writer.write("\n");
            }
            //acrescenta Informação
            int numeroJogadasTotais = tabuleiro.getEquipa(10).getNrJogadas() + tabuleiro.getEquipa(20).getNrJogadas()
                    + tabuleiro.getEquipa(10).getNrJogadasInvalidas() +
                    tabuleiro.getEquipa(20).getNrJogadasInvalidas();
            writer.write("" + numeroJogadasTotais + "\n");
            for (String jogada : tabuleiro.getJogadas()) {
                writer.write(jogada + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao criar o ficheiro");
        }
    }

    public void undo() {
        ronda--;
        File file = tabuleiroHistory.get(ronda);
        try {
            loadGame(file);
        } catch (InvalidGameInputException | IOException ignored) {}
    }

    public List<Comparable> getHints(int x, int y) {

        if (x >= tabuleiro.getDimensao() || y >= tabuleiro.getDimensao() || x < 0 || y < 0) { //cordenadas com o tabuleiro
            return null;
        }
        if (tabuleiro.squareIsEmpty(x, y)) { //está vazia
            return null;
        }
        if (tabuleiro.getPecaByCordenada(x, y).getEquipa() != tabuleiro.getEquipaIdAjogar()) { //equipa diferente a jogar
            return null;
        }

        ArrayList<Comparable> jogadaValidas = new ArrayList<>();

        for (int y1 = 0; y1 < tabuleiro.getDimensao(); y1++) {
            for (int x1 = 0; x1 < tabuleiro.getDimensao(); x1++) {
                if (tabuleiro.verificaJogada(x, y, x1, y1) && (tabuleiro.verificaEquipasDiferentes(x, y, x1, y1) || tabuleiro.squareIsEmpty(x1, y1))) {
                    JogadaValida jogadaNova = new JogadaValida(x1, y1); //add jogada valida
                    if (tabuleiro.getPecaByCordenada(x1, y1) == null) { //verifica se exite peça ou nao
                        jogadaNova.setPontos(0); //add 0 pontos na jogada
                    } else {
                        jogadaNova.setPontos(tabuleiro.getPecaByCordenada(x1, y1).getPontos()); //add pontos da peça na jogada
                    }
                    jogadaValidas.add(jogadaNova);
                }
            }
        }

        return jogadaValidas;

    }

    public Map<String, String> customizeBoard() {
        HashMap<String, String> result = new HashMap<String, String>();

        result.put("title", "STARWARS DEISI CHESS");
        result.put("imageBlackSquare", "black-box.png");
        result.put("imageWhiteSquare", "white-box.png");
        result.put("imageBackground", "imageBackground.png");
        //result.put("boardMarginTop", "STARWARS DEISI CHESS");
        //result.put("boardMarginBottom", "STARWARS DEISI CHESS");
        return result;
        /*
        - title
        - imageBlackSquare - nome do ficheiro png (incluindo extensão)
        - imageWhiteSquare - nome do ficheiro png (incluindo extensão)
        - imageBackground - nome do ficheiro png (incluindo extensão)
        - boardMarginTopboardMarginTop - margem entre o tabuleiro e o topo da janela em pixeis
        - boardMarginBottom - margem entre o tabuleiro e o fundo da janela em pixeis
        */
    }


}


