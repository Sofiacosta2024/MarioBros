package Plataforma;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Enemigo.PiranhaPlant;

public class TuberiaConPiranha extends Tuberia{

	protected PiranhaPlant piranha;
	
	public TuberiaConPiranha(int x, int y, int velocidad, PiranhaPlant piranhaPlant) {
		super(x,y, velocidad);
		piranha = piranhaPlant;
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		visitor.visitarTuberiaPiranha(this);
	}
	
	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		visitor.visitarTuberiaPiranha(this);
	}

	public PiranhaPlant getPiranha() {
		return piranha;
	}	
}
