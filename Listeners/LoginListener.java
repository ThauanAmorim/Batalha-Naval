package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Arqs.DAO;
import Arqs.Jogador;
import Arqs.Servico;
import Telas.JanelaCadastroLogin;
import Telas.JanelaLogin;
import Telas.JanelaRecuperarSenha;
import Telas.TelaInicial;

public class LoginListener {
	private JanelaLogin janela;
	
	public LoginListener(JanelaLogin janela) {
		this.janela = janela;
		jPasswordListenerEnter();
		botaoCadastrarListener();
		botaoLoginListener();
		botaoRecuperarSenhaListener();
	}
	
	private void jPasswordListenerEnter() {
		JTextField jpf = janela.getJtSenha();
		JButton botaoAcao = janela.getBotaoLogin();
		
		jpf.addKeyListener(new KeyAdapter() {
	    	public void keyTyped(KeyEvent e) {
	    		if(e.getKeyChar() == KeyEvent.VK_ENTER) {
	    			botaoAcao.doClick();
	    		}
			}
		});
	}
	
	private void botaoCadastrarListener() {
		janela.getBotaoCadastrar().addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e) {
	    		janela.getFrame().setVisible(false);
	    		new JanelaCadastroLogin();
	    	}
	    	
	    });
	}
	
	private void botaoLoginListener() {
		janela.getBotaoLogin().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = janela.getJtUser().getText().replaceAll(" ", "");
				String senhaDigitada = janela.getJtSenha().getText().replaceAll(" ", "");
				if(usuario.equals("") || senhaDigitada.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos Vazios", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else if(!DAO.VerificarUserJaCadastrado(usuario, null)) {
					JOptionPane.showMessageDialog(null, "User não existente!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					Jogador player = DAO.LerPlayer(usuario);
					if(Servico.Autenticacao(player, senhaDigitada)) {
						new TelaInicial(player);
						janela.getFrame().setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Senha Incorreta!!", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	private void botaoRecuperarSenhaListener() {
		janela.getBotaoRecuperarSenha().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janela.getFrame().setVisible(false);
				new JanelaRecuperarSenha();
			}
		});
	}
}
