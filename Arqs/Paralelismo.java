package Arqs;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import Listeners.TabuleiroListener;

public class Paralelismo extends SwingWorker<Object, Object>{
	JFrame frame;
	
	public Paralelismo(JFrame frame) {
		this.frame = frame;
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		TabuleiroListener.AcoesTabulieroInimigo();
		frame.setEnabled(true);
		return null;
	}

}
