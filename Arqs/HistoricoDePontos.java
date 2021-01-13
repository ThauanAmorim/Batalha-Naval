package Arqs;

import java.util.ArrayList;

public class HistoricoDePontos {
	private ArrayList<ArrayList<Integer>> dados = new ArrayList<>();

	public ArrayList<ArrayList<Integer>> getDados() {
		return dados;
	}

	public void setDados(ArrayList<ArrayList<Integer>> dados) {
		this.dados = dados;
	}
	
	public void addDados(ArrayList<Integer> dados) {
		this.dados.add(dados);
	}
	
}
