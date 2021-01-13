package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Telas.JanelaHistoricoPontos;
import Telas.TelaInicial;

public class HistoricoPontosListener {
	private JanelaHistoricoPontos janela;
	public HistoricoPontosListener(JanelaHistoricoPontos janela) {
		this.janela = janela;
		
		BotaoSalvarListener();
	}
	
	private void BotaoSalvarListener() {
		janela.getVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				new TelaInicial(janela.getPlayer());
				
				janela.getFrame().setVisible(false);
			}
		});
	}
}
