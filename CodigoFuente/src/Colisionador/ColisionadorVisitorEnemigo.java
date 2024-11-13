package Colisionador;

import java.awt.Rectangle;

import Enemigo.*;
import Logica.Entidad;
import Logica.Juego;
import Plataforma.*;

public class ColisionadorVisitorEnemigo {
	
	protected Enemigo entidadColisionable;
	protected Juego juego;

	public ColisionadorVisitorEnemigo(Enemigo enemigo, Juego juego) {
		entidadColisionable = enemigo;
		this.juego = juego;
	}

	public void visitarVacio(Vacio vacio) {
		entidadColisionable.retirar();
		entidadColisionable.getObserver().actualizar();

	}

	public void visitarPiranhaPlant(PiranhaPlant piranhaPlant) {

	}

	public void visitarSpiny(Spiny spiny) {
		if(spiny.getLakituLanzador() != entidadColisionable && entidadColisionable != spiny) {
			if(!entidadColisionable.estaEnEnfriamiento()) {
				if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
					entidadColisionable.mover(5, 0);
					entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
				}
				else {
					entidadColisionable.mover(-5, 0);
					entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
				}
				if(spiny.getMovimiento() == entidadColisionable.IZQUIERDA) {
					spiny.mover(5, 0);
					spiny.setMovimiento(entidadColisionable.DERECHA);
				}
				else {
					spiny.mover(-5, 0);
					spiny.setMovimiento(entidadColisionable.IZQUIERDA);
				}
				entidadColisionable.getObserver().actualizarPosicionTamano();
				entidadColisionable.activarEnfriamiento();
				spiny.getObserver().actualizarPosicionTamano();
				spiny.activarEnfriamiento();
			}
		}
	}

	public void visitarLakitu(Lakitu lakitu) {
		for(Spiny spiny : lakitu.getSpinyssLanzados()) {
			if(entidadColisionable != spiny && lakitu != entidadColisionable) {
				if(!entidadColisionable.estaEnEnfriamiento()) {
					if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
						entidadColisionable.mover(5, 0);
						entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
					}
					else {
						entidadColisionable.mover(-5, 0);
						entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
					}
					entidadColisionable.getObserver().actualizarPosicionTamano();
					entidadColisionable.activarEnfriamiento();
				}
			}
		}
	}

	public void visitarTuberiaPiranha(TuberiaConPiranha  tuberiaConPiranha) {
		if(entidadColisionable!= tuberiaConPiranha.getPiranha()) {
			if(!entidadColisionable.estaEnEnfriamiento()) {
				if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
					entidadColisionable.mover(5, 0);
					entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
				}
				else {
					entidadColisionable.mover(-5, 0);
					entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
				}
				entidadColisionable.getObserver().actualizarPosicionTamano();
				entidadColisionable.activarEnfriamiento();
			}

		}
	}

	public void visitarKoopaTroopa(KoopaTroopa koopaTroopa) {
		if(!entidadColisionable.estaEnEnfriamiento()) {
			koopaTroopa.getEstado().interactuarEnemigos(entidadColisionable);
			entidadColisionable.getObserver().actualizarPosicionTamano();
			entidadColisionable.activarEnfriamiento();
		}
	}

	public void visitarPlataformaOEnemigo(Entidad entidad) {
	    String direccion = detectarDireccion(entidad);
	    if(!entidadColisionable.estaEnEnfriamiento()) {
	        if(direccion.equals("ARRIBA")) {
	            entidadColisionable.detenerOActivarCaida(true);
	            if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
	                entidadColisionable.mover(-2, 0);
	            } else if(entidadColisionable.getMovimiento() == entidadColisionable.DERECHA) {
	                entidadColisionable.mover(2, 0);
	            }
	        }
	        else {
	        	if(entidadColisionable.getMovimiento() == entidadColisionable.DERECHA) {
	                entidadColisionable.mover(-5, 0); 
	                entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
	            } else {
	                entidadColisionable.mover(5, 0); 
	                entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
	            }
	        }
	        entidadColisionable.activarEnfriamiento();
	        entidadColisionable.getObserver().actualizarPosicionTamano();
	    }
	}

	private String detectarDireccion(Entidad entidad) {
		String direccion = "NINGUNA";
		Rectangle hitboxColisionador = entidadColisionable.getAreaColision();
		Rectangle hitboxEntidad = entidad.getAreaColision();
		int dx = hitboxColisionador.x - hitboxEntidad.x;
		int dy = hitboxColisionador.y - hitboxEntidad.y;
		if (Math.abs(dy) > Math.abs(dx)) 
			if (dy > 0) 
				direccion = "ARRIBA"; 
			else 
				direccion = "ABAJO"; 
		else 
			if (dx > 0) 
				direccion = "DERECHA"; 
			else 
				direccion = "IZQUIERDA";
		return direccion; 
	}

}
