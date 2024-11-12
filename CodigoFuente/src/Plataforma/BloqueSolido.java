package Plataforma;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;

public class BloqueSolido extends Plataforma {

	public BloqueSolido(int x, int y, int velocidad, Sprite sprite) {
		super(x,y, velocidad, sprite,32,32);
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarBloqueSolido(this);		
	}
	
	public void retirar() {
		
	}
}
