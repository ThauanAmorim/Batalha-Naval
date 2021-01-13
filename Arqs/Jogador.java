package Arqs;

import java.util.ArrayList;

public class Jogador{
	private String usuario;
	private String email;
	private String senha;
	private ArrayList<ArrayList<Integer>> locaisDosBarcos;
	private int pontuacao = 0;
	private int partidas = 0;
	private int vitorias = 0;
	private int derrotas = 0;
	
	public void addPartida() {
		partidas++;
	}
	public void addVitoria() {
		vitorias++;
	}
	public void addDerrota() {
		derrotas++;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public ArrayList<ArrayList<Integer>> getLocaisDosBarcos() {
		return locaisDosBarcos;
	}
	public void setLocaisDosBarcos(ArrayList<ArrayList<Integer>> locaisDosBarcos) {
		this.locaisDosBarcos = locaisDosBarcos;
	}
	public int getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}
	public void addPontuacao(int pontuacao) {
		this.pontuacao += pontuacao;
	}
	public int getPartidas() {
		return partidas;
	}
	public void setPartidas(int partidas) {
		this.partidas = partidas;
	}
	public int getVitorias() {
		return vitorias;
	}
	public void setVitorias(int vitorias) {
		this.vitorias = vitorias;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	
}	
