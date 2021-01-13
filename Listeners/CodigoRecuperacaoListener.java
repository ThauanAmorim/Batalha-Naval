package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Telas.JanelaCodigoRecuperacao;
import Telas.JanelaLogin;
import Telas.JanelaNovaSenha;

public class CodigoRecuperacaoListener {
	private JanelaCodigoRecuperacao janela;
	
	public CodigoRecuperacaoListener(JanelaCodigoRecuperacao janela) {
		this.janela = janela;
		botaoProseguirListener();
		botaoVoltarListener();
	}
	
	private void botaoProseguirListener() {
		janela.getProsseguir().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(janela.getCaixaDeTextoCodigo().getText().equals(janela.getCodigo())) {
					new JanelaNovaSenha(janela.getPlayer());
					janela.getFrame().setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Codigo Incorreto", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	private void botaoVoltarListener() {
		janela.getVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaLogin();
				janela.getFrame().setVisible(false);
			}
		});
	}
}
