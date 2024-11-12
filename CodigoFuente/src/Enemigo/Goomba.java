package Enemigo;
import Mario.*;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
public class Goomba extends Enemigo{
	
	public Goomba(int x, int y, int velocidad, Sprite sprite) {
		super(x,y, velocidad, sprite,32,32);
		puntaje = 60;
		puntajeMatarAMario = -30;
	}
	
	public void atacar(Mario mario) {
		mario.decrementarVida();
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarGoomba(this);
	}
	
}