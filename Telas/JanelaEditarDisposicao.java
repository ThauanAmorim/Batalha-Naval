package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Arqs.DAO;
import Arqs.Diretorios;
import Arqs.Jogador;
import Listeners.DragListener;

public class JanelaEditarDisposicao extends JFrame{
	private static final long serialVersionUID = 1L;

	private ArrayList<JButton> listaDeNavios = new ArrayList<>();
    
    private int Inicial = 50;
    private JButton[][] matrizBotoes =
    	{{AdicionaroBotao(Inicial, Inicial), AdicionaroBotao(Inicial*2, Inicial), AdicionaroBotao(Inicial*3, Inicial), AdicionaroBotao(Inicial*4, Inicial), AdicionaroBotao(Inicial*5, Inicial) },
    	{AdicionaroBotao(Inicial, Inicial*2), AdicionaroBotao(Inicial*2, Inicial*2), AdicionaroBotao(Inicial*3, Inicial*2), AdicionaroBotao(Inicial*4, Inicial*2), AdicionaroBotao(Inicial*5, Inicial*2)}, 
    	{AdicionaroBotao(Inicial, Inicial*3), AdicionaroBotao(Inicial*2, Inicial*3), AdicionaroBotao(Inicial*3, Inicial*3), AdicionaroBotao(Inicial*4, Inicial*3), AdicionaroBotao(Inicial*5, Inicial*3)}, 
    	{AdicionaroBotao(Inicial, Inicial*4), AdicionaroBotao(Inicial*2, Inicial*4), AdicionaroBotao(Inicial*3, Inicial*4), AdicionaroBotao(Inicial*4, Inicial*4), AdicionaroBotao(Inicial*5, Inicial*4)}, 
    	{AdicionaroBotao(Inicial, Inicial*5), AdicionaroBotao(Inicial*2, Inicial*5), AdicionaroBotao(Inicial*3, Inicial*5), AdicionaroBotao(Inicial*4, Inicial*5), AdicionaroBotao(Inicial*5, Inicial*5)}}; 
  
    private JButton[] posicoesDosNavios = {CriarNavio(3, 350, 50), CriarNavio(3, 350, 110), CriarNavio(2, 350, 170), CriarNavio(1, 350, 230), CriarNavio(1, 350, 290)};
    
    public JanelaEditarDisposicao(Jogador player, String Direcao) {
        setSize(750, 425);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(3);
        setTitle("Sua Dispocisão de Navios");
        
        ///posicionar barcos
        DragListener dl;
        ArrayList<JButton> listaDosBotoesPosicionado = new ArrayList<>();
        for(int i = 0 ; i < posicoesDosNavios.length ; i++) {
        	JButton navio = posicoesDosNavios[i]; add(navio);
        	//BotaoNavioListener(navio);
        	dl = new DragListener(navio, listaDosBotoesPosicionado); 
            dl.addHandle(navio);
            
            listaDeNavios.add(navio);
        }
        
        ////colocar os botoes na tela
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton botao = matrizBotoes[j][i];
                add(botao);
            }
        }
        
        JButton okBotao = new JButton();
        okBotao.setText("Avançar");
        okBotao.setBounds(200, 330, 90, 30);
        add(okBotao);
        
        if(!Direcao.equals("JanelaDeLogin")) {
        	JButton voltarButton = new JButton();
        	voltarButton.setText("Voltar");
        	voltarButton.setBounds(55, 330, 90, 30);
            add(voltarButton);
            voltarButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new TelaInicial(player);
					setVisible(false);
				}
			});
        }
        okBotao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int quantidadeDeBarcos = 0;
				for(JButton botao : listaDeNavios) {
					if(botao.getX() > 300) {
						quantidadeDeBarcos++;
					}
					
				}
				if(quantidadeDeBarcos <= 0) {
					player.setLocaisDosBarcos(GerarPosicoesDosNaviosNoTabuleiro(listaDeNavios));
					DAO.SalvarPlayer(player);
					
					if(Direcao.equals("JanelaDeLogin")) {
						new JanelaLogin();
					}else {
						new TelaInicial(player);
					}
					
					setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "Posicione todos os barcos para prosseguir", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
        JTextArea regras = new JTextArea();
        regras.setBackground(new Color(79,79,79,60));
        regras.setBounds(550, 50, 150, 300);
        regras.setText("\n    REGRAS E DICAS\n - Clicar no barco muda \n  a orientação dele \n  (vertical e horinzontal)\n\n\n- Proibido colocar barcos \n  um ao lado do outro");
        add(regras);
        repaint();
        
        
    }
    
    public ArrayList<ArrayList<Integer>> GerarPosicoesDosNaviosNoTabuleiro (ArrayList<JButton> listaDeNavios){
    	
    	ArrayList<ArrayList<Integer>> listaDeTodosOsDados = new ArrayList<>();
    	for(int i = 0 ; i < listaDeNavios.size(); i++) {
    		ArrayList<Integer> listaDeDados = new ArrayList<>();
    		
    		int largura = listaDeNavios.get(i).getWidth();
    		int altura = listaDeNavios.get(i).getHeight();
    		int x = listaDeNavios.get(i).getX();
    		int y = listaDeNavios.get(i).getY();
    		
    		listaDeDados.add(largura);
    		listaDeDados.add(altura);
    		listaDeDados.add(x);
    		listaDeDados.add(y);
    		
    		listaDeTodosOsDados.add(listaDeDados);
    		
    	}
    	return listaDeTodosOsDados;
    }
    
    public JButton AdicionaroBotao(int X, int Y) {
        final JButton botao = new JButton();
        botao.setBounds(X, Y, 50, 50);
        botao.setIcon(new ImageIcon(Diretorios.SeaIcon()));
        return botao;
    }
    
    public JButton CriarNavio(int tamanhoNavio, int x, int y) {
    	JButton navio = new JButton();
    	navio.setBounds(x, y, 50*tamanhoNavio, 50);
    	navio.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(tamanhoNavio), "l")));
    	BotaoNavioListener(navio);
    	return navio;
    }
    
    public void BotaoNavioListener(JButton navio) {
    	navio.addMouseListener(new MouseAdapter() {
    		String direcao = "a";
			public void mouseClicked(MouseEvent e) {
				int largura = navio.getWidth();
				int altura = navio.getHeight();
				int x = navio.getX();
				int y = navio.getY();
				if(!(x < 50 || x > 300 && y <50 || y > 300)) {
					navio.setBounds(x, y, altura, largura);
					if(direcao.equals("a")) {
						navio.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(largura/50), direcao)));
						direcao = "l";
					}else {
						navio.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(altura/50), direcao)));
						direcao = "a";
					}
				
				}
				
			}
		});
    }
    
    public JLabel AdicionarLabel(String texto, int X, int Y) {
        Font f = new Font("Arial", 1, 15);
        JLabel lb = new JLabel(texto);
        lb.setBounds(X, Y, 50, 50);
        lb.setFont(f);
        return lb;
    }
}
