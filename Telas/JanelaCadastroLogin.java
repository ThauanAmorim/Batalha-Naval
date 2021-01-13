package Telas;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import Listeners.CadastroLoginListener;

public class JanelaCadastroLogin extends TelaDefault{
	private JTextField jtEmail;
	private JTextField jtUser;
	private JTextField jtSenha;
	private JTextField jtConfirmarSenha;
	private JButton jbOk;
	private JButton jbCancelar;
	private JFrame frame;
	
	public JanelaCadastroLogin() {
		frame = configTela(400, 500, "Cadastro");
		
		//labels
		frame.add(adicionarLabel("Digite seu e-mail:", 20, 20, 200, 30, Font.BOLD, 16));
		frame.add(adicionarLabel("Digite seu Nome de Usuário:", 20, 100, 300, 30, Font.BOLD, 16));
		frame.add(adicionarLabel("Digite sua senha", 20, 180, 200, 30, Font.BOLD, 16));
		frame.add(adicionarLabel("Confirme sua senha:", 20, 260, 200, 30, Font.BOLD, 16));
		
		
		//JTextFileds
		jtEmail = adicionarTextField(20, 60, 350, 30);
		frame.add(jtEmail);
		
		jtUser = adicionarTextField(20, 140, 350, 30);
		frame.add(jtUser);
		
		jtSenha = adicionarJPassword(20, 220, 350, 30);
		frame.add(jtSenha);
		
		jtConfirmarSenha = adicionarJPassword(20, 300, 350, 30);
		frame.add(jtConfirmarSenha);
		
		
		//JButtons
		jbOk = adicionarJButton("OK", 200, 360, 100, 30);
		frame.add(jbOk);
		
		jbCancelar = adicionarJButton("Cancelar", 70, 360, 100, 30);
		frame.add(jbCancelar);
		
		
		//Listeners
		new CadastroLoginListener(this);
		
		frame.repaint();
	}
	
	public JTextField getJtEmail() {
		return jtEmail;
	}

	public JTextField getJtUser() {
		return jtUser;
	}

	public JTextField getJtSenha() {
		return jtSenha;
	}

	public JTextField getJtConfirmarSenha() {
		return jtConfirmarSenha;
	}

	public JButton getJbOk() {
		return jbOk;
	}
	
	public JButton getJbCancelar() {
		return jbCancelar;
	}

	public JFrame getFrame() {
		return frame;
	}
}
