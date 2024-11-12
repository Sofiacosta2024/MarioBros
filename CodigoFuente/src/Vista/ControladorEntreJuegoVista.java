package Vista;

import Logica.*;

public interface ControladorEntreJuegoVista {
	public Observer registrarEntidadLogica(EntidadLogica entidadLogica);
	public Observer registrarEntidadMario(EntidadMario entidadMugador);
	public void mostrarPantallaPartida();
	public void mostrarPantallaFinJuego();
	public void mostrarPantallaInicial();
	public void mostrarPantallaRanking();
	public void mostrarPantallaModoJuego();
	public void mostrarPantallaIngreso();
	public void mostrarPantallaVictoria();
}