package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Arqs.DAO;
import Arqs.Jogador;
import Telas.JanelaAlterarSenha;
import Telas.JanelaLogin;
import Telas.JanelaProfile;
import Telas.TelaInicial;

public class ProfileListener {
	private JanelaProfile janela;
	
	public ProfileListener(JanelaProfile janela) {
		this.janela = janela;
		
		BotaoAlterarSenhaListener();
		BotaoDeletarContaListener();
		BotaoVoltarSalvarListener();
	}
	
	private void BotaoAlterarSenhaListener() {
		janela.getJbAlterarSenha().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				janela.getFrame().setVisible(false);
				new JanelaAlterarSenha(janela.getPlayer());
			}
		});
	}
	
	private void BotaoDeletarContaListener() {
		janela.getJbDeletarConta().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente deletar sua conta?", null, JOptionPane.YES_NO_OPTION);
				if(escolha == JOptionPane.YES_OPTION) {
					DAO.DeletarConta(janela.getPlayer().getUsuario());
					janela.getFrame().setVisible(false);
					new JanelaLogin();
				}
			}
		});
	}
	
	private void BotaoVoltarSalvarListener() {
		Jogador player = janela.getPlayer();
		janela.getJbVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!player.getUsuario().equals(janela.getDisplayName().getText())) {
					player.setUsuario(janela.getDisplayName().getText());
				}if(!player.getEmail().equals(janela.getEmail().getText())) {
					player.setEmail(janela.getEmail().getText());
				}
				DAO.SalvarPlayer(player);
				
				janela.getFrame().setVisible(false);
				new TelaInicial(player);
			}
		});
	}
}
