package Logica;

import Sonido.*;
import Threads.MoverEnemigo;
import Threads.MoverMario;
import Vista.*;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

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

	public void seleccionarModo(String rutaNuevaModo) {
		if(rutaModo != rutaNuevaModo) {
			this.getControladorVista().limpiezaDeEntidadesGraficas();
		}
		
		if(rutaNuevaModo == "/Imagenes/Sprites/Original") {
			fabricaSprite = new FabricaOriginal();
			rutaModo = rutaNuevaModo;
		}
		else
			if(rutaNuevaModo == "/Imagenes/Sprites/Especial") {
				fabricaSprite = new FabricaEspecial();
				rutaModo = rutaNuevaModo;
			}
		
		fabricaEntidades = new FabricaEntidades(fabricaSprite);
		this.getControladorVista().seleccionarImagenFondoPartida();
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
			plataforma.setEstadoInicial();
			plataforma.notificarObservador();
		}
		for(Enemigo enemigo: nivelActual.getEnemigos()) {
			enemigo.setEstadoInicial();
			enemigo.setColisionable(true);
			enemigo.notificarObservador();
		}
		for(PowerUp powerUp: nivelActual.getPowerUps()) {
			powerUp.setEstadoInicial();
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
		nivelActual.getMario().setEstadoInicial();
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
			int puntaje = getNivelActual().getMario().getPuntaje();
			EstadoMario estado = getNivelActual().getMario().getEstadoMario();
			cargarNivel();
			getNivelActual().getMario().setVidas(vidas);
			getNivelActual().getMario().setEstadoMario(estado);
			getNivelActual().getMario().setPuntaje(puntaje);
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
		detenerHilos();
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
	
	public void actualizarPuntaje() {
		controladorVistas.actualizarPuntajePantallaPartida();
	}
	
	public void actualizarVidas() {
		controladorVistas.actualizarVidasPantallaPartida(nivelActual.getMario());
	}
	
	public void actualizarNivel() {
		controladorVistas.actualizarlNivelPantallaPartida();
	}
	
	public void actualizarTiempo() {
		controladorVistas.actualizarTiempoPantallaPartida();
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
	    ColisionadorVisitorMario colisionador = new ColisionadorVisitorMario(nivelActual.getMario(), this);
	    Iterator<Plataforma> iterPlataformas = nivelActual.getPlataformas().iterator();
	    while (iterPlataformas.hasNext()) {
	        Plataforma plataforma = iterPlataformas.next();
	        if (plataforma.colisionable() && nivelActual.getMario().getAreaColision().intersects(plataforma.getAreaColision())) {
	            plataforma.aceptarVisita(colisionador);
	        }
	    }
	    Iterator<Enemigo> iterEnemigos = nivelActual.getEnemigos().iterator();
	    while (iterEnemigos.hasNext()) {
	        Enemigo enemigo = iterEnemigos.next();
	        if (enemigo.colisionable() && nivelActual.getMario().getAreaColision().intersects(enemigo.getAreaColision())) {
	            enemigo.aceptarVisita(colisionador);
	        }
	    }
	    Iterator<Spiny> iterSpinys = nivelActual.getSpinyssLanzados().iterator();
	    while (iterSpinys.hasNext()) {
	        Spiny spiny = iterSpinys.next();
	        if (spiny.colisionable() && nivelActual.getMario().getAreaColision().intersects(spiny.getAreaColision())) {
	            spiny.aceptarVisita(colisionador);
	        }
	    }
	    Iterator<PowerUp> iterPowerUps = nivelActual.getPowerUps().iterator();
	    while (iterPowerUps.hasNext()) {
	        Entidad entidad = iterPowerUps.next();
	        if (entidad.colisionable() && nivelActual.getMario().getAreaColision().intersects(entidad.getAreaColision())) {
	            entidad.aceptarVisita(colisionador);
	        }
	    }
	}

	public void detectarColisionesMundoSinMario() {
	    try {
	        ArrayList<Enemigo> enemigos = nivelActual.getEnemigos();
	        ArrayList<Plataforma> plataformas = nivelActual.getPlataformas();
	        ArrayList<Spiny> spinyss = nivelActual.getSpinyssLanzados();
	        ArrayList<BolaFuego> bolasFuego = nivelActual.getProyectiles();
	        Iterator<Enemigo> iterEnemigos = enemigos.iterator();
	        while (iterEnemigos.hasNext()) {
	            Enemigo enemigo = iterEnemigos.next();
	            ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(enemigo, this);
	            boolean sobreAlgunaPlataforma = false;
	            Iterator<Enemigo> iterOtrosEnemigos = enemigos.iterator();
	            while (iterOtrosEnemigos.hasNext()) {
	                Enemigo enemigoColisionable = iterOtrosEnemigos.next();
	                if (enemigoColisionable != enemigo && enemigoColisionable.colisionable() && enemigo.colisionable() &&
	                        enemigo.getAreaColision().intersects(enemigoColisionable.getAreaColision())) {
	                    enemigoColisionable.aceptarVisita(colisionador);
	                }
	            }
	            Iterator<Plataforma> iterPlataformas = plataformas.iterator();
	            while (iterPlataformas.hasNext()) {
	                Plataforma plataforma = iterPlataformas.next();
	                if (plataforma.colisionable() && enemigo.colisionable() &&
	                        enemigo.getAreaColision().intersects(plataforma.getAreaColision())) {
	                    plataforma.aceptarVisita(colisionador);
	                    sobreAlgunaPlataforma = true;
	                }
	            }
	            Iterator<Spiny> iterSpinys = spinyss.iterator();
	            while (iterSpinys.hasNext()) {
	                Spiny spiny = iterSpinys.next();
	                if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo &&
	                        enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
	                    enemigo.aceptarVisita(colisionador);
	                }
	            }
	            enemigo.detenerOActivarCaida(sobreAlgunaPlataforma);
	        }
	        Iterator<Spiny> iterSpinys = spinyss.iterator();
	        while (iterSpinys.hasNext()) {
	            Spiny spiny = iterSpinys.next();
	            ColisionadorVisitorEnemigo colisionador = new ColisionadorVisitorEnemigo(spiny, this);
	            boolean sobreAlgunaPlataforma = false;
	            Iterator<Plataforma> iterPlataformas = plataformas.iterator();
	            while (iterPlataformas.hasNext()) {
	                Plataforma plataforma = iterPlataformas.next();
	                if (plataforma.colisionable() && spiny.colisionable() &&
	                        spiny.getAreaColision().intersects(plataforma.getAreaColision())) {
	                    plataforma.aceptarVisita(colisionador);
	                    sobreAlgunaPlataforma = true;
	                }
	            }
	            spiny.detenerOActivarCaida(sobreAlgunaPlataforma);
	            Iterator<Enemigo> iterEnemigosCol = enemigos.iterator();
	            while (iterEnemigosCol.hasNext()) {
	                Enemigo enemigo = iterEnemigosCol.next();
	                if (spiny.colisionable() && enemigo.colisionable() && spiny != enemigo &&
	                        enemigo.getAreaColision().intersects(spiny.getAreaColision())) {
	                    enemigo.aceptarVisita(colisionador);
	                }
	            }
	        }
	        Iterator<BolaFuego> iterBolasFuego = bolasFuego.iterator();
	        while (iterBolasFuego.hasNext()) {
	            BolaFuego bolaFuego = iterBolasFuego.next();
	            ColisionadorVisitorBolaFuego colisionador = new ColisionadorVisitorBolaFuego(bolaFuego, this);
	            Iterator<Enemigo> iterEnemigosCol = enemigos.iterator();
	            while (iterEnemigosCol.hasNext()) {
	                Enemigo enemigo = iterEnemigosCol.next();
	                if (enemigo.colisionable() && bolaFuego.colisionable() &&
	                        bolaFuego.getAreaColision().intersects(enemigo.getAreaColision())) {
	                    enemigo.aceptarVisita(colisionador);
	                    iterBolasFuego.remove();
	                    break;
	                }
	            }
	            Iterator<Plataforma> iterPlataformasCol = plataformas.iterator();
	            while (iterPlataformasCol.hasNext()) {
	                Plataforma plataforma = iterPlataformasCol.next();
	                if (plataforma.colisionable() && bolaFuego.colisionable() &&
	                        bolaFuego.getAreaColision().intersects(plataforma.getAreaColision())) {
	                    plataforma.aceptarVisita(colisionador);
	                    iterBolasFuego.remove();
	                    break;
	                }
	            }
	            Iterator<Spiny> iterSpinysCol = spinyss.iterator();
	            while (iterSpinysCol.hasNext()) {
	                Spiny spiny = iterSpinysCol.next();
	                if (spiny.colisionable() && bolaFuego.colisionable() &&
	                        bolaFuego.getAreaColision().intersects(spiny.getAreaColision())) {
	                    spiny.aceptarVisita(colisionador);
	                    iterBolasFuego.remove();
	                    break;
	                }
	            }
	        }
	    } catch (ConcurrentModificationException excepcion) {
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