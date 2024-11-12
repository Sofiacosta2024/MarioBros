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
	
	public void atacar(Mario mario) {
		mario.decrementarVida();
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarSpiny(this);
	}
	
	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarSpiny(this);
	}
	
	public void setLakituLanzador(Lakitu lakitu) {
		lakituLanzador = lakitu;
	}
	
	public Lakitu getLakituLanzador() {
		return lakituLanzador;
	}
}
