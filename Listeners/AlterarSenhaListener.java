package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Arqs.DAO;
import Arqs.Jogador;
import Telas.JanelaAlterarSenha;
import Telas.JanelaProfile;

public class AlterarSenhaListener {
	private JanelaAlterarSenha janela;
	public AlterarSenhaListener(JanelaAlterarSenha janela) {
		this.janela = janela;
		BotaoSalvarListener();
		BotaoVoltarListener();
		
	}
	
	private void BotaoSalvarListener() {
		Jogador player = janela.getPlayer();
		janela.getBotaoSalvar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				String senhaAtual = janela.getSenhaAtual().getText().replaceAll(" ", "");
				String senhaNova = janela.getSenhaNova().getText().replaceAll(" ", "");
				String confirmacaoSenhaNova = janela.getConfirmacaoSenhaNova().getText().replaceAll(" ", "");
				
				//verifica se os campos estão vazios
				if(senhaAtual.equals("") || senhaNova.equals("") || confirmacaoSenhaNova.equals("")) {
					JOptionPane.showMessageDialog(null, "Campos vazios, complete todos os campos para continuar.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				//verifica se a senha corresponde a do user
				else if(senhaAtual.equals(player.getSenha())) {
					if(senhaNova.equals(confirmacaoSenhaNova)) {
						player.setSenha(senhaNova);
						DAO.SalvarPlayer(player);
						
						JOptionPane.showMessageDialog(null, "Senha alterada com sucesso.", "Error", JOptionPane.ERROR_MESSAGE);
						janela.getFrame().setVisible(false);
						new JanelaProfile(player);
						
					}else {
						JOptionPane.showMessageDialog(null, "Senha nova não coincide com a confirmação da senha", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Senha Atual não corresponde a senha do User, tente novamente.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void BotaoVoltarListener() {
		janela.getBotaoVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaProfile(janela.getPlayer());
				janela.getFrame().setVisible(false);
			}
		});
	}
}
