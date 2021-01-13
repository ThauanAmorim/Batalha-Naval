package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import Arqs.DAO;
import Arqs.Jogador;
import Telas.JanelaCadastroLogin;
import Telas.JanelaEditarDisposicao;
import Telas.JanelaLogin;

public class CadastroLoginListener {
	JanelaCadastroLogin janela;
	public CadastroLoginListener(JanelaCadastroLogin janela) {
		this.janela = janela;
		
		botaoOkListener();
		confirmarSenhaEnterListener();
		cancelarListener();
	}
	
	private void botaoOkListener() {
		JTextField jtEmail = janela.getJtEmail();
		JTextField jtUser = janela.getJtUser();
		JTextField jtSenha = janela.getJtSenha();
		JTextField jtConfirmarSenha = janela.getJtConfirmarSenha();
		
		janela.getJbOk().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = jtEmail.getText().replaceAll(" ", "");
				String user = jtUser.getText();
				String senha = jtSenha.getText().replaceAll(" ", "");
				String confirmarSenha = jtConfirmarSenha.getText().replaceAll(" ", "");
				
				if(email.equals("") || user.equals("") || senha.equals("") || confirmarSenha.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos Vazios, preencha todos os campos para prosseguir", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else if(!senha.equals(confirmarSenha)){
					JOptionPane.showMessageDialog(null, "Digite as senhas iguais nos dois campos", "Error", JOptionPane.ERROR_MESSAGE);
					
				}else if(DAO.VerificarUserJaCadastrado(user, email)){
					JOptionPane.showMessageDialog(null, "usuário ja cadastrado, tente outro email e username", "Error", JOptionPane.ERROR_MESSAGE);
					jtEmail.setText(""); jtUser.setText(""); jtSenha.setText(""); jtConfirmarSenha.setText("");
				}else if(user.contains(" ")) {
					JOptionPane.showMessageDialog(null, "Não pode conter espaços vazios", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					Jogador player = new Jogador();
					player.setEmail(email);
					player.setUsuario(user);
					player.setSenha(senha);
					
					DAO.SalvarPlayer(player);
					
					new JanelaEditarDisposicao(player, "JanelaDeLogin");
					janela.getFrame().setVisible(false);
				}
				janela.getFrame().repaint();
				
				
			}
		});
	}
	
	private void confirmarSenhaEnterListener() {
		janela.getJtConfirmarSenha().addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == KeyEvent.VK_ENTER) {
					janela.getJbOk().doClick();
				}
			}
		});
		
	}
	
	private void cancelarListener() {
		janela.getJbCancelar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaLogin();
				janela.getFrame().setVisible(false);
			}
		});
	}
	
}
