package Arqs;

import java.util.ArrayList;

public class BDP {
	
	public static ArrayList<Jogador> BarraDePesquisa(String busca){
		ArrayList<String> listaDePlayers = new ArrayList<>();
		ArrayList<String> listaDeCaracteres = new ArrayList<>();
		ArrayList<ArrayList<String>> listaComOsNomeDoPlayersSeparadosEmCaracteres = new ArrayList<>();
		ArrayList<String> buscaSeparadoEmCaracteres = SepararCaracteres(busca);
		ArrayList<String> listaDosPlayersEncontrados = new ArrayList<>();
		ArrayList<Jogador> listaDosPlayers = new ArrayList<>();
		
		listaDePlayers = DAO.LerListaDePlayers().getListaDePlayers();
		
		// gera o nome de todos os players e coloca ele em um arraylist com o nome separado em caracteres
		for(int i = 0 ; i < listaDePlayers.size() ; i++) {
			String nomeDoPlayer = listaDePlayers.get(i);
			listaDeCaracteres = SepararCaracteres(nomeDoPlayer);
			listaComOsNomeDoPlayersSeparadosEmCaracteres.add(listaDeCaracteres);
		}
		
		// gera a lista dos player realitivo a busca
		for(int i = 0 ; i < listaComOsNomeDoPlayersSeparadosEmCaracteres.size() ; i++) {
			for(int j = 0 ; j < buscaSeparadoEmCaracteres.size() ; j++) {
				if(listaComOsNomeDoPlayersSeparadosEmCaracteres.get(i).get(0).toLowerCase().contains(buscaSeparadoEmCaracteres.get(j).toLowerCase())) {
					
					if(UnirCaracteres(listaComOsNomeDoPlayersSeparadosEmCaracteres.get(i)).toLowerCase().contains(busca.toLowerCase())) {
						listaDosPlayersEncontrados.add(UnirCaracteres(listaComOsNomeDoPlayersSeparadosEmCaracteres.get(i)));
						break;
					}
				}
			}
		}
		
		//transforma em objetos do tipo Jogador()
		for(String nomesDosPlayersEncontrado : listaDosPlayersEncontrados) {
			Jogador player = DAO.LerPlayer(nomesDosPlayersEncontrado);
			listaDosPlayers.add(player);
		}
		return listaDosPlayers;
	}
	
	private static ArrayList<String> SepararCaracteres(String texto) {
		ArrayList<String> caracteres = new ArrayList<>();
		for(int i = 0 ; i < texto.length() ; i++) {
			caracteres.add(String.valueOf(texto.charAt(i)));
		}
			
		return caracteres;
	}
	
	private static String UnirCaracteres(ArrayList<String> listaDeCaracteres) {
		String textoUnido = "";
		for(int i = 0 ; i < listaDeCaracteres.size() ; i++) {
			textoUnido += listaDeCaracteres.get(i);
		}
		return textoUnido;
	}
}
