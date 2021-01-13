package Listeners;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class DragListener extends MouseAdapter { 
	private final Component COMPONENT_TO_DRAG;
	private final int MOUSE_BUTTON;
	private Point mousePosition; 
	private Point sourceLocation; 
	private Point locationOnScreen; 
	private int buttonPressed; 
	private Point localizacaoInicial;
	private ArrayList<JButton> listaDosBotoesPosicionado;
	
	public DragListener(final Component componentToDrag, final ArrayList<JButton> listaDosBotoesPosicionado) {
		this(componentToDrag, MouseEvent.BUTTON1);
		localizacaoInicial = componentToDrag.getLocation();
		this.listaDosBotoesPosicionado = listaDosBotoesPosicionado;
	} 
	
	public DragListener(final Component componentToDrag, final int mouseButton) {
		this.COMPONENT_TO_DRAG = componentToDrag; 
		this.MOUSE_BUTTON = mouseButton;
	}
	public void mousePressed(final MouseEvent e) {
		this.buttonPressed = e.getButton(); 
		this.mousePosition = MouseInfo.getPointerInfo().getLocation();
		this.sourceLocation = new Point();
	}
	
	public void mouseReleased(MouseEvent e) {
		this.sourceLocation = ReguladorDoSourceLocation(this.sourceLocation);
		this.COMPONENT_TO_DRAG.setLocation(this.sourceLocation);
		//colocar aki
		if(!listaDosBotoesPosicionado.contains((JButton)(this.COMPONENT_TO_DRAG))) {
			listaDosBotoesPosicionado.add((JButton)(this.COMPONENT_TO_DRAG));
		}
	}
	
	public void mouseDragged(final MouseEvent e) { 
		if (this.buttonPressed == MOUSE_BUTTON) { 
		this.locationOnScreen = e.getLocationOnScreen();
		this.sourceLocation = this.COMPONENT_TO_DRAG.getLocation(this.sourceLocation);
		this.sourceLocation.translate((int) (this.locationOnScreen.getX() - this.mousePosition.getX()), (int) (this.locationOnScreen.getY() - this.mousePosition.getY()));
		this.mousePosition = MouseInfo.getPointerInfo().getLocation(); 
		this.COMPONENT_TO_DRAG.setLocation(this.sourceLocation); 
		}
	}
	
	public Point ReguladorDoSourceLocation(Point sourceLocation) {
		double doubleX = sourceLocation.getX();
		double doubleY = sourceLocation.getY();
		
		int largura = COMPONENT_TO_DRAG.getWidth();
		int altura = COMPONENT_TO_DRAG.getHeight();
		
		double X = (Math.floor(doubleX/50))*50;
		double Y = (Math.floor(doubleY/50))*50;
		
		if(doubleX > 0 && X < 50) X = 50;
		if(doubleY > 0 && Y < 50) Y = 50;
		if(X + largura == 350 || X + largura == 400 && X < 300) X = 300-largura;
		if(Y + altura == 350 || Y + altura == 400 && Y < 300) Y = 300-altura;
		
		if(largura+X > 300 || altura+Y > 300) {
			X = localizacaoInicial.getX();
			Y = localizacaoInicial.getY();
		}
		
		if(X < 50 || X> 300 || Y <50 || Y >300) {
			X = localizacaoInicial.getX();
			Y = localizacaoInicial.getY();
		}
		
		///evitar que navios sejam colocados nas proximidades de 1
		for(JButton navio : listaDosBotoesPosicionado) {
			if(navio != null) {
				int xDoNavio = navio.getX();
				int yDoNavio = navio.getY();
				int larguraDoNavio = navio.getWidth();
				int alturaDoNavio = navio.getHeight();
				
				if(alturaDoNavio > larguraDoNavio) {
					for(int distanciaHorinzontal = -50 ; distanciaHorinzontal < larguraDoNavio+50 ; distanciaHorinzontal += 50) {
						for(int distanciaVertical = 0; distanciaVertical < alturaDoNavio ; distanciaVertical += 50) {
							if(X == xDoNavio+distanciaHorinzontal && Y == yDoNavio+distanciaVertical || X+largura == xDoNavio+distanciaHorinzontal+50 && Y+altura-50 == yDoNavio+distanciaVertical) {
								X = localizacaoInicial.getX();
								Y = localizacaoInicial.getY();
							}
							for(int count = 0; count < largura ; count += 50) {
								if(X == xDoNavio-count && Y == yDoNavio-50 || X == xDoNavio-count && Y == yDoNavio+alturaDoNavio) {
									X = localizacaoInicial.getX();
									Y = localizacaoInicial.getY();
								}else if(X == xDoNavio && Y+altura-50 == yDoNavio-50) {
									X = localizacaoInicial.getX();
									Y = localizacaoInicial.getY();
								}
								
							}
						}
					}
				}else {
					for(int distanciaVertical = -50 ; distanciaVertical < alturaDoNavio+50 ; distanciaVertical += 50) {
						for(int distanciaHorinzontal = 0; distanciaHorinzontal < larguraDoNavio ; distanciaHorinzontal += 50) {
							if(X == xDoNavio+distanciaHorinzontal && Y == yDoNavio+distanciaVertical || X == xDoNavio+distanciaHorinzontal && Y+altura == yDoNavio+distanciaVertical+50) {
								X = localizacaoInicial.getX();
								Y = localizacaoInicial.getY();
							}
							for(int count = 0; count < altura ; count += 50) {
								if(X == xDoNavio-50 && Y == yDoNavio-count || X == xDoNavio+larguraDoNavio && Y == yDoNavio-count) {
									X = localizacaoInicial.getX();
									Y = localizacaoInicial.getY();
								}else if(X+largura-50 == xDoNavio-50 && Y == yDoNavio){
									X = localizacaoInicial.getX();
									Y = localizacaoInicial.getY();
								}
								
							}
						}
					}
				}
			}
		}
		
		sourceLocation.setLocation(X, Y);
		return sourceLocation;
		
	}
	
	public void addHandle(Component handle) { 
		handle.addMouseListener(this);
		handle.addMouseMotionListener(this);
	} 
}
