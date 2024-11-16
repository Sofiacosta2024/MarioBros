package Logica;

public interface VistaDeJuego {
	public void iniciarPartida();
	public void seleccionarModo(String modo);
	public void salirDelJuego();
	public Nivel getNivelActual();
	public String getRutaModo();
	public void tiempoNivelTerminado();
}
