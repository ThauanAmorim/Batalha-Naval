package Listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Arqs.BDP;
import Arqs.DAO;
import Arqs.Jogador;
import Telas.JanelaTabuleiro;
import Telas.TelaInicial;

public class TelaInicialListener {
	private TelaInicial janela;
	
	private static String jogadorDesafiado = null;
	private static ArrayList<JLabel> labelClicado = new ArrayList<>();
	private ArrayList<Jogador> listaDePlayerTotais;
	
	public TelaInicialListener(TelaInicial janela) {
		this.janela = janela;
		this.listaDePlayerTotais = janela.getListaDePlayerTotais();
		
		JButtonListener();
		JtextFieldListener();
	}
	
	private void JButtonListener() {
		janela.getBotaoDesafiar().addActionListener(new ActionListener() {
			Jogador player = janela.getPlayer();
			public void actionPerformed(ActionEvent e) {
				if(jogadorDesafiado.equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione algum player para jogar!", "ERROR", JOptionPane.ERROR_MESSAGE);
				
				}else if(player.getUsuario().equals(jogadorDesafiado)) {
					JOptionPane.showMessageDialog(null, "Você Não pode se desafiar!", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					Jogador playerInimigo = DAO.LerPlayer(jogadorDesafiado);

					new JanelaTabuleiro(playerInimigo, player);
					janela.getFrame().setVisible(false);
					
				}
			}
		});
		
		
		janela.getBotaoOrganizarPorNomes().addActionListener(new ActionListener() {
			JPanel painel = janela.getPainel();
			public void actionPerformed(ActionEvent e) {
				ArrayList<JLabel> LabelsPlayersRank = janela.AdicionarJlabelRank(listaDePlayerTotais, false); //coloca as informações de cada player em um label
				janela.PosicionarJlabelsRank(LabelsPlayersRank);
				painel.setVisible(false);
				painel.setVisible(true);
			}
		});
		
		janela.getBotaoOrganizarPorPontos().addActionListener(new ActionListener() {
			JPanel painel = janela.getPainel();
			public void actionPerformed(ActionEvent e) {
				ArrayList<JLabel> LabelsPlayersRank = janela.AdicionarJlabelRank(listaDePlayerTotais, true); //coloca as informações de cada player em um label
				janela.PosicionarJlabelsRank(LabelsPlayersRank);
				painel.setVisible(false);
				painel.setVisible(true);
			}
		});
	}
	
	private void JtextFieldListener() {
		JTextField jtBarraDePesquisa = janela.getJtBarraDePesquisa();
		JPanel painel = janela.getPainel();
		
		jtBarraDePesquisa.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(jtBarraDePesquisa.getText().equals("")) {
					ArrayList<JLabel> LabelsPlayersRank = janela.AdicionarJlabelRank(listaDePlayerTotais, true); //coloca as informações de cada player em um label
					janela.PosicionarJlabelsRank(LabelsPlayersRank);
					
				}else {
					String texto = jtBarraDePesquisa.getText();
					ArrayList<Jogador> lista = BDP.BarraDePesquisa(texto);
					ArrayList<JLabel> LabelsPlayersRank = janela.AdicionarJlabelRank(lista, true);
					janela.PosicionarJlabelsRank(LabelsPlayersRank);
					
				}
				painel.setVisible(false);
				painel.setVisible(true);
			}
		});
		
		jtBarraDePesquisa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				jtBarraDePesquisa.setText("");
				
			}
		});
	}
	
	public static void JLabelListener(JLabel label, TelaInicial janelaParametro) {
		JFrame frame = janelaParametro.getFrame();
		
		label.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				if(!labelClicado.contains(label)) {
					jogadorDesafiado = label.getText().split(" ")[0];
					label.setBorder(BorderFactory.createEtchedBorder(frame.getBackground(), Color.GREEN));
					labelClicado.add(label);
					
					if(!labelClicado.get(0).equals(label)) {
						labelClicado.get(0).setBorder(BorderFactory.createEtchedBorder(frame.getBackground(), Color.BLACK));
						labelClicado.remove(0);
					}
				}else {
					label.setBorder(BorderFactory.createEtchedBorder(frame.getBackground(), Color.BLACK));
					jogadorDesafiado = "";
					labelClicado.remove(0);
				}
			}
		});
	}
}
