package Arqs;

import java.util.ArrayList;

public class ListaDePlayers {
	private ArrayList<String> listaDePlayers = new ArrayList<>();
	
	public void addPlayer(String userName) {
		if(!listaDePlayers.contains(userName))
			listaDePlayers.add(userName);
	}
	
	public void removerPlayer(String userName) {
		if(listaDePlayers.contains(userName)) {
			listaDePlayers.remove(userName);
		}
	}
	
	public void substituirNome(String nome, String novoNome) {
		if(listaDePlayers.contains(nome)) {
			listaDePlayers.remove(nome);
			listaDePlayers.add(novoNome);
		}
	}
	
	public ArrayList<String> getListaDePlayers() {
		return listaDePlayers;
	}

	public void setListaDePlayers(ArrayList<String> listaDePlayers) {
		this.listaDePlayers = listaDePlayers;
	}
	
}
