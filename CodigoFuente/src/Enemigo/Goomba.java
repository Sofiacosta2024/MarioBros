package Enemigo;

import Mario.*;
import Colisionador.ColisionadorVisitorMario;

public class Goomba extends Enemigo{
	
	public Goomba(int x, int y, int velocidad) {
		super(x,y, velocidad,32,32);
		puntaje = 60;
		puntajeMatarAMario = -30;
	}
	
	public void atacar(Mario mario) {
		if(!muerto) {
			mario.decrementarVida();
		}
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(!muerto) {
			visitor.visitarGoomba(this);
		}
	}
}