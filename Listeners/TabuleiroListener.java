package Listeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Arqs.DAO;
import Arqs.Diretorios;
import Arqs.IA;
import Arqs.Jogador;
import Arqs.Servico;
import Arqs.Paralelismo;
import Telas.JanelaTabuleiro;
import Telas.TelaInicial;

public class TabuleiroListener{
	private static JFrame frame;
	private static int Tentativas;
    private static int TentativasInimiga;
    private static int pontuacao;
    private static int pontuacaoInimiga;
    private static int contadorDeBarcosInimigos;
    private static int contadorDeBarcos;
    private static boolean VezDoInimigo;
    private static ArrayList<JButton> botoesClicados = new ArrayList<JButton>();
    private static ArrayList<JButton> listaDosBotoesVerificadosIA = new ArrayList<>();
    private static Jogador player;
    private static Jogador playerInimigo;
    private static JLabel jlCliques;
    private static JLabel barcosRestantes;
    private static JLabel barcosRestantesInimigo;
    private static JLabel jlCliquesInimigo;
    private static IA ia;
    private static String logs;
	
	public TabuleiroListener(JanelaTabuleiro janela) {
		TabuleiroListener.Tentativas = 0;
		TabuleiroListener.TentativasInimiga = 0;
		TabuleiroListener.pontuacao = 0;
		TabuleiroListener.pontuacaoInimiga = 0;
		TabuleiroListener.contadorDeBarcosInimigos = 5;
		TabuleiroListener.contadorDeBarcos = 5;
		TabuleiroListener.VezDoInimigo = false;
		TabuleiroListener.frame = janela.getFrame();
		TabuleiroListener.jlCliques = janela.getJlCliques();
		TabuleiroListener.jlCliquesInimigo = janela.getJlCliquesInimigo();
		TabuleiroListener.barcosRestantes = janela.getBarcosRestantes();
		TabuleiroListener.barcosRestantesInimigo = janela.getBarcosRestantesInimigo();
		TabuleiroListener.player = janela.getPlayer();
		TabuleiroListener.playerInimigo = janela.getPlayerInimigo();
		TabuleiroListener.logs = (janela.getLogs() == null)? "": janela.getLogs();
		TabuleiroListener.ia = new IA(janela.getPlayer().getLocaisDosBarcos());
		
	}
	
	public static void ListenersBotao(JButton botao) {
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(!botoesClicados.contains(event.getSource())) {
					botoesClicados.add(botao);
		
					//clica e verifica se tem barco ali, se sim muda a cor e veirifca se todo o barco foi descoberto, se sim muda o icone dele pra o do barco
					boolean verificadorDeNavios = false;
					for(ArrayList<Integer> listaDeDados : playerInimigo.getLocaisDosBarcos()) {
						ArrayList<JButton> listaDosBotoesVerificados = new ArrayList<>();
						
						int largura = listaDeDados.get(0);
						int altura = listaDeDados.get(1);
						int x = listaDeDados.get(2);
						int y = listaDeDados.get(3);
						
						JButton botaoRecuperado = null;
						int contadorDeBarcosAchados = 0;
						int valorHorizontal = 0; int valorVertical = 0;
						String tipoDoBarco = "";
						int tamanhoDoBarco = (largura >= altura)? largura : altura;
						
						for(int i = 0 ; i < tamanhoDoBarco/50 ; i++) {
							
							botaoRecuperado = (JButton) frame.getContentPane().getComponentAt(x + valorHorizontal, y + valorVertical);
							
							if(botao.getX() == x + valorHorizontal && botao.getY() == y + valorVertical) {
								botao.setBackground(Color.YELLOW);
								botao.setEnabled(false);
								verificadorDeNavios = true;
								logs += Servico.GerarLogsDePartidas("BotaoEscolhidoNoTabuleiro", botao.getX(), botao.getY(), player, playerInimigo, null, "Barco"); // salva Logs inimigo
							}
							
							if(!botaoRecuperado.isEnabled()) {
								listaDosBotoesVerificados.add(botaoRecuperado);
								contadorDeBarcosAchados++;
							}
							
							if(largura >= altura) {
								valorHorizontal += 50;
								tipoDoBarco = "l";
							}else {
								valorVertical += 50;
								tipoDoBarco = "a";
							}
							
							if(contadorDeBarcosAchados == tamanhoDoBarco/50) {
								for(JButton botaoAtual : listaDosBotoesVerificados) {
									frame.remove(botaoAtual);
								}
								contadorDeBarcosInimigos--;
								barcosRestantes.setText(String.valueOf(contadorDeBarcosInimigos));
								JButton botaoNovo = new JButton(); botaoNovo.setBounds(x, y, largura, altura); frame.add(botaoNovo);
								botaoNovo.setIcon(new ImageIcon(Diretorios.NavioIcon(String.valueOf(contadorDeBarcosAchados), tipoDoBarco)));
							}
						}
					}
		
					if(verificadorDeNavios == false) {
						botao.setIcon(new ImageIcon(Diretorios.DiretorioBomba()));
						logs += Servico.GerarLogsDePartidas("BotaoEscolhidoNoTabuleiro", botao.getX(), botao.getY(), player, playerInimigo, null, "Bomba"); // salva Logs inimigo
						VezDoInimigo = true;
					}else {
						VezDoInimigo = false;
						verificadorDeNavios = false;
					}
					jlCliques.setText(Integer.toString(++Tentativas));
					verificadorFimDeGame(botao);
					
					frame.setEnabled(false);
					new Paralelismo(frame).execute();
					
				}
			}
		});
	}
	
    public static void AcoesTabulieroInimigo(){
		while(VezDoInimigo) {
			ArrayList<Integer> dados = ia.Jogar(frame);
			
			///recuperar o botão
			Component botaoRecuperado = frame.getContentPane().getComponentAt(dados.get(1), dados.get(2));
			JButton botao = (JButton)(botaoRecuperado);
			
			TentativasInimiga++;
			jlCliquesInimigo.setText(String.valueOf(TentativasInimiga));
			VezDoInimigo = true;
			
			if(dados.get(0) == 1) {
				botao.setBackground(new Color(255,255,0, 95));
				listaDosBotoesVerificadosIA.add(botao);
				logs += Servico.GerarLogsDePartidas("BotaoEscolhidoNoTabuleiro", botao.getX(), botao.getY(), playerInimigo, player, (Integer) (null), "Barco"); // salva Logs inimigo
				
			}else if (dados.get(0) == 2) {
				contadorDeBarcos--;
				barcosRestantesInimigo.setText(String.valueOf(contadorDeBarcos));
				listaDosBotoesVerificadosIA.add(botao);
				for(JButton botoesIterados : listaDosBotoesVerificadosIA) {
					botoesIterados.setBackground(new Color(100,255,100, 85));
					frame.repaint();
				}
				
				listaDosBotoesVerificadosIA = new ArrayList<>();
				logs += Servico.GerarLogsDePartidas("BotaoEscolhidoNoTabuleiro", botao.getX(), botao.getY(), playerInimigo, player, (Integer) (null), "Barco"); // salva Logs inimigo
				
			}else {
				VezDoInimigo = false;
				botao.setIcon(new ImageIcon(Diretorios.DiretorioBomba()));
				
				
				logs += Servico.GerarLogsDePartidas("BotaoEscolhidoNoTabuleiro", botao.getX(), botao.getY(), playerInimigo, player, (Integer) (null), "Bomba"); // salva logs inimigo
			}
			
			verificadorFimDeGame(botao);
			if(VezDoInimigo) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
    }
	
	private static void verificadorFimDeGame(JButton botao) {
		if(contadorDeBarcosInimigos == 0) {
			pontuacao = 10;
			JOptionPane.showMessageDialog(null, "Parabéns você venceu!!!. \nSua pontuação: "+ pontuacao +"\npontuação Inimiga: " + pontuacaoInimiga +"\nTentativas: "+ Tentativas , "VITORIA", JOptionPane.WARNING_MESSAGE);
			
			player.addVitoria();
			playerInimigo.addDerrota();
			
			//salva os logs de wins
			String logVitoria = logs + Servico.GerarLogsDePartidas("Vitoria", 0, 0, player, playerInimigo, pontuacao, null) +
					Servico.GerarLogsDePartidas("Derrota", 0, 0, playerInimigo, player, pontuacaoInimiga, null);
			String logDerrota = logs + Servico.GerarLogsDePartidas("Derrota", 0, 0, playerInimigo, player, pontuacaoInimiga, null) +
					Servico.GerarLogsDePartidas("Vitoria", 0, 0, player, playerInimigo, pontuacao, null); 
			
			DAO.SalvarHistoricoDePartida(player.getUsuario(), logVitoria);
			DAO.SalvarHistoricoDePartida(playerInimigo.getUsuario(), logDerrota);
		}
		//verifica se o game acabou para a IA
		if(contadorDeBarcos == 0 ) {
			VezDoInimigo = false;
			pontuacao = -5;
			pontuacaoInimiga = 2;
			
			JOptionPane.showMessageDialog(null, "Você perdeu!!!. \nPontuação: " + pontuacao +"\npontuação Inimiga: " + pontuacaoInimiga + "\nTentativas: "+ Tentativas , "DERROTA", JOptionPane.WARNING_MESSAGE);
			
			///salva os logs de wins
			String logVitoria = logs + Servico.GerarLogsDePartidas("Vitoria", botao.getX(), botao.getY(), playerInimigo, player, pontuacaoInimiga, null) +
					Servico.GerarLogsDePartidas("Derrota", botao.getX(), botao.getY(), player, playerInimigo, pontuacao, null);
			String logDerrota = logs + Servico.GerarLogsDePartidas("Derrota", botao.getX(), botao.getY(), player, playerInimigo, pontuacao, null) +
					Servico.GerarLogsDePartidas("Vitoria", botao.getX(), botao.getY(), playerInimigo, player, pontuacaoInimiga, null);
			
			DAO.SalvarHistoricoDePartida(playerInimigo.getUsuario(), logDerrota);
			DAO.SalvarHistoricoDePartida(player.getUsuario(), logVitoria);
			
			player.addDerrota();
			playerInimigo.addVitoria();
			
		}
		
		if(contadorDeBarcos == 0 || contadorDeBarcosInimigos == 0) {
			player.addPontuacao(pontuacao);
			playerInimigo.addPontuacao(pontuacaoInimiga);
			
			player.addPartida();
			playerInimigo.addPartida();
			DAO.SalvarPlayer(player);
			DAO.SalvarPlayer(playerInimigo);
			
			DAO.salvarHistoricoDePontos(player);
			DAO.salvarHistoricoDePontos(playerInimigo);
			
			new TelaInicial(player);
			frame.setVisible(false);
		}
	}
}
