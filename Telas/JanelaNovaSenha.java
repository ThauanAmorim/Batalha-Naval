package Telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Arqs.Jogador;
import Listeners.NovaSenhaListener;

public class JanelaNovaSenha extends TelaDefault {
	private JButton prosseguir;
	private JTextField novaSenha;
	private JTextField confirmarSenha;
	private JFrame janela;
	private Jogador player;
	
	public JanelaNovaSenha(Jogador player) {
		this.player = player;
		janela = configTela(440, 250, "Nova Senha");
		
		//Label
		janela.add(adicionarLabel("Nova Senha:", 15, 40, 100, 30));
		janela.add(adicionarLabel("Confirmar Senha:", 15, 100, 100, 30));
		
		//Botões
		prosseguir = adicionarJButton("Próximo", 315, 160, 100, 30);
		janela.add(prosseguir);
		
		//TextField
		novaSenha = adicionarJPassword(120, 40, 200, 30);
		janela.add(novaSenha);
		
		confirmarSenha = adicionarJPassword(120, 100, 200, 30);
		janela.add(confirmarSenha);
		
		//Listener
		new NovaSenhaListener(this);
		
		frame.repaint();
	}


	public JButton getProsseguir() {
		return prosseguir;
	}

	public JTextField getNovaSenha() {
		return novaSenha;
	}

	public JTextField getConfirmarSenha() {
		return confirmarSenha;
	}

	public Jogador getPlayer() {
		return player;
	}


	public JFrame getJanela() {
		return janela;
	}
}
