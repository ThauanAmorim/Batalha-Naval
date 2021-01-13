package Telas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public abstract class TelaDefault {
	protected JFrame frame = new JFrame();
	
	public JFrame configTela(int alturaFrame, int larguraFrame, String tituloFrame) {
		frame.setSize(alturaFrame, larguraFrame);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(tituloFrame);
		return frame;
	}
	
	public JLabel adicionarLabel( String texto, int X, int Y, int largura, int altura) {
		JLabel lb = new JLabel(texto);
		lb.setBounds(X, Y, largura, altura);
		return lb;
	}
	
	public JLabel adicionarLabel( String texto, int X, int Y, int largura, int altura, int style, int size) {
		JLabel lb = new JLabel(texto);
		lb.setBounds(X, Y, largura, altura);
		lb.setFont(new Font("Arial", style, size));
		return lb;
	}
	
	public JTextField adicionarTextField(int X,int Y, int largura, int altura) {
		JTextField campotexto = new JTextField();
		campotexto.setBounds(X, Y, largura, altura);
		return campotexto;
		
	}
	
	public JTextField adicionarTextField(String texto, int X,int Y, int largura, int altura) {
		JTextField campotexto = new JTextField();
		campotexto.setText(texto);
		campotexto.setBounds(X, Y, largura, altura);
		return campotexto;
		
	}
	
	public JButton adicionarJButton(String nome, int X, int Y, int largura, int altura) {
		JButton botao = new JButton(nome);
		botao.setFont(new Font("Arial", Font.BOLD, 15));
		botao.setBounds(X, Y, largura, altura);
		return botao;
	}
	
	public JButton adicionarJButton(String nome, int X, int Y, int largura, int altura, Color cor) {
		JButton botao = new JButton(nome);
		botao.setFont(new Font("Arial", Font.BOLD, 15));
		botao.setBounds(X, Y, largura, altura);
		botao.setBackground(cor);
		return botao;
	}
	
	public JPasswordField adicionarJPassword(int X, int Y, int largura, int altura) {
		JPasswordField senha = new JPasswordField();
		senha.setBounds(X, Y, largura, altura);
		return senha;
		
	}
}
