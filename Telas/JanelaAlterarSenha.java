package Telas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Arqs.Jogador;
import Listeners.AlterarSenhaListener;

public class JanelaAlterarSenha extends TelaDefault{
	private JFrame frame;
	private Jogador player;
	private JTextField senhaAtual;
	private JTextField senhaNova;
	private JTextField confirmacaoSenhaNova;
	private JButton botaoVoltar;
	private JButton botaoSalvar;
	
	public JanelaAlterarSenha(Jogador player) {
		this.player = player;
		frame = configTela(450, 250, "Alteração de Senha");
		
		//labels
		frame.add(adicionarLabel("Senha atual: ", 10, 10, 200, 30, Font.BOLD, 16));
		frame.add(adicionarLabel("Nova senha: ", 10, 50, 200, 30, Font.BOLD, 16));
		frame.add(adicionarLabel("Confirme a nova senha: ", 10, 90, 200, 30, Font.BOLD, 16));
		
		//JTextField
		senhaAtual = adicionarJPassword(200, 10, 200, 30);
		frame.add(senhaAtual);
		
		senhaNova = adicionarJPassword(200, 50, 200, 30); 
		frame.add(senhaNova);
		
		confirmacaoSenhaNova = adicionarJPassword(200, 90, 200, 30); 
		frame.add(confirmacaoSenhaNova);
		
		//JButton
		botaoVoltar = adicionarJButton("Voltar", 50, 150, 100, 30);
		frame.add(botaoVoltar);
		
		botaoSalvar = adicionarJButton("Salvar", 250, 150, 100, 30); 
		frame.add(botaoSalvar);
		
		//Listener
		new AlterarSenhaListener(this);
		
		frame.repaint();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public Jogador getPlayer() {
		return player;
	}

	public JTextField getSenhaAtual() {
		return senhaAtual;
	}

	public JTextField getSenhaNova() {
		return senhaNova;
	}

	public JTextField getConfirmacaoSenhaNova() {
		return confirmacaoSenhaNova;
	}

	public JButton getBotaoVoltar() {
		return botaoVoltar;
	}

	public JButton getBotaoSalvar() {
		return botaoSalvar;
	}
}
