package Enemigo;

import Mario.*;
import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorEnemigo;
import Fabrica.*;
import Logica.*;
import Sonido.*;

public abstract class Enemigo extends Entidad{
	
	protected String movimiento;
	protected boolean enEnfriamiento = false;
	protected long tiempoUltimaColision;
	protected long TIEMPO_ENFRIAMIENTO = 90;
	protected final int posSuelo = 60;
	protected boolean sobrePlataforma,sobreSuelo;
	protected int puntaje, puntajeMatarAMario, posicionXInicial, posicionYInicial;
	protected float gravedad;
	protected Sonido player;
	
	public Enemigo(int x, int y, int velocidad, Sprite sprite, int anchoHitBox, int altoHitBox) {
		super(x, y, velocidad, sprite,anchoHitBox,altoHitBox);
		movimiento = "IZQUIERDA";
		posicionXInicial = x;
		posicionYInicial = y;
		sobrePlataforma = false;
		if(posicionY > posSuelo)
			sobreSuelo = false;
		else
			sobreSuelo = true;
		gravedad = 1.0f;
		player=new Sonido();
	}

	public abstract void atacar(Mario m);

	public void mover(int nuevaX, int nuevaY) {
		if(!sobreSuelo && !sobrePlataforma) {
			caer();
		}
		posicionX = posicionX + nuevaX;
		if(posicionX <= -65)
			retirar();
		actualizarAreaColision();
		observador.actualizarPosicionTamano();
	}

	public void caer() {
		gravedad += 0.2;
		posicionY -= gravedad;
		if (posicionY <= posSuelo) {
			posicionY = posSuelo;
			sobreSuelo = true;
		}
	}

	public void activarEnfriamiento() {
		enEnfriamiento = true;
		tiempoUltimaColision = System.currentTimeMillis();
	}

	public boolean estaEnEnfriamiento() {
		long tiempoActual = System.currentTimeMillis();
		if (tiempoActual - tiempoUltimaColision > TIEMPO_ENFRIAMIENTO) {
			enEnfriamiento = false;
		}
		return enEnfriamiento;
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarPlataformaOEnemigo(this);
	}

	public void aceptarVisita(ColisionadorVisitorBolaFuego visitor) {
		visitor.visitarEnemigo(this);
	}

	public void retirar() {
		esColisionable = false;
		notificarObservador();
	}

	public void setPosicionInicial() {
		posicionX = posicionXInicial;
		posicionY = posicionYInicial;
		sobrePlataforma = false;
		if(posicionY > posSuelo)
			sobreSuelo = false;
		else
			sobreSuelo = true;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public void detenerOActivarCaida(boolean enPlataforma) {
		sobrePlataforma = enPlataforma;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public boolean getCaida() {
		return sobrePlataforma;
	}

	public int puntajeMatarAMario() {
		return puntajeMatarAMario;
	}
}