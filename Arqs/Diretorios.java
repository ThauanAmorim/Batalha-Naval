package Arqs;

import java.io.File;

public class Diretorios {
    private static String diretorio = new File("").getAbsolutePath();
    
	public static String DiretorioBackGround() {
		return diretorio + "\\Imagens\\batalhanaval.png";
	}
	public static String DiretorioBomba() {
		return diretorio + "\\Imagens\\boom.png";
	}
	public static String SeaIcon() {
		return diretorio + "\\Imagens\\sea.png";
	}
	public static String setaDireita() {
		return diretorio + "\\Imagens\\SetaDireita.png";
	}
	public static String setaBaixo() {
		return diretorio + "\\Imagens\\SetaBaixo.png";
	}
	public static String NavioIcon(String tamanho, String direcao) {
		return diretorio + "\\Imagens\\Nt"+ tamanho + direcao + ".png";
	}
	public static String ListaDePlayers() {
		return diretorio + "\\Dados\\ListaDePlayers.xml";
	}
	public static String DadosDoPlayer(String userName) {
		return diretorio + "\\Dados\\"+userName+".xml";
	}
	public static String diretorioHistoricoDePartida(String userName) {
		return diretorio + "\\Dados\\Historico De Partidas\\"+userName+".xml";
	}
	public static String diretorioHistoricoDePontos(String userName) {
		return diretorio + "\\Dados\\Historico De Pontos\\"+userName+".xml";
	}
	public static String diretorioRelatoriosVitoriasPlayer(String userName) {
		return diretorio + "\\Dados\\Relatórios\\"+userName+" Vitorias.pdf";
	}
	public static String diretorioRelatoriosDerrotasPlayer(String userName) {
		return diretorio + "\\Dados\\Relatórios\\"+userName+" Derrotas.pdf";
	}
}

