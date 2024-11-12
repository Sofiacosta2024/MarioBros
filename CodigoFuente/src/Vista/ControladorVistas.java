package Vista;

import Mario.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import Enemigo.Spiny;
import Logica.*;
import Sonido.*;

public class ControladorVistas implements ControladorDeVistas,ControladorEntreJuegoVista{

	protected JFrame ventana;
	protected PantallaInicial panelInicial;
	protected PantallaPartida panelPartida;
	protected PantallaFinJuego panelFinJuego;
	protected PantallaRanking panelRanking;
	protected PantallaIngresoNombre panelIngresoNombre;
	protected PantallaModoJuego panelModoJuego;
	protected PantallaVictoria panelVictoria;
	protected ControladorEntradas controladorEntradas;
	protected Juego juego;
	protected Sonido player;
	private ArrayList<Observer> lista;


	public ControladorVistas(Juego j) {
		juego = j;
		panelInicial = new PantallaInicial(this);
		panelPartida = new PantallaPartida(this,juego);
		panelFinJuego = new PantallaFinJuego(this);
		panelIngresoNombre= new PantallaIngresoNombre(this,juego);
		panelRanking = new PantallaRanking(this);
		panelModoJuego = new PantallaModoJuego(this);
		panelVictoria=new PantallaVictoria(this);
		lista = new ArrayList<Observer>();
		configurarVentana();
		player= Sonido.obtenerInstancia();
	}

	public void configurarVentana() {
		ventana = new JFrame("Proyecto SuperMarioBros");
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setSize(ConstantesVistas.VENTANA_ANCHO, ConstantesVistas.VENTANA_ALTO);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}

	public void registarControladorEntradas() {
		Mario mario = juego.getNivelActual().getMario();
		controladorEntradas = new ControladorEntradas(mario);
	}

	public Observer registrarEntidadLogica(EntidadLogica entidadLogica) {
		Observer observerEntidad = panelPartida.incorporarEntidad(entidadLogica);
		lista.add(observerEntidad);
		refrescar();
		return observerEntidad;
	}

	public Observer registrarEntidadLogicaSpinyLanzado(Spiny spiny) {
		Observer observerEntidad = panelPartida.incorporarEntidad(spiny);
		lista.add(observerEntidad);
		refrescar();
		return observerEntidad;
	}

	public Observer registrarEntidadMario(EntidadMario entidadMario) {
		Observer observerEntidad = panelPartida.incorporarEntidadMario(entidadMario);
		lista.add(observerEntidad);
		refrescar();
		return observerEntidad;
	}

	public void mostrarPantallaPartida() {
		guardarRanking();
		ventana.setContentPane(panelPartida);
		panelPartida.requestFocus();
		panelPartida.addKeyListener(controladorEntradas);
		refrescar();
	}

	public void mostrarPantallaFinJuego() {
		guardarRanking();
		ventana.setContentPane(panelFinJuego);
		refrescar();
	}

	public void mostrarPantallaInicial() {
		ventana.setContentPane(panelInicial);
		refrescar();
	}

	public void mostrarPantallaModoJuego() {
		ventana.setContentPane(panelModoJuego);
		refrescar();
	}

	public void mostrarPantallaIngreso() {
		ventana.setContentPane(panelIngresoNombre);
		refrescar();
	}

	public void mostrarPantallaRanking() {
		ventana.setContentPane(panelRanking);
	}

	public void mostrarPantallaVictoria() {
		guardarRanking();
		ventana.setContentPane(panelVictoria);
	}

	public void reiniciarPantallas() {
		panelInicial = new PantallaInicial(this);
		panelPartida = new PantallaPartida(this,juego);
		panelFinJuego = new PantallaFinJuego(this);
		panelRanking = new PantallaRanking(this);
		panelModoJuego = new PantallaModoJuego(this);
	}

	public void reiniciarListaDeObservers() {
		lista = new ArrayList<Observer>();
	}

	public void reiniciarPantallaPartida() {
		panelPartida = new PantallaPartida(this,juego);
	}

	private void guardarRanking() {
		String nombre=juego.getNivelActual().getMario().getNombre();
		int puntaje=juego.getNivelActual().getMario().getPuntaje();
		Ranking.getInstancia().agregarJugador(nombre,puntaje);
		Ranking.getInstancia().guardarPuntajes();
	}

	public void accionarInicioJuego() {
		juego.iniciarPartida();
	}

	public void accionarPantallaRanking() {
		mostrarPantallaRanking();
	}

	public void accionarPantallaAlIngresar() {
		mostrarPantallaModoJuego();
		refrescar();
	}

	public void accionarPantallaAlSalir() {
		reiniciarPantallas();
		juego.salirDelJuego();
		reiniciarListaDeObservers();
	}

	public void accionarModoOriginal() {		
		juego.seleccionarModoOriginal();
		mostrarPantallaInicial();
	}

	public void accionarModoEspecial() {	
		juego.seleccionarModoEspecial();
		mostrarPantallaInicial();	
	}

	public void accionarPantallaVictoria() {
		mostrarPantallaModoJuego();
	}
	
	public void iniciarTemporizadorPantallaPartida() {
		panelPartida.iniciarTemporizador();
	}
	
	public void iniciarActualizarInformacionPantallaPartida() {
		panelPartida.iniciarActualizacionInformacion();
	}
	
	public void limpiezaDeEntidadesGraficas() {
		panelPartida.limpiarEntidadesGraficas();
	}
	
	public void seleccionarImagenFondo() {
		panelPartida.agregarImagenFondo();
	}
	
	public void setTiempoNivel() {
		panelPartida.setTiempoNivel(panelPartida.getTiempoNivelInicial());
	}
	
	public void actualizarPantallaPasajeDeNivel(int numeroDeNivel) {
		panelPartida.limpiarEntidadesGraficas();
		panelPartida.resetearScroll();
		if(numeroDeNivel == 2) {
			panelPartida.setTiempoNivel(300);
		   }
		else
			if(numeroDeNivel == 3) {
				panelPartida.setTiempoNivel(200);
			  }
	}
	
	public void setPuntajeLabel(int puntaje) {
		panelVictoria.setPuntajeLabel(puntaje);
	}
	
	public void setNombreJugadorGanador(String nombre) {
		panelVictoria.setNombreGanador(nombre);
	}
	
	public void detenerActualizacionPantallaPartida() {
		panelPartida.detenerActualizacion();
	}
	
	public void getTiempoNivelActual() {
		panelPartida.getTiempoNivel();
	}
	
	protected void refrescar() {	
		for(Observer observers : new ArrayList<>(lista)) {
	        observers.actualizar();
	    }
		ventana.revalidate();
		ventana.repaint();
	}

	public PantallaPartida getPantallaPartida() {	
		return panelPartida;
	}

	public PantallaVictoria getPantallaVictoria() {
		return panelVictoria;
	}
}