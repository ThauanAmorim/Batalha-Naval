package Telas;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import Arqs.Diretorios;
import Arqs.Jogador;
import Arqs.Servico;
import Listeners.TabuleiroListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.ArrayList;

import javax.swing.JFrame;

public class JanelaTabuleiro extends TelaDefault{
    private JFrame frame;
    private Jogador player;
    private Jogador playerInimigo;
    private String[] arrayLetras = {"A", "B", "C", "D", "E"};
    private String[] arrayNumeros = {"1", "2", "3", "4", "5"};
    private String logs = "";
    
    private JLabel jlCliques;
    private JLabel barcosRestantes;
    private JLabel jlCliquesInimigo;
    private JLabel barcosRestantesInimigo;
    
    private int Inicial = 50; 
    private JButton[][] matrizBotoes =
    	{{AdicionaroBotao(Inicial, Inicial, false), AdicionaroBotao(Inicial*2, Inicial, false), AdicionaroBotao(Inicial*3, Inicial, false), AdicionaroBotao(Inicial*4, Inicial, false), AdicionaroBotao(Inicial*5, Inicial, false) },
    	{AdicionaroBotao(Inicial, Inicial*2, false), AdicionaroBotao(Inicial*2, Inicial*2, false), AdicionaroBotao(Inicial*3, Inicial*2, false), AdicionaroBotao(Inicial*4, Inicial*2, false), AdicionaroBotao(Inicial*5, Inicial*2, false)}, 
    	{AdicionaroBotao(Inicial, Inicial*3, false), AdicionaroBotao(Inicial*2, Inicial*3, false), AdicionaroBotao(Inicial*3, Inicial*3, false), AdicionaroBotao(Inicial*4, Inicial*3, false), AdicionaroBotao(Inicial*5, Inicial*3, false)}, 
    	{AdicionaroBotao(Inicial, Inicial*4, false), AdicionaroBotao(Inicial*2, Inicial*4, false), AdicionaroBotao(Inicial*3, Inicial*4, false), AdicionaroBotao(Inicial*4, Inicial*4, false), AdicionaroBotao(Inicial*5, Inicial*4, false)}, 
    	{AdicionaroBotao(Inicial, Inicial*5, false), AdicionaroBotao(Inicial*2, Inicial*5, false), AdicionaroBotao(Inicial*3, Inicial*5, false), AdicionaroBotao(Inicial*4, Inicial*5, false), AdicionaroBotao(Inicial*5, Inicial*5, false)}};
    private JButton[][] matrizBotoesInimiga =
    	{{AdicionaroBotao(Inicial*10, Inicial, true), AdicionaroBotao(Inicial*11, Inicial, true), AdicionaroBotao(Inicial*12, Inicial, true), AdicionaroBotao(Inicial*13, Inicial, true), AdicionaroBotao(Inicial*14, Inicial, true) },
    	{AdicionaroBotao(Inicial*10, Inicial*2, true), AdicionaroBotao(Inicial*11, Inicial*2, true), AdicionaroBotao(Inicial*12, Inicial*2, true), AdicionaroBotao(Inicial*13, Inicial*2, true), AdicionaroBotao(Inicial*14, Inicial*2, true)}, 
    	{AdicionaroBotao(Inicial*10, Inicial*3, true), AdicionaroBotao(Inicial*11, Inicial*3, true), AdicionaroBotao(Inicial*12, Inicial*3, true), AdicionaroBotao(Inicial*13, Inicial*3, true), AdicionaroBotao(Inicial*14, Inicial*3, true)}, 
    	{AdicionaroBotao(Inicial*10, Inicial*4, true), AdicionaroBotao(Inicial*11, Inicial*4, true), AdicionaroBotao(Inicial*12, Inicial*4, true), AdicionaroBotao(Inicial*13, Inicial*4, true), AdicionaroBotao(Inicial*14, Inicial*4, true)}, 
    	{AdicionaroBotao(Inicial*10, Inicial*5, true), AdicionaroBotao(Inicial*11, Inicial*5, true), AdicionaroBotao(Inicial*12, Inicial*5, true), AdicionaroBotao(Inicial*13, Inicial*5, true), AdicionaroBotao(Inicial*14, Inicial*5, true)}};
    
    
    public JanelaTabuleiro(Jogador playerInimigo, Jogador player){
    	frame = configTela(810, 570, "Tabuleiro");
    	this.player = player;
    	this.playerInimigo = playerInimigo;
    	
    	logs += Servico.GerarLogsDePartidas("Desafiado", 0, 0, player, playerInimigo, null, null);
        
        // labels do player1
        frame.add(adicionarLabel("Tentativas: ", 50, 370, 50, 50)).setSize(200, 100); 
        frame.add(adicionarLabel("Barcos restantes: ", 50, 410, 50, 50)).setSize(200, 100); 
        jlCliques = adicionarLabel("0", 140, 395, 50, 50); 
        barcosRestantes = adicionarLabel("5", 200, 435, 50, 50);
        frame.add(jlCliques); frame.add(barcosRestantes);
        
        // labels do inimigo
        frame.add(adicionarLabel("Tentativas: ", 500, 370, 50, 50)).setSize(200, 100); 
        frame.add(adicionarLabel("Barcos restantes: ", 500, 410, 50, 50)).setSize(200, 100); 
        jlCliquesInimigo = adicionarLabel("0", 590, 395, 50, 50); 
        barcosRestantesInimigo = adicionarLabel("5", 650, 435, 50, 50);
        frame.add(jlCliquesInimigo); frame.add(barcosRestantesInimigo);
        
        //Listener
        new TabuleiroListener(this);
        
        //posiciona os botoes no frame
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                JButton botao = matrizBotoes[j][i];
                botao.setIcon(new ImageIcon(Diretorios.SeaIcon()));
                
                JButton botaoInimigo = matrizBotoesInimiga[j][i];
                botaoInimigo.setIcon(new ImageIcon(Diretorios.SeaIcon()));
                
                frame.add(botao); frame.add(botaoInimigo);
                TabuleiroListener.ListenersBotao(botao);
            }
        }
        
        //posiciona as letras e numeros do tabuleiro
        for(int coordenadaDolocal = 50 ; coordenadaDolocal < 300 ; coordenadaDolocal += 50) {
        	Font font = new Font("Arial", Font.BOLD, 13);
        	JLabel botaoL = new JLabel();
        	botaoL.setFont(font);
        	botaoL.setBounds(20, coordenadaDolocal, 10, 50);
        	botaoL.setText(arrayLetras[(coordenadaDolocal/50)-1]);
        	frame.add(botaoL);
        	
        	JLabel botaoL1 = new JLabel();
        	botaoL1.setFont(font);
        	botaoL1.setBounds(465, coordenadaDolocal, 10, 50);
        	botaoL1.setText(botaoL.getText());
        	frame.add(botaoL1);
        	
        	JLabel botaoN = new JLabel();
        	botaoN.setFont(font);
        	botaoN.setBounds(coordenadaDolocal+20, 20, 15, 15);
        	botaoN.setText(arrayNumeros[(coordenadaDolocal/50)-1]);
        	frame.add(botaoN);
        	
        	JLabel botaoN1 = new JLabel();
        	botaoN1.setFont(font);
        	botaoN1.setBounds((coordenadaDolocal+20)+450, 20, 15, 15);
        	botaoN1.setText(botaoN.getText());
        	frame.add(botaoN1);
        	
        }
        
        //altera a imagem do botoes
        for(ArrayList<Integer> listaDeDados : player.getLocaisDosBarcos()) {
        	//ex 150-50-100-50
        	int largura = listaDeDados.get(0);
        	int altura = listaDeDados.get(1);
        	int x = listaDeDados.get(2);
        	int y = listaDeDados.get(3);
        	
        	int distancia = 450;
        	JButton botao = new JButton(); botao.setBounds(x+distancia, y, largura, altura);
        	frame.add(botao);
        	
        	//remove o botão padrao e coloca o icone dos navios
        	if(largura >= altura) {
        		for(int i = 0 ; i < largura/50 ; i++) {
        			Component botaoRecuperadoComponent = frame.getContentPane().getComponentAt(x+distancia, y);
        			JButton botaoRecuperado = (JButton)(botaoRecuperadoComponent);
        			frame.remove(botaoRecuperado);
        			JButton botaoInvisivel = new JButton(); botaoInvisivel.setBounds(x+distancia, y, 50, 50); botaoInvisivel.setVisible(false);
        			frame.add(botaoInvisivel);
        			distancia+= 50;
        		}
        		botao.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(largura/50), "l")));
        		
        	}else if (altura > largura) {
        		for(int i = 0 ; i < altura/50 ; i++) {
        			Component botaoRecuperadoComponent = frame.getContentPane().getComponentAt(x+distancia, y);
        			JButton botaoRecuperado = (JButton) (botaoRecuperadoComponent);
        			frame.remove(botaoRecuperado);
        			JButton botaoInvisivel = new JButton(); botaoInvisivel.setBounds(x+distancia, y, 50, 50); botaoInvisivel.setVisible(false);
        			frame.add(botaoInvisivel);
        			y+= 50;
        		}
        		botao.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(altura/50), "a")));
        	}
        	frame.add(botao);
        }
        frame.repaint();
    }
    
    public JFrame getFrame() {
		return frame;
	}

	public Jogador getPlayer() {
		return player;
	}

	public Jogador getPlayerInimigo() {
		return playerInimigo;
	}

	public String[] getArrayLetras() {
		return arrayLetras;
	}

	public String[] getArrayNumeros() {
		return arrayNumeros;
	}

	public JLabel getJlCliques() {
		return jlCliques;
	}

	public JLabel getBarcosRestantes() {
		return barcosRestantes;
	}

	public JLabel getJlCliquesInimigo() {
		return jlCliquesInimigo;
	}

	public JLabel getBarcosRestantesInimigo() {
		return barcosRestantesInimigo;
	}

	public String getLogs() {
		return logs;
	}

	public int getInicial() {
		return Inicial;
	}

	private JButton AdicionaroBotao(int X, int Y, boolean tipo) {
        final JButton botao = new JButton();
        botao.setBounds(X, Y, 50, 50);
        if(tipo) {
			botao.setBackground(new Color(255, 255, 255));
        	botao.setEnabled(true);
        }
        return botao;
    }
}