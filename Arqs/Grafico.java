package Arqs;

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.Rotation;

public class Grafico{
	public static JPanel pizza3DStatic(ArrayList<String> nome, ArrayList<String> valor, String tituloGrafico){
		return pizza3D(nome, valor, tituloGrafico, 0.5f, "Static");
	}
	
	@SuppressWarnings("deprecation")
	public static JPanel pizza3D(ArrayList<String> nome, ArrayList<String> valor, String tituloGrafico, float transparencia, String tipo){
		DefaultPieDataset data = new DefaultPieDataset();

		for(int i = 0; i < nome.toArray().length; i++){
		data.setValue("" + nome.get(i).toString(),
		new Double(valor.get(i).toString()));
		}

		JFreeChart chart = ChartFactory.createPieChart3D (tituloGrafico, data, true, true, true);

		chart.setBackgroundPaint(Color.gray);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setLabelLinksVisible(true);
		plot.setNoDataMessage("Não existem dados para serem exibidos no gráfico");

		plot.setStartAngle(90);
		plot.setDirection(Rotation.CLOCKWISE);

		plot.setForegroundAlpha(transparencia);
		plot.setInteriorGap(0.20);

		ChartPanel chartPanel = new ChartPanel(chart);

		return chartPanel;
	}
	
	public XYDataset createDataset(ArrayList<ArrayList<Integer>> listaDeValores) {
	    
	    final XYSeries series1 = new XYSeries("Pontos");
	    if(listaDeValores != null) {
	    	for(ArrayList<Integer> valores : listaDeValores) {
	    		series1.add(valores.get(0), valores.get(1));
	    	}
	    	
	    }

	    final XYSeriesCollection dataset = new XYSeriesCollection();
	    dataset.addSeries(series1);
	            
	    return dataset;
	    
	}
	
	public JFreeChart createChart(final XYDataset dataset) {
	    
	    final JFreeChart chart = ChartFactory.createXYLineChart(
	        "Histórico de pontos",      // chart title
	        "Partidas",                      // x axis label
	        "Pontos",                      // y axis label
	        dataset,                  // data
	        PlotOrientation.VERTICAL,
	        true,                     // include legend
	        true,                     // tooltips
	        false                     // urls
	    );
	    chart.setBackgroundPaint(Color.white);
	    final XYPlot plot = chart.getXYPlot();
	    plot.setBackgroundPaint(Color.lightGray);
	    plot.setDomainGridlinePaint(Color.white);
	    plot.setRangeGridlinePaint(Color.white);
	    
	    final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
	    renderer.setSeriesLinesVisible(0, false);
	    renderer.setSeriesShapesVisible(1, false);
	    plot.setRenderer(renderer);

	    final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	            
	    return chart;
	}

}
