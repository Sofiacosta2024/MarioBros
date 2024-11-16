package Enemigo;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;

public class Spiny extends Enemigo{
	private Lakitu lakituLanzador;

	public Spiny(int x, int y, int velocidad) {
		super(x,y, velocidad,16,24);
		puntaje = 60;
		puntajeMatarAMario = -30;
	}

	public void mover() {
		if(!muerto && !enEnfriamiento) {
			if(!sobreSuelo && !sobrePlataforma) {
				caer();
			}
			if(direccionMovimiento == IZQUIERDA) {
				posicionX = posicionX - 2*velocidad;
			}
			else
				if(direccionMovimiento == DERECHA) {
					posicionX = posicionX + 2*velocidad;
				}
			if(posicionX <= -65) {
				retirar();
			}
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		}
	}

	public void atacar(Mario mario) {
		if(!muerto)
			mario.decrementarVida();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(!muerto)
			visitor.visitarSpiny(this);
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		if(!muerto)
			visitor.visitarSpiny(this);
	}

	public void setLakituLanzador(Lakitu lakitu) {
		lakituLanzador = lakitu;
	}

	public Lakitu getLakituLanzador() {
		return lakituLanzador;
	}
}
