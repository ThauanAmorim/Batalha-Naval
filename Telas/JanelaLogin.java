package Telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Arqs.Diretorios;
import Listeners.LoginListener;

public class JanelaLogin extends TelaDefault {
	private JButton botaoCadastrar;
	private JButton botaoRecuperarSenha;
	private JButton botaoLogin;
	private JTextField jtUser;
	private JTextField jtSenha;
	private JFrame frame;
	public JanelaLogin() {
		
		frame = configTela(440, 250, "Login");
		
		//labels
		frame.add(adicionarLabel("Usuário", 90, 20, 100, 30));
		frame.add(adicionarLabel("Senha", 89, 50, 100, 30));
		
		//botoes
		botaoCadastrar = adicionarJButton("Cadastrar", 25, 150, 130, 30);
		frame.add(botaoCadastrar);
		
		botaoRecuperarSenha = adicionarJButton("Recuperar senha", 110, 185, 200, 20);
		frame.add(botaoRecuperarSenha);
		
		botaoLogin = adicionarJButton("Login", 280, 150, 130, 30);
		frame.add(botaoLogin);
		

		//Textfild and JPassawordFilde
	    jtUser = adicionarTextField(140, 20, 200, 30);
	    frame.add(jtUser);
	    
	    jtSenha = adicionarJPassword(140, 50, 200, 30);
	    frame.add(jtSenha);
	    
	    //tela de Fundo
	    frame.add(new TelaBackGround(Diretorios.DiretorioBackGround(), frame));
	    
	    //listeners
	    new LoginListener(this);
	    
	    frame.repaint();
	}
	
	public JButton getBotaoRecuperarSenha() {
		return botaoRecuperarSenha;
	}

	public JButton getBotaoCadastrar() {
		return botaoCadastrar;
	}
	public JButton getBotaoLogin() {
		return botaoLogin;
	}

	public JTextField getJtUser() {
		return jtUser;
	}

	public JTextField getJtSenha() {
		return jtSenha;
	}

	public void setBotaoCadastrar(JButton botaoCadastrar) {
		this.botaoCadastrar = botaoCadastrar;
	}

	public JFrame getFrame() {
		return frame;
	}
}
	
