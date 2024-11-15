package Vista;

public interface ControladorDeVistas {
	public void accionarInicioJuego();
	public void accionarPantallaRanking();
	public void accionarPantallaFinDeJuegoSalir();
	public void accionarModoOriginal();
	public void accionarModoEspecial();
	public void accionarPantallaAlIngresarNombre();
	public void accionarPantallaVictoria();
	public void limpiarEntidadGrafica(ObserverGrafica observer);
}