package Vista;

import Logica.EntidadLogica;

public class ObserverEntidades extends ObserverGrafica {
	private static final long serialVersionUID = 1L;

	public ObserverEntidades(EntidadLogica entidadObservada) {
		super(entidadObservada);
		actualizar();
	}
}