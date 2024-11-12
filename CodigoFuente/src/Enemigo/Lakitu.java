package Enemigo;
import java.util.ArrayList;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Logica.*;
import Mario.Mario;
import Vista.Observer;

public class Lakitu extends Enemigo implements Runnable{

	protected Juego juego;
	protected Sprite spinySprite;
	protected ArrayList<Spiny> spinyssLanzados;

	public Lakitu(int x, int y, int velocidad, Sprite sprite,Juego juego) {
		super(x,y, velocidad, sprite,16,24);
		puntaje = 60;
		puntajeMatarAMario = 0;
		this.juego = juego;
		spinyssLanzados = new  ArrayList<Spiny>();
		new Thread(this).start();
	}
	
	public void caer() {
		//No cae
	}

	public void atacar(Mario mario) {
		mario.decrementarVida();
	}

	public void lanzarSpinyss(int x, int y) {
		spinySprite = new Sprite(sprite.getModo()+"/Spiny.gif",sprite.getModo());
		Spiny spiny= new Spiny(x,y, velocidad, spinySprite);
		spiny.setLakituLanzador(this);
		Observer observadorEntidad =  juego.getControladorVista().registrarEntidadLogicaSpinyLanzado(spiny);
		spiny.setObserver(observadorEntidad);
		juego.getNivelActual().agregarSpinyLanzado(spiny);
		spinyssLanzados.add(spiny);
		spiny.notificarObservador();
		player.playClip("/Sonido/Sounds/lanzaspiny.wav");
	}

	public void run() {
		while (esColisionable) {
			try {
				Thread.sleep(3000);
				lanzarSpinyss(posicionX, posicionY); 
				Thread.sleep(8000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
			}
		}

	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarLakitu(this);

	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarLakitu(this);
	}

	public ArrayList<Spiny> getSpinyssLanzados(){
		return spinyssLanzados;
	}

}
