package Telas;
import Arqs.*;
import Listeners.TelaInicialListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TelaInicial extends TelaDefault{
	private Jogador player;
	private JFrame frame;
	private JPanel painel;
	private JTextField jtBarraDePesquisa;
	private JButton botaoDesafiar;
	private JButton botaoOrganizarPorNomes;
	private JButton botaoOrganizarPorPontos;
	
	private ArrayList<Jogador> listaDePlayerTotais;
	
	public TelaInicial(Jogador player){
		this.player = player;
		frame = configTela(400, 385, "Tela Inicial");
		listaDePlayerTotais = gerarListaDePlayers();
		
		//Menu
		adicionarMenu(player);
		
		//JPanels
		painel = new JPanel();
		painel.setBounds(5, 80, 200, 235);
		
		//JLabels
		frame.add(adicionarLabel("Pontuação: ",230,5, 200, 30));
		frame.add(adicionarLabel(String.valueOf(player.getPontuacao()),300,5, 200, 30));
		frame.add(adicionarLabel("Jogadores prontos:  ",5,35, 200, 30));
		frame.add(adicionarLabel("Olá " + player.getUsuario(), 250,90, 200, 30));
		
		ArrayList<JLabel> LabelsPlayersRank = AdicionarJlabelRank(listaDePlayerTotais, true); //coloca as informações de cada player em um label
		PosicionarJlabelsRank(LabelsPlayersRank);
		
		//JButtons
		botaoDesafiar = adicionarJButton("Desafiar", 270,285, 100, 30);
		frame.add(botaoDesafiar);
		
		botaoOrganizarPorNomes = adicionarJButton("nomes", 5, 60, 85, 15);
		botaoOrganizarPorNomes.setFont(new Font("Arial", Font.PLAIN, 15));
		frame.add(botaoOrganizarPorNomes);
		
		botaoOrganizarPorPontos = adicionarJButton("pontos", 100, 60, 85, 15);
		botaoOrganizarPorPontos.setFont(new Font("Arial", Font.PLAIN, 15));
		frame.add(botaoOrganizarPorPontos);
		
		//JTextField
		jtBarraDePesquisa = adicionarTextField("Pesquisa", 5, 10, 200, 30);
		frame.add(jtBarraDePesquisa);
	
		//scrollbar
		frame.add(adicionarScroll(LabelsPlayersRank.size()));
		
		//Listener
		new TelaInicialListener(this);
		
		frame.repaint();
		
	}
	
	private JScrollPane adicionarScroll(int tamanho) {
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		painel.setPreferredSize(new Dimension(10, 30*tamanho+1));
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(painel.getBounds());
		scroll.setVisible(true);
		scroll.setAutoscrolls(true);
		scroll.setViewportView(painel);
		scroll.setViewportBorder(BorderFactory.createLoweredBevelBorder());
		return scroll;
	 }
	
	public ArrayList<JLabel> AdicionarJlabelRank(ArrayList<Jogador> listaDePlayersInicial, Boolean organizacaoPorPontos) {
		ArrayList<JLabel> listaDeJLabels = new ArrayList<>();
		ArrayList<Jogador> listaDePlayers = null;
		if(organizacaoPorPontos) {
			listaDePlayers = OrganizarRankingPorPontos(listaDePlayersInicial);
			
		}else {
			listaDePlayers = OrganizarRankingPorNome(listaDePlayersInicial);
			
		}
		
		for(int i = 0 ; i < listaDePlayers.size() ; i++) {
			Jogador player = listaDePlayers.get(i);
			JLabel label = new JLabel();
			label.setPreferredSize(new Dimension(170, 25));
			String quantidadeDeEspacos= "";
			for(int j = player.getUsuario().length(); j <= 20 - String.valueOf(player.getPontuacao()).length() ; j++) {
				quantidadeDeEspacos+=" ";
			}
			label.setText(player.getUsuario() + quantidadeDeEspacos + player.getPontuacao());
			label.setBorder(BorderFactory.createEtchedBorder(frame.getBackground(), Color.BLACK));
			listaDeJLabels.add(label);
		}
		
		return listaDeJLabels;
	}
	
	public ArrayList<Jogador> OrganizarRankingPorPontos(ArrayList<Jogador> listaInicial){
		Jogador[] listaOrganizada = new Jogador[listaInicial.size()];
		ArrayList<Integer> listaDePontuacoes = new ArrayList<Integer>();
		if(listaInicial.size() == 1) {
			return listaInicial;
		}
		
		for(Jogador player : listaInicial) {
			listaDePontuacoes.add(player.getPontuacao());
		}
		listaDePontuacoes.sort(null);
		Collections.reverse(listaDePontuacoes);
		
		for(Jogador player : listaInicial) {
			int index = listaDePontuacoes.indexOf(player.getPontuacao());
			listaDePontuacoes.set(index, null);
			listaOrganizada[index] = player;
		}
		return new ArrayList<>(Arrays.asList(listaOrganizada));
	}
	
	public ArrayList<Jogador> OrganizarRankingPorNome(ArrayList<Jogador> listaInicial){
		Jogador[] listaOrganizada = new Jogador[listaInicial.size()];
		ArrayList<String> listaDeUserNames = new ArrayList<>();
		if(listaInicial.size() == 1) {
			return listaInicial;
		}
		
		for(Jogador player : listaInicial) {
			listaDeUserNames.add(player.getUsuario().toLowerCase());
		}
		listaDeUserNames.sort(null);
		
		for(Jogador player : listaInicial) {
			int index = listaDeUserNames.indexOf(player.getUsuario().toLowerCase());
			listaDeUserNames.set(index, null);
			listaOrganizada[index] = player;
		}
		
		return new ArrayList<>(Arrays.asList(listaOrganizada));
	}
	
	public void PosicionarJlabelsRank(ArrayList<JLabel> LabelsPlayersRank) {
		painel.removeAll();
		for(int i = 0 ; i < LabelsPlayersRank.size() ; i++) {
			 JLabel label = LabelsPlayersRank.get(i);
			painel.add(label);
			
			TelaInicialListener.JLabelListener(label, this);
		}
	}
	
	
	
	
	private ArrayList<Jogador> gerarListaDePlayers() {
		ArrayList<String> listaDeUsernames = new ArrayList<>();
		ArrayList<Jogador> listaObjJogador = new ArrayList<>();
		
		listaDeUsernames =  DAO.LerListaDePlayers().getListaDePlayers();
		

		for(int i = 0; i < listaDeUsernames.size() ; i++) {
			listaObjJogador.add(DAO.LerPlayer(listaDeUsernames.get(i)));
		}
		
		return listaObjJogador;
	}

	private void adicionarMenu(Jogador player) {
		JMenuBar jtMenu = new JMenuBar();
		
		JMenu menu = new JMenu("Opções");
		JMenu subMenuHisotrico = new JMenu("Historico");
		jtMenu.add(menu);
		menu.add(subMenuHisotrico);
		
		JMenuItem alterarDisposição = new JMenuItem("Alterar Disposição");
		menu.add(alterarDisposição);
		JMenuItem profile = new JMenuItem("Profile");
		menu.add(profile);
		JMenuItem historicoPartidas = new JMenuItem("Partidas");
		subMenuHisotrico.add(historicoPartidas);
		
		JMenuItem historicoDepontuacao = new JMenuItem("Pontos");
		subMenuHisotrico.add(historicoDepontuacao);
		
		JMenuItem sair = new JMenuItem("Logoff");
		menu.add(sair);
		
		frame.setJMenuBar(jtMenu);
		frame.repaint();
		
		//listener	
		alterarDisposição.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaEditarDisposicao(player, "TelaInicial");
				frame.setVisible(false);
			}
		});
		
		profile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaProfile(player);
				frame.setVisible(false);
			}
		});
		
		historicoPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaHistoricoDePartida(player);
				frame.setVisible(false);
			}
		});
		
		historicoDepontuacao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JanelaHistoricoPontos(player);
				frame.setVisible(false);
			}
		});
		
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int escolha = JOptionPane.showConfirmDialog(null, "Deseja realmente fazer logoff?", null, JOptionPane.YES_NO_OPTION);
				if(escolha == JOptionPane.YES_OPTION) {
					frame.setVisible(false);
					new JanelaLogin();
				}
				
			}
		});
	}
	
	public JPanel getPainel() {
		return painel;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JButton getBotaoOrganizarPorNomes() {
		return botaoOrganizarPorNomes;
	}

	public JButton getBotaoOrganizarPorPontos() {
		return botaoOrganizarPorPontos;
	}

	public JTextField getJtBarraDePesquisa() {
		return jtBarraDePesquisa;
	}

	public Jogador getPlayer() {
		return player;
	}

	public ArrayList<Jogador> getListaDePlayerTotais() {
		return listaDePlayerTotais;
	}

	public JButton getBotaoDesafiar() {
		return botaoDesafiar;
	}
}
