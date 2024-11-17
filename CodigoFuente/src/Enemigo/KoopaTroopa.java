package Enemigo;

import Colisionador.ColisionadorVisitorEnemigo;
import Colisionador.ColisionadorVisitorMario;
import Fabrica.Sprite;
import Mario.Mario;
import Sonido.Sonido;

public class KoopaTroopa extends Enemigo{

	protected EstadoKoopaTroopa estado;
	protected boolean detener;
	protected int contadorColisiones;
	protected Sprite[] spritesKoopa;

	public KoopaTroopa(int x, int y, int velocidad, Sprite[] sprites) {
		super(x,y, velocidad,32,48);
		puntaje = 90;
		puntajeMatarAMario = -45;
		estado = new EstadoCaminando(this);
		TIEMPO_ENFRIAMIENTO = 1000;
		detener = false;
		contadorColisiones = 0;
		spritesKoopa = sprites;
	}

	public void morir(Mario mario) {
		if(!muerto && !enEnfriamiento) {
			atacado();
			mario.setPuntaje(mario.getPuntaje() + this.getPuntaje());
		}
	}

	public void setEstadoInicial() {
		super.setEstadoInicial();
		estado = new EstadoCaminando(this);
		sprite = spritesKoopa[0];
		detener = false;
		contadorColisiones = 0;
	}

	public void atacar(Mario mario) {
		if(!muerto) {
			mario.decrementarVida();
		}
	}

	public void mover() {
		if(!detener) {
			estado.mover(velocidad);
			actualizarAreaColision();
			observador.actualizarPosicionTamano();
		}
	}

	public void movilidad() {
		if(!muerto && !enEnfriamiento) {
			if(contadorColisiones <= 1) {
				detener = !detener;
				activarEnfriamiento();
			}
			contadorColisiones++;
		}
	}

	public void aceptarVisita(ColisionadorVisitorMario visitor) {
		if(!muerto && !enEnfriamiento) {
			visitor.visitarKoopa(this);
		}
	}

	public void aceptarVisita(ColisionadorVisitorEnemigo visitor) {
		if(!muerto && !enEnfriamiento) {
			visitor.visitarKoopaTroopa(this);
		}
	}

	public void atacado() {
		if(!muerto && !enEnfriamiento) {
			Sonido.obtenerInstancia().reproducirSonidoMuereKoopa();
			estado.afectado(this, spritesKoopa);
		}
	}

	public void setEstado(EstadoKoopaTroopa estado) {
		this.estado = estado;
	}

	public EstadoKoopaTroopa getEstado() {
		return estado;
	}
	
	public int getContadorColisiones() {
		return contadorColisiones;
	}

	public boolean getMovilidad() {
		return detener;
	}

}
