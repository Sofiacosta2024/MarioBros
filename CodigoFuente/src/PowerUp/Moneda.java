package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;

public class Moneda extends PowerUp {
	
	public Moneda(int x, int y, int velocidad, Sprite sprite) {
		super(x,y, velocidad, sprite,10,14);
		puntaje = 5;
	}
	
	public void afectar(Mario mario) {
		mario.aumentarMonedas();	
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarMoneda(this);
	}
}
