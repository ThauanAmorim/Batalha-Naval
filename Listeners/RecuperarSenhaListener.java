package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JOptionPane;
import Arqs.DAO;
import Arqs.EnviarEmail;
import Arqs.Jogador;
import Arqs.ListaDePlayers;
import Telas.JanelaCodigoRecuperacao;
import Telas.JanelaLogin;
import Telas.JanelaRecuperarSenha;

public class RecuperarSenhaListener {
	private JanelaRecuperarSenha janela;
	public RecuperarSenhaListener(JanelaRecuperarSenha janela) {
		this.janela = janela;
		botaoProximoListener();
		botaoVoltarListener();
	}
	
	private void botaoProximoListener() {
		janela.getProximo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				String username = janela.getUsername().getText();
				ListaDePlayers objetoListaDeNomesUsuarios = DAO.LerListaDePlayers();
				
				if(objetoListaDeNomesUsuarios.getListaDePlayers().contains(username)) {
					Jogador player = DAO.LerPlayer(username);
					Random random = new Random();
					String codigo = String.valueOf(random.nextInt(90000)+10000);
					
					new JanelaCodigoRecuperacao(player, codigo);
					janela.getTela().setVisible(false);
					
					EnviarEmail.enviar(player, "Código de Verificação", String.valueOf(codigo), false);
					
					
				}else {
					JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado com esse user!!", "ERROR", JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
	}
	private void botaoVoltarListener() {
		janela.getVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent r) {
				janela.getTela().setVisible(false);
				new JanelaLogin();
			}
		});
	}	
}
