package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TestGameManager {
    @Test
    public void testMain(){
        Main.main(new String[0]);
    }

    @Test
    public void testLoadGame(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertEquals(8,jogo.getBoardSize());
        assertEquals(10,jogo.getCurrentTeamID());
        assertFalse(jogo.gameOver());
    }

    @Test
    public void testLoadGameComErro1() {
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xErro1.txt");
        assertThrows(InvalidGameInputException.class,()->{
            jogo.loadGame(ficheiro);
        });
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException e) {
            assertEquals(3,e.getLineWithError());
            assertEquals("DADOS A MENOS (Esperava: 4 ; Obtive: 3)",e.getProblemDescription());
        } catch (IOException ignored) {}
    }

    @Test
    public void testLoadGameComErro2() {
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/Erro2.txt");
        assertThrows(FileNotFoundException.class,()->{
            jogo.loadGame(ficheiro);
        });
    }

    @Test
    public void testLoadGameComErro3() {
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xErro3.txt");
        assertThrows(InvalidGameInputException.class,()->{
            jogo.loadGame(ficheiro);
        });
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException e) {
            assertEquals(3,e.getLineWithError());
            assertEquals("DADOS A MAIS (Esperava: 4 ; Obtive: 5)",e.getProblemDescription());
        } catch (IOException ignored) {}
    }


    @Test
    public void testMoveInvalido1EquipaAJogar(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(0,7,0,6));
    }

    @Test
    public void testMoveInvalidoComerMesmaEquipa(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(0,0,1,0));
    }

    @Test
    public void testMoveInvalidoForaTabuleiro(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(-1,-1,8,8));
    }

    @Test
    public void testMoveInvalidoOrigemVazia(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(4,3,4,2));
    }

    @Test
    public void testGetHintsSquareEmpty(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(null, jogo.getHints(0,3));
    }

    @Test
    public void testGetHintsEquipaDiferenteJoga(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(null, jogo.getHints(1,7));
    }

    @Test
    public void testGetHints(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        List<Comparable> hints = jogo.getHints(0,0);
        assertEquals(2, hints.size());
        assertEquals("(0,1) -> 0", hints.get(0).toString());
        assertEquals("(1,1) -> 0", hints.get(1).toString());
    }

    @Test
    public void testMoveValidoRei(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(0,0,0,1));
    }

    @Test
    public void testMoveInalidoRei(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(0,0,0,2));
    }

    @Test
    public void testMoveValidoRainha(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(1,0,1,5));
    }

    @Test
    public void testMoveInvalidoRainha(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(1,0,1,7));
    }

    @Test
    public void testMoveValidoPoneiMagico(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(2,0,4,2));
    }

    @Test
    public void testMoveInvalidoPoneiMagico(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(2,0,4,1));
    }

    @Test
    public void testMoveValidoPadreDaVila(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(3,0,6,3));
    }

    @Test
    public void testMoveInvalidoPadreDaVila(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(3,0,7,4));
    }

    @Test
    public void testMoveInvalidoTorreHor(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(4,0,4,1));
    }

    @Test
    public void testMoveValidoTorreVert(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(5,0,5,4));
    }

    @Test
    public void testMoveInvalidoTorreVert(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(5,0,6,0));
    }

    @Test
    public void testMoveInvalidoHomerDormir(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(6,0,5,1));
    }

    @Test
    public void testMoveInvalidoHomer(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(0,0,0,1));
        assertFalse(jogo.move(6,7,6,6));
    }

    @Test
    public void testMoveValidoHomerE(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(0,0,0,1));
        assertTrue(jogo.move(6,7,5,6));
    }

    @Test
    public void testMoveValidoHomerD(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(0,0,0,1));
        assertTrue(jogo.move(6,7,7,6));
    }

    @Test
    public void testMoveValidoAndar(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(10,jogo.getCurrentTeamID());
        assertTrue(jogo.move(0,0,0,1));
        assertEquals(20,jogo.getCurrentTeamID());
        assertEquals(0, jogo.getSquareInfo(0, 0).length);
        assertTrue(jogo.getSquareInfo(0,1).length > 0);
    }
    @Test
    public void testMoveValidoAndarComCoordenadasFora(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(10,jogo.getCurrentTeamID());
        assertTrue(jogo.move(0,0,0,1));
        assertEquals(20,jogo.getCurrentTeamID());
        assertEquals(null, jogo.getSquareInfo(-1, -3));
        assertEquals(null, jogo.getSquareInfo(40, 20));
        assertEquals(null, jogo.getSquareInfo(0, -1));
        assertEquals(null, jogo.getSquareInfo(8, 0));
    }


    @Test
    public void testMoveValidoComer(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(10,jogo.getCurrentTeamID());
        assertTrue(jogo.move(5,0,5,7));
        assertEquals(20,jogo.getCurrentTeamID());
        assertEquals(0, jogo.getSquareInfo(5, 0).length);
        assertTrue(jogo.getSquareInfo(5,7).length > 0);
    }

    @Test
    public void testJogoComVitoriaBranca(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(0,0,0,1);
        jogo.move(1,7,1,4);
        assertFalse(jogo.move(1,0,1,4));
        jogo.move(2,0,4,2);
        jogo.move(5,7,5,0);
        jogo.move(4,0,5,0);
        jogo.move(1,4,3,4);
        jogo.move(3,0,0,3);
        assertFalse(jogo.gameOver());
        jogo.move(3,4,0,1);
        assertTrue(jogo.gameOver());
        assertEquals("VENCERAM AS BRANCAS",jogo.tabuleiro.equipaVencedora());

        ArrayList<String> resultados = jogo.getGameResults();

        assertEquals("JOGO DE CRAZY CHESS",resultados.get(0));
        assertEquals("Resultado: VENCERAM AS BRANCAS",resultados.get(1));
        assertEquals("---",resultados.get(2));
        assertEquals("Equipa das Pretas",resultados.get(3));
        assertEquals("1",resultados.get(4));
        assertEquals("4",resultados.get(5));
        assertEquals("1",resultados.get(6));
        assertEquals("Equipa das Brancas",resultados.get(7));
        assertEquals("2",resultados.get(8));
        assertEquals("4",resultados.get(9));
        assertEquals("0",resultados.get(10));
    }

    @Test
    public void testJogoComVitoriaPreta(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(0,0,0,1);
        jogo.move(1,7,1,4);
        assertFalse(jogo.move(1,0,1,4));
        jogo.move(2,0,4,2);
        jogo.move(5,7,5,0);
        jogo.move(4,0,5,0);
        jogo.move(1,4,3,4);
        jogo.move(1,0,1,2);
        assertFalse(jogo.gameOver());
        assertFalse(jogo.move(3,4,0,1));
        jogo.move(0,7,1,6);
        jogo.move(1,2,1,6);
        assertTrue(jogo.gameOver());
        assertEquals("VENCERAM AS PRETAS",jogo.tabuleiro.equipaVencedora());
    }

    @Test
    public void testJogoComEmpate10Jogadas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(5,0,5,7);
        jogo.move(0,7,0,6);
        jogo.move(0,0,0,1);
        jogo.move(0,6,0,7);
        jogo.move(0,1,0,0);
        jogo.move(0,7,0,6);
        jogo.move(0,0,0,1);
        jogo.move(0,6,0,7);
        jogo.move(0,1,0,0);
        jogo.move(0,7,0,6);
        jogo.move(0,0,0,1);
        assertTrue(jogo.gameOver());
        assertEquals("EMPATE",jogo.tabuleiro.equipaVencedora());
    }

    @Test
    public void testMoveJoker(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertFalse(jogo.move(7,0,1,6));
        assertFalse(jogo.move(7,0,7,7));
        assertFalse(jogo.move(7,0,5,0));

        assertTrue(jogo.move(7,0,7,5));

        assertFalse(jogo.move(7,7,7,6));
        assertFalse(jogo.move(7,7,6,6));
        assertFalse(jogo.move(7,7,5,5));

        assertTrue(jogo.move(0,7,0,6));

        assertFalse(jogo.move(7,5,3,1));
        assertFalse(jogo.move(7,5,7,6));
        assertFalse(jogo.move(7,5,3,5));
        assertFalse(jogo.move(7,5,7,4));

        assertTrue(jogo.move(7,5,5,7));

        jogo.move(0,6,0,7);

        assertTrue(jogo.move(5,7,5,1));
        assertTrue(jogo.move(7,7,6,6));
        assertTrue(jogo.move(5,1,5,2));
        assertTrue(jogo.move(0,7,0,6));
        assertTrue(jogo.move(5,2,3,4));
        assertTrue(jogo.move(6,6,3,6));

    }

    @Test
    public void testStringEquipa(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        String texto1[] = jogo.tabuleiro.equipas[0].equipaString();
        String texto2[] = jogo.tabuleiro.equipas[1].equipaString();

        assertEquals("Equipa das Pretas",texto1[0]);
        assertEquals("0",texto1[1]);
        assertEquals("0",texto1[2]);
        assertEquals("0",texto1[3]);

        assertEquals("Equipa das Brancas",texto2[0]);
        assertEquals("0",texto2[1]);
        assertEquals("0",texto2[2]);
        assertEquals("0",texto2[3]);

        jogo.move(0,0,0,1);
        jogo.move(0,7,0,6);
        jogo.move(0,1,0,7);
        jogo.move(0,1,0,0);

        String texto3[] = jogo.tabuleiro.equipas[0].equipaString();
        String texto4[] = jogo.tabuleiro.equipas[1].equipaString();

        assertEquals("Equipa das Pretas",texto1[0]);
        assertEquals("0",texto3[1]);
        assertEquals("2",texto3[2]);
        assertEquals("1",texto3[3]);

        assertEquals("Equipa das Brancas",texto2[0]);
        assertEquals("0",texto4[1]);
        assertEquals("1",texto4[2]);
        assertEquals("0",texto4[3]);
    }

    @Test
    public void testUndo(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(0,0,0,1);
        assertEquals(0, jogo.getSquareInfo(0, 0).length);
        assertTrue(jogo.getSquareInfo(0,1).length > 0);
        jogo.undo();
        assertEquals(0, jogo.getSquareInfo(0, 1).length);
        assertTrue(jogo.getSquareInfo(0,0).length > 0);
    }

    @Test
    public void testHintsTorreVert(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        List<Comparable> hints = jogo.getHints(5,0);

        assertEquals(7, hints.size());
        assertEquals("(5,1) -> 0", hints.get(0).toString());
        assertEquals("(5,2) -> 0", hints.get(1).toString());
        assertEquals("(5,3) -> 0", hints.get(2).toString());
        assertEquals("(5,4) -> 0", hints.get(3).toString());
        assertEquals("(5,5) -> 0", hints.get(4).toString());
        assertEquals("(5,6) -> 0", hints.get(5).toString());
        assertEquals("(5,7) -> 3", hints.get(6).toString());
    }

    @Test
    public void testJogadaValidaCompare(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        JogadaValida jv1 = new JogadaValida(0,0);
        jv1.setPontos(5);
        JogadaValida jv2 = new JogadaValida(0,1);
        jv2.setPontos(0);
        JogadaValida jv3 = new JogadaValida(0,2);
        jv3.setPontos(10);
        JogadaValida jv4 = new JogadaValida(0,3);
        jv4.setPontos(5);

        assertEquals(0, jv1.compareTo(jv4));
        assertEquals(-1, jv1.compareTo(jv2));
        assertEquals(1, jv1.compareTo(jv3));
    }

    @Test
    public void testStringPecas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        int id = jogo.tabuleiro.getPecaByCordenada(0,0).getId();
        String peca = jogo.getPieceInfoAsString(id);

        assertEquals("1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (0, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(1,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("2 | Rainha | 8 | 10 | A Dama Selvagem @ (1, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(2,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("3 | Ponei Mágico | 5 | 10 | O Grande Artista @ (2, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(3,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("4 | Padre da Vila | 3 | 10 | Amante de Praia @ (3, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(4,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("5 | TorreHor | 3 | 10 | Artolas @ (4, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(5,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("6 | TorreVert | 3 | 10 | O Maior Grande @ (5, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(6,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("Doh! zzzzzz",peca);

        id = jogo.tabuleiro.getPecaByCordenada(7,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (7, 0)",peca);

        jogo.move(0,0,0,1);

        id = jogo.tabuleiro.getPecaByCordenada(6,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("7 | Homer Simpson | 2 | 10 | Hommie @ (6, 0)",peca);

        id = jogo.tabuleiro.getPecaByCordenada(7,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("8 | Joker/Ponei Mágico | 4 | 10 | O Beberolas @ (7, 0)",peca);

    }

    @Test
    public void testStringPecasCapturadas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        jogo.tabuleiro.getPecaByCordenada(0,0).atulizaEstado();
        int id = jogo.tabuleiro.getPecaByCordenada(0,0).getId();
        String peca = jogo.getPieceInfoAsString(id);

        assertEquals("1 | Rei | (infinito) | 10 | O Poderoso Chefao @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(1,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(1,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("2 | Rainha | 8 | 10 | A Dama Selvagem @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(2,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(2,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("3 | Ponei Mágico | 5 | 10 | O Grande Artista @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(3,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(3,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("4 | Padre da Vila | 3 | 10 | Amante de Praia @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(4,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(4,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("5 | TorreHor | 3 | 10 | Artolas @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(5,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(5,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("6 | TorreVert | 3 | 10 | O Maior Grande @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(6,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(6,0).getId();
        peca = jogo.getPieceInfoAsString(id);


        assertEquals("7 | Doh! zzzzzz | 2 | 10 | Hommie @ (n/a)",peca);

        jogo.tabuleiro.getPecaByCordenada(7,0).atulizaEstado();
        id = jogo.tabuleiro.getPecaByCordenada(7,0).getId();
        peca = jogo.getPieceInfoAsString(id);

        assertEquals("8 | Joker/Rainha | 4 | 10 | O Beberolas @ (n/a)",peca);
    }

    @Test
    public void testNomeTipoPecas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        String peca = jogo.tabuleiro.getPecaByCordenada(0,0).nomeTipo();

        assertEquals("Rei",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(1,0).nomeTipo();

        assertEquals("Rainha",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(2,0).nomeTipo();

        assertEquals("Ponei Mágico",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(3,0).nomeTipo();

        assertEquals("Padre da Vila",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(4,0).nomeTipo();

        assertEquals("TorreHor",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(5,0).nomeTipo();

        assertEquals("TorreVert",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(6,0).nomeTipo();

        assertEquals("Homer Simpson",peca);

        peca = jogo.tabuleiro.getPecaByCordenada(7,0).nomeTipo();

        assertEquals("Joker/Rainha",peca);
    }

    @Test
    public void testPontosPecas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        int peca = jogo.tabuleiro.getPecaByCordenada(0,0).getPontos();

        assertEquals(1000,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(1,0).getPontos();

        assertEquals(8,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(2,0).getPontos();

        assertEquals(5,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(3,0).getPontos();

        assertEquals(3,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(4,0).getPontos();

        assertEquals(3,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(5,0).getPontos();

        assertEquals(3,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(6,0).getPontos();

        assertEquals(2,peca);

        peca = jogo.tabuleiro.getPecaByCordenada(7,0).getPontos();

        assertEquals(4,peca);
    }

    @Test
    public void testSaveGame(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(0,0,0,1);

        File ficheiro2 = new File("test-files/temp.txt");
        try {
            jogo.saveGame(ficheiro2);
        } catch (IOException ignored) {}

        try {
            jogo.loadGame(ficheiro2);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertEquals(0, jogo.getSquareInfo(0, 0).length);
    }

    @Test
    public void testCriaPecaInvalida(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        Peca peca = jogo.criaPecaTipo(1,8,10,"");

        assertNull(peca);
    }

    @Test
    public void testCustomizeBoard(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        Map<String, String> result = jogo.customizeBoard();

        assertNotNull(result);
    }

    @Test
    public void testJPanel(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        JPanel result = jogo.getAuthorsPanel();

        assertNotNull(result);
    }

    @Test
    public void testGetPieceInfo(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        String[] result = jogo.getPieceInfo(1);

        assertEquals("1", result[0]);
        assertEquals("0", result[1]);
        assertEquals("10", result[2]);
        assertEquals("O Poderoso Chefao", result[3]);
        assertEquals("em jogo", result[4]);
        assertEquals("0", result[5]);
        assertEquals("0", result[6]);
    }

    @Test
    public void testEstatistica(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        StatType statType = StatType.TOP_5_CAPTURAS;

        ArrayList<String> result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals("O Poderoso Chefao (PRETA) fez 0 capturas", result.get(0));
        assertEquals("A Dama Selvagem (PRETA) fez 0 capturas", result.get(1));
        assertEquals("O Grande Artista (PRETA) fez 0 capturas", result.get(2));
        assertEquals("Amante de Praia (PRETA) fez 0 capturas", result.get(3));
        assertEquals("Artolas (PRETA) fez 0 capturas", result.get(4));

        statType = StatType.TOP_5_PONTOS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals(0, result.size());

        statType = StatType.PECAS_MAIS_5_CAPTURAS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals(0, result.size());

        statType = StatType.PECAS_MAIS_BARALHADAS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals(0, result.size());

        statType = StatType.TIPOS_CAPTURADOS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals(0, result.size());
    }


    @Test
    public void testGetPieceInfoComida(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(5,0,5,7);

        String[] result = jogo.getPieceInfo(14);

        assertEquals("14", result[0]);
        assertEquals("5", result[1]);
        assertEquals("20", result[2]);
        assertEquals("Torre Trapalhona", result[3]);
        assertEquals("capturado", result[4]);
        assertEquals("", result[5]);
        assertEquals("", result[6]);
    }

    @Test
    public void testStatisticComJogoCompleto(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        jogo.move(0,0,0,1);
        jogo.move(1,7,1,4);
        assertFalse(jogo.move(1,0,1,4));
        jogo.move(2,0,4,2);
        jogo.move(5,7,5,0);
        jogo.move(4,0,5,0);
        jogo.move(1,4,3,4);
        jogo.move(3,0,0,3);
        assertFalse(jogo.gameOver());
        jogo.move(3,4,0,1);
        assertTrue(jogo.gameOver());
        assertEquals("VENCERAM AS BRANCAS",jogo.tabuleiro.equipaVencedora());

        StatType statType = StatType.TOP_5_CAPTURAS;

        ArrayList<String> result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals("Artolas (PRETA) fez 1 capturas", result.get(0));
        assertEquals("A Barulhenta do Bairro (BRANCA) fez 1 capturas", result.get(1));
        assertEquals("Torre Trapalhona (BRANCA) fez 1 capturas", result.get(2));
        assertEquals("O Poderoso Chefao (PRETA) fez 0 capturas", result.get(3));
        assertEquals("A Dama Selvagem (PRETA) fez 0 capturas", result.get(4));

        statType = StatType.TOP_5_PONTOS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals("A Barulhenta do Bairro (BRANCA) tem 1000 pontos", result.get(0));
        assertEquals("Artolas (PRETA) tem 3 pontos", result.get(1));
        assertEquals("Torre Trapalhona (BRANCA) tem 3 pontos", result.get(2));

        statType = StatType.PECAS_MAIS_5_CAPTURAS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals(0, result.size());

        statType = StatType.PECAS_MAIS_BARALHADAS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals("10:A Dama Selvagem:1:0", result.get(0));

        statType = StatType.TIPOS_CAPTURADOS;

        result = Objects.requireNonNull(StatisticsKt.getStatsCalculator(statType)).invoke(jogo);

        assertEquals("Rei", result.get(0));
        assertEquals("TorreVert", result.get(1));

    }

    @Test
    public void testMovePoneiMagico(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xtestPoneiMagico.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertFalse(jogo.move(3,3,1,1));
        assertFalse(jogo.move(3,3,5,1));
        assertFalse(jogo.move(3,3,1,5));
        assertFalse(jogo.move(3,3,5,5));

    }

    @Test
    public void testGetHintsForaDoTabuleiro(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        List<Comparable> hints = jogo.getHints(8,8);

        assertNull(hints);
    }

    @Test
    public void testSaveGameSemJogada(){
        GameManager jogo = new GameManager();

        File ficheiro2 = new File("test-files/temp.txt");

        assertThrows(NullPointerException.class,()->{
            jogo.saveGame(ficheiro2);
        });
    }

    @Test
    public void testMoveValidoGuardiao(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xGuardiao.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(3,3,3,4));
        assertTrue(jogo.move(4,4,4,3));
        assertTrue(jogo.move(3,4,4,4));
        assertTrue(jogo.move(4,3,3,3));
    }

    @Test
    public void testMoveInvalidoGuardiao(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xGuardiao.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertFalse(jogo.move(3,3,2,2));
        assertFalse(jogo.move(3,3,4,2));
        assertFalse(jogo.move(3,3,4,4));
        assertFalse(jogo.move(3,3,2,4));
        assertFalse(jogo.move(3,3,6,3));
        assertFalse(jogo.move(3,3,0,3));
        assertFalse(jogo.move(3,3,3,6));
        assertTrue(jogo.move(0,0,1,1));
        assertFalse(jogo.move(4,4,4,1));
    }

    @Test
    public void testMoveGuardiaoProtegeVariado(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xGuardiao.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}
        assertTrue(jogo.move(3,3,3,1));
        assertTrue(jogo.move(4,4,6,4));
        assertTrue(jogo.move(3,1,4,1));
        assertFalse(jogo.move(5,7,5,0));
        assertTrue(jogo.move(6,4,6,6));
        assertTrue(jogo.move(1,0,6,5));
        assertTrue(jogo.move(6,6,7,6));
        assertFalse(jogo.move(6,5,6,7));
        assertTrue(jogo.move(0,0,1,1));
        assertTrue(jogo.move(5,7,5,2));
        assertTrue(jogo.move(4,1,5,1));
        assertTrue(jogo.move(7,6,7,5));
        assertFalse(jogo.move(5,1,5,3));
        assertTrue(jogo.move(1,1,0,2));
        assertFalse(jogo.move(7,5,5,5));
        assertTrue(jogo.move(0,7,1,6));
        assertTrue(jogo.move(6,5,7,4));
        assertFalse(jogo.move(7,5,7,3));
        assertTrue(jogo.move(1,6,0,5));
        assertTrue(jogo.move(7,0,6,1));
        assertTrue(jogo.move(7,5,7,4));
        assertFalse(jogo.move(5,1,7,1));
    }

    @Test
    void testGuardiaoString(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xGuardiao.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        int id = jogo.tabuleiro.getPecaByCordenada(3,3).getId();
        String peca = jogo.getPieceInfoAsString(id);
        String nomeTipo = jogo.tabuleiro.getPecaByCordenada(3,3).nomeTipo();
        int pontos = jogo.tabuleiro.getPecaByCordenada(3,3).getPontos();
        String png = jogo.tabuleiro.getPecaByCordenada(3,3).getPng();

        assertEquals("Guardião",nomeTipo);
        assertEquals(2,pontos);
        assertEquals("guardiao_black.png",png);

        assertEquals("17 | Guardião | 2 | 10 | Rui Ferreira @ (3, 3)",peca);

        jogo.move(3,3,4,3);
        jogo.move(4,4,4,3);

        peca = jogo.getPieceInfoAsString(id);

        assertEquals("17 | Guardião | 2 | 10 | Rui Ferreira @ (n/a)",peca);
    }

    @Test
    void testePNGpecas(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        String png = jogo.tabuleiro.getPecaByCordenada(0,0).getPng();

        assertEquals("crazy_emoji_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(1,0).getPng();

        assertEquals("rainha_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(2,0).getPng();

        assertEquals("ponei_magico_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(3,0).getPng();

        assertEquals("padre_vila_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(4,0).getPng();

        assertEquals("torre_h_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(5,0).getPng();

        assertEquals("torre_v_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(6,0).getPng();

        assertEquals("homer_black.png",png);

        png = jogo.tabuleiro.getPecaByCordenada(7,0).getPng();

        assertEquals("joker_black.png",png);
    }

    @Test
    void testeMoveInvalidoOrigemNull(){
        GameManager jogo = new GameManager();
        File ficheiro = new File("test-files/8x8xEmpate.txt");
        try {
            jogo.loadGame(ficheiro);
        } catch (InvalidGameInputException | IOException ignored) {}

        assertTrue(jogo.gameOver());
    }

}
