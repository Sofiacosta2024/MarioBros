package Colisionador;

import Enemigo.*;
import Logica.*;
import Mario.*;
import Plataforma.*;
import PowerUp.*;
import Sonido.*;

public class ColisionadorVisitorMario{

	protected Mario entidadColisionable;
	protected String direccionColision;
	protected Juego juego;
	protected Sonido player;

	public ColisionadorVisitorMario(Mario entidadColisionable,Juego juego) {
		this.entidadColisionable = entidadColisionable;
		this.juego = juego;
		player = Sonido.obtenerInstancia();
	}

	public void visitarGoomba(Goomba goomba) {
		if(entidadColisionable.colisionable()) {
			direccionColision = detectarDireccion( goomba);
			if(direccionColision.equals("ARRIBA")) {
				player.reproducirSonidoMorir();
				goomba.retirar();
				juego.getJugador().setPuntaje(goomba.getPuntaje() + juego.getJugador().getPuntaje());
			} else
				if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){
					entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,goomba);	
					juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + goomba.puntajeMatarAMario());
				}
		}
	}

	public void visitarKoopa(KoopaTroopa koopa) {
		direccionColision = detectarDireccion(koopa);
		if(!koopa.estaEnEnfriamiento()) {
			if(direccionColision.equals("ARRIBA")) {
				Sonido.obtenerInstancia().reproducirSonidoMuereKoopa();
				koopa.atacado();
				juego.getJugador().setPuntaje(koopa.getPuntaje() + juego.getJugador().getPuntaje());
			}
			if(entidadColisionable.colisionable()) {
				if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){
					if(!koopa.getMovilidad()) {
						entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,koopa);
						juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + koopa.puntajeMatarAMario());
					}
					else {
						koopa.movilidad();
					}
				}
			}
		}
	}

	public void visitarLakitu(Lakitu lakitu) {
		if(entidadColisionable.colisionable()) {
			direccionColision = detectarDireccion(lakitu);
			if(direccionColision.equals("ARRIBA")) {
				Sonido.obtenerInstancia().reproducirSonidoMuereEnemigo();
				lakitu.retirar();
				juego.getJugador().setPuntaje(lakitu.getPuntaje() + juego.getJugador().getPuntaje());
			}
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){	
				entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,lakitu);	
			}
		}
	}

	public void visitarBuzzy(BuzzyBeetle buzzy) {
		if(entidadColisionable.colisionable()) {
			direccionColision = detectarDireccion( buzzy);
			if(direccionColision.equals("ARRIBA")) {
				Sonido.obtenerInstancia().reproducirSonidoMuereEnemigo();
				retirarEntidad(buzzy);
				juego.getJugador().setPuntaje(buzzy.getPuntaje() + juego.getJugador().getPuntaje());
			}
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){	
				entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,buzzy);
				juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + buzzy.puntajeMatarAMario());
			}
		}
	}

	public void visitarSpiny(Spiny spiny) {
		if(entidadColisionable.colisionable()) {
			direccionColision = detectarDireccion(spiny);
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA") || direccionColision.equals("ARRIBA")){
				entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,spiny);
				juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + spiny.puntajeMatarAMario());
			}
		}
	}

	public void visitarPiranha(PiranhaPlant piranha) {
		if(entidadColisionable.colisionable()) {
			direccionColision = detectarDireccion(piranha);
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA") || direccionColision.equals("ARRIBA")){
				entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,piranha);	
				juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + piranha.puntajeMatarAMario());
			}
		}
	}

	public void visitarBloqueSolido(BloqueSolido bloque) {
		direccionColision = detectarDireccion(bloque);
		bloque.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarBloquePregunta(BloquePregunta bloque) {
		direccionColision = detectarDireccion(bloque);
		bloque.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarLadrillo(LadrilloSolido ladrillo) {
		direccionColision = detectarDireccion(ladrillo);
		ladrillo.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarTuberia(Tuberia tuberia) {
		direccionColision = detectarDireccion(tuberia);
		tuberia.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarTuberiaPiranha(TuberiaConPiranha tuberia) {
		visitarTuberia(tuberia);
	}

	public void visitarVacio(Vacio vacio) {
		entidadColisionable.morirPorVacio();
		entidadColisionable.setCambioEstado(true);
		entidadColisionable.decrementarVida();
		juego.getJugador().setPuntaje(juego.getJugador().getPuntaje() + vacio.puntajeMarioVacio());
		vacio.setColisionable(false);
		new Thread(() -> {
			try {
				Thread.sleep(550); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			entidadColisionable.resetearPosicion();
			juego.reiniciarNivel();
		}).start();

	}

	public void visitarChampinonVerde(ChampinonVerde champinon) {
		if (champinon.colisionable() && champinon.getAreaColision().intersects(champinon.getAreaColision())) {
			Sonido.obtenerInstancia().reproducirSonidoObtuvoVida();
			champinon.afectar(entidadColisionable);
			juego.getJugador().setPuntaje(champinon.getPuntaje() + juego.getJugador().getPuntaje());
			retirarEntidad(champinon);
		}
	}

	public void visitarEstrella(Estrella estrella) {
		Sonido.obtenerInstancia().reproducirSonidoPowerUp();
		estrella.afectar(entidadColisionable);
		entidadColisionable.setAlto(32);
		entidadColisionable.actualizarAreaColision();
		juego.getJugador().setPuntaje(entidadColisionable.getEstadoMario().puntajeEstrella() + juego.getJugador().getPuntaje());
		retirarEntidad(estrella);
		entidadColisionable.iniciarRegresionEstadoEstrella();
	}

	public void visitarFlorFuego(FlorFuego flor) {
		Sonido.obtenerInstancia().reproducirSonidoFlorDeFuego();
		flor.afectar(entidadColisionable);
		entidadColisionable.setAlto(32);
		entidadColisionable.actualizarAreaColision();
		juego.getJugador().setPuntaje(entidadColisionable.getEstadoMario().puntajeFlorFuego() + juego.getJugador().getPuntaje());
		retirarEntidad(flor);
	}

	public void visitarMoneda(Moneda moneda) {
		Sonido.obtenerInstancia().reproducirSonidoMoneda();
		moneda.afectar(entidadColisionable);
		juego.getJugador().setPuntaje(moneda.getPuntaje() + juego.getJugador().getPuntaje());
		retirarEntidad(moneda);
	}

	public void visitarSuperChampinon(SuperChampinon champinon) {
		Sonido.obtenerInstancia().reproducirSonidoSuperChampinon();
		champinon.afectar(entidadColisionable);
		entidadColisionable.setCambioEstado(true);
		entidadColisionable.setAlto(32);
		entidadColisionable.actualizarAreaColision();
		juego.getJugador().setPuntaje(entidadColisionable.getEstadoMario().puntajeSuperChampinon() + juego.getJugador().getPuntaje());
		retirarEntidad(champinon);
	}

	private void retirarEntidad(Entidad entidad) {
		entidad.retirar();
		entidad.notificarObservador();
	}

	private String detectarDireccion(Entidad entidad) {
		String direccion = "NINGUNA";
		boolean arriba,derecha,izquierda;
		arriba = entidadColisionable.getPosicionY() + entidadColisionable.getAlto()-1 >= entidad.getPosicionY() +entidad.getAlto(); 
		izquierda = entidadColisionable.getPosicionX() + entidadColisionable.getAncho() <= entidad.getPosicionX()+10; 
		derecha = entidadColisionable.getPosicionX()+10 >= entidad.getPosicionX() + entidad.getAncho();
		if(izquierda) {
			direccion="IZQUIERDA";
		}
		if(derecha) {
			direccion= "DERECHA";
		}
		if(arriba) {
			direccion="ARRIBA";
		} else if(!derecha && !izquierda){
			direccion="ABAJO";
		}
		return direccion;
	}
}
