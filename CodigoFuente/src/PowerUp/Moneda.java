package PowerUp;

import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;
import Sonido.Sonido;

public class Moneda extends PowerUp {
	
	public Moneda(int x, int y, int velocidad) {
		super(x,y, velocidad,20,28);
		puntaje = 5;
	}
	
	public void afectar(Mario mario) {
		if(esColisionable) {
			Sonido.obtenerInstancia().reproducirSonidoMoneda();
			mario.aumentarMonedas();
			mario.setPuntaje(mario.getPuntaje() + puntaje);
			retirar();
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarMoneda(this);
	}
}
