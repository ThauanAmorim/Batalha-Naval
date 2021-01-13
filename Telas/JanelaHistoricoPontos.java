package Telas;

import javax.swing.JButton;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;

import Arqs.DAO;
import Arqs.Grafico;
import Arqs.Jogador;
import Listeners.HistoricoPontosListener;

public class JanelaHistoricoPontos extends TelaDefault{
	private JFrame frame;
	private JButton voltar;
	private Jogador player;
	
	public JanelaHistoricoPontos(Jogador player) {
		this.player = player;
		frame = configTela(1000, 600, "Historico de pontos");
		
		Grafico grafico = new Grafico();
		XYDataset dataset = grafico.createDataset(DAO.LerHistoricoDePontos(player.getUsuario()).getDados());
		JFreeChart chart = grafico.createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(0, 0, 980, 500);
		frame.add(chartPanel);
	    
	    //JButtons
	    voltar = adicionarJButton("Voltar", 750, 510, 150, 40);
	    frame.add(voltar);
	    
	    //Listener
	    new HistoricoPontosListener(this);
	    
	    frame.repaint();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	public JButton getVoltar() {
		return voltar;
	}
	public Jogador getPlayer() {
		return player;
	}
}
