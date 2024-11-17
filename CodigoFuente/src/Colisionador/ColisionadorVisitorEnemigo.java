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
				if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
					entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
					entidadColisionable.mover();
				}
				else {
					   entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
					   entidadColisionable.mover();
				}
				if(spiny.getMovimiento() == entidadColisionable.IZQUIERDA) {
					spiny.setMovimiento(entidadColisionable.DERECHA);
					spiny.mover();
				}
				else {
					  spiny.setMovimiento(entidadColisionable.IZQUIERDA);
					  spiny.mover();
				}
				entidadColisionable.getObserver().actualizarPosicionTamano();
				entidadColisionable.activarEnfriamiento();
				spiny.getObserver().actualizarPosicionTamano();
				spiny.activarEnfriamiento();
		}
	}

	public void visitarLakitu(Lakitu lakitu) {
		for(Spiny spiny : lakitu.getSpinyssLanzados()) {
			if(entidadColisionable != spiny && lakitu != entidadColisionable) {
					if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
						entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
						entidadColisionable.mover();
					}
					else {
						  entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
						  entidadColisionable.mover();
					}
					entidadColisionable.getObserver().actualizarPosicionTamano();
					entidadColisionable.activarEnfriamiento();
			}
		}
	}

	public void visitarTuberiaPiranha(TuberiaConPiranha  tuberiaConPiranha) {
		if(entidadColisionable!= tuberiaConPiranha.getPiranha()) {
				if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
					entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
					entidadColisionable.mover();
				}
				else {
					   entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
					   entidadColisionable.mover();
				}
				entidadColisionable.getObserver().actualizarPosicionTamano();
				entidadColisionable.activarEnfriamiento();
		}
	}

	public void visitarKoopaTroopa(KoopaTroopa koopaTroopa) {
			koopaTroopa.getEstado().interactuarEnemigos(entidadColisionable);
			entidadColisionable.getObserver().actualizarPosicionTamano();
			entidadColisionable.activarEnfriamiento();
	}

	public void visitarPlataformaOEnemigo(Entidad entidad) {
	    String direccion = detectarDireccion(entidad);
	        if(direccion.equals("ARRIBA")) {
	            entidadColisionable.detenerOActivarCaida(true);
	            if(entidadColisionable.getMovimiento() == entidadColisionable.IZQUIERDA) {
	                entidadColisionable.mover();
	            } else if(entidadColisionable.getMovimiento() == entidadColisionable.DERECHA) {
	                entidadColisionable.mover();
	            }
	        }
	        else {
	        	if(entidadColisionable.getMovimiento() == entidadColisionable.DERECHA) {
	        		entidadColisionable.setMovimiento(entidadColisionable.IZQUIERDA);
	        		entidadColisionable.mover(); 	  
	            } else {
	            	    entidadColisionable.setMovimiento(entidadColisionable.DERECHA);
	            	    entidadColisionable.mover(); 	                
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
