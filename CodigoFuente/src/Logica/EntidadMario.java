package Logica;

import Mario.EstadoMario;

public interface EntidadMario extends EntidadLogica{
	
	public void aumentarMonedas();
	public void decrementarVida();
	public void setCambioEstado(boolean cambio);
	public boolean getCambioEstado();
	public int getVidas();
	public EstadoMario getEstadoMario();
}
