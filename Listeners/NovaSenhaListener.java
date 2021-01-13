package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Arqs.DAO;
import Arqs.Jogador;
import Telas.JanelaLogin;
import Telas.JanelaNovaSenha;

public class NovaSenhaListener {
	private JanelaNovaSenha janela;
	public NovaSenhaListener(JanelaNovaSenha janela) {
		this.janela = janela;
		botaoProsseguirListener();
	}
	
	private void botaoProsseguirListener() {
		janela.getProsseguir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				String novaSenha = janela.getNovaSenha().getText();
				String confirmarSenha = janela.getConfirmarSenha().getText();
				if(!novaSenha.equals(confirmarSenha)) {
					JOptionPane.showMessageDialog(null, "Senhas diferentes", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else if (novaSenha.equals("") || confirmarSenha.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos Vazios", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					Jogador player = janela.getPlayer();
					player.setSenha(novaSenha);
					DAO.SalvarPlayer(player);
					new JanelaLogin();
					janela.getJanela().setVisible(false);

				}
			}
		});
	}
}
