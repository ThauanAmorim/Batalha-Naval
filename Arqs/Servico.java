package Arqs;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servico {
	private static String[] arrayLetras = {"A", "B", "C", "D", "E"};
    private static String[] arrayNumeros = {"1", "2", "3", "4", "5"};
    
	public static boolean Autenticacao(Jogador player, String senha) {
		if(player.getSenha().equals(senha)) {
			return true;
		}
		return false;
	}
	
	public static String GerarHoraAtual() {
		Date date = new Date();
		SimpleDateFormat dataFormatada = new SimpleDateFormat("hh:mm");
		return dataFormatada.format(date);
	}
	
	public static String GerarLogsDePartidas(String tipoDaAcao, int x, int y, Jogador player, Jogador playerInimigo, Integer pontuacao, String acao) {
		if(x > 400) {
			x = x-450;
		}
		
		String textoLog = "";
		if(tipoDaAcao.equals("Desafiado")) {
			textoLog = player.getUsuario() + " desafiou "+ playerInimigo.getUsuario() + " as " + Servico.GerarHoraAtual();
			
		}else if(tipoDaAcao.equals("BotaoEscolhidoNoTabuleiro")) {
			if(acao.equals("Barco")) {
				textoLog = "-" + player.getUsuario() + " clicou em "+ arrayLetras[(y/50)-1] + arrayNumeros[(x/50)-1] + " encontrou um barco.";
				
			}else if(acao.equals("Bomba")) {
				textoLog = "-" + player.getUsuario() + " clicou em "+ arrayLetras[(y/50)-1] + arrayNumeros[(x/50)-1] + " foi surpreendido com uma bomba na cara.";
				
			}
		}else if(tipoDaAcao.equals("Vitoria")){
			textoLog = "-" + player.getUsuario() + " Ganhou a partida com " + pontuacao + " pontos.";
		}else if(tipoDaAcao.equals("Derrota")) {
			textoLog = "-" + player.getUsuario() + " Perdeu a partida com " + pontuacao + " pontos.";
		}
		
		return textoLog + "\n";
	}

}
