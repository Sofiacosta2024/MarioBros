package Logica;

import Sonido.*;
import Threads.MoverEnemigo;
import Threads.MoverMario;
import Vista.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
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
	protected Mario mario;;
	protected Parser parser;
	protected MoverMario hiloMario;
	protected MoverEnemigo hiloMundo;
	protected boolean juegoTerminado;
	protected int numeroDeNivel;
	protected String rutaModo;

	public Juego() {
		player=Sonido.obtenerInstancia();
		numeroDeNivel = 1;
		nivelActual = new Nivel(numeroDeNivel);
	}

	public void iniciarPartida() {
		Sonido.obtenerInstancia().reproducirSonidoFondo();;
		juegoTerminado = false;
		controladorVistas.registarControladorEntradas();
		registrarObservers();
		controladorVistas.mostrarPantallaPartida();
		iniciarHilos();
		controladorVistas.iniciarTemporizadorPantallaPartida();
		controladorVistas.iniciarActualizarInformacionPantallaPartida();
		player.reproducirSonidoComienzaPartida();
	}

	public void iniciarHilos() {
		hiloMario= new MoverMario(this, nivelActual.getMario());
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
			this.getControladorVista().limpiezaDeEntidadesGraficas();
		fabricaSprite = new FabricaOriginal();
		rutaModo="/Imagenes/Sprites/Original";
		fabricaEntidades = new FabricaEntidades(fabricaSprite);
		this.getControladorVista().seleccionarImagenFondo();
		cargarNivel();
	}

	public void seleccionarModoEspecial() {
		if(rutaModo == "/Imagenes/Sprites/Original")
			this.getControladorVista().limpiezaDeEntidadesGraficas();
		fabricaSprite = new FabricaEspecial();
		rutaModo="/Imagenes/Sprites/Especial";
		fabricaEntidades = new FabricaEntidades(fabricaSprite);
		this.getControladorVista().seleccionarImagenFondo();
		cargarNivel();
	}

	public void cargarNivel() {
		parser = Parser.getInstancia(this);
		parser.setFabrica(fabricaEntidades);
		nivelActual = new Nivel(numeroDeNivel);
		parser.parsearNivel(nivelActual);
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
		controladorVistas.setTiempoNivel();
	}

	public void jugadorPasaDeNivel() {
		if(numeroDeNivel<3) {
			numeroDeNivel++;
			detenerHilos();
			player.detenerSonidoFondo();
			controladorVistas.actualizarPantallaPasajeDeNivel(numeroDeNivel);
			getNivelActual().getMario().setPosicionX(50);
			int vidas = getNivelActual().getMario().getVidas();
			EstadoMario estado = getNivelActual().getMario().getEstadoMario();
			cargarNivel();
			getNivelActual().getMario().setVidas(vidas);
			getNivelActual().getMario().setEstadoMario(estado);
			iniciarPartida();
			getNivelActual().getMario().setMarioEstadoNivel();
			if(numeroDeNivel!=1)
				player.reproducirSonidoPasoNivel();
		}
		else {
			player.reproducirSonidoGanar();
			controladorVistas.setPuntajeLabel(nivelActual.getMario().getPuntaje());
			controladorVistas.setNombreJugadorGanador(nivelActual.getMario().getNombre());
			controladorVistas.mostrarPantallaVictoria();
			juegoTerminado = true;
		}
	}

	public void finalizarPartida() {
		juegoTerminado = true;
		player.reproducirSonidoGameOver();
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
		controladorVistas.detenerActualizacionPantallaPartida();
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

	public void registrarObserverProyectiles(BolaFuego bolaFuego) {
		Observer observadorEntidad = controladorVistas.registrarEntidadLogica(bolaFuego);
		bolaFuego.setObserver(observadorEntidad);
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
		try {
			ArrayList<Enemigo> enemigos = new ArrayList<>(nivelActual.getEnemigos());
			ArrayList<Plataforma> plataformas = new ArrayList<>(nivelActual.getPlataformas());
			ArrayList<Spiny> spinyss = new ArrayList<>(nivelActual.getSpinyssLanzados());
			ArrayList<BolaFuego> bolasFuego = new ArrayList<>(nivelActual.getProyectiles());
			
			for(Enemigo enemigo: enemigos) {
				ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(enemigo,this);
				boolean sobreAlgunaPlataforma = false;
				for(Enemigo enemigoColisionable : nivelActual.getEnemigos()) {
					if (enemigoColisionable != enemigo && enemigoColisionable.colisionable() && enemigo.colisionable() &&
							enemigo.getAreaColision().intersects(enemigoColisionable.getAreaColision())) {
						enemigoColisionable.aceptarVisita(colisionador);
					}
				}
				for(Plataforma plataforma : plataformas) {
					if (plataforma.colisionable() && enemigo.colisionable() && 
							enemigo.getAreaColision().intersects(plataforma.getAreaColision())) {
						plataforma.aceptarVisita(colisionador);
						sobreAlgunaPlataforma = true;
					}
				}
				enemigo.detenerOActivarCaida(sobreAlgunaPlataforma);
				for(Spiny spiny : spinyss) {
					if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo && 
							enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
						enemigo.aceptarVisita(colisionador);
					}
				}
			}	
			for(Spiny spiny : spinyss) {
				ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(spiny,this);
				boolean sobreAlgunaPlataforma = false;
				for(Plataforma plataforma : plataformas) {
					if (plataforma.colisionable() && spiny.colisionable() && 
							spiny.getAreaColision().intersects(plataforma.getAreaColision())) {
						plataforma.aceptarVisita(colisionador);
						sobreAlgunaPlataforma = true;
					}
				}
				spiny.detenerOActivarCaida(sobreAlgunaPlataforma);
				for(Enemigo enemigo: enemigos) 
					if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo && enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
						enemigo.aceptarVisita(colisionador);
					}
			}
			for(BolaFuego bolaFuego : bolasFuego) {
				ColisionadorVisitorBolaFuego colisionador = new ColisionadorVisitorBolaFuego(bolaFuego,this);
				for(Enemigo enemigoColisionable : enemigos)
					if (enemigoColisionable.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(enemigoColisionable.getAreaColision())) {
						enemigoColisionable.aceptarVisita(colisionador);
					}
				for(Plataforma plataforma : plataformas)
					if (plataforma.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(plataforma.getAreaColision())) {
						plataforma.aceptarVisita(colisionador);
					}
				for(Spiny spiny : spinyss) {
					if (spiny.colisionable() && bolaFuego.colisionable() && bolaFuego.getAreaColision().intersects(spiny.getAreaColision())) {
						spiny.aceptarVisita(colisionador);
					}
				}
			}
		}catch(ConcurrentModificationException excepcion) {
			System.out.println("CONCURRENNNNTTTTTTTTTTTT");
			detectarColisionesMundoSinMario();
		}
	}

	public void moverMario() {
		this.getNivelActual().getMario().mover();
	}

	public void setControladorVista(ControladorVistas controladorVistas) {
		this.controladorVistas= controladorVistas;
	}
	
	public int getTiempoActual() {
		int tiempo = controladorVistas.getTiempoNivelActual();
		return tiempo;
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