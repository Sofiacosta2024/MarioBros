package Enemigo;

import Mario.*;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.*;

public class BuzzyBeetle extends Enemigo{
	
	public BuzzyBeetle(int x, int y, int velocidad){
		super(x, y, velocidad,32,32);
		puntaje = 30;
		puntajeMatarAMario = -15;
	}
	
	public void atacar(Mario mario) {
		if(mario.colisionable())
			mario.decrementarVida();
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(!muerto)
			visitor.visitarBuzzy(this);
	}
	
	public int getPosicionX() {
		return posicionX;
	}
	
	public int getPosicionY() {
		return posicionY;
	}

}
