package Enemigo;
import Mario.*;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.*;
public class BuzzyBeetle extends Enemigo{
	public BuzzyBeetle(int x, int y, int velocidad, Sprite sprite){
		super(x, y, velocidad, sprite,32,32);
		puntaje = 30;
		puntajeMatarAMario = -15;
	}
	public void atacar(Mario mario) {
		mario.decrementarVida();
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarBuzzy(this);
	}
	
	public int getPosicionX() {
		return posicionX;
	}
	
	public int getPosicionY() {
		return posicionY;
	}

}
