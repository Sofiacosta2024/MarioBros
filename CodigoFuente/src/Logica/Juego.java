package Logica;

import Sonido.*;
import Threads.MoverEnemigo;
import Threads.MoverMario;
import Vista.*;
import java.util.ArrayList;

import Colisionador.ColisionadorVisitorBolaFuego;
import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Enemigo.*;
import Fabrica.*;
import Mario.*;
import Parser.*;
import Plataforma.*;
import PowerUp.*;

public class Juego {

	protected Sonido player;
	protected ControladorVistas controladorVistas;
	protected Nivel nivelActual;
	protected FabricaSprite fabricaSprite;
	protected FabricaEntidades fabricaEntidades;
	protected Jugador jugador;
	protected Parser parser;
	protected MoverMario hiloMario;
	protected MoverEnemigo hiloMundo;
	protected boolean juegoTerminado;
	protected int numeroDeNivel;
	protected String rutaModo;

	public Juego() {
		jugador = new Jugador(null,0);
		player=new Sonido();
		numeroDeNivel = 1;
		nivelActual = new Nivel(numeroDeNivel);
	}

	public void iniciarPartida() {
		player.playSonidoFondo();
		juegoTerminado = false;
		controladorVistas.registarControladorEntradas();
		registrarObservers();
		controladorVistas.mostrarPantallaPartida();
		iniciarHilos();
		controladorVistas.getPantallaPartida().iniciarTemporizador();
		controladorVistas.getPantallaPartida().iniciarActualizacionInformacion();
		player.playClip("/Sonido/Sounds/herewego.wav");
	}

	public void iniciarHilos() {
		hiloMario= new MoverMario(this, jugador.getPersonaje());
		hiloMundo = new MoverEnemigo(this);
		hiloMario.iniciarHilo();
		hiloMundo.iniciarHilo();
	}

	public void detenerHilos() {
		hiloMario.detenerHilo();
		hiloMundo.detenerHilo();
	}

	public void seleccionarModoOriginal() {
		if(rutaModo == "/Imagenes/Sprites/Especial")
			this.getControladorVista().getPantallaPartida().limpiarEntidadesGraficas();
		fabricaSprite = new FabricaOriginal();
		rutaModo="/Imagenes/Sprites/Original";
		fabricaEntidades = new FabricaEntidades(fabricaSprite);
		this.getControladorVista().getPantallaPartida().agregarImagenFondo();
		cargarNivel();
	}

	public void seleccionarModoEspecial() {
		if(rutaModo == "/Imagenes/Sprites/Original")
			this.getControladorVista().getPantallaPartida().limpiarEntidadesGraficas();
		fabricaSprite = new FabricaEspecial();
		rutaModo="/Imagenes/Sprites/Especial";
		fabricaEntidades = new FabricaEntidades(fabricaSprite);
		this.getControladorVista().getPantallaPartida().agregarImagenFondo();
		cargarNivel();
	}

	public void cargarNivel() {
		parser = Parser.getInstancia(this);
		parser.setFabrica(fabricaEntidades);
		nivelActual = new Nivel(numeroDeNivel);
		parser.parsearNivel(nivelActual);
		jugador.setPersonaje(nivelActual.getMario());
	}

	public void reiniciarNivel() {
		for(Plataforma plataforma: nivelActual.getPlataformas()) {
			plataforma.setColisionable(true);
			plataforma.notificarObservador();
		}
		for(Enemigo enemigo: nivelActual.getEnemigos()) {
			enemigo.setPosicionInicial();
			enemigo.setColisionable(true);
			enemigo.notificarObservador();
		}
		for(PowerUp powerUp: nivelActual.getPowerUps()) {
			powerUp.setPosicionInicial();
			powerUp.setColisionable(true);
			powerUp.notificarObservador();
		}
		for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
			spiny.retirar();
			spiny.notificarObservador();
		}
		for(BolaFuego bola: nivelActual.getProyectiles()) {
			bola.retirar();
			bola.notificarObservador();
		}
		nivelActual.getMario().resetearPosicion();
		controladorVistas.getPantallaPartida().setTiempoNivel(controladorVistas.getPantallaPartida().getTiempoNivelInicial());
	}

	public void jugadorPasaDeNivel() {
		if(numeroDeNivel<3) {
			numeroDeNivel++;
			detenerHilos();
			player.detenerSonidoFondo();
			actualizarPantallaPasajeDeNivel();
			getNivelActual().getMario().setPosicionX(50);
			int vidas = getNivelActual().getMario().getVidas();
			EstadoMario estado = getNivelActual().getMario().getEstadoMario();
			cargarNivel();
			getNivelActual().getMario().setVidas(vidas);
			getNivelActual().getMario().setEstadoMario(estado);
			iniciarPartida();
			getNivelActual().getMario().setMarioEstadoNivel();
			if(numeroDeNivel!=1)
				player.playClip("/Sonido/Sounds/yahoo.wav");
		}
		else {
			player.playClip("/Sonido/Sounds/ganar.wav");
			controladorVistas.getPantallaVictoria().setPuntajeLabel(getJugador().getPuntaje());
			controladorVistas.getPantallaVictoria().setNombreGanador(getJugador().getNombre());
			controladorVistas.mostrarPantallaVictoria();
			juegoTerminado = true;
		}
	}

	public void actualizarPantallaPasajeDeNivel() {
		controladorVistas.getPantallaPartida().limpiarEntidadesGraficas();
		controladorVistas.getPantallaPartida().resetearScroll();
		if(numeroDeNivel == 2)
			controladorVistas.getPantallaPartida().setTiempoNivel(300);
		else
			controladorVistas.getPantallaPartida().setTiempoNivel(200);
	}

	public void finalizarPartida() {
		juegoTerminado = true;
		player.playClip("/Sonido/Sounds/gameover.wav");
		controladorVistas.mostrarPantallaFinJuego();
		for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
			spiny.retirar();
			spiny.notificarObservador();
		}
		for(BolaFuego bola: nivelActual.getProyectiles()) {
			bola.retirar();
			bola.notificarObservador();
		}
		numeroDeNivel = 1;
		controladorVistas.getPantallaPartida().detenerActualizacion();
	}

	public void salirDelJuego() {
		controladorVistas.mostrarPantallaModoJuego();
		player.detenerSonidoFondo();
	} 

	public boolean juegoTerminado() {
		return juegoTerminado;
	}

	public void registrarObservers() {
		registrarObserverMario(nivelActual.getMario());
		registrarObserversPlataformas(nivelActual.getPlataformas());
		registrarObserversEnemigos(nivelActual.getEnemigos());
		registrarObserversPowerUps(nivelActual.getPowerUps());		
	}

	public void registrarObserversPlataformas(ArrayList<Plataforma> list) {
		for(Entidad entidad : list) {
			Observer observadorEntidad =  controladorVistas.registrarEntidadLogica(entidad);
			entidad.setObserver(observadorEntidad);
		}
	}

	public void registrarObserversPowerUps(ArrayList<PowerUp> list) {
		for(Entidad entidad : list) {
			Observer observadorEntidad =  controladorVistas.registrarEntidadLogica(entidad);
			entidad.setObserver(observadorEntidad);
		}
	}

	public void registrarObserversEnemigos(ArrayList<Enemigo> list) {
		for(Entidad entidad : list) {
			Observer observadorEntidad =  controladorVistas.registrarEntidadLogica(entidad);
			entidad.setObserver(observadorEntidad);
		}
	}

	public void registrarObserverMario(Mario mario) {
		Observer observadorMario = controladorVistas.registrarEntidadMario(mario);
		mario.setObserver(observadorMario);
	}

	public void registrarObserverProyectiles(ArrayList<BolaFuego> list) {
		for (BolaFuego entidad : list) {
			Observer observadorEntidad = controladorVistas.registrarEntidadLogica(entidad);
			entidad.setObserver(observadorEntidad);
		}
	}

	public void detectarColisionesMarioMundo() {
		ColisionadorVisitorMario colisionador = new ColisionadorVisitorMario(nivelActual.getMario(),this);
		for (Plataforma plataforma : nivelActual.getPlataformas()) {
			if (plataforma.colisionable() && nivelActual.getMario().getAreaColision().intersects(plataforma.getAreaColision())) {
				plataforma.aceptarVisita(colisionador);	
			}
		}
		for (Enemigo enemigo : nivelActual.getEnemigos()) {
			if (enemigo.colisionable() && nivelActual.getMario().getAreaColision().intersects(enemigo.getAreaColision())) {
				enemigo.aceptarVisita(colisionador);
			}
		}
		for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
			if (spiny.colisionable() && nivelActual.getMario().getAreaColision().intersects(spiny.getAreaColision())) {
				spiny.aceptarVisita(colisionador);
			}
		}
		for (Entidad entidad : nivelActual.getPowerUps()) {
			if (entidad.colisionable() && nivelActual.getMario().getAreaColision().intersects(entidad.getAreaColision())) {
				entidad.aceptarVisita(colisionador);
			}
		}
	}

	public void detectarColisionesMundoSinMario() {
	    for(Enemigo enemigo: nivelActual.getEnemigos()) {
	        ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(enemigo,this);
	        boolean sobreAlgunaPlataforma = false;
	        for(Enemigo enemigoColisionable : nivelActual.getEnemigos()) {
	            if (enemigoColisionable != enemigo && enemigoColisionable.colisionable() && enemigo.colisionable() &&
	                enemigo.getAreaColision().intersects(enemigoColisionable.getAreaColision())) {
	                enemigoColisionable.aceptarVisita(colisionador);
	            }
	        }
	        for(Plataforma plataforma : nivelActual.getPlataformas()) {
	            if (plataforma.colisionable() && enemigo.colisionable() && 
	            	enemigo.getAreaColision().intersects(plataforma.getAreaColision())) {
	                plataforma.aceptarVisita(colisionador);
	                sobreAlgunaPlataforma = true;
	            }
	        }
	        enemigo.detenerOActivarCaida(sobreAlgunaPlataforma);
	        for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
	            if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo && 
	                enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
	                enemigo.aceptarVisita(colisionador);
	            }
	        }
	    }	
		for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
			ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(spiny,this);
				boolean sobreAlgunaPlataforma = false;
				for(Plataforma plataforma : nivelActual.getPlataformas()) {
		            if (plataforma.colisionable() && spiny.colisionable() && 
		            	spiny.getAreaColision().intersects(plataforma.getAreaColision())) {
		                plataforma.aceptarVisita(colisionador);
		                sobreAlgunaPlataforma = true;
		            }
		        }
			spiny.detenerOActivarCaida(sobreAlgunaPlataforma);
			for(Enemigo enemigo: nivelActual.getEnemigos()) 
				if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo && enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
					enemigo.aceptarVisita(colisionador);
				}
		}
		for(BolaFuego bolaFuego : nivelActual.getProyectiles()) {
			ColisionadorVisitorBolaFuego colisionador = new ColisionadorVisitorBolaFuego(bolaFuego,this);
			for(Enemigo enemigoColisionable : nivelActual.getEnemigos())
				if (enemigoColisionable.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(enemigoColisionable.getAreaColision())) {
					enemigoColisionable.aceptarVisita(colisionador);
				}
			for(Plataforma plataforma : nivelActual.getPlataformas())
				if (plataforma.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(plataforma.getAreaColision())) {
					plataforma.aceptarVisita(colisionador);
				}
			for(Spiny spiny : nivelActual.getSpinyssLanzados()) {
				if (spiny.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(spiny.getAreaColision())) {
					spiny.aceptarVisita(colisionador);
				}
			}
		}
	}

	public void moverMario() {
		this.getNivelActual().getMario().mover();
	}

	public void setControladorVista(ControladorVistas controladorVistas) {
		this.controladorVistas= controladorVistas;
	}

	public Jugador getJugador() {
		return jugador;
	}
	
	public int getTiempoActual() {
		return controladorVistas.getPantallaPartida().getTiempoNivel();
	}
	public Nivel getNivelActual() {
		return nivelActual;
	}

	public String getRutaModo() {
		return rutaModo;
	}

	public int getNumeroDeNivel() {
		return numeroDeNivel;
	}

	public ControladorVistas getControladorVista() {
		return controladorVistas;
	}
}