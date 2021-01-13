package Telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Arqs.Diretorios;
import Listeners.RecuperarSenhaListener;

public class JanelaRecuperarSenha extends TelaDefault{
	private JButton proximo;
	private JButton voltar;
	private JTextField Username;
	private JFrame tela;
	
	public JanelaRecuperarSenha() {
		tela = configTela(440, 250, "Recuperar Senha");
		//Label
		tela.add(adicionarLabel("Usuário:", 70, 60, 100, 30));
		
		//Botões
		proximo =  adicionarJButton("Próximo", 315, 150, 100, 30);
		tela.add(proximo);
		
		voltar = adicionarJButton("Voltar", 15, 150, 100, 30);
		tela.add(voltar);
		
		//JtextField
		
		Username = adicionarTextField(120, 60, 200, 30);
		tela.add(Username);
		
		//Tela de fundo
		tela.add(new TelaBackGround(Diretorios.DiretorioBackGround(), tela));
		
		new RecuperarSenhaListener(this);
		
		frame.repaint();
	}

	public JButton getProximo() {
		return proximo;
	}

	public JButton getVoltar() {
		return voltar;
	}

	public JTextField getUsername() {
		return Username;
	}

	public JFrame getTela() {
		return tela;
	}
}
