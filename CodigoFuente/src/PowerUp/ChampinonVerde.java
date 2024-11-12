package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;

public class ChampinonVerde extends PowerUp{
	
	public ChampinonVerde(int x, int y, int velocidad) {
		super(x,y, velocidad,16,16);
		puntaje = 100;
	}
	
	public void afectar(Mario mario) {
		mario.aumentarVida();
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarChampinonVerde(this);
	}
}
