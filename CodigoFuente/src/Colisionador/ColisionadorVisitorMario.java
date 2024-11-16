package Colisionador;

import Enemigo.*;
import Logica.*;
import Mario.*;
import Plataforma.*;
import PowerUp.*;
import Sonido.*;

public class ColisionadorVisitorMario{

	protected Mario entidadColisionable;
	protected String direccionColision;
	protected Juego juego;
	protected Sonido player;

	public ColisionadorVisitorMario(Mario entidadColisionable,Juego juego) {
		this.entidadColisionable = entidadColisionable;
		this.juego = juego;
		player = Sonido.obtenerInstancia();
	}

	public void visitarGoomba(Goomba goomba) {
		direccionColision = detectarDireccion(goomba);
		if(direccionColision.equals("ARRIBA")) {
			goomba.morir(entidadColisionable);
		} else
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){
				entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,goomba);	
			}
	}

	public void visitarKoopa(KoopaTroopa koopa) {
		direccionColision = detectarDireccion(koopa);
		if(direccionColision.equals("ARRIBA")) {
			koopa.atacado();
		}
		else 
			if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){
				if(!koopa.getMovilidad()) {
					entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,koopa);
				}
				else {
					koopa.movilidad();
				}
			}
	}

	public void visitarLakitu(Lakitu lakitu) {
		direccionColision = detectarDireccion(lakitu);
		if(direccionColision.equals("ARRIBA")) {
			lakitu.morir(entidadColisionable);
		}
		if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){	
			entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,lakitu);	
		}
	}

	public void visitarBuzzy(BuzzyBeetle buzzy) {
		direccionColision = detectarDireccion(buzzy);
		if(direccionColision.equals("ARRIBA")) {
			buzzy.morir(entidadColisionable);
		}
		if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA")){	
			entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,buzzy);
		}
	}

	public void visitarSpiny(Spiny spiny) {
		direccionColision = detectarDireccion(spiny);
		if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA") || direccionColision.equals("ARRIBA")){
			entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,spiny);
		}
	}

	public void visitarPiranha(PiranhaPlant piranha) {
		direccionColision = detectarDireccion(piranha);
		if(direccionColision.equals("IZQUIERDA") || direccionColision.equals("DERECHA") || direccionColision.equals("ARRIBA")){
			entidadColisionable.getEstadoMario().recibirDaño(entidadColisionable,piranha);	
		}
	}

	public void visitarBloqueSolido(BloqueSolido bloque) {
		direccionColision = detectarDireccion(bloque);
		bloque.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarBloquePregunta(BloquePregunta bloque) {
		direccionColision = detectarDireccion(bloque);
		bloque.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarLadrillo(LadrilloSolido ladrillo) {
		direccionColision = detectarDireccion(ladrillo);
		ladrillo.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarTuberia(Tuberia tuberia) {
		direccionColision = detectarDireccion(tuberia);
		tuberia.interactuar(entidadColisionable, direccionColision);
	}

	public void visitarTuberiaPiranha(TuberiaConPiranha tuberia) {
		visitarTuberia(tuberia);
	}

	public void visitarBandera(Bandera bandera) {
		bandera.interactuar(juego, entidadColisionable);
	}

	public void visitarVacio(Vacio vacio) {
		entidadColisionable.morirPorVacio();
		vacio.interactuar(entidadColisionable, juego);
	}

	public void visitarChampinonVerde(ChampinonVerde champinon) {
		champinon.afectar(entidadColisionable);
	}

	public void visitarEstrella(Estrella estrella) {
		estrella.afectar(entidadColisionable);
	}

	public void visitarFlorFuego(FlorFuego flor) {
		flor.afectar(entidadColisionable);
	}

	public void visitarMoneda(Moneda moneda) {
		moneda.afectar(entidadColisionable);
	}

	public void visitarSuperChampinon(SuperChampinon champinon) {
		champinon.afectar(entidadColisionable);
	}

	private String detectarDireccion(Entidad entidad) {
		String direccion = "NINGUNA";
		boolean arriba,derecha,izquierda;
		arriba = entidadColisionable.getPosicionY() + entidadColisionable.getAlto()-1 >= entidad.getPosicionY() +entidad.getAlto(); 
		izquierda = entidadColisionable.getPosicionX() + entidadColisionable.getAncho() <= entidad.getPosicionX()+10; 
		derecha = entidadColisionable.getPosicionX()+10 >= entidad.getPosicionX() + entidad.getAncho();
		if(izquierda) {
			direccion="IZQUIERDA";
		}
		if(derecha) {
			direccion= "DERECHA";
		}
		if(arriba) {
			direccion="ARRIBA";
		} else if(!derecha && !izquierda){
			direccion="ABAJO";
		}
		return direccion;
	}
}
