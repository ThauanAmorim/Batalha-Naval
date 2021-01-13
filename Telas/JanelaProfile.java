package Telas;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import Arqs.DAO;
import Arqs.Grafico;
import Arqs.Jogador;
import Listeners.ProfileListener;

public class JanelaProfile extends TelaDefault{
	private JFrame frame;
	private Jogador player;
	private JPanel painelDados;
	private JPanel painelSenha;
	private JTextField displayName ;
	private JTextField email;
	private JTextField pontuacao;
	private JButton jbVoltar;
	private JButton JbDeletarConta;
	private JButton jbAlterarSenha;
	
	public JanelaProfile(Jogador player) {
		this.player = player;
		frame = configTela(420, 680, "Profile");
		
		//JPanels
		painelDados = new JPanel();
		painelDados.setBackground(Color.gray);
		painelDados.setBounds(10, 60, 380, 130);
		painelDados.setLayout(null);
		frame.add(painelDados); 
		
		painelSenha = new JPanel();
		painelSenha.setBackground(Color.gray);
		painelSenha.setBounds(10, 500, 380, 70);
		painelSenha.setLayout(null);
		frame.add(painelSenha);
		
		//JLabels
		frame.add(adicionarLabel("PROFILE", 10, 10, 200, 30, Font.BOLD, 30));
		frame.add(adicionarLabel("PARTIDAS", 10, 200, 200, 30, Font.BOLD, 30));
		frame.add(adicionarLabel("SENHA", 10, 450, 200, 30, Font.BOLD, 30));
		
		painelDados.add(adicionarLabel("Display Name: ", 5, 10, 200, 30, Font.BOLD, 15));
		painelDados.add(adicionarLabel("Email: ", 5, 50, 200, 30, Font.BOLD, 15));
		painelDados.add(adicionarLabel("Pontuação Geral: ", 5, 90, 200, 30, Font.BOLD, 15));
		
		//Grafico pizza
		ArrayList<String> valores = new ArrayList<>();
		valores.add(String.valueOf(player.getPartidas()));
		valores.add(String.valueOf(player.getVitorias()));
		valores.add(String.valueOf(player.getDerrotas()));
		
		ArrayList<String> nome = new ArrayList<>(); 
		nome.add("Partidas"); nome.add("Vitorias"); nome.add("Derrotas");
		
		JPanel graficoDoPlayer = Grafico.pizza3DStatic(nome, valores, " "); 
		graficoDoPlayer.setBounds(10, 250, 380, 180); 
		frame.add(graficoDoPlayer);
		
		//JTextFields
		displayName = adicionarTextField(player.getUsuario(), 135, 10, 230, 30); 
		painelDados.add(displayName);
		
		email = adicionarTextField(player.getEmail(), 135, 50, 230, 30); 
		painelDados.add(email);
		
		pontuacao = adicionarTextField(String.valueOf(player.getPontuacao()), 135, 90, 230, 30); 
		pontuacao.setEditable(false); 
		painelDados.add(pontuacao);
		
		//JButtons
		jbVoltar = adicionarJButton("Salvar / Voltar", 200, 590, 150, 30);
		frame.add(jbVoltar); 
		
		JbDeletarConta = adicionarJButton("Deletar Conta", 20, 590, 150, 30, new Color(200, 0, 0, 200)); 
		frame.add(JbDeletarConta); 
		
		jbAlterarSenha = adicionarJButton("Alterar senha", 105, 20, 150, 30); 
		painelSenha.add(jbAlterarSenha); 
		
		//Listener
		new ProfileListener(this);
		
		frame.repaint();
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public Jogador getPlayer() {
		return player;
	}

	public JPanel getPainelDados() {
		return painelDados;
	}

	public JPanel getPainelSenha() {
		return painelSenha;
	}

	public JTextField getDisplayName() {
		return displayName;
	}

	public JTextField getEmail() {
		return email;
	}

	public JTextField getPontuacao() {
		return pontuacao;
	}

	public JButton getJbVoltar() {
		return jbVoltar;
	}

	public JButton getJbDeletarConta() {
		return JbDeletarConta;
	}

	public JButton getJbAlterarSenha() {
		return jbAlterarSenha;
	}
}
