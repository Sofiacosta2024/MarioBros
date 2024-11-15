package Enemigo;
import java.util.ArrayList;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.*;
import Mario.Mario;
import Vista.Observer;
import Vista.ObserverGrafica;
import Sonido.*;
public class Lakitu extends Enemigo {

	protected Juego juego;
	protected Sprite spinySprite;
	protected ArrayList<Spiny> spinyssLanzados;
	private int tiempoLanzar; 
	private int tiempoActual; 

	public Lakitu(int x, int y, int velocidad, Juego juego, Sprite[] spriteSpiny) {
		super(x,y, velocidad,16,24);
		puntaje = 60;
		puntajeMatarAMario = 0;
		this.juego = juego;
		spinyssLanzados = new  ArrayList<Spiny>();
		spinySprite = spriteSpiny[0];
		this.tiempoActual = 0;
		this.tiempoLanzar = 200;
	}

	public void caer() {
		//No cae
	}

	public void atacar(Mario mario) {
		mario.decrementarVida();
	}

	 public void mover() {
	        super.mover();
	        tiempoActual +=1;
	        if (tiempoActual >= tiempoLanzar) {
	            lanzarSpiny();
	            tiempoActual = 0; 
	        }
	    }

	    private void lanzarSpiny() {
	        Spiny spiny = new Spiny(this.getPosicionX(), this.getPosicionY(), velocidad);
	        spiny.setSprite(spinySprite);
	        spiny.setLakituLanzador(this);
	        Observer observadorEntidad = juego.getControladorVista().registrarEntidadLogicaSpinyLanzado(spiny);
	        spiny.setObserver(observadorEntidad);
	        juego.getNivelActual().agregarSpinyLanzado(spiny);
	        spinyssLanzados.add(spiny); 
	        spiny.notificarObservador();
	        
	        Sonido.obtenerInstancia().reproducirSonidoLakituLanzaSpinyss();
	    }

	    public void aceptarVisita(ColisionadorVisitorMario visitor) {
	        visitor.visitarLakitu(this);
	    }

	    public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
	        visitor.visitarLakitu(this);
	    }

	    public ArrayList<Spiny> getSpinyssLanzados() {
	        return spinyssLanzados;
	    }
}
