package Telas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Arqs.DAO;
import Arqs.Diretorios;
import Arqs.Jogador;

public class JanelaHistoricoDePartida extends TelaDefault{
	ArrayList<JLabel> labelClicado = new ArrayList<>();
	private JFrame frameJanela;
	private JFrame frameLabels;
	private JPanel painel = new JPanel();
	public JanelaHistoricoDePartida(Jogador player){
		frameJanela = configTela(600, 600, "Historico De Partidas");
		frameLabels = ConfigurarAsPropriedadesDoJframe();
		
		ArrayList<JLabel> listaDeLabelsHistorico = AdicionarJlabelHistorico(player);
		
		// Scrollbar
		adicionarScrollBar(listaDeLabelsHistorico.size());
		
		for(int i = 0 ; i < listaDeLabelsHistorico.size() ; i++) {
			painel.add(listaDeLabelsHistorico.get(i));
		}
		
		frameJanela.add(AdicionandoBotao("SAIR", 450, 520, player));
		frameJanela.repaint();
		
		
	}
	
	private void adicionarScrollBar(int tamanho) {
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painel.setPreferredSize(new Dimension(10, 40*tamanho));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(painel.getBounds());
		scroll.setVisible(true);
		scroll.setAutoscrolls(true);
		scroll.setViewportView(painel);
		scroll.setViewportBorder(BorderFactory.createLoweredBevelBorder());
		frameJanela.add(scroll);
	}
	
	private ArrayList<JLabel> AdicionarJlabelHistorico(Jogador player) {
		
		ArrayList<String> listaDeDadosLidos = DAO.LerHistoricoDePartidasArrayList(player.getUsuario());
		
		ArrayList<JLabel> listaDeLabels = new ArrayList<>();
		int indexDosLabelsPrincipais = 0;
		int indexDosLabelsSecundarios = 1;
		for(int i = 0 ; i < listaDeDadosLidos.size()/2 ; i++) {
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(530, 40));
			label.setText(listaDeDadosLidos.get(indexDosLabelsPrincipais));
			label.setBorder(BorderFactory.createEtchedBorder(frameJanela.getBackground(), Color.BLACK));
			label.setIcon(new ImageIcon(Diretorios.setaDireita()));
			indexDosLabelsPrincipais += 2; // 0 vai para 2
			JLabelListener(label, listaDeDadosLidos.get(indexDosLabelsSecundarios));
			indexDosLabelsSecundarios += 2; // 1 vai pra 3 
			
			listaDeLabels.add(label);
		}
		return listaDeLabels;
	}
	private void JLabelListener(JLabel label, String textoDosLabelsSecundarios) {
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				CriarJanelaDosLabelsSecundarios(textoDosLabelsSecundarios, label);	
			}
		});
	}
	
	private void CriarJanelaDosLabelsSecundarios(String texto, JLabel label) {
		double x = label.getLocationOnScreen().getX();
		double y = label.getLocationOnScreen().getY();
		frameLabels.setLocation((int) x, (int) (y) + 40);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBorder(BorderFactory.createEtchedBorder(frameJanela.getBackground(), Color.BLACK));
		textArea.setFont(new Font("Arial", Font.PLAIN, 16));
		textArea.setText(texto);
		
		if(!labelClicado.contains(label)) {
			frameLabels.setVisible(true);
			labelClicado.add(label);
			textArea.setLineWrap(true);
			JScrollPane scroll = new JScrollPane(textArea);
			frameLabels.add(scroll);
			label.setIcon(new ImageIcon(Diretorios.setaBaixo()));
		}else{
			label.setIcon(new ImageIcon(Diretorios.setaDireita()));
			labelClicado.remove(label);
			frameLabels.setVisible(false);
			frameJanela.toFront();
		}
	}
	
	private JFrame ConfigurarAsPropriedadesDoJframe() {
		JFrame frameLabels = new JFrame();
		painel.setBounds(5, 5, 575, 500);
		frameLabels.setSize(530, 300);
		frameLabels.setBackground(Color.black);
		frameLabels.setUndecorated(true);
		return frameLabels;
	}
	
	
	private JButton AdicionandoBotao(String nome, int X, int Y, Jogador player) {
		JButton botaozinho = new JButton(nome);
		botaozinho.setBounds(X, Y, 100, 30);
		botaozinho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameJanela.setVisible(false);
				new TelaInicial(player);
				frame.setVisible(false);
			}
		});
		return botaozinho;
	}
}
