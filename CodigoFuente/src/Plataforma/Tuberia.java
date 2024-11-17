package Plataforma;

import java.awt.Rectangle;
import Colisionador.ColisionadorVisitorMario;


public class Tuberia extends Plataforma {
	
	public Tuberia(int x, int y, int velocidad) {
		super(x,y, velocidad, 24,70);
		areaColision = new Rectangle(getPosicionX(),getPosicionY(), 24, 70);
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarTuberia(this);	
	}

	public void retirar() {
		
	}
	
}
