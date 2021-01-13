package Arqs;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;

public class IA {
	private ArrayList<ArrayList<Integer>> arrayListDasPosicoesDosBotoes = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> coordenadasJaUtilizadas = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> locaisDosBarcosDoPlayer = new ArrayList<>();
	
	private int xDoUltimoAcerto = 0;
	private int yDoultimoAcerto = 0;
	private int xAntesDoUltimoAcerto = 0;
	private int yAntesDoUltimoAcerto = 0;
	private int contadorDeSequencia = 0; 
	private boolean ocorreuAcerto = false;
	private boolean verificadorDeNavio = false;
	private boolean deuErradoNaSequencia = false;
	private boolean apagador = true;
	private Random r = new Random();
	
	@SuppressWarnings("unchecked")
	public IA(ArrayList<ArrayList<Integer>> locaisDosBarcosDoPlayerEntrada) {
		locaisDosBarcosDoPlayer = (ArrayList<ArrayList<Integer>>) locaisDosBarcosDoPlayerEntrada.clone();
		for(int y = 1 ; y <= 5 ; y++) {
			for(int x = 1 ; x <= 5 ; x++) {
				ArrayList<Integer> arrayTemp = new ArrayList<>();
				arrayTemp.add(y*50);
				arrayTemp.add(x*50);
				arrayListDasPosicoesDosBotoes.add(arrayTemp);
			}
		}
	}
	
	public ArrayList<Integer> Jogar(JFrame frame){
		ArrayList<Integer> dados = new ArrayList<>();
		//matriz tem numeros 1 0
		int valor = 0;
		
		//gera um valor aleatorio
		int indexDoArrayDeposicoes;
		if(arrayListDasPosicoesDosBotoes.size() > 1) {
			indexDoArrayDeposicoes = r.nextInt(arrayListDasPosicoesDosBotoes.size()-1);
		}else {
			indexDoArrayDeposicoes = 0;
		}
		int xDoArray = arrayListDasPosicoesDosBotoes.get(indexDoArrayDeposicoes).get(0); 
		int yDoArray = arrayListDasPosicoesDosBotoes.get(indexDoArrayDeposicoes).get(1);
		
		ArrayList<Integer> coordenadas = new ArrayList<>();
		coordenadas.add(xDoArray); coordenadas.add(yDoArray);
		
		while(coordenadasJaUtilizadas.contains(coordenadas)) {
			if(arrayListDasPosicoesDosBotoes.size() > 1) {
				indexDoArrayDeposicoes = r.nextInt(arrayListDasPosicoesDosBotoes.size()-1);
			}else {
				indexDoArrayDeposicoes = 0;
			}
			
			xDoArray = arrayListDasPosicoesDosBotoes.get(indexDoArrayDeposicoes).get(0); 
			yDoArray = arrayListDasPosicoesDosBotoes.get(indexDoArrayDeposicoes).get(1);
			
			ArrayList<Integer> coordenadasWhile = new ArrayList<>();
			coordenadasWhile.add(xDoArray); coordenadasWhile.add(yDoArray);
			if(!coordenadasJaUtilizadas.contains(coordenadasWhile)) {
				break;
			}else {
				arrayListDasPosicoesDosBotoes.remove(coordenadasWhile);
				apagador = false;
			}
		}
		
		if(ocorreuAcerto) {
				/* 1
				 *4  2
				 *  3*/
			ArrayList<String> direcoesPossiveis = new ArrayList<String>() {
				private static final long serialVersionUID = 1L;
				{add("CIMA"); add("DIREITA"); add("BAIXO"); add("ESQUERDA");}};
			ArrayList<Integer> coordenadasA;
			do {
				
				int valorRandom;
				
				if(direcoesPossiveis.size() > 1) {
					valorRandom = r.nextInt(direcoesPossiveis.size()-1);
				}else {
					valorRandom = 0;
				}
				
				if(contadorDeSequencia == 1) {
					xAntesDoUltimoAcerto = xDoUltimoAcerto;
					yAntesDoUltimoAcerto = yDoultimoAcerto;
				}
				
				if(contadorDeSequencia == 1) {
					String direcao = direcoesPossiveis.get(valorRandom);
					if(direcao.equals("CIMA")) {
						yDoArray = yDoultimoAcerto - 50;
						xDoArray = xDoUltimoAcerto;
					}else if(direcao.equals("DIREITA")) {
						xDoArray = xDoUltimoAcerto + 50;
						yDoArray = yDoultimoAcerto;
					}else if (direcao.equals("BAIXO")) {
						yDoArray = yDoultimoAcerto + 50;
						xDoArray = xDoUltimoAcerto;
					}else if(direcao.equals("ESQUERDA")){
						xDoArray = xDoUltimoAcerto - 50;
						yDoArray = yDoultimoAcerto;
					}
				}
				else if(contadorDeSequencia >= 2) {
					if(yAntesDoUltimoAcerto > yDoultimoAcerto) {
						yDoArray = yDoultimoAcerto - 50;
						xDoArray = xDoUltimoAcerto;
					}else if(xDoUltimoAcerto > xAntesDoUltimoAcerto) {
						xDoArray = xDoUltimoAcerto + 50;
						yDoArray = yDoultimoAcerto;
					}else if(yAntesDoUltimoAcerto < yDoultimoAcerto) {
						yDoArray = yDoultimoAcerto + 50;
						xDoArray = xDoUltimoAcerto;
					}else {
						xDoArray = xDoUltimoAcerto -50;
						yDoArray = yDoultimoAcerto;
					}
					
					ArrayList<Integer> coordenadasB = new ArrayList<>();
					coordenadasB.add(xDoArray); coordenadasB.add(yDoArray);
					
					if(xDoArray < 50 || yDoArray < 50 || xDoArray > 250 || yDoArray > 250 || deuErradoNaSequencia || coordenadasJaUtilizadas.contains(coordenadasB)) {
						deuErradoNaSequencia = false;
						if(yAntesDoUltimoAcerto > yDoultimoAcerto) {
							yDoArray = yDoultimoAcerto + 100;
							xDoArray = xDoUltimoAcerto;
						}else if(xDoUltimoAcerto > xAntesDoUltimoAcerto) {
							xDoArray = xDoUltimoAcerto - 100;
							yDoArray = yDoultimoAcerto;
						}else if(yAntesDoUltimoAcerto < yDoultimoAcerto) {
							yDoArray = yDoultimoAcerto - 100;
							xDoArray = xDoUltimoAcerto;
						}else {
							xDoArray = xDoUltimoAcerto + 100;
							yDoArray = yDoultimoAcerto;
						}
					}
					
				}
				direcoesPossiveis.remove(valorRandom);
				coordenadasA = new ArrayList<>();
				coordenadasA.add(xDoArray); coordenadasA.add(yDoArray);
				
			}while(xDoArray < 50 || xDoArray > 250 || yDoArray < 50 || yDoArray > 250 || coordenadasJaUtilizadas.contains(coordenadasA));
		}
		ArrayList<Integer> coordenadas1 = new ArrayList<>();
		coordenadas1.add(xDoArray); coordenadas1.add(yDoArray);
		if(!coordenadasJaUtilizadas.contains(coordenadas1)) {
			coordenadasJaUtilizadas.add(coordenadas1);
			
		}
		if(coordenadasJaUtilizadas.contains(coordenadas1)) {
			if(apagador) {
				arrayListDasPosicoesDosBotoes.remove(coordenadas1);
			}
			apagador = true;
		}
		
		for(ArrayList<Integer> listaDeDados : locaisDosBarcosDoPlayer) {
			//ex 150-50-150-50
			
        	int largura = listaDeDados.get(0);
        	int altura = listaDeDados.get(1);
        	int x = listaDeDados.get(2);
        	int y = listaDeDados.get(3);

        	int count = 0;
        	if(largura >= altura) {
        		//vai verificar se a coordenada clicada faz algum sentido, se não fizer ele vai pular o item do array para não perder tempo fazendo os calculos
        		if(!(xDoArray >= x && xDoArray <= x+largura-50 && yDoArray == y)) {
        			continue;
        		}
        		
        		int distancia = 0;
        		for(int i = 0 ; i < largura/50 ; i++) {
        			//recuperaria o botao
        			Component botaoRecuperadoComponent = frame.getContentPane().getComponentAt(x+distancia+450, y);
        			JButton botaoRecuperado = (JButton) (botaoRecuperadoComponent);
        			if(xDoArray == x+distancia && yDoArray == y ) {
        				////quando encontra algo
        				valor = 1;
        				contadorDeSequencia++;
        				ocorreuAcerto = true;
        				verificadorDeNavio = true;
        				
        				xDoUltimoAcerto = xDoArray;
        				yDoultimoAcerto = yDoArray;
        				botaoRecuperado.setEnabled(false);
        				botaoRecuperado.setVisible(true);
        				
        			}
        			if(!botaoRecuperado.isEnabled() && botaoRecuperado.getWidth() == 50 && botaoRecuperado.getHeight() == 50) {
        				count++;
        			}
    				if(count == largura/50) {
    					contadorDeSequencia = 0;
    					count = 0;
    					valor = 2;
    					ocorreuAcerto = false; // ja encontrou todo o barco então ja foi
    					break;
    					///encontrou o barco todo, joga em outro lugar depois disso
    				}
        			distancia += 50;
        		}
        	}else if(altura > largura) {
        		//vai verificar se a coordenada clicada faz algum sentido, se não fizer ele vai pular o item do array para não perder tempo fazendo os calculos
        		if(!(xDoArray == x && yDoArray >= y && yDoArray <= y+altura-50)) {
        			continue;
        		}
        		
        		int distancia = 0;
        		for(int i = 0 ; i < altura/50 ; i++) {
        			Component botaoRecuperadoComponent = frame.getContentPane().getComponentAt(x+450, y+distancia);
        			JButton botaoRecuperado = (JButton) (botaoRecuperadoComponent);
					
        			coordenadas.clear();
					coordenadas.add(xDoArray); coordenadas.add(yDoArray);
        			if(xDoArray == x && yDoArray == y+distancia) {
        				////quando encontra algo
        				valor = 1;
        				contadorDeSequencia++;
        				ocorreuAcerto = true;
        				verificadorDeNavio = true;
        				
        				xDoUltimoAcerto = xDoArray;
        				yDoultimoAcerto = yDoArray;
        				botaoRecuperado.setEnabled(false);
        				botaoRecuperado.setVisible(true);
    					
        			}
        			if(!botaoRecuperado.isEnabled() && botaoRecuperado.getWidth() == 50 && botaoRecuperado.getHeight() == 50) {
        				count++;
        			}

    				if(count == altura/50) {
    					///encontrou o barco todo, joga em outro lugar depois disso
    					contadorDeSequencia = 0;
    					count = 0;
    					valor = 2;
    					ocorreuAcerto = false;
    					break;
    				}
        			distancia += 50;
        		}
		
        	}
        	//remove o barco ja achado da lista de todos os barcos e para o for ja que foi encontrado o que precisava
        	if(valor == 2) {
        		//parte da inteligencia para evitar que a ia joga ao lado de barcos ja encontrado, isso não faria sentido ja que não é permitido pocisionar barcos a 1 espaço de distancia
        		if(largura >= altura) {
        			for(int distanciaH = 0 ; distanciaH < largura ; distanciaH+= 50) {
        				ArrayList<Integer> coordenada = new ArrayList<>();
        				coordenada.add(x+distanciaH); coordenada.add(y-50);
        				coordenadasJaUtilizadas.add(coordenada);
        				
        				ArrayList<Integer> coordenada1 = new ArrayList<>();
        				coordenada1.add(x+distanciaH); coordenada1.add(y+50);
        				coordenadasJaUtilizadas.add(coordenada1);
        			}
        			
        			ArrayList<Integer> coordenada = new ArrayList<>();
    				coordenada.add(x-50); coordenada.add(y);
    				coordenadasJaUtilizadas.add(coordenada);
    				
    				ArrayList<Integer> coordenada1 = new ArrayList<>();
    				coordenada1.add(x+largura); coordenada1.add(y);
    				coordenadasJaUtilizadas.add(coordenada1);
        		}else {
        			for(int distanciaV = 0 ; distanciaV < altura ; distanciaV+= 50) {
        				ArrayList<Integer> coordenada = new ArrayList<>();
        				coordenada.add(x-50); coordenada.add(y+distanciaV);
        				coordenadasJaUtilizadas.add(coordenada);
        				
        				ArrayList<Integer> coordenada1 = new ArrayList<>();
        				coordenada1.add(x+50); coordenada1.add(y+distanciaV);
        				coordenadasJaUtilizadas.add(coordenada1);
        			}
        			ArrayList<Integer> coordenada = new ArrayList<>();
    				coordenada.add(x); coordenada.add(y-50);
    				coordenadasJaUtilizadas.add(coordenada);
    				
    				ArrayList<Integer> coordenada1 = new ArrayList<>();
    				coordenada1.add(x); coordenada1.add(y+altura);
    				coordenadasJaUtilizadas.add(coordenada1);
        		}
        		
        		//remove o navio da lista dos navios ja que ele foi encontrado
        		ArrayList<Integer> navioParaRemover = new ArrayList<>();
        		navioParaRemover.add(largura); navioParaRemover.add(altura); navioParaRemover.add(x); navioParaRemover.add(y);
        		locaisDosBarcosDoPlayer.remove(navioParaRemover);
        		break;
        	}
		}
		if(verificadorDeNavio == false) {
			valor = 0;
			coordenadas.clear();
			if(contadorDeSequencia == 2) {
				deuErradoNaSequencia = true;
			}
			
		}else {
			verificadorDeNavio = false;
		}
		xDoArray += 450;
    	dados.add(valor); dados.add(xDoArray); dados.add(yDoArray);
		//envia 0 ou 1 e la fora faz a verificação se ja foi todos os barcos. se acertar um ele coloca aqui dentro um aviso q q na proxima joga nas proximidades
		return dados;
	}
	
	public static void remover(ArrayList<Integer> oQueVaiSerRemovido, ArrayList<ArrayList<Integer>> local) {
		local.remove(oQueVaiSerRemovido);
	}
}


