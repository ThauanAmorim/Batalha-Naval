package Telas;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import Arqs.Jogador;
import Listeners.CodigoRecuperacaoListener;

public class JanelaCodigoRecuperacao extends TelaDefault{
	private JFrame frame;
	private JTextField caixaDeTextoCodigo;
	private JButton voltar;
	private JButton prosseguir;
	private Jogador player;
	private String codigo;
	
	public JanelaCodigoRecuperacao(Jogador player, String codigo) {
		frame = configTela(300, 160, "Codigo de Recuperação");
		this.player = player;
		this.codigo = codigo;
		
		//JLabels
		frame.add(adicionarLabel("Codigo: ", 10, 10, 150, 30, Font.BOLD, 16));
		
		//JTextFields
		caixaDeTextoCodigo = adicionarTextField(80, 10, 180, 30);
		frame.add(caixaDeTextoCodigo);
		
		//JButtons
		voltar = adicionarJButton("Voltar", 10, 70, 120, 30);
		frame.add(voltar);
		
		prosseguir = adicionarJButton("Prosseguir", 150, 70, 120, 30);
		frame.add(prosseguir);
		
		frame.repaint();
		
		//Listener
		new CodigoRecuperacaoListener(this);
		
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public JTextField getCaixaDeTextoCodigo() {
		return caixaDeTextoCodigo;
	}
	
	public JButton getVoltar() {
		return voltar;
	}

	public JButton getProsseguir() {
		return prosseguir;
	}

	public Jogador getPlayer() {
		return player;
	}
	
	public String getCodigo() {
		return codigo;
	}
}
