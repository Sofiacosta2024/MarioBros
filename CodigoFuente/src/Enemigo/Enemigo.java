package Enemigo;

import Mario.*;
import Sonido.Sonido;
import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorEnemigo;
import Logica.*;

public abstract class Enemigo extends Entidad{

	protected boolean enEnfriamiento = false;
	protected long tiempoUltimaColision;
	protected long TIEMPO_ENFRIAMIENTO = 90;
	protected final int posSuelo = 60;
	protected boolean sobrePlataforma,sobreSuelo;
	protected int puntaje, puntajeMatarAMario;
	protected float gravedad;
	protected boolean muerto;

	public Enemigo(int x, int y, int velocidad, int anchoHitBox, int altoHitBox) {
		super(x, y, velocidad, anchoHitBox,altoHitBox);
		direccionMovimiento = IZQUIERDA;
		sobrePlataforma = false;
		if(posicionY > posSuelo) {
			sobreSuelo = false;
		   }
		else {
			   sobreSuelo = true;
			 }
		gravedad = 1.0f;
		muerto = false;
	}

	public abstract void atacar(Mario m);
	
	public void morir(Mario mario) {
		if(!muerto) {
			Sonido.obtenerInstancia().reproducirSonidoMuereEnemigo();;
			retirar();
			mario.setPuntaje(mario.getPuntaje() + this.getPuntaje());
		}
	}

	public void mover() {
		if(!muerto) {
			if(!sobreSuelo && !sobrePlataforma) {
				caer();
			}
			
			if(direccionMovimiento == IZQUIERDA) {
				posicionX = posicionX - velocidad;
			}
			else
				if(direccionMovimiento == DERECHA) {
					posicionX = posicionX + velocidad;
				}
			
			if(posicionX <= -65) {
				retirar();
			}
			
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		}
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
		if(!muerto) {
			visitor.visitarPlataformaOEnemigo(this);
		}
	}

	public void aceptarVisita(ColisionadorVisitorBolaFuego visitor) {
		if(!muerto) {
			visitor.visitarEnemigo(this);
		}
	}

	public void retirar() {
			muerto = true;
			observador.actualizarPorMuerte();
	}
	
	public void notificarObservador() {
		if(!muerto) {
			observador.actualizar();
		}
	}

	public void setEstadoInicial() {
		super.setEstadoInicial();
		sobrePlataforma = false;
		if(posicionY > posSuelo) {
			sobreSuelo = false;
		}
		else {
			  sobreSuelo = true;
		     }
		muerto = false;
		notificarObservador();
	}

	public void setMovimiento(char movimiento) {
		direccionMovimiento = movimiento;
	}
	
	public void setVivo(boolean vivo) {
		muerto = !vivo;
	}

	public void detenerOActivarCaida(boolean enPlataforma) {
		sobrePlataforma = enPlataforma;
	}

	public char getMovimiento() {
		return direccionMovimiento;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public boolean getCaida() {
		return sobrePlataforma;
	}
	
	public boolean colisionable() {
		return !muerto;
	}

	public int puntajeMatarAMario() {
		return puntajeMatarAMario;
	}
}